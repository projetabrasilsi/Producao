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
@Table(name="Endereco")
public class Endereco extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_endereco",sequenceName="messsounds_endereco", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_endereco")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_bairro", nullable=true)
	private Bairro bairro;
	
	@ManyToOne
	@JoinColumn(name = "id_logradouro")
	private Logradouro logradouro;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	@Column(name="numero", nullable=true) 
	private Integer numero;
	
	
	@Column(name="complemento", length=50, nullable=true) 
	private String complemento;
	
	public Endereco(){
		super();
	}
	
	public Endereco(Logradouro l){
		setLogradouro(l);
	}
	public Endereco(Bairro b){
		setBairro(b);
	}
	public Endereco(Logradouro l,Bairro b){
		setLogradouro(l);
		setBairro(b);
		
	}
	
	public Endereco(Bairro bairro, Cidade cidade, Estado estado) {
		super();
		this.bairro = bairro;
		this.bairro.setCidade(cidade);
		this.bairro.getCidade().setEstado(estado);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", bairro=" + bairro + ", logradouro=" + logradouro + ", pessoa=" + pessoa + "]";
	}
	
	
	
}
