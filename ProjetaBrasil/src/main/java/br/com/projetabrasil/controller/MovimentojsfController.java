package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.projetabrasil.model.dao.Combo_DetalheDAO;
import br.com.projetabrasil.model.dao.Combo_MestreDAO;
import br.com.projetabrasil.model.dao.Movimento_DetalheDAO;
import br.com.projetabrasil.model.dao.Movimento_MestreDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.entities.Combo_Detalhe;
import br.com.projetabrasil.model.entities.Combo_Mestre;
import br.com.projetabrasil.model.entities.Enum_Aux_Credita_Debita;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Movimento;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Pagamento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Movimento;
import br.com.projetabrasil.model.entities.Movimento_Detalhe;
import br.com.projetabrasil.model.entities.Movimento_Mestre;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
@ManagedBean(name = "movimento")
@ViewScoped
public class MovimentojsfController extends GenericController implements Serializable {
	private Movimento_Mestre mM;
	private Movimento_Mestre mMTemp;
	private List<Movimento_Mestre> mMs;
	private Movimento_Detalhe mD;
	private List<Movimento_Detalhe> mDs;
	private List<Movimento_Detalhe> mDsTemp;
	private List<Combo_Mestre> cBMs;
	private Combo_Mestre cBM;
	private List<Combo_Detalhe> cBDs;
	private Combo_Detalhe cBD;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores;
	private Enum_Aux_Tipo_Identificador tipoIdentificador;
	private String cpf_Cnpj;

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	@ManagedProperty(value = "#{paginas}")
	private PaginasjsfController paginas;

