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
@Table(name="QRCode")
public class QRCode extends GenericDomain implements Serializable{
	@Id
	@SequenceGenerator(name="pk_qrcode",sequenceName="messsounds_qrcode", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_qrcode")
	private Long id;
	@ManyToOne
	@JoinColumn(name="id_Pessoa_Registro",nullable=false)
	private Pessoa id_Pessoa_Registro;
	
	@Column(name="id_Pessoa_Supervisores", nullable=true)
	private Pessoa id_Pessoa_Supervisores;
	@Column(name="id_Pessoa_Representantes", nullable=true)
	private Pessoa id_Pessoa_Representantes;	
	@Column(name="id_Pessoa_Assinantes", nullable=true)
	private Pessoa id_Pessoa_Assinantes;
	@Column(name="id_Pessoa_Clientes", nullable=true)
	private Pessoa id_Pessoa_Clientes;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable=false)
	private Enum_Aux_Status_QRCodes status;
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_Objeto")
	private Enum_Aux_Tipos_Objetos tipo_Objeto;
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_qrcode")
	private Enum_Aux_Tipos_QRCode tipo_QRCode;
	
	
	
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_Supervisao")
	private Calendar data_Supervisao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_Representacao")
	private Calendar data_Representacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_Assinatura")
	private Calendar data_Assinatura;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_Registro")
	private Calendar data_Registro;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_Vencimento")
	private Calendar data_Vencimento;
	
	@Column(name="numero_Meses")
	private int numero_Meses;
	@Column(name="coders")
	private String coders;
	
