
package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import br.com.projetabrasil.model.dao.Movimento_DetalheDAO;
import br.com.projetabrasil.model.dao.Movimento_Detalhe_ADAO;
import br.com.projetabrasil.model.dao.Movimento_Detalhe_Dias_DisponiveisDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Dia_da_Semana;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Movimento_Detalhe;
import br.com.projetabrasil.model.entities.Movimento_Detalhe_A;
import br.com.projetabrasil.model.entities.Movimento_Detalhe_Dias_Disponiveis;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
@ManagedBean(name = "detalhe_A")
@ViewScoped
public class Movimento_Detalhe_AjsfController extends GenericController implements Serializable {
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	List<Movimento_Detalhe> mDs;
	Movimento_Detalhe mD;
	List<Movimento_Detalhe_A> mDAs;
	Movimento_Detalhe_A mDA;
	List<Movimento_Detalhe_Dias_Disponiveis> mDDDs;
	List<SelectItem> lDiasSemana;
	String[] diasEscolhidos;
	Movimento_Detalhe_Dias_Disponiveis mDDD;
	private List<Enum_Aux_Sim_ou_Nao> listaSN;
	private boolean skip;
	private double saldoDisponivel;
	private final String tipoDeImagem = Utilidades.getTipoImagemSemPonto();

	@PostConstruct
	public void listar() {
		listarMovimento_Detalhe_A(perfilLogado.getAssLogado());
		listar_Movimento_Detalhe();

	}

	public void listarDiasdaSemana() {
		lDiasSemana = new ArrayList<SelectItem>();
		Enum_Aux_Dia_da_Semana[] dias;
		dias = Enum_Aux_Dia_da_Semana.values();
		ArrayList<String> ar = new ArrayList<String>();
		int i = 0;
		for (Enum_Aux_Dia_da_Semana dia : dias) {
			SelectItem sl = new SelectItem(dia, dia.getExtenso());
			lDiasSemana.add(sl);
			String dd;
			if (mDDDs != null)
				for (Movimento_Detalhe_Dias_Disponiveis m : mDDDs) {
					if (sl.getLabel().equals(m.getEnum_Aux_Dia_da_Semana().getExtenso())) {
						dd = Utilidades.retiraCaracteres(
								Utilidades.removerAcentos(m.getEnum_Aux_Dia_da_Semana().getExtenso().toUpperCase()));
						ar.add(dd);
						SelectItem e = new SelectItem(m.getEnum_Aux_Dia_da_Semana(),
								m.getEnum_Aux_Dia_da_Semana().getExtenso());
						lDiasSemana.set(i, e);
					}
				}
			i++;
		}
		diasEscolhidos = new String[ar.size()];
		diasEscolhidos = ar.toArray(diasEscolhidos);
	}
	
	

	public void listar_Movimento_Detalhe() {
		Movimento_DetalheDAO mDDAO = new Movimento_DetalheDAO();
		mDs = mDDAO.listarPeloMestre(null, perfilLogado.getAssLogado(), Enum_Aux_Tipo_Item_de_Movimento.ITEMDESERVICO);
		Movimento_Detalhe_ADAO mDADAO = new Movimento_Detalhe_ADAO();
		setSaldoDisponivel(
				mDADAO.somapeloTipodeMovimento(perfilLogado.getAssLogado(), Enum_Aux_Tipo_Item_de_Movimento.ITEMDESERVICO));
		setListaSN(Utilidades.listaSN());
		listarDiasdaSemana();
	}

	public void listarMovimento_Detalhe_A(Pessoa id_Pessoa_Assinante) {
		Movimento_Detalhe_ADAO mDADAO = new Movimento_Detalhe_ADAO();
		mDAs = mDADAO.listar(id_Pessoa_Assinante);
		definecaminhodaImagem();
	}
	public void definecaminhodaImagem(){
		int i=0;
	    for (Movimento_Detalhe_A movDet : mDAs) {
	    	movDet.setCaminhoDaImagem(Utilidades.getCaminhofotovouchers()+""+movDet.getId()+Utilidades.getTipoimagem());	    	
	    	mDAs.set(i,movDet);
	    	
			i++;
		}	  
	 	
	}

	public void novo() {
		mDDDs = new ArrayList<Movimento_Detalhe_Dias_Disponiveis>();
		mDA = new Movimento_Detalhe_A();
		configuraNovoMovimento_Detalhe_A();
		Utilidades.abrirfecharDialogos("dialogoVoucher", true);
	}

