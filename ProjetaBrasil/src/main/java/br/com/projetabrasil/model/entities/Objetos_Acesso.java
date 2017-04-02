package br.com.projetabrasil.model.entities;

import java.io.Serializable;

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
@Table(name="objetos_acesso")
public class Objetos_Acesso extends GenericDomain implements Serializable{
	
	@Id
	@SequenceGenerator(name="pk_objetos_acesso",sequenceName="messsounds_objetos_acesso", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_objetos_acesso")
	private Long id;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa", nullable=false)	
	private Pessoa id_Pessoa;
	
	private Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objetos; 
	
	public Objetos_Acesso(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Enum_Aux_Tipos_Objetos getEnum_Aux_Tipos_Objetos() {
		return enum_Aux_Tipos_Objetos;
	}

	public void setEnum_Aux_Tipos_Objetos(Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objetos) {
		this.enum_Aux_Tipos_Objetos = enum_Aux_Tipos_Objetos;
	}

	@Override
	public String toString() {
		return "Objetos_Acesso [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa=" + id_Pessoa
				+ ", enum_Aux_Tipos_Objetos=" + enum_Aux_Tipos_Objetos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((enum_Aux_Tipos_Objetos == null) ? 0 : enum_Aux_Tipos_Objetos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa == null) ? 0 : id_Pessoa.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
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
		Objetos_Acesso other = (Objetos_Acesso) obj;
		if (enum_Aux_Tipos_Objetos != other.enum_Aux_Tipos_Objetos)
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
		return true;
	}
	

}

