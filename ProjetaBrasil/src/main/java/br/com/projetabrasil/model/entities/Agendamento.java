package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Agendamento extends GenericDomain implements Serializable {
	@Id
	   @SequenceGenerator(name="pk_agendamento", sequenceName="mess_sounds_agendamento", allocationSize=1)
	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_agendamento")
	    private Long id;

		@ManyToOne
		@JoinColumn(name = "id_Pessoa_Registro")
		private Pessoa id_Pessoa_Registro;
		
		
		@ManyToOne
		@JoinColumn(name = "id_Pessoa_Baixa")
		private Pessoa id_Pessoa_Baixa;
		
		@ManyToOne
		@JoinColumn(name = "id_Pessoa_Assinante")
		private Pessoa id_Pessoa_Assinante;
		
		@ManyToOne
		@JoinColumn(name = "id_Pessoa_Cliente")
		private Pessoa id_Pessoa_Cliente;
		
		@ManyToOne	
		@JoinColumn(name="id_Movimento_Detalhe_A" )
		private Movimento_Detalhe_A id_Movimento_Detalhe_A;
		
		@Column(name="dataAgendamento")
		@Temporal(TemporalType.DATE)
		private Calendar dataAgendamento;
		@Column(name="dataBaixa")
		@Temporal(TemporalType.DATE)
		private Date dataBaixa;
		
		@Enumerated(EnumType.STRING)
		Enum_Aux_Status_Agendamento enum_Aux_Status_Agendamento;
		@Column(name="codigo",length=12)
		private String codigo;
		
		@Column(name="valor")
		private double valor;

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

		public Pessoa getId_Pessoa_Baixa() {
			return id_Pessoa_Baixa;
		}

		public void setId_Pessoa_Baixa(Pessoa id_Pessoa_Baixa) {
			this.id_Pessoa_Baixa = id_Pessoa_Baixa;
		}

		public Pessoa getId_Pessoa_Assinante() {
			return id_Pessoa_Assinante;
		}

		public void setId_Pessoa_Assinante(Pessoa id_Pessoa_Assinante) {
			this.id_Pessoa_Assinante = id_Pessoa_Assinante;
		}

		public Pessoa getId_Pessoa_Cliente() {
			return id_Pessoa_Cliente;
		}

		public void setId_Pessoa_Cliente(Pessoa id_Pessoa_Cliente) {
			this.id_Pessoa_Cliente = id_Pessoa_Cliente;
		}

		public Movimento_Detalhe_A getId_Movimento_Detalhe_A() {
			return id_Movimento_Detalhe_A;
		}

		public void setId_Movimento_Detalhe_A(Movimento_Detalhe_A id_Movimento_Detalhe_A) {
			this.id_Movimento_Detalhe_A = id_Movimento_Detalhe_A;
		}

		public Calendar getDataAgendamento() {
			return dataAgendamento;
		}

		public void setDataAgendamento(Calendar dataAgendamento) {
			this.dataAgendamento = dataAgendamento;
		}

		public Date getDataBaixa() {
			return dataBaixa;
		}

		public void setDataBaixa(Date dataBaixa) {
			this.dataBaixa = dataBaixa;
		}

		public Enum_Aux_Status_Agendamento getEnum_Aux_Status_Agendamento() {
			return enum_Aux_Status_Agendamento;
		}

		public void setEnum_Aux_Status_Agendamento(Enum_Aux_Status_Agendamento enum_Aux_Status_Agendamento) {
			this.enum_Aux_Status_Agendamento = enum_Aux_Status_Agendamento;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		@Override
		public String toString() {
			return "Agendamento [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Baixa="
					+ id_Pessoa_Baixa + ", id_Pessoa_Assinante=" + id_Pessoa_Assinante + ", id_Pessoa_Cliente="
					+ id_Pessoa_Cliente + ", id_Movimento_Detalhe_A=" + id_Movimento_Detalhe_A + ", dataAgendamento="
					+ dataAgendamento + ", dataBaixa=" + dataBaixa + ", enum_Aux_Status_Agendamento="
					+ enum_Aux_Status_Agendamento + ", codigo=" + codigo + ", valor=" + valor + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
			result = prime * result + ((dataAgendamento == null) ? 0 : dataAgendamento.hashCode());
			result = prime * result + ((dataBaixa == null) ? 0 : dataBaixa.hashCode());
			result = prime * result
					+ ((enum_Aux_Status_Agendamento == null) ? 0 : enum_Aux_Status_Agendamento.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((id_Movimento_Detalhe_A == null) ? 0 : id_Movimento_Detalhe_A.hashCode());
			result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
			result = prime * result + ((id_Pessoa_Baixa == null) ? 0 : id_Pessoa_Baixa.hashCode());
			result = prime * result + ((id_Pessoa_Cliente == null) ? 0 : id_Pessoa_Cliente.hashCode());
			result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
			long temp;
			temp = Double.doubleToLongBits(valor);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Agendamento other = (Agendamento) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			if (dataAgendamento == null) {
				if (other.dataAgendamento != null)
					return false;
			} else if (!dataAgendamento.equals(other.dataAgendamento))
				return false;
			if (dataBaixa == null) {
				if (other.dataBaixa != null)
					return false;
			} else if (!dataBaixa.equals(other.dataBaixa))
				return false;
			if (enum_Aux_Status_Agendamento != other.enum_Aux_Status_Agendamento)
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
			if (id_Pessoa_Assinante == null) {
				if (other.id_Pessoa_Assinante != null)
					return false;
			} else if (!id_Pessoa_Assinante.equals(other.id_Pessoa_Assinante))
				return false;
			if (id_Pessoa_Baixa == null) {
				if (other.id_Pessoa_Baixa != null)
					return false;
			} else if (!id_Pessoa_Baixa.equals(other.id_Pessoa_Baixa))
				return false;
			if (id_Pessoa_Cliente == null) {
				if (other.id_Pessoa_Cliente != null)
					return false;
			} else if (!id_Pessoa_Cliente.equals(other.id_Pessoa_Cliente))
				return false;
			if (id_Pessoa_Registro == null) {
				if (other.id_Pessoa_Registro != null)
					return false;
			} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
				return false;
			if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
				return false;
			return true;
		}

						

}
