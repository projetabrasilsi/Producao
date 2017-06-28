package br.com.projetabrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@Entity
@Table(name="profissao")
public class Profissao extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_profissao",sequenceName="messsounds_profissao", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_profissao")
	private Long id;
	@Column(name="descricao", length = 120, nullable=false)
	private String descricao;
	
	@OneToOne
	@JoinColumn ( name ="id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;
	
	public Profissao(){
		
	}
	public Profissao(Pessoa id_Pessoa_Registro, String descricao ){
		this.id_Pessoa_Registro = id_Pessoa_Registro;
		this.setUltimaAtualizacao(Utilidades.retornaCalendario());
		this.setId_Empresa(1);
		this.setDescricao(descricao);
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




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Profissao other = (Profissao) obj;
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
		return true;
	}




	@Override
	public String toString() {
		return "Profissao [id=" + id + ", descricao=" + descricao + ", id_Pessoa_Registro=" + id_Pessoa_Registro + "]";
	}




	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}




	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}

}
