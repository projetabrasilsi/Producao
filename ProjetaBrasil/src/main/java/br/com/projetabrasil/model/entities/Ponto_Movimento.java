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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.primefaces.model.StreamedContent;

@SuppressWarnings("serial")
@Entity
@Table(name="Ponto_Movimento")
public class Ponto_Movimento extends GenericDomain implements Serializable {

	@Id
	@SequenceGenerator(name="pk_pontuacao_Movimento",sequenceName="messsounds_pontuacao_Movimento", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pontuacao_Movimento")
	private Long id;
	
	@ManyToOne	
	@JoinColumn ( name ="id_pessoa_associado", nullable=false)
	private Pessoa id_pessoa_associado;
	
	@ManyToOne	
	@JoinColumn ( name ="id_pessoa_cliente", nullable=false)
	private Pessoa id_pessoa_cliente;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	@ManyToOne
	@JoinColumn(name="id_ponto", nullable =false)
	private Ponto id_ponto;
	@Column(name="pontuacaoMinima", nullable=false)	
	private int pontuacaoMinima;
	@Column(name="unidadeporPonto", nullable=false)	
	private int unidadeporPonto;	
	@Column(name="diasValidade",nullable=false)	
	private int diasValidade;
	
	@Column(name="valordaUnidade",precision=18,scale=4,nullable=false)
	private double valordaUnidade;	
	@Column(name="valorUnidadeTroca",precision=18,scale=4,nullable=false)
	private double valorUnidadeTroca;
	@Column(name="valorUnidadeDevolucao",precision=18,scale=4,nullable=false)
	private double valorUnidadeDevolucao;
	@Column(name="valoraPontuar", precision=18,scale=4,nullable=false)
	private double valoraPontuar;
	@Column(name="pontosAtingidos",precision=18,scale=4,nullable=false)
	private double pontosAtingidos;	
	@Enumerated(EnumType.STRING)
	@Column(name="creditaDebita", nullable=false)
	private Enum_Aux_Tipo_Mov_Ponto creditaDebita;	
	@Temporal(TemporalType.DATE)
	private Date validade;
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	@Column(name="numDoc",length=90)
	private String numDoc;
	
	@Transient
	@Column(name = "caminhodaImagem")	
	 private String caminhodaImagem;
	@Transient
	@Column(name = "caminhodaImagem2")	
	 private String caminhodaImagem2;
	
	@Transient
	@Column(name = "foto")	
	 private StreamedContent foto;
	
	@Transient
	@Column(name = "foto2")	
	 private StreamedContent foto2;
	
	@Transient
	@Column(name = "tipodeImagem")	
	 private String tipodeImagem;
	@Transient
	@Column(name = "caminhoTemp")	
	 private String caminhoTemp;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Status_Movimento_Ponto")
	private Enum_Aux_Status_Movimento_Ponto enum_Aux_Status_Movimento_Ponto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getId_pessoa_associado() {
		return id_pessoa_associado;
	}

	public void setId_pessoa_associado(Pessoa id_pessoa_associado) {
		this.id_pessoa_associado = id_pessoa_associado;
	}

	public Pessoa getId_pessoa_cliente() {
		return id_pessoa_cliente;
	}

	public void setId_pessoa_cliente(Pessoa id_pessoa_cliente) {
		this.id_pessoa_cliente = id_pessoa_cliente;
	}

	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}

	public Ponto getId_ponto() {
		return id_ponto;
	}

	public void setId_ponto(Ponto id_ponto) {
		this.id_ponto = id_ponto;
	}

	public int getPontuacaoMinima() {
		return pontuacaoMinima;
	}

	public void setPontuacaoMinima(int pontuacaoMinima) {
		this.pontuacaoMinima = pontuacaoMinima;
	}

	public int getUnidadeporPonto() {
		return unidadeporPonto;
	}

	public void setUnidadeporPonto(int unidadeporPonto) {
		this.unidadeporPonto = unidadeporPonto;
	}

	public int getDiasValidade() {
		return diasValidade;
	}

	public void setDiasValidade(int diasValidade) {
		this.diasValidade = diasValidade;
	}

	public double getValordaUnidade() {
		return valordaUnidade;
	}

	public void setValordaUnidade(double valordaUnidade) {
		this.valordaUnidade = valordaUnidade;
	}

	public double getValorUnidadeTroca() {
		return valorUnidadeTroca;
	}

	public void setValorUnidadeTroca(double valorUnidadeTroca) {
		this.valorUnidadeTroca = valorUnidadeTroca;
	}

	public double getValorUnidadeDevolucao() {
		return valorUnidadeDevolucao;
	}

	public void setValorUnidadeDevolucao(double valorUnidadeDevolucao) {
		this.valorUnidadeDevolucao = valorUnidadeDevolucao;
	}

	public double getValoraPontuar() {
		return valoraPontuar;
	}

	public void setValoraPontuar(double valoraPontuar) {
		this.valoraPontuar = valoraPontuar;
	}

	

	public Enum_Aux_Tipo_Mov_Ponto getCreditaDebita() {
		return creditaDebita;
	}

