package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// @mappedSuperclass - indica que ela não é uma tabela mas que será usada por
// outras classes para gerar tabela
@MappedSuperclass
public class GenericDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4664956343242539547L;
	
	@Temporal(TemporalType.TIMESTAMP)
	
	private Calendar ultimaAtualizacao;
	@Column(name = "id_empresa")
	private int id_Empresa;
	/**
	 * @return the ultimaAtualizacao
	 */
	public Calendar getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}
	/**
	 * @param ultimaAtualizacao the ultimaAtualizacao to set
	 */
	public void setUltimaAtualizacao(Calendar ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	/**
	 * @return the id_Empresa
	 */
	public int getId_Empresa() {
		return id_Empresa;
	}
	/**
	 * @param id_Empresa the id_Empresa to set
	 */
	public void setId_Empresa(int id_Empresa) {
		this.id_Empresa = id_Empresa;
	}
	@Override
	public String toString() {
		return "GenericDomain "+ "[ultimaAtualizacao=" + ultimaAtualizacao+ 
				", id_Empresa=" + id_Empresa + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Empresa;
		result = prime * result + ((ultimaAtualizacao == null) ? 0 : ultimaAtualizacao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (id_Empresa != other.id_Empresa)
			return false;
		if (ultimaAtualizacao == null) {
			if (other.ultimaAtualizacao != null)
				return false;
		} else if (!ultimaAtualizacao.equals(other.ultimaAtualizacao))
			return false;
		return true;
	}
	
	
	
	
			

}
