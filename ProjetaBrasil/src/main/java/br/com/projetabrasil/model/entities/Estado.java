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
@Table(name="Estado")
public class Estado extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_estado",sequenceName="messsounds_estado", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_estado")
	private Long id;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	
	@Column(name="sigla", length = 5, nullable=false)
	private String sigla;
	
	@ManyToOne
	@JoinColumn(name = "id_pais")
	private Pais pais;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	
	public Estado(){
		super();
		
	
		
	}
	
	public Estado(Pais pais){
		super();
		this.pais = pais;
		
		
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}

	

	

	@Override
	public String toString() {
		return "Estado [id=" + id + ", descricao=" + descricao + ", sigla=" + sigla + ", pais=" + pais
				+ ", id_Pessoa_Registro=" + id_Pessoa_Registro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		Estado other = (Estado) obj;
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
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	
	
}
