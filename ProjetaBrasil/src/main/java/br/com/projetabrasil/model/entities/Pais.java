package br.com.projetabrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="Pais")
public class Pais extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_pais",sequenceName="messsounds_pais", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pais")
	private Long id;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	
	@Column(name="sigla", length = 5, nullable=false)
	private String sigla;
	
	@Column(name="maskZip", length = 20, nullable=true)
	private String maskZip;
	
	@Column(name="maskTel", length = 20, nullable=true)
	private String maskTel;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	
	
	public Pais(){
		super();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getMaskZip() {
		return maskZip;
	}

	public void setMaskZip(String maskZip) {
		this.maskZip = maskZip;
	}

	public String getMaskTel() {
		return maskTel;
	}

	public void setMaskTel(String maskTel) {
		this.maskTel = maskTel;
	}


	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", descricao=" + descricao + ", sigla=" + sigla + ", maskZip=" + maskZip
				+ ", maskTel=" + maskTel + ", id_Pessoa_Registro=" + id_Pessoa_Registro +  "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((maskTel == null) ? 0 : maskTel.hashCode());
		result = prime * result + ((maskZip == null) ? 0 : maskZip.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Pais other = (Pais) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (maskTel == null) {
			if (other.maskTel != null)
				return false;
		} else if (!maskTel.equals(other.maskTel))
			return false;
		if (maskZip == null) {
			if (other.maskZip != null)
				return false;
		} else if (!maskZip.equals(other.maskZip))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	

	
	
}
