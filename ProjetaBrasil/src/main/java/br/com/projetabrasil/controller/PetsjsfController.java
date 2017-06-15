package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PetsjsfController extends GenericController implements Serializable {
	
	private Objeto objetoSelecionado;
	private List<Objeto> listaPets;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@ManagedProperty(value = "#{qrcodevinculadojsfController}")
	private QRCodeVinculadojsfController qrcodeController;
	
	@ManagedProperty(value = "#{qrcodevinculadojsfController.status}")
	private Enum_Aux_Status_QRCodes status;
	
	@ManagedProperty(value = "#{qrcodevinculadojsfController.listaStatus}")
	private List<Enum_Aux_Status_QRCodes> listaStatus;
	
	@ManagedProperty(value = "#{qrcodevinculadojsfController.idObjeto}")
	private Long idObjeto;
	
	@PostConstruct
	public void listar() {	
		this.objetoSelecionado = new Objeto();
		this.listaPets = new ArrayList<Objeto>();
		buscaObjetos();
	}
	
	public void buscaObjetos() {
		Objeto obj = new ObjetoDAO().buscar(idObjeto);
		this.listaPets = new QRCodeDAO().buscaObjetos(this.status, obj);
		List<Objeto> listaPetsImagens = new ArrayList<Objeto>();
		
		int x = 0;
		for(Objeto o : this.listaPets){
			o.setCaminhodaImagem(Utilidades.getCaminhofotoobjetos() + "" + o.getId() + Utilidades.getTipoimagem());
			o.setTipodeImagem(Utilidades.tipodeImagem());
			listaPetsImagens.add(x, o);
			x++;
		}
		this.listaPets = listaPetsImagens;
		
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public PerfilLogado getPerfilLogadoTemp() {
		return perfilLogadoTemp;
	}

	public void setPerfilLogadoTemp(PerfilLogado perfilLogadoTemp) {
		this.perfilLogadoTemp = perfilLogadoTemp;
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public Objeto getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Objeto objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public List<Objeto> getListaPets() {
		return listaPets;
	}

	public void setListaPets(List<Objeto> listaPets) {
		this.listaPets = listaPets;
	}

	public QRCodeVinculadojsfController getQrcodeController() {
		return qrcodeController;
	}

	public void setQrcodeController(QRCodeVinculadojsfController qrcodeController) {
		this.qrcodeController = qrcodeController;
	}

	public Enum_Aux_Status_QRCodes getStatus() {
		return status;
	}

	public void setStatus(Enum_Aux_Status_QRCodes status) {
		this.status = status;
	}

	public Long getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(Long idObjeto) {
		this.idObjeto = idObjeto;
	}

	public List<Enum_Aux_Status_QRCodes> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Enum_Aux_Status_QRCodes> listaStatus) {
		this.listaStatus = listaStatus;
	}
		
	
}
