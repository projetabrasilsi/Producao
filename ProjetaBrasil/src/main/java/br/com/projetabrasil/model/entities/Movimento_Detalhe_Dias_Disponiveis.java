package br.com.projetabrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class Movimento_Detalhe_Dias_Disponiveis extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name="pk_movimento_Detalhe_Dias_Disponiveis",
	sequenceName="mess_sounds_movimento_Detalhe_Dias_Disponiveis", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_movimento_Detalhe_Dias_Disponiveis")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;

	
	@ManyToOne	
	@JoinColumn(name="id_Movimento_Detalhe_A", nullable=false )
	private Movimento_Detalhe_A id_Movimento_Detalhe_A;
	
	@Enumerated(EnumType.STRING)
	private Enum_Aux_Dia_da_Semana enum_Aux_Dia_da_Semana;
	
	@Enumerated(EnumType.STRING)
	private Enum_Aux_Sim_ou_Nao enum_Aux_Sim_ou_Nao;

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

	public Movimento_Detalhe_A getId_Movimento_Detalhe_A() {
		return id_Movimento_Detalhe_A;
	}

	public void setId_Movimento_Detalhe_A(Movimento_Detalhe_A id_Movimento_Detalhe_A) {
		this.id_Movimento_Detalhe_A = id_Movimento_Detalhe_A;
	}

	public Enum_Aux_Dia_da_Semana getEnum_Aux_Dia_da_Semana() {
		return enum_Aux_Dia_da_Semana;
	}

	public void setEnum_Aux_Dia_da_Semana(Enum_Aux_Dia_da_Semana enum_Aux_Dia_da_Semana) {
		this.enum_Aux_Dia_da_Semana = enum_Aux_Dia_da_Semana;
	}

	public Enum_Aux_Sim_ou_Nao getEnum_Aux_Sim_ou_Nao() {
		return enum_Aux_Sim_ou_Nao;
	}

	public void setEnum_Aux_Sim_ou_Nao(Enum_Aux_Sim_ou_Nao enum_Aux_Sim_ou_Nao) {
		this.enum_Aux_Sim_ou_Nao = enum_Aux_Sim_ou_Nao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((enum_Aux_Dia_da_Semana == null) ? 0 : enum_Aux_Dia_da_Semana.hashCode());
		result = prime * result + ((enum_Aux_Sim_ou_Nao == null) ? 0 : enum_Aux_Sim_ou_Nao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Movimento_Detalhe_A == null) ? 0 : id_Movimento_Detalhe_A.hashCode());
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
		Movimento_Detalhe_Dias_Disponiveis other = (Movimento_Detalhe_Dias_Disponiveis) obj;
		if (enum_Aux_Dia_da_Semana != other.enum_Aux_Dia_da_Semana)
			return false;
		if (enum_Aux_Sim_ou_Nao != other.enum_Aux_Sim_ou_Nao)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Movimento_Detalhe_A == null) {
			if (other.id_Movimento_Detalhe_A != null)
				return false;
		} else if (!id_Movimento_Detalhe_A.equals(other.id_Movimento_Detalhe_A))
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
		return "Movimento_Detalhe_Dias_Disponiveis [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro
				+ ", id_Movimento_Detalhe_A=" + id_Movimento_Detalhe_A + ", enum_Aux_Dia_da_Semana="
				+ enum_Aux_Dia_da_Semana + ", enum_Aux_Sim_ou_Nao=" + enum_Aux_Sim_ou_Nao + "]";
	}

	
}