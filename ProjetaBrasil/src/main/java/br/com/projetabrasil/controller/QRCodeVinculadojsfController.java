package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.QRCode;

@SuppressWarnings("serial")
@ManagedBean(name = "qrcodevinculadojsfController")
@SessionScoped()
public class QRCodeVinculadojsfController extends GenericController implements Serializable {
	
	private QRCode qrCode;
	private List<QRCode> listaQRCodes;
	private List<Enum_Aux_Status_QRCodes> listaStatus;
	private Enum_Aux_Status_QRCodes status;
	private Long idObjeto;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {	
		this.qrCode = new QRCode();
		this.listaQRCodes = new ArrayList<QRCode>();
		this.listaStatus = new ArrayList<Enum_Aux_Status_QRCodes>();
		this.listaQRCodes = new QRCodeDAO().listarQRCoders();		
		listarStatus();
	}
	
	public void listarStatus(){		
		listaStatus.add(Enum_Aux_Status_QRCodes.VENDIDOS);
		listaStatus.add(Enum_Aux_Status_QRCodes.REVENDIDOS);				
	}
	
	public void listarQRCodes(){
		Objeto o = new ObjetoDAO().buscar(idObjeto);
		this.listaQRCodes = new QRCodeDAO().listarQRCoders(status, o);
		if(this.listaQRCodes == null){
			this.listaQRCodes = new ArrayList<QRCode>();
		}
	}

	public QRCode getQrcode() {
		return qrCode;
	}

	public void setQrcode(QRCode qrcode) {
		this.qrCode = qrcode;
	}

	public List<QRCode> getListaQRCodes() {
		return listaQRCodes;
	}

	public void setListaQRCodes(List<QRCode> listaQRCodes) {
		this.listaQRCodes = listaQRCodes;
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

	public List<Enum_Aux_Status_QRCodes> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Enum_Aux_Status_QRCodes> listaStatus) {
		this.listaStatus = listaStatus;
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

	public QRCode getQrCode() {
		return qrCode;
	}

	public void setQrCode(QRCode qrCode) {
		this.qrCode = qrCode;
	}	
	
	
}
