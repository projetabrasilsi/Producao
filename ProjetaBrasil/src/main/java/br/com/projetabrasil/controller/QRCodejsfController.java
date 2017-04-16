package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.QRCode;

@SuppressWarnings("serial")
@ManagedBean(name = "qrcodejsfController")
@RequestScoped
public class QRCodejsfController  implements Serializable{
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	
	private List<QRCode> qRCodes;
	private List<QRCode> qRCodesSelecionados;
	
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new  FacesMessage("QRCode selecionado", ((QRCode) event.getObject()).getId().toString()	 );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage("QRCode descelecionado", ((QRCode)  event.getObject()).getId().toString());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	    
	    public void imprimeListagem(){
	    	for (QRCode qrCode : qRCodesSelecionados) {
	    		System.out.println("id: " +qrCode.getId()+ " - "+qrCode.getCoders());
				
			}
	    }
	    
	


	private QRCode qRCode;	
	
	
	@PostConstruct	
	public void listargemQrCode(){
		QRCodeDAO qDAO = new QRCodeDAO();
		qRCodes = qDAO.listarQRCodersPorPerfil(perfilLogado,true);
		
	}
	public void adicionarNaLista(){
		
	}
	public void removerDaLista(){
		
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
}
