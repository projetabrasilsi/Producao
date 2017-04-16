package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.PessoaBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness2;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean(name = "qrcodejsfController")
@ViewScoped
public class QRCodejsfController implements Serializable {

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;

	private List<QRCode> qRCodes;
	private List<QRCode> qRCodesSelecionados;

	private PessoaConfig pessoaConfig;
	private Pessoa pessoa;
	private Usuario usuario;

	public void cancelaTransferencia() {
		Utilidades.abrirfecharDialogos("dialogoIdentidade", false);
	}

	public void RealizarTransferencia() {

		if (!buscaPessoa())
			return;
		Pessoa_Enum_Aux_Perfil_Pessoa pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
		Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
		pp.setId_pessoa(pessoa);
		pp.setEnum_Aux_Perfil_Pessoa(perfilLogado.getPerfildeTransferencia());
		pp = ppDAO.isPerfilExiste(pp);
		if (pp == null) {
			Utilidades.mensagensDisparar("Pessoado CPF informado, não tem o perfil \n"
					+ perfilLogado.getPerfildeTransferencia() + "\n para que a transferencia possa ocorrer");
			
			return;
		}

		if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES)
				|| perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.REVENDEDORES)) {
			Pessoa_Vinculo pVin = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
			pVin.setId_pessoa_d(pessoa);
			pVin.setId_pessoa_m(perfilLogado.getAssLogado());
			pVin = pVinDAO.retornaVinculo_Mestre(pessoa,pVin.getId_pessoa_m(),  perfilLogado.getPerfildeTransferencia());
			if (pVin == null) {
				if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES))
					Utilidades.mensagensDisparar("Representante não está cadastrado para este Distribuidor");

				else if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.REVENDEDORES)) {
					if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES))
						Utilidades.mensagensDisparar("Revenda não está cadastrado para este Distribuidor");
					
					else if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES))
						Utilidades.mensagensDisparar("Revenda não está cadastrado para este Representante");
					
					

				}

 				return;
			}

		}

		for (QRCode qrCode : qRCodesSelecionados) {
			if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES)) {
				qrCode.setId_Pessoa_Distribuicao(pessoa);
				qrCode.setData_Distribuicao(Utilidades.retornaCalendario());
				qrCode.setStatus(Enum_Aux_Status_QRCodes.DISTRIBUIDOS);
			} else if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES)) {
				qrCode.setId_Pessoa_Representacao(pessoa);
				qrCode.setData_Representacao(Utilidades.retornaCalendario());
				qrCode.setStatus(Enum_Aux_Status_QRCodes.REPRESENTADOS);
			} else if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.REVENDEDORES)) {
				qrCode.setId_Pessoa_Revenda(pessoa);
				qrCode.setData_Revenda(Utilidades.retornaCalendario());
				qrCode.setStatus(Enum_Aux_Status_QRCodes.REPRESENTADOS);
			}
			qrCode.setUltimaAtualizacao(Utilidades.retornaCalendario());
			if (perfilLogado.getAssLogado() == null || perfilLogado.getAssLogado().getId() == null) {
				qrCode.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
				qrCode.setId_Pessoa_Logistica(perfilLogado.getUsLogado().getPessoa());
			} else {
				qrCode.setId_Pessoa_Registro(perfilLogado.getAssLogado());
				qrCode.setId_Pessoa_Logistica(perfilLogado.getAssLogado());
			}

			QRCodeDAO qrDAO = new QRCodeDAO();
			qrDAO.merge(qrCode);
		}
		Utilidades.abrirfecharDialogos("dialogoIdentidade", false);
		listargemQrCode();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formTemplate:formLista:tabela");

	}

	public boolean buscaPessoa() {
		/*
		 * o padrão aqui é física ou jurídica mas quando ele chama
		 * PessoasBusiness.buscaPessoa ele pode buscar perfil OUTROS
		 */
		pessoa.setCpf_Cnpj(Utilidades.retiraCaracteres(pessoa.getCpf_Cnpj()));
		pessoa.setIdentificador(pessoa.getCpf_Cnpj());
		Enum_Aux_Tipo_Identificador tipoIdent = pessoa.getEnum_Aux_Tipo_Identificador();
		usuario.setPessoa(pessoa);
		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, true, true, true))
			return false;

		pessoa = PessoaBusiness.buscaPessoa(pessoa);

		if (pessoa.getId() == null) {
			if (tipoIdent.equals(Enum_Aux_Tipo_Identificador.CPF))
				Utilidades.mensagensDisparar("Este CPF não existe na base de dados");
			else if (tipoIdent.equals(Enum_Aux_Tipo_Identificador.CNPJ))
				Utilidades.mensagensDisparar("Este CNPJ não existe na base de dados");
			return false;
		}

		return true;
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("QRCode selecionado", ((QRCode) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("QRCode descelecionado", ((QRCode) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private QRCode qRCode;

	@PostConstruct
	public void listargemQrCode() {
		pessoa = new Pessoa();
		usuario = new Usuario();
		QRCodeDAO qDAO = new QRCodeDAO();
		boolean livre;
		if (perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.LOGISTICA)
				|| perfilLogado.getPerfildeTransferencia().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES))
			livre = true;
		else
			livre = false;

		qRCodes = qDAO.listarQRCodersPorPerfil(perfilLogado, livre);

		qRCodesSelecionados = new ArrayList<>();
		configurarPessoa();
		pessoa = new Pessoa();
		pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CNPJ);
	}

	public void identidade(ActionEvent event) {
		configurarPessoa();
		pessoa = new Pessoa();
		pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CNPJ);
		Utilidades.abrirfecharDialogos("dialogoIdentidade", true);
	}

	public void adicionarNaLista() {

	}

	public void removerDaLista() {

	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<QRCode> getqRCodes() {
		return qRCodes;
	}

	public void setqRCodes(List<QRCode> qRCodes) {
		this.qRCodes = qRCodes;
	}

	public QRCode getqRCode() {
		return qRCode;
	}

	public void setqRCode(QRCode qRCode) {
		this.qRCode = qRCode;
	}

	public List<QRCode> getqRCodesSelecionados() {
		return qRCodesSelecionados;
	}

	public void setqRCodesSelecionados(List<QRCode> qRCodesSelecionados) {
		this.qRCodesSelecionados = qRCodesSelecionados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
