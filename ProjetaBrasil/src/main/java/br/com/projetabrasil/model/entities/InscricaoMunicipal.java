package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="InscricaoMunicipal")
public class InscricaoMunicipal extends GenericDomain implements Serializable{
    
	
	@Id
	@SequenceGenerator(name="pk_InscricaoMunicipal", sequenceName="messsounds_InscricaoMunicipal", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_InscricaoMunicipal")
	private Long id;
	
	private String nInsc;
	private Enum_Aux_Status_Insc_Municipal status;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicioProcesso;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fimProcesso;
	private Enum_Aux_Sim_ou_Nao  downloadDamOk ;
	private Enum_Aux_Sim_ou_Nao  downloadPdfOk ;
	private Enum_Aux_Sim_ou_Nao  relacaoGeradaOk;
	 
	private int qtdeDam;
	@ManyToOne
	@JoinColumn( name = "id_Pessoa_Cliente")
	private Pessoa id_Pessoa_Cliente;
	@JoinColumn( name = "id_Pessoa_Empresa")
	private Pessoa id_Pessoa_Empresa;
	@JoinColumn( name = "id_Cidade")
	private Pessoa id_Cidade;
	
	public InscricaoMunicipal() {
		setStatus(Enum_Aux_Status_Insc_Municipal.AGUARDANDO);
		setDownloadDamOk(Enum_Aux_Sim_ou_Nao.NAO);
		setDownloadPdfOk(Enum_Aux_Sim_ou_Nao.NAO);
		setRelacaoGeradaOk(Enum_Aux_Sim_ou_Nao.NAO);
		setQtdeDam(0);
	}
	public String getnInsc() {
		return nInsc;
	}

	public void setnInsc(String nInsc) {
		this.nInsc = nInsc;
	}



	public Enum_Aux_Status_Insc_Municipal getStatus() {
		return status;
	}



	public void setStatus(Enum_Aux_Status_Insc_Municipal status) {
		this.status = status;
	}



	public Calendar getInicioProcesso() {
		return inicioProcesso;
	}



	public void setInicioProcesso(Calendar inicioProcesso) {
		this.inicioProcesso = inicioProcesso;
	}



	public Calendar getFimProcesso() {
		return fimProcesso;
	}



	public void setFimProcesso(Calendar fimProcesso) {
		this.fimProcesso = fimProcesso;
	}



	public Enum_Aux_Sim_ou_Nao getDownloadDamOk() {
		return downloadDamOk;
	}



	public void setDownloadDamOk(Enum_Aux_Sim_ou_Nao downloadDamOk) {
		this.downloadDamOk = downloadDamOk;
	}



	public Enum_Aux_Sim_ou_Nao getDownloadPdfOk() {
		return downloadPdfOk;
	}



	public void setDownloadPdfOk(Enum_Aux_Sim_ou_Nao downloadPdfOk) {
		this.downloadPdfOk = downloadPdfOk;
	}



	public Enum_Aux_Sim_ou_Nao getRelacaoGeradaOk() {
		return relacaoGeradaOk;
	}



	public void setRelacaoGeradaOk(Enum_Aux_Sim_ou_Nao relacaoGeradaOk) {
		this.relacaoGeradaOk = relacaoGeradaOk;
	}



	public int getQtdeDam() {
		return qtdeDam;
	}



	public void setQtdeDam(int qtdeDam) {
		this.qtdeDam = qtdeDam;
	}



	@Override
	public String toString() {
		return "InscricaoMunicipal [id=" + id + ", nInsc=" + nInsc + ", status=" + status + ", inicioProcesso="
				+ inicioProcesso + ", fimProcesso=" + fimProcesso + ", downloadDamOk=" + downloadDamOk
				+ ", downloadPdfOk=" + downloadPdfOk + ", relacaoGeradaOk=" + relacaoGeradaOk + ", qtdeDam=" + qtdeDam
				+ ", id_Pessoa_Cliente=" + id_Pessoa_Cliente + ", id_Pessoa_Empresa=" + id_Pessoa_Empresa
				+ ", id_Cidade=" + id_Cidade + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((downloadDamOk == null) ? 0 : downloadDamOk.hashCode());
		result = prime * result + ((downloadPdfOk == null) ? 0 : downloadPdfOk.hashCode());
		result = prime * result + ((fimProcesso == null) ? 0 : fimProcesso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Cidade == null) ? 0 : id_Cidade.hashCode());
		result = prime * result + ((id_Pessoa_Cliente == null) ? 0 : id_Pessoa_Cliente.hashCode());
		result = prime * result + ((id_Pessoa_Empresa == null) ? 0 : id_Pessoa_Empresa.hashCode());
		result = prime * result + ((inicioProcesso == null) ? 0 : inicioProcesso.hashCode());
		result = prime * result + ((nInsc == null) ? 0 : nInsc.hashCode());
		result = prime * result + qtdeDam;
		result = prime * result + ((relacaoGeradaOk == null) ? 0 : relacaoGeradaOk.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InscricaoMunicipal other = (InscricaoMunicipal) obj;
		if (downloadDamOk != other.downloadDamOk)
			return false;
		if (downloadPdfOk != other.downloadPdfOk)
			return false;
		if (fimProcesso == null) {
			if (other.fimProcesso != null)
				return false;
		} else if (!fimProcesso.equals(other.fimProcesso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Cidade == null) {
			if (other.id_Cidade != null)
				return false;
		} else if (!id_Cidade.equals(other.id_Cidade))
			return false;
		if (id_Pessoa_Cliente == null) {
			if (other.id_Pessoa_Cliente != null)
				return false;
		} else if (!id_Pessoa_Cliente.equals(other.id_Pessoa_Cliente))
			return false;
		if (id_Pessoa_Empresa == null) {
			if (other.id_Pessoa_Empresa != null)
				return false;
		} else if (!id_Pessoa_Empresa.equals(other.id_Pessoa_Empresa))
			return false;
		if (inicioProcesso == null) {
			if (other.inicioProcesso != null)
				return false;
		} else if (!inicioProcesso.equals(other.inicioProcesso))
			return false;
		if (nInsc == null) {
			if (other.nInsc != null)
				return false;
		} else if (!nInsc.equals(other.nInsc))
			return false;
		if (qtdeDam != other.qtdeDam)
			return false;
		if (relacaoGeradaOk != other.relacaoGeradaOk)
			return false;
		if (status != other.status)
			return false;
		return true;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Pessoa getId_Pessoa_Cliente() {
		return id_Pessoa_Cliente;
	}



	public void setId_Pessoa_Cliente(Pessoa id_Pessoa_Cliente) {
		this.id_Pessoa_Cliente = id_Pessoa_Cliente;
	}



	public Pessoa getId_Pessoa_Empresa() {
		return id_Pessoa_Empresa;
	}



	public void setId_Pessoa_Empresa(Pessoa id_Pessoa_Empresa) {
		this.id_Pessoa_Empresa = id_Pessoa_Empresa;
	}



	public Pessoa getId_Cidade() {
		return id_Cidade;
	}



	public void setId_Cidade(Pessoa id_Cidade) {
		this.id_Cidade = id_Cidade;
	}



		
}
