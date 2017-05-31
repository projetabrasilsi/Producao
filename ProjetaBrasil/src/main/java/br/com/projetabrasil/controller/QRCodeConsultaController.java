package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.glassfish.jersey.process.internal.RequestScoped;

import br.com.projetabrasil.model.dao.ContatoDAO;
import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.dao.Prontuario_de_EmergenciaDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Prontuario_de_Emergencia;
import br.com.projetabrasil.model.entities.QRCode;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class QRCodeConsultaController implements Serializable {
	private String kldasjfsd;
	private QRCode qRCode = new QRCode();
	private Endereco end = new Endereco();
	private List<Contato> cont = new ArrayList<>();
	private List<Prontuario_de_Emergencia> pront = new ArrayList<>();
	private Pessoa pessoa = new Pessoa();
	private Objeto objeto = new Objeto();

	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=a527173445d117cbf177084bd34e60f2
	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=197fb688fc1c8f2ae27ea797e7f73283
	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=a340a2e078676b2c8f69258ebb9809f5
	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=0e9cf7f9efe01ffb04d883c75ce1c5d2
	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=277d11c0d890fa50d2c48302b7c37e35
	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=72d14bf1127677c8ad802764cde2c550
	// www.projetabrasil.com.br/faces/pages/achei.xhtml?kldasjfsd=170736fee90ba01be4bd05ce3759feca

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

		setPessoa(getqRCode().getId_Pessoa_Cliente());
		if(getPessoa() == null)
			setPessoa(new Pessoa());
		setObjeto(qRCode.getId_Objeto());
		if(getObjeto() == null)
			setObjeto(new Objeto());
		EnderecoDAO endDAO = new EnderecoDAO();
		ContatoDAO contDAO = new ContatoDAO();
		Prontuario_de_EmergenciaDAO prontDAO = new Prontuario_de_EmergenciaDAO();

		setEnd(endDAO.buscaEnderecoPorPessoa(pessoa));
		if (getEnd() == null)
			setEnd(new Endereco());
		
		setCont(contDAO.listardeContatosporPessoa(pessoa));
		if(getCont()==null || getCont().size() == 0)
			setCont(new ArrayList<>());
			
			setPront(prontDAO.listarProntuarioporPessoa(pessoa));
		if(getPront() == null || getPront().size() ==0 )
			setPront(new ArrayList<>());
			

		return getqRCode();
	}

	public String getKldasjfsd() {
		return kldasjfsd;
	}

	public void setKldasjfsd(String kldasjfsd) {
		this.kldasjfsd = kldasjfsd;
	}

	public QRCode getqRCode() {
		return qRCode;
	}

	public void setqRCode(QRCode qRCode) {
		this.qRCode = qRCode;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public List<Contato> getCont() {
		return cont;
	}

	public void setCont(List<Contato> cont) {
		this.cont = cont;
	}

	public List<Prontuario_de_Emergencia> getPront() {
		return pront;
	}

	public void setPront(List<Prontuario_de_Emergencia> pront) {
		this.pront = pront;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

}