	public QRCode(){
		
	}
	
	
	public QRCode  (Pessoa id_Pessoa_Registro, Enum_Aux_Status_QRCodes status, String coders  ){
		
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


	public Pessoa getId_Pessoa_Supervisores() {
		return id_Pessoa_Supervisores;
	}


	public void setId_Pessoa_Supervisores(Pessoa id_Pessoa_Supervisores) {
		this.id_Pessoa_Supervisores = id_Pessoa_Supervisores;
	}


	public Pessoa getId_Pessoa_Representantes() {
		return id_Pessoa_Representantes;
	}


	public void setId_Pessoa_Representantes(Pessoa id_Pessoa_Representantes) {
		this.id_Pessoa_Representantes = id_Pessoa_Representantes;
	}


	public Pessoa getId_Pessoa_Assinantes() {
		return id_Pessoa_Assinantes;
	}


	public void setId_Pessoa_Assinantes(Pessoa id_Pessoa_Assinantes) {
		this.id_Pessoa_Assinantes = id_Pessoa_Assinantes;
	}


	public Pessoa getId_Pessoa_Clientes() {
		return id_Pessoa_Clientes;
	}


	public void setId_Pessoa_Clientes(Pessoa id_Pessoa_Clientes) {
		this.id_Pessoa_Clientes = id_Pessoa_Clientes;
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


	public Calendar getData_Supervisao() {
		return data_Supervisao;
	}


	public void setData_Supervisao(Calendar data_Supervisao) {
		this.data_Supervisao = data_Supervisao;
	}


	public Calendar getData_Representacao() {
		return data_Representacao;
	}


	public void setData_Representacao(Calendar data_Representacao) {
		this.data_Representacao = data_Representacao;
	}


	public Calendar getData_Assinatura() {
		return data_Assinatura;
	}


	public void setData_Assinatura(Calendar data_Assinatura) {
		this.data_Assinatura = data_Assinatura;
	}


	public Calendar getData_Registro() {
		return data_Registro;
	}


	public void setData_Registro(Calendar data_Registro) {
		this.data_Registro = data_Registro;
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
	public String toString() {
		return "QRCode [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Supervisores="
				+ id_Pessoa_Supervisores + ", id_Pessoa_Representantes=" + id_Pessoa_Representantes
				+ ", id_Pessoa_Assinantes=" + id_Pessoa_Assinantes + ", id_Pessoa_Clientes=" + id_Pessoa_Clientes
				+ ", status=" + status + ", tipo_Objeto=" + tipo_Objeto + ", tipo_QRCode=" + tipo_QRCode
				+ ", data_Supervisao=" + data_Supervisao + ", data_Representacao=" + data_Representacao
				+ ", data_Assinatura=" + data_Assinatura + ", data_Registro=" + data_Registro + ", data_Vencimento="
				+ data_Vencimento + ", numero_Meses=" + numero_Meses + ", coders=" + coders + "]";
	}


	
	public Enum_Aux_Tipos_QRCode getTipo_QRCode() {
		return tipo_QRCode;
	}


	public void setTipo_QRCode(Enum_Aux_Tipos_QRCode tipo_QRCode) {
		this.tipo_QRCode = tipo_QRCode;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((coders == null) ? 0 : coders.hashCode());
		result = prime * result + ((data_Assinatura == null) ? 0 : data_Assinatura.hashCode());
		result = prime * result + ((data_Registro == null) ? 0 : data_Registro.hashCode());
		result = prime * result + ((data_Representacao == null) ? 0 : data_Representacao.hashCode());
		result = prime * result + ((data_Supervisao == null) ? 0 : data_Supervisao.hashCode());
		result = prime * result + ((data_Vencimento == null) ? 0 : data_Vencimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Assinantes == null) ? 0 : id_Pessoa_Assinantes.hashCode());
		result = prime * result + ((id_Pessoa_Clientes == null) ? 0 : id_Pessoa_Clientes.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_Pessoa_Representantes == null) ? 0 : id_Pessoa_Representantes.hashCode());
		result = prime * result + ((id_Pessoa_Supervisores == null) ? 0 : id_Pessoa_Supervisores.hashCode());
		result = prime * result + numero_Meses;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipo_Objeto == null) ? 0 : tipo_Objeto.hashCode());
		result = prime * result + ((tipo_QRCode == null) ? 0 : tipo_QRCode.hashCode());
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
		if (data_Assinatura == null) {
			if (other.data_Assinatura != null)
				return false;
		} else if (!data_Assinatura.equals(other.data_Assinatura))
			return false;
		if (data_Registro == null) {
			if (other.data_Registro != null)
				return false;
		} else if (!data_Registro.equals(other.data_Registro))
			return false;
		if (data_Representacao == null) {
			if (other.data_Representacao != null)
				return false;
		} else if (!data_Representacao.equals(other.data_Representacao))
			return false;
		if (data_Supervisao == null) {
			if (other.data_Supervisao != null)
				return false;
		} else if (!data_Supervisao.equals(other.data_Supervisao))
			return false;
		if (data_Vencimento == null) {
			if (other.data_Vencimento != null)
				return false;
		} else if (!data_Vencimento.equals(other.data_Vencimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Assinantes == null) {
			if (other.id_Pessoa_Assinantes != null)
				return false;
		} else if (!id_Pessoa_Assinantes.equals(other.id_Pessoa_Assinantes))
			return false;
		if (id_Pessoa_Clientes == null) {
			if (other.id_Pessoa_Clientes != null)
				return false;
		} else if (!id_Pessoa_Clientes.equals(other.id_Pessoa_Clientes))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (id_Pessoa_Representantes == null) {
			if (other.id_Pessoa_Representantes != null)
				return false;
		} else if (!id_Pessoa_Representantes.equals(other.id_Pessoa_Representantes))
			return false;
		if (id_Pessoa_Supervisores == null) {
			if (other.id_Pessoa_Supervisores != null)
				return false;
		} else if (!id_Pessoa_Supervisores.equals(other.id_Pessoa_Supervisores))
			return false;
		if (numero_Meses != other.numero_Meses)
			return false;
		if (status != other.status)
			return false;
		if (tipo_Objeto != other.tipo_Objeto)
			return false;
		if (tipo_QRCode != other.tipo_QRCode)
			return false;
		return true;
	}			  
}