	@PostConstruct
	public void listar() {
		listarTipodeIdentificadores();
		Movimento_MestreDAO mMDAO = new Movimento_MestreDAO();
		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES))
			mMs = mMDAO.listarMovimento(null, null, null);
		else if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.SUPERVISORES))
			mMs = mMDAO.listarMovimento(null, null, perfilLogado.getUsLogado().getPessoa());
		else if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES))
			mMs = mMDAO.listarMovimento(null, perfilLogado.getUsLogado().getPessoa(), null);
		Combo_MestreDAO cBMDAO = new Combo_MestreDAO();
		cBMs = cBMDAO.listar();
		
		

	}

	public void listarTipodeIdentificadores() {
		Enum_Aux_Tipo_Identificador[] identificadores;
		identificadores = Enum_Aux_Tipo_Identificador.values();
		listaTipodeIdentificadores = new ArrayList<Enum_Aux_Tipo_Identificador>();
		for (Enum_Aux_Tipo_Identificador identificador : identificadores) {
			if (identificador.getAux_tipo_pessoa().isSelecionar())
				listaTipodeIdentificadores.add(identificador);
		}
	}

	public void limpadados() {
		setCpf_Cnpj("");
		mM.setId_Pessoa_Assinante(null);
		mM.setId_Combo_Mestre(null);
	}

	public Pessoa verificaPessoa() {
		String cpf_Cnpj = getCpf_Cnpj();
		cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
		cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);
		setCpf_Cnpj(cpf_Cnpj);
		Pessoa pS = null;
		if (cpf_Cnpj.length() > 0) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pS = pessoaDAO.retornaPelaIdentificacao(cpf_Cnpj);
			if (pS == null)
				mensagensDisparar("Assinante não Encontrado. Cadastre-o!!!");
			else
				mM.setId_Pessoa_Assinante(pS);

		}
		
		return pS;
	}

	public void novo() {
		setTipoIdentificador(Enum_Aux_Tipo_Identificador.CNPJ);
		setCpf_Cnpj("");
		mM = new Movimento_Mestre();
		mD = new Movimento_Detalhe();
		configuraMM(true);
		Utilidades.abrirfecharDialogos("dialogoMovimento", true);
	}

	public void configuraMM(boolean novo) {
		if (novo) {
			mM.setDesconto(0d);
			mM.setDescricao(Enum_Aux_Tipo_Movimento.CONTRATOS.getDescricao());
			mM.setEnum_Aux_Tipo_Movimento(Enum_Aux_Tipo_Movimento.CONTRATOS);
			mM.setId_Empresa(1);
			mM.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			mM.setPercdesc(0d);
			mM.setReferencia("");
			mM.setTotal(0d);
			mM.setTotalLiquido(0d);
			mM.setEnum_Aux_Status_Movimento(Enum_Aux_Status_Movimento.ABERTO);
			mM.setEnum_Aux_Status_Pagamento(Enum_Aux_Status_Pagamento.AAPROVAR);
			mM.setUltimaAtualizacao(Utilidades.retornaCalendario());
			mudaStatusGeral(Enum_Aux_Status_Movimento.ABERTO);
		} else {
			mM = mMTemp;
		}
	}
	
	public String payPal(ActionEvent event){		
		Utilidades.abrirfecharDialogos("dialogoMovimento", false);
		
		perfilLogado.setPaginaAtual(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTO);
		Movimento_DetalheDAO mDDAO = new Movimento_DetalheDAO();
		
		if(mMTemp.getEnum_Aux_Status_Movimento()==null){
		   mMTemp.setEnum_Aux_Status_Movimento(Enum_Aux_Status_Movimento.ABERTO);
		   mMTemp.setEnum_Aux_Status_Pagamento(Enum_Aux_Status_Pagamento.AAPROVAR);
		}
		configuraMM(false);
		if (mM.getId_Pessoa_Assinante().getCpf_Cnpj().length() == 14)
			setTipoIdentificador(Enum_Aux_Tipo_Identificador.CNPJ);
		else
			setTipoIdentificador(Enum_Aux_Tipo_Identificador.CPF);
		setCpf_Cnpj(mM.getId_Pessoa_Assinante().getCpf_Cnpj());
		mDsTemp = mDDAO.listarPeloMestre(mM,null, null);		
		
		
		
		paginas.mudaPaginaAtual(event);
		return "payPal";
	}

	public void merge() {
		Movimento_MestreDAO mMDAO = new Movimento_MestreDAO();
		mM.setUltimaAtualizacao(Utilidades.retornaCalendario());
		if (mM.getId() != null) {
			Movimento_DetalheDAO mDDAO = new Movimento_DetalheDAO();
			mDs = mDDAO.listarPeloMestre(mM,null,null);
			
			for (Movimento_Detalhe cD : mDs) {
				mDDAO.excluir(cD);
			}
		}
		mM = mMDAO.merge(mM);
		Movimento_DetalheDAO mDDAO = new Movimento_DetalheDAO();
		
		for (Movimento_Detalhe cD : mDsTemp) {
			cD.setId_Movimento_Mestre(mM);
			cD.setUltimaAtualizacao(mM.getUltimaAtualizacao());
			mDDAO.merge(cD);
		}
		listar();
		Utilidades.abrirfecharDialogos("dialogoMovimento", false);
	}

	public void mudaStatusPgamento(Enum_Aux_Status_Pagamento status) {
		mM.setEnum_Aux_Status_Pagamento(status);
	}
	public void mudaStatusGeral(Enum_Aux_Status_Movimento status) {
		mM.setEnum_Aux_Status_Movimento(status);
		if (mM.getEnum_Aux_Status_Movimento().equals(Enum_Aux_Status_Movimento.ABERTO))		
			mudaStatusPgamento(Enum_Aux_Status_Pagamento.ADEPOSITAR);
		if (mM.getEnum_Aux_Status_Movimento().equals(Enum_Aux_Status_Movimento.APROVADO))
			mudaStatusPgamento(Enum_Aux_Status_Pagamento.ADEPOSITAR);
		if (mM.getEnum_Aux_Status_Movimento().equals(Enum_Aux_Status_Movimento.CANCELADO))
			mudaStatusPgamento(Enum_Aux_Status_Pagamento.CANCELADO);
	}
	

	public void editar(ActionEvent event) {
		Movimento_DetalheDAO mDDAO = new Movimento_DetalheDAO();
		mMTemp = (Movimento_Mestre) event.getComponent().getAttributes().get("registroAtual");
		if(mMTemp.getEnum_Aux_Status_Movimento()==null){
		   mMTemp.setEnum_Aux_Status_Movimento(Enum_Aux_Status_Movimento.ABERTO);
		   mMTemp.setEnum_Aux_Status_Pagamento(Enum_Aux_Status_Pagamento.AAPROVAR);
		}
		
		configuraMM(false);
		if (mM.getId_Pessoa_Assinante().getCpf_Cnpj().length() == 14)
			setTipoIdentificador(Enum_Aux_Tipo_Identificador.CNPJ);
		else
			setTipoIdentificador(Enum_Aux_Tipo_Identificador.CPF);
		setCpf_Cnpj(mM.getId_Pessoa_Assinante().getCpf_Cnpj());

		mDsTemp = mDDAO.listarPeloMestre(mM,null, null);
		Utilidades.abrirfecharDialogos("dialogoMovimento", true);
	}

	public void cancelar() {
		Utilidades.abrirfecharDialogos("dialogoMovimento", false);
	}

	public void excluir() {

	}

	public void buscaListaMovimentoDetalhe() {
		Movimento_DetalheDAO mDDAO = new Movimento_DetalheDAO();
		mDs = mDDAO.listarPeloResponsavel(perfilLogado.getUsLogado().getPessoa(), mM);
	}

	public void ajustaComboMestreDetalhe() {
		Combo_DetalheDAO cDDAO = new Combo_DetalheDAO();
		PessoaDAO pDAO = new PessoaDAO();
		String cpf_Cnpj = "10712565000155";
		Pessoa ass = pDAO.retornaPelaIdentificacao(cpf_Cnpj);
		cBDs = cDDAO.listar(ass, cBM);
		Movimento_Detalhe mDTemp;
		mDsTemp = new ArrayList<Movimento_Detalhe>();
		for (Combo_Detalhe cbd : cBDs) {
			mDTemp = new Movimento_Detalhe();
			mDTemp.setId_Pessoa_Assinante(mM.getId_Pessoa_Assinante());
			mDTemp.setId_Movimento_Mestre(mM);
			mDTemp.setId_Pessoa_Registro(mM.getId_Pessoa_Registro());
			mDTemp.setId_Combo_Mestre(cBM);
			mDTemp.setId_Combo_Detalhe(cbd);
			mDTemp.setId_Item_de_Movimento(cbd.getId_Itens_Movimento());
			mDTemp.setcD(Enum_Aux_Credita_Debita.C);
			mDTemp.setReferencia(cbd.getId_Itens_Movimento().getReferencia());
			mDTemp.setDescricao(cbd.getId_Itens_Movimento().getDescricao());
			mDTemp.setQtde(cbd.getQtde());
			mDTemp.setValorUnidade(cbd.getValorUnidade());
			mDTemp.setTotal(cbd.getTotal());
			mDTemp.setUltimaAtualizacao(Utilidades.retornaCalendario());
			mDTemp.setDesconto(cbd.getDesconto());
			mDTemp.setTotalLiquido(cbd.getDesconto());
			mDsTemp.add(mDTemp);
		}
		
		mM.setDesconto(cBM.getDesconto());
		mM.setDescricao(cBM.getDescricao());
		mM.setEnum_Aux_Tipo_Movimento(Enum_Aux_Tipo_Movimento.CONTRATOS);
		mM.setId_Combo_Mestre(cBM);
		mM.setId_Empresa(1);
		mM.setId_Pessoa_Assinante(verificaPessoa());
		mM.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		mM.setPercdesc(cBM.getPercDesc());
		mM.setReferencia(cBM.getReferencia());
		mM.setTotal(cBM.getTotal());
		mM.setTotalLiquido(cBM.getTotalLiquido());
		mM.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
	}

	public Movimento_Mestre getmM() {
		return mM;
	}

	public void setmM(Movimento_Mestre mM) {
		this.mM = mM;
	}

	public List<Movimento_Mestre> getmMs() {
		return mMs;
	}

	public void setmMs(List<Movimento_Mestre> mMs) {
		this.mMs = mMs;
	}

	public Movimento_Detalhe getmD() {
		return mD;
	}

	public void setmD(Movimento_Detalhe mD) {
		this.mD = mD;
	}

	public List<Movimento_Detalhe> getmDs() {
		return mDs;
	}

	public void setmDs(List<Movimento_Detalhe> mDs) {
		this.mDs = mDs;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Combo_Mestre> getcBMs() {
		return cBMs;
	}

	public void setcBMs(List<Combo_Mestre> cBMs) {
		this.cBMs = cBMs;

	}

	public Combo_Mestre getcBM() {
		return cBM;
	}

	public void setcBM(Combo_Mestre cBM) {
		this.cBM = cBM;
	}

	public List<Combo_Detalhe> getcBDs() {
		return cBDs;
	}

	public void setcBDs(List<Combo_Detalhe> cBDs) {
		this.cBDs = cBDs;
	}

	public Combo_Detalhe getcBD() {
		return cBD;
	}

	public void setcBD(Combo_Detalhe cBD) {
		this.cBD = cBD;
	}

	public Enum_Aux_Tipo_Identificador getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Enum_Aux_Tipo_Identificador tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadores() {
		return listaTipodeIdentificadores;
	}

	public void setListaTipodeIdentificadores(List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores) {
		this.listaTipodeIdentificadores = listaTipodeIdentificadores;
	}

	public String getCpf_Cnpj() {
		return cpf_Cnpj;
	}

	public void setCpf_Cnpj(String cpf_Cnpj) {
		this.cpf_Cnpj = cpf_Cnpj;
	}

	public List<Movimento_Detalhe> getmDsTemp() {
		return mDsTemp;
	}

	public void setmDsTemp(List<Movimento_Detalhe> mDsTemp) {
		this.mDsTemp = mDsTemp;
	}

	public Movimento_Mestre getmMTemp() {
		return mMTemp;
	}

	public void setmMTemp(Movimento_Mestre mMTemp) {
		this.mMTemp = mMTemp;
	}

	public PaginasjsfController getPaginas() {
		return paginas;
	}

	public void setPaginas(PaginasjsfController paginas) {
		this.paginas = paginas;
	}

}
