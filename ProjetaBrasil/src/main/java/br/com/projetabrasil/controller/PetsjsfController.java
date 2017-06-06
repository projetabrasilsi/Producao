package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.projetabrasil.model.dao.QRCodeDAO;
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
	
	@PostConstruct
	public void listar() {	
		this.objetoSelecionado = new Objeto();
		this.listaPets = new ArrayList<Objeto>();
		buscaObjetosVendidos();
	}
	
	public void buscaObjetosVendidos() {
		this.listaPets = new QRCodeDAO().buscaObjetosVendidos();
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
			
}
