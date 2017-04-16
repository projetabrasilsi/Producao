package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.projetabrasil.util.Utilidades;

@Entity
@SuppressWarnings("serial")
@Table(name = "QRCode")
public class QRCode extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name = "pk_qrcode", sequenceName = "messsounds_qrcode", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_qrcode")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_Pessoa_Registro", nullable = false)
	private Pessoa id_Pessoa_Registro;

	@Column(name = "id_Pessoa_Logistica", nullable = true)
	private Pessoa id_Pessoa_Logistica;

	@Column(name = "id_Pessoa_Distribuicao", nullable = true)
	private Pessoa id_Pessoa_Distribuicao;

	@Column(name = "id_Pessoa_Representacao", nullable = true)
	private Pessoa id_Pessoa_Representacao;

	@Column(name = "id_Pessoa_Revenda", nullable = true)
	private Pessoa id_Pessoa_Revenda;

	@Column(name = "id_Pessoa_Cliente", nullable = true)
	private Pessoa id_Pessoa_Cliente;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Enum_Aux_Status_QRCodes status;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_Objeto")
	private Enum_Aux_Tipos_Objetos tipo_Objeto;
	@Enumerated(EnumType.STRING)

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_Logistica")
	private Calendar data_Logistica;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_Distribuicao")
	private Calendar data_Distribuicao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_Representacao")
	private Calendar data_Representacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_Revenda")
	private Calendar data_Revenda;
	@Column(name = "data_Venda")
	private Calendar data_Venda;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_Vencimento")
	private Calendar data_Vencimento;

	@Column(name = "numero_Meses")
	private int numero_Meses;
	@Column(name = "coders")
	private String coders;

	public QRCode() {

	}

	public QRCode(Pessoa id_Pessoa_Registro, Enum_Aux_Status_QRCodes status, String coders) {

		this.id_Pessoa_Registro = id_Pessoa_Registro;
		this.status = status;
		this.setUltimaAtualizacao(Utilidades.retornaCalendario());
		this.setId_Empresa(1);
		this.setCoders(coders);
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

	public Pessoa getId_Pessoa_Logistica() {
		return id_Pessoa_Logistica;
	}

	public void setId_Pessoa_Logistica(Pessoa id_Pessoa_Logistica) {
		this.id_Pessoa_Logistica = id_Pessoa_Logistica;
	}

	public Pessoa getId_Pessoa_Distribuicao() {
		return id_Pessoa_Distribuicao;
	}

	public void setId_Pessoa_Distribuicao(Pessoa id_Pessoa_Distribuicao) {
		this.id_Pessoa_Distribuicao = id_Pessoa_Distribuicao;
	}

	public Pessoa getId_Pessoa_Representacao() {
		return id_Pessoa_Representacao;
	}

	public void setId_Pessoa_Representacao(Pessoa id_Pessoa_Representacao) {
		this.id_Pessoa_Representacao = id_Pessoa_Representacao;
	}

	public Pessoa getId_Pessoa_Revenda() {
		return id_Pessoa_Revenda;
	}

	public void setId_Pessoa_Revenda(Pessoa id_Pessoa_Revenda) {
		this.id_Pessoa_Revenda = id_Pessoa_Revenda;
	}

	public Pessoa getId_Pessoa_Cliente() {
		return id_Pessoa_Cliente;
	}

	public void setId_Pessoa_Cliente(Pessoa id_Pessoa_Cliente) {
		this.id_Pessoa_Cliente = id_Pessoa_Cliente;
	}

	public Enum_Aux_Status_QRCodes getStatus() {
		return status;
	}

	public void setStatus(Enum_Aux_Status_QRCodes status) {
		this.status = status;
	}

	public Enum_Aux_Tipos_Objetos getTipo_Objeto() {
		return tipo_Objeto;
	}

	public void setTipo_Objeto(Enum_Aux_Tipos_Objetos tipo_Objeto) {
		this.tipo_Objeto = tipo_Objeto;
	}

	public Calendar getData_Logistica() {
		return data_Logistica;
	}

	public void setData_Logistica(Calendar data_Logistica) {
		this.data_Logistica = data_Logistica;
	}

	public Calendar getData_Distribuicao() {
		return data_Distribuicao;
	}

	public void setData_Distribuicao(Calendar data_Distribuicao) {
		this.data_Distribuicao = data_Distribuicao;
	}

	public Calendar getData_Representacao() {
		return data_Representacao;
	}

	public void setData_Representacao(Calendar data_Representacao) {
		this.data_Representacao = data_Representacao;
	}

	public Calendar getData_Revenda() {
		return data_Revenda;
	}

	public void setData_Revenda(Calendar data_Revenda) {
		this.data_Revenda = data_Revenda;
	}

	public Calendar getData_Venda() {
		return data_Venda;
	}

	public void setData_Venda(Calendar data_Venda) {
		this.data_Venda = data_Venda;
	}

	public Calendar getData_Vencimento() {
		return data_Vencimento;
	}

	public void setData_Vencimento(Calendar data_Vencimento) {
		this.data_Vencimento = data_Vencimento;
	}

	public int getNumero_Meses() {
		return numero_Meses;
	}

	public void setNumero_Meses(int numero_Meses) {
		this.numero_Meses = numero_Meses;
	}

	public String getCoders() {
		return coders;
	}

	public void setCoders(String coders) {
		this.coders = coders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((coders == null) ? 0 : coders.hashCode());
		result = prime * result + ((data_Distribuicao == null) ? 0 : data_Distribuicao.hashCode());
		result = prime * result + ((data_Logistica == null) ? 0 : data_Logistica.hashCode());
		result = prime * result + ((data_Representacao == null) ? 0 : data_Representacao.hashCode());
		result = prime * result + ((data_Revenda == null) ? 0 : data_Revenda.hashCode());
		result = prime * result + ((data_Vencimento == null) ? 0 : data_Vencimento.hashCode());
		result = prime * result + ((data_Venda == null) ? 0 : data_Venda.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Cliente == null) ? 0 : id_Pessoa_Cliente.hashCode());
		result = prime * result + ((id_Pessoa_Distribuicao == null) ? 0 : id_Pessoa_Distribuicao.hashCode());
		result = prime * result + ((id_Pessoa_Logistica == null) ? 0 : id_Pessoa_Logistica.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_Pessoa_Representacao == null) ? 0 : id_Pessoa_Representacao.hashCode());
		result = prime * result + ((id_Pessoa_Revenda == null) ? 0 : id_Pessoa_Revenda.hashCode());
		result = prime * result + numero_Meses;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipo_Objeto == null) ? 0 : tipo_Objeto.hashCode());
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
		QRCode other = (QRCode) obj;
		if (coders == null) {
			if (other.coders != null)
				return false;
		} else if (!coders.equals(other.coders))
			return false;
		if (data_Distribuicao == null) {
			if (other.data_Distribuicao != null)
				return false;
		} else if (!data_Distribuicao.equals(other.data_Distribuicao))
			return false;
		if (data_Logistica == null) {
			if (other.data_Logistica != null)
				return false;
		} else if (!data_Logistica.equals(other.data_Logistica))
			return false;
		if (data_Representacao == null) {
			if (other.data_Representacao != null)
				return false;
		} else if (!data_Representacao.equals(other.data_Representacao))
			return false;
		if (data_Revenda == null) {
			if (other.data_Revenda != null)
				return false;
		} else if (!data_Revenda.equals(other.data_Revenda))
			return false;
		if (data_Vencimento == null) {
			if (other.data_Vencimento != null)
				return false;
		} else if (!data_Vencimento.equals(other.data_Vencimento))
			return false;
		if (data_Venda == null) {
			if (other.data_Venda != null)
				return false;
		} else if (!data_Venda.equals(other.data_Venda))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Cliente == null) {
			if (other.id_Pessoa_Cliente != null)
				return false;
		} else if (!id_Pessoa_Cliente.equals(other.id_Pessoa_Cliente))
			return false;
		if (id_Pessoa_Distribuicao == null) {
			if (other.id_Pessoa_Distribuicao != null)
				return false;
		} else if (!id_Pessoa_Distribuicao.equals(other.id_Pessoa_Distribuicao))
			return false;
		if (id_Pessoa_Logistica == null) {
			if (other.id_Pessoa_Logistica != null)
				return false;
		} else if (!id_Pessoa_Logistica.equals(other.id_Pessoa_Logistica))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (id_Pessoa_Representacao == null) {
			if (other.id_Pessoa_Representacao != null)
				return false;
		} else if (!id_Pessoa_Representacao.equals(other.id_Pessoa_Representacao))
			return false;
		if (id_Pessoa_Revenda == null) {
			if (other.id_Pessoa_Revenda != null)
				return false;
		} else if (!id_Pessoa_Revenda.equals(other.id_Pessoa_Revenda))
			return false;
		if (numero_Meses != other.numero_Meses)
			return false;
		if (status != other.status)
			return false;
		if (tipo_Objeto != other.tipo_Objeto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QRCode [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Logistica="
				+ id_Pessoa_Logistica + ", id_Pessoa_Distribuicao=" + id_Pessoa_Distribuicao
				+ ", id_Pessoa_Representacao=" + id_Pessoa_Representacao + ", id_Pessoa_Revenda=" + id_Pessoa_Revenda
				+ ", id_Pessoa_Cliente=" + id_Pessoa_Cliente + ", status=" + status + ", tipo_Objeto=" + tipo_Objeto
				+ ", data_Logistica=" + data_Logistica + ", data_Distribuicao=" + data_Distribuicao
				+ ", data_Representacao=" + data_Representacao + ", data_Revenda=" + data_Revenda + ", data_Venda="
				+ data_Venda + ", data_Vencimento=" + data_Vencimento + ", numero_Meses=" + numero_Meses + ", coders="
				+ coders + "]";
	}

}
