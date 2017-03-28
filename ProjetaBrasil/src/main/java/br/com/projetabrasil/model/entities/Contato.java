package br.com.projetabrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="contato")
public class Contato extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_contato",sequenceName="messsounds_contato", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_contato")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipoRelacionamento", nullable=false)	
	private Enum_Aux_Tipo_Relacionamento tipoRelacionamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipoContato", nullable=false)	
	private Enum_Aux_Tipo_de_Contato tipoContato;
	
	
	@Column(name="contato", length = 90, nullable=false)
	private String contato;
	
	@OneToOne
	@JoinColumn ( name ="id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;
	
	@OneToOne
	@JoinColumn ( name ="id_Pessoa")
	private Pessoa id_Pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enum_Aux_Tipo_Relacionamento getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(Enum_Aux_Tipo_Relacionamento tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public Enum_Aux_Tipo_de_Contato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(Enum_Aux_Tipo_de_Contato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}

	public Pessoa getId_Pessoa() {
		return id_Pessoa;
	}

	public void setId_Pessoa(Pessoa id_Pessoa) {
		this.id_Pessoa = id_Pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa == null) ? 0 : id_Pessoa.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((tipoContato == null) ? 0 : tipoContato.hashCode());
		result = prime * result + ((tipoRelacionamento == null) ? 0 : tipoRelacionamento.hashCode());
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
		Contato other = (Contato) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa == null) {
			if (other.id_Pessoa != null)
				return false;
		} else if (!id_Pessoa.equals(other.id_Pessoa))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (tipoContato != other.tipoContato)
			return false;
		if (tipoRelacionamento != other.tipoRelacionamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", tipoRelacionamento=" + tipoRelacionamento + ", tipoContato=" + tipoContato
				+ ", contato=" + contato + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa=" + id_Pessoa
				+ "]";
	}
	
	
	
	

}