	public void configuraNovoMovimento_Detalhe_A() {
		mDA.setAgendado(0d);
		mDA.setCaminhoDaImagem(null);
		mDA.setCaminhoTemp(null);
		mDA.setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento.VOUCHER);
		mDA.setDisponibilizado(0d);
		mDA.setId_Empresa(1);
		mDA.setId_Movimento_Detalhe(mD);
		mDA.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		mDA.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		mDA.setIsAgendado(Enum_Aux_Sim_ou_Nao.SIM);
		mDA.setLivre(0d);
		mDA.setSaldo(0d);
		mDA.setInicio(Utilidades.retornaCalendario().getTime());
		mDA.setFim(Utilidades.retornaValidade(30));
		String Hora = "00:00";

		mDA.setHorarioMaximoAgendamentodoDia(Utilidades.retornaHora(Hora));

		mDA.setUltimaAtualizacao(Utilidades.retornaCalendario());
	}

	public boolean mergeOk() {
		if (mDA.getCaminhoTemp() == null || mDA.getCaminhoTemp() == "") {
			mensagensDisparar("Imagem é obrigatória!!!");
			return false;
		}
		Path caminhoTemp;
		caminhoTemp = Paths.get(mDA.getCaminhoTemp());

		if (!Files.exists(caminhoTemp)) {
			mensagensDisparar("Imagem é obrigatória!!!");
			return false;
		}
        
		if (mDA.getInicio().before(Utilidades.retornaCalendario2())) {
			mensagensDisparar("escolha uma data Válida!!!");
			return false;
		}
		if (mDA.getFim().before(mDA.getInicio())) {
			mensagensDisparar("escolha uma data Válida!!!");
			return false;
		}
		if (mDA.getValor() <= 0) {
			mensagensDisparar("informe qual será o valor do voucher!!!");
			return false;
		}
		if (mDA.getDisponibilizado() > getSaldoDisponivel()) {
			mensagensDisparar("Saldo disponível: " + getSaldoDisponivel()
					+ " é menor do que o informado para ser disponibilizado!!!");
			return false;
		}
		return true;
	}

	

	public void merge() {
		if (!mergeOk())
			return;
		Movimento_Detalhe_ADAO mDADAO = new Movimento_Detalhe_ADAO();
		String caminhoTemp = mDA.getCaminhoTemp();
		mDA = mDADAO.merge(mDA);
		mDA.setCaminhoTemp(caminhoTemp);
		
		
		mDA.setCaminhoDaImagem(Utilidades.getCaminhofotovouchers()+""+mDA.getId()+Utilidades.getTipoimagem());
		
		
		copiarImagem();
		Movimento_Detalhe_Dias_DisponiveisDAO mDDDDAO = new Movimento_Detalhe_Dias_DisponiveisDAO();
		setmDDDs(mDDDDAO.retornaDiasDisponiveis(mDA));
		if (mDDDs != null) {
			excluirMovimento_Detalhe_Dias_Disponiveis(mDDDs);
		}
		setmDDDs(adicionaDiasDisponiveis(mDA));
		for (Movimento_Detalhe_Dias_Disponiveis d : mDDDs) {
			mDDDDAO.merge(d);
		}
		listar();
		Utilidades.abrirfecharDialogos("dialogoVoucher", false);
	}

	public void excluirMovimento_Detalhe_Dias_Disponiveis(List<Movimento_Detalhe_Dias_Disponiveis> mDDDs) {
		Movimento_Detalhe_Dias_DisponiveisDAO mDDDDAO = new Movimento_Detalhe_Dias_DisponiveisDAO();
		if (mDDDs != null) {
			for (Movimento_Detalhe_Dias_Disponiveis e : mDDDs) {
				mDDDDAO.excluir(e);
			}
		}
	}

	public void editar(ActionEvent event) {
		mDA = (Movimento_Detalhe_A) event.getComponent().getAttributes().get("registroAtual");
		// QUANDO SALVA NO BANCO SALVAO O CAMINHO TEMP NO CAMINHO DA IMAGEM;
		mDA.setCaminhoDaImagem(Utilidades.getCaminhofotovouchers()+""+mDA.getId()+Utilidades.getTipoimagem() );
		
		mDA.setCaminhoTemp(mDA.getCaminhoDaImagem());	
		
		Movimento_Detalhe_Dias_DisponiveisDAO mdddDAO = new Movimento_Detalhe_Dias_DisponiveisDAO();
		mDDDs = mdddDAO.retornaDiasDisponiveis(mDA);
		mDA.setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento.VOUCHER);
		listarDiasdaSemana();

		Utilidades.abrirfecharDialogos("dialogoVoucher", true);
	}

	public void cancelar() {
		Utilidades.abrirfecharDialogos("dialogoVoucher", false);
	}

	public void excluir(ActionEvent event) {
		mDA = (Movimento_Detalhe_A) event.getComponent().getAttributes().get("registroAtual");
		Movimento_Detalhe_ADAO mDADAO = new Movimento_Detalhe_ADAO();
		mDADAO.excluir(mDA);
		try {
			Path arquivo = Paths.get(Utilidades.getCaminhofotovouchers()+""+mDA.getId()+Utilidades.getTipoimagem());
			Files.deleteIfExists(arquivo);
		} catch (IOException error) {

			error.printStackTrace();
		}
		
	}

	public void copiarImagem() {
		
		
		Path origem = Paths.get(mDA.getCaminhoTemp());
		Path destino = Paths.get(mDA.getCaminhoDaImagem());
		
		try {
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException error) {
			mensagensDisparar("erro ao tentar copiar imagem temporaria para caminho de destino");
			error.printStackTrace();
		}
	}

	public void upload(FileUploadEvent event) {
		try {
			UploadedFile arquivoUpload = event.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			mDA.setCaminhoTemp(arquivoTemp.toString());
			
			mDA.setCaminhoDaImagem(mDA.getCaminhoTemp());

			mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}

	
 
 

	public List<Movimento_Detalhe_Dias_Disponiveis> adicionaDiasDisponiveis(Movimento_Detalhe_A mD) {
		List<Movimento_Detalhe_Dias_Disponiveis> mDDDs = new ArrayList<Movimento_Detalhe_Dias_Disponiveis>();

		for (String e : diasEscolhidos) {

			e = Utilidades.removerAcentos(e).toUpperCase();
			Enum_Aux_Dia_da_Semana j = Enum_Aux_Dia_da_Semana.valueOf(e);
			Movimento_Detalhe_Dias_Disponiveis mDDD = new Movimento_Detalhe_Dias_Disponiveis();

			mDDD.setEnum_Aux_Dia_da_Semana(j);
			mDDD.setEnum_Aux_Sim_ou_Nao(Enum_Aux_Sim_ou_Nao.SIM);
			mDDD.setId_Empresa(1);
			mDDD.setId_Movimento_Detalhe_A(mD);
			mDDD.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			mDDD.setUltimaAtualizacao(Utilidades.retornaCalendario());
			mDDDs.add(mDDD);
		}
		return mDDDs;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public List<Movimento_Detalhe_A> getmDAs() {
		return mDAs;
	}

	public void setmDAs(List<Movimento_Detalhe_A> mDAs) {
		this.mDAs = mDAs;
	}

	public Movimento_Detalhe_A getmDA() {
		return mDA;
	}

	public void setmDA(Movimento_Detalhe_A mDA) {
		this.mDA = mDA;
	}

	public List<Movimento_Detalhe_Dias_Disponiveis> getmDDDs() {
		return mDDDs;
	}

	public void setmDDDs(List<Movimento_Detalhe_Dias_Disponiveis> mDDDs) {
		this.mDDDs = mDDDs;
	}

	public Movimento_Detalhe_Dias_Disponiveis getmDDD() {
		return mDDD;
	}

	public void setmDDD(Movimento_Detalhe_Dias_Disponiveis mDDD) {
		this.mDDD = mDDD;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Movimento_Detalhe> getmDs() {
		return mDs;
	}

	public void setmDs(List<Movimento_Detalhe> mDs) {
		this.mDs = mDs;
	}

	public Movimento_Detalhe getmD() {
		return mD;
	}

	public void setmD(Movimento_Detalhe mD) {
		this.mD = mD;
	}

	public List<Enum_Aux_Sim_ou_Nao> getListaSN() {
		return listaSN;
	}

	public void setListaSN(List<Enum_Aux_Sim_ou_Nao> listaSN) {
		this.listaSN = listaSN;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<SelectItem> getlDiasSemana() {
		return lDiasSemana;
	}

	public void setlDiasSemana(List<SelectItem> lDiasSemana) {
		this.lDiasSemana = lDiasSemana;
	}

	public String[] getDiasEscolhidos() {
		return diasEscolhidos;
	}

	public void setDiasEscolhidos(String[] diasEscolhidos) {
		this.diasEscolhidos = diasEscolhidos;
	}

	public double getSaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setSaldoDisponivel(double saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}

	public String getTipoDeImagem() {
		return tipoDeImagem;
	}
}
