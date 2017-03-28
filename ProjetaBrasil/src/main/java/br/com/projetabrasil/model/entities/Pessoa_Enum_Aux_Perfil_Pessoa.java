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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@SuppressWarnings("serial")
@Entity
@Table(name="Pessoa_Enum_Aux_Perfil_Pessoa")
public class Pessoa_Enum_Aux_Perfil_Pessoa extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name="pk_pessoa_Enum_Aux_Perfil_Pessoa",sequenceName="messsounds_pessoa_Enum_Perfil_Pessoa", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pessoa_Enum_Aux_Perfil_Pessoa")
	private Long id;
	
	
	@Enumerated(EnumType.STRING)	
	@Column(name="enum_Aux_Perfil_Pessoa", nullable=false)
	private Enum_Aux_Perfil_Pessoa enum_Aux_Perfil_Pessoa;	
	@ManyToOne
	
	@JoinColumn ( name ="id_pessoa", nullable=false)
	private Pessoa id_pessoa;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	
	public Enum_Aux_Perfil_Pessoa getEnum_Aux_Perfil_Pessoa() {
		return enum_Aux_Perfil_Pessoa;
	}
	
	public Pessoa_Enum_Aux_Perfil_Pessoa() {
		 
	}
	
	
	


	public void setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa enum_Aux_Perfil_Pessoa) {
		this.enum_Aux_Perfil_Pessoa = enum_Aux_Perfil_Pessoa;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pessoa_Enum_Aux_Perfil_Pessoa [id=" + id + ", enum_Aux_Perfil_Pessoa=" + enum_Aux_Perfil_Pessoa
				+ ", id_pessoa=" + id_pessoa + ", id_Pessoa_Registro=" + id_Pessoa_Registro + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((enum_Aux_Perfil_Pessoa == null) ? 0 : enum_Aux_Perfil_Pessoa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_pessoa == null) ? 0 : id_pessoa.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa_Enum_Aux_Perfil_Pessoa other = (Pessoa_Enum_Aux_Perfil_Pessoa) obj;
		if (enum_Aux_Perfil_Pessoa != other.enum_Aux_Perfil_Pessoa)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (id_pessoa == null) {
			if (other.id_pessoa != null)
				return false;
		} else if (!id_pessoa.equals(other.id_pessoa))
			return false;
		return true;
	}

	public Pessoa getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Pessoa id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id_Pessoa_Registro
	 */
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	/**
	 * @param id_Pessoa_Registro the id_Pessoa_Registro to set
	 */
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
}
