package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.glassfish.jersey.process.internal.RequestScoped;

import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.QRCode;


@SuppressWarnings("serial")
@ManagedBean(name = "qrcodeConsultaController")
@RequestScoped
public class QRCodeConsultaController implements Serializable {
	private String kldasjfsd;
	private QRCode qRCode = new QRCode();
	
	
	//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=a527173445d117cbf177084bd34e60f2
		//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=197fb688fc1c8f2ae27ea797e7f73283
		//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=a340a2e078676b2c8f69258ebb9809f5
		//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=0e9cf7f9efe01ffb04d883c75ce1c5d2
		//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=277d11c0d890fa50d2c48302b7c37e35
		//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=72d14bf1127677c8ad802764cde2c550
		//www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=170736fee90ba01be4bd05ce3759feca
	
    @PostConstruct
	public void getParam() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String projectId = paramMap.get("kldasjfsd");		
		qRCode = buscar(projectId);
		
	}

	public QRCode buscar(String id) {

		QRCodeDAO qrCodersDAO = new QRCodeDAO();
		setqRCode((QRCode) qrCodersDAO.buscaCoders(id));
		return getqRCode();
	}

	

	public QRCode getqRCode() {
		return qRCode;
	}

	public void setqRCode(QRCode qRCode) {
		this.qRCode = qRCode;
	}

	public String getKldasjfsd() {
		return kldasjfsd;
	}

	public void setKldasjfsd(String kldasjfsd) {
		this.kldasjfsd = kldasjfsd;
	}

	
	

	

}
