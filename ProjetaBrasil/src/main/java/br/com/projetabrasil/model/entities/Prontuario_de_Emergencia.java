package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")


@Entity
@Table(name="prontuario_de_emergencia")
public class Prontuario_de_Emergencia extends GenericDomain implements Serializable {
	

	@Id
	@SequenceGenerator(name="pk_prontuario_de_emergencia",sequenceName="messsounds_prontuario_de_emergencia", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_prontuario_de_emergencia")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_Prontuario_Emergencia", nullable=true)	
	private Enum_Aux_Tipo_Prontuario_de_Emergencia tipo_Prontuario_Emergencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_Vacina", nullable=true)	
	private Enum_Aux_Tipo_Vacina tipo_Vacina;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	
	@Column(name="dataVacinacao", nullable=true)
	@Temporal(TemporalType.DATE)	
	private Date dataVacinacao;
	
	@Column(name="dataAviso", nullable=true)
	@Temporal(TemporalType.DATE)	
	private Date dataAviso;
	
	@OneToOne
	@JoinColumn ( name ="id_clinica_responsavel")
	private Pessoa id_clinica_responsavel;	
	
	@OneToOne
	@JoinColumn ( name ="id_veterinario_responsavel")
	private Pessoa id_veterinario_responsavel;	
	
	@OneToOne
	@JoinColumn ( name ="id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;	
	
	@OneToOne
	@JoinColumn ( name ="id_Pessoa")
	private Pessoa id_Pessoa;
	
	@OneToOne
	@JoinColumn ( name ="id_Objeto")
	private Objeto id_Objeto;

	public Long getId() {
		return id;
	}

	public Enum_Aux_Tipo_Prontuario_de_Emergencia getTipo_Prontuario_Emergencia() {
		return tipo_Prontuario_Emergencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	public Pessoa getId_Pessoa() {
		return id_Pessoa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipo_Prontuario_Emergencia(Enum_Aux_Tipo_Prontuario_de_Emergencia tipo_Prontuario_Emergencia) {
		this.tipo_Prontuario_Emergencia = tipo_Prontuario_Emergencia;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}

	public void setId_Pessoa(Pessoa id_Pessoa) {
		this.id_Pessoa = id_Pessoa;
	}
	
	

	public Date getDataVacinacao() {
		return dataVacinacao;
	}

	public void setDataVacinacao(Date dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	public Date getDataAviso() {
		return dataAviso;
	}

	public void setDataAviso(Date dataAviso) {
		this.dataAviso = dataAviso;
	}

	public Pessoa getId_clinica_responsavel() {
		return id_clinica_responsavel;
	}

	public void setId_clinica_responsavel(Pessoa id_clinica_responsavel) {
		this.id_clinica_responsavel = id_clinica_responsavel;
	}

	public Pessoa getId_veterinario_responsavel() {
		return id_veterinario_responsavel;
	}

	public void setId_veterinario_responsavel(Pessoa id_veterinario_responsavel) {
		this.id_veterinario_responsavel = id_veterinario_responsavel;
	}

	public Objeto getId_Objeto() {
		return id_Objeto;
	}

	public void setId_Objeto(Objeto id_Objeto) {
		this.id_Objeto = id_Objeto;
	}

	public Enum_Aux_Tipo_Vacina getTipo_Vacina() {
		return tipo_Vacina;
	}

	public void setTipo_Vacina(Enum_Aux_Tipo_Vacina tipo_Vacina) {
		this.tipo_Vacina = tipo_Vacina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa == null) ? 0 : id_Pessoa.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((tipo_Prontuario_Emergencia == null) ? 0 : tipo_Prontuario_Emergencia.hashCode());
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
		Prontuario_de_Emergencia other = (Prontuario_de_Emergencia) obj;
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
		if (tipo_Prontuario_Emergencia != other.tipo_Prontuario_Emergencia)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prontuario_de_Emergencia [id=" + id + ", tipo_Prontuario_Emergencia=" + tipo_Prontuario_Emergencia
				+ ", descricao=" + descricao + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa=" + id_Pessoa
				+ "]";
	}

}
