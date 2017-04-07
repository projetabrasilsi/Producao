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
@Table(name="marca_e_raca")
public class Marca_e_Raca extends GenericDomain implements Serializable{
	@Id
	@SequenceGenerator(name="pk_marca_e_raca",sequenceName="messsounds_marca_e_raca", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_marca_e_raca")
	private Long id;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	@Enumerated(EnumType.STRING)	
	private Enum_Aux_Classificacao_Objetos enum_Aux_Classificacao_Objetos;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	
	@Column(name="codigo", length = 20, nullable=false)
	private String codigo;

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

	public Enum_Aux_Classificacao_Objetos getEnum_Aux_Classificacao_Objetos() {
		return enum_Aux_Classificacao_Objetos;
	}

	public void setEnum_Aux_Classificacao_Objetos(Enum_Aux_Classificacao_Objetos enum_Aux_Classificacao_Objetos) {
		this.enum_Aux_Classificacao_Objetos = enum_Aux_Classificacao_Objetos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((enum_Aux_Classificacao_Objetos == null) ? 0 : enum_Aux_Classificacao_Objetos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Marca_e_Raca other = (Marca_e_Raca) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (enum_Aux_Classificacao_Objetos != other.enum_Aux_Classificacao_Objetos)
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
		return true;
	}

	@Override
	public String toString() {
		return "Marca_e_Raca [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro
				+ ", enum_Aux_Classificacao_Objetos=" + enum_Aux_Classificacao_Objetos + ", descricao=" + descricao
				+ ", codigo=" + codigo + "]";
	}

		
}
