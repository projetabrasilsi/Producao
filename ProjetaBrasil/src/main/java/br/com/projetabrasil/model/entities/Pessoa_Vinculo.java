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
@Table(name="Pessoa_Vinculo")
public class Pessoa_Vinculo extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_pessoa_Vinculo",sequenceName="messsounds_pessoa_Vinculo", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pessoa_Vinculo")
	private Long id;
	
	@ManyToOne
	// estou vinculado a ?
	@JoinColumn(name="id_pessoa_m", nullable=false)
	private Pessoa id_pessoa_m;
	@ManyToOne
	// sou a pessoa que estou vinculada
	@JoinColumn(name="id_pessoa_d", nullable=false)	
	private Pessoa id_pessoa_d;
	
	@Column(name="ativo" , nullable=false)
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro ;

	@Enumerated(EnumType.STRING)
	private Enum_Aux_Perfil_Pessoa enum_Aux_Perfil_Pessoa ;

	
	public Pessoa getId_pessoa_d() {
		return id_pessoa_d;
	}

	public void setId_pessoa_d(Pessoa id_pessoa_d) {
		this.id_pessoa_d = id_pessoa_d;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getId_pessoa_m() {
		return id_pessoa_m;
	}

	public void setId_pessoa_m(Pessoa id_pessoa_m) {
		this.id_pessoa_m = id_pessoa_m;
	}

	@Override
	public String toString() {
		return "Pessoa_Vinculo [id=" + id + ", id_pessoa_m=" + id_pessoa_m + ", id_pessoa_d=" + id_pessoa_d + ", ativo="
				+ ativo + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", enum_Aux_Perfil_Pessoa="
				+ enum_Aux_Perfil_Pessoa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((enum_Aux_Perfil_Pessoa == null) ? 0 : enum_Aux_Perfil_Pessoa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_pessoa_d == null) ? 0 : id_pessoa_d.hashCode());
		result = prime * result + ((id_pessoa_m == null) ? 0 : id_pessoa_m.hashCode());
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
		Pessoa_Vinculo other = (Pessoa_Vinculo) obj;
		if (ativo != other.ativo)
			return false;
		if (enum_Aux_Perfil_Pessoa != other.enum_Aux_Perfil_Pessoa)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (id_pessoa_d == null) {
			if (other.id_pessoa_d != null)
				return false;
		} else if (!id_pessoa_d.equals(other.id_pessoa_d))
			return false;
		if (id_pessoa_m == null) {
			if (other.id_pessoa_m != null)
				return false;
		} else if (!id_pessoa_m.equals(other.id_pessoa_m))
			return false;
		return true;
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

	public Enum_Aux_Perfil_Pessoa getEnum_Aux_Perfil_Pessoa() {
		return enum_Aux_Perfil_Pessoa;
	}

	public void setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa enum_Aux_Perfil_Pessoa) {
		this.enum_Aux_Perfil_Pessoa = enum_Aux_Perfil_Pessoa;
	}

	
}