	public void setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto creditaDebita) {
		this.creditaDebita = creditaDebita;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Enum_Aux_Status_Movimento_Ponto getEnum_Aux_Status_Movimento_Ponto() {
		return enum_Aux_Status_Movimento_Ponto;
	}

	public void setEnum_Aux_Status_Movimento_Ponto(Enum_Aux_Status_Movimento_Ponto enum_Aux_Status_Movimento_Ponto) {
		this.enum_Aux_Status_Movimento_Ponto = enum_Aux_Status_Movimento_Ponto;
	}

	@Override
	public String toString() {
		return "Ponto_Movimento [id=" + id + ", id_pessoa_associado=" + id_pessoa_associado + ", id_pessoa_cliente="
				+ id_pessoa_cliente + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_ponto=" + id_ponto
				+ ", pontuacaoMinima=" + pontuacaoMinima + ", unidadeporPonto=" + unidadeporPonto + ", diasValidade="
				+ diasValidade + ", valordaUnidade=" + valordaUnidade + ", valorUnidadeTroca=" + valorUnidadeTroca
				+ ", valorUnidadeDevolucao=" + valorUnidadeDevolucao + ", valoraPontuar=" + valoraPontuar
				+ ", pontosAtingidos=" + pontosAtingidos + ", creditaDebita=" + creditaDebita + ", validade=" + validade
				+ ", dataLancamento=" + dataLancamento + ", numDoc=" + numDoc + ", enum_Aux_Status_Movimento_Ponto="
				+ enum_Aux_Status_Movimento_Ponto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((creditaDebita == null) ? 0 : creditaDebita.hashCode());
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + diasValidade;
		result = prime * result
				+ ((enum_Aux_Status_Movimento_Ponto == null) ? 0 : enum_Aux_Status_Movimento_Ponto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_pessoa_associado == null) ? 0 : id_pessoa_associado.hashCode());
		result = prime * result + ((id_pessoa_cliente == null) ? 0 : id_pessoa_cliente.hashCode());
		result = prime * result + ((id_ponto == null) ? 0 : id_ponto.hashCode());
		result = prime * result + ((numDoc == null) ? 0 : numDoc.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pontosAtingidos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + pontuacaoMinima;
		result = prime * result + unidadeporPonto;
		result = prime * result + ((validade == null) ? 0 : validade.hashCode());
		temp = Double.doubleToLongBits(valorUnidadeDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnidadeTroca);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valoraPontuar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valordaUnidade);
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
		Ponto_Movimento other = (Ponto_Movimento) obj;
		if (creditaDebita != other.creditaDebita)
			return false;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (diasValidade != other.diasValidade)
			return false;
		if (enum_Aux_Status_Movimento_Ponto != other.enum_Aux_Status_Movimento_Ponto)
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
		if (id_pessoa_associado == null) {
			if (other.id_pessoa_associado != null)
				return false;
		} else if (!id_pessoa_associado.equals(other.id_pessoa_associado))
			return false;
		if (id_pessoa_cliente == null) {
			if (other.id_pessoa_cliente != null)
				return false;
		} else if (!id_pessoa_cliente.equals(other.id_pessoa_cliente))
			return false;
		if (id_ponto == null) {
			if (other.id_ponto != null)
				return false;
		} else if (!id_ponto.equals(other.id_ponto))
			return false;
		if (numDoc == null) {
			if (other.numDoc != null)
				return false;
		} else if (!numDoc.equals(other.numDoc))
			return false;
		if (Double.doubleToLongBits(pontosAtingidos) != Double.doubleToLongBits(other.pontosAtingidos))
			return false;
		if (pontuacaoMinima != other.pontuacaoMinima)
			return false;
		if (unidadeporPonto != other.unidadeporPonto)
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
			return false;
		if (Double.doubleToLongBits(valorUnidadeDevolucao) != Double.doubleToLongBits(other.valorUnidadeDevolucao))
			return false;
		if (Double.doubleToLongBits(valorUnidadeTroca) != Double.doubleToLongBits(other.valorUnidadeTroca))
			return false;
		if (Double.doubleToLongBits(valoraPontuar) != Double.doubleToLongBits(other.valoraPontuar))
			return false;
		if (Double.doubleToLongBits(valordaUnidade) != Double.doubleToLongBits(other.valordaUnidade))
			return false;
		return true;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}

	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}

	public String getCaminhodaImagem2() {
		return caminhodaImagem2;
	}

	public void setCaminhodaImagem2(String caminhodaImagem2) {
		this.caminhodaImagem2 = caminhodaImagem2;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public StreamedContent getFoto2() {
		return foto2;
	}

	public void setFoto2(StreamedContent foto2) {
		this.foto2 = foto2;
	}

	public String getTipodeImagem() {
		return tipodeImagem;
	}

	public void setTipodeImagem(String tipodeImagem) {
		this.tipodeImagem = tipodeImagem;
	}

	public String getCaminhoTemp() {
		return caminhoTemp;
	}

	public void setCaminhoTemp(String caminhoTemp) {
		this.caminhoTemp = caminhoTemp;
	}

	public double getPontosAtingidos() {
		return pontosAtingidos;
	}

	public void setPontosAtingidos(double pontosAtingidos) {
		this.pontosAtingidos = pontosAtingidos;
	}
}
