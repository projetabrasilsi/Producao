package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

import br.com.projetabrasil.model.dao.Item_de_MovimentoDAO;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
@Entity
public class Item_de_Movimento extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_item_de_movimento",sequenceName="messounds_item_de_movimento", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_item_de_movimento")
	private Long id;
	@ManyToOne
	@JoinColumn(name="id_Pessoa_Registro", nullable=false)
	private Pessoa id_Pessoa_Registro;
	@ManyToOne
	@JoinColumn(name="id_Pessoa_Assinante", nullable = false)
	private Pessoa id_Pessoa_Assinante;
	@Column(name="descricao", length = 90, nullable = false)
	private String descricao;
	@Column(name="valordaUnidade", precision= 18, scale=4, nullable=false)
	private double valordaUnidade;
	@Column(name="referencia",length =20, nullable =false )
	private String referencia;
	@Enumerated(EnumType.STRING)
	@Column(name="Enum_Aux_Tipo_Item_de_Movimento")
	private Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="precoUnico")
	private Enum_Aux_Sim_ou_Nao isPrecoUnico;
	@Enumerated(EnumType.STRING)
	@Column(name="eAnual")
	private Enum_Aux_Sim_ou_Nao isAnual;
	
	
	@Column(name = "ponto", precision=18, scale=4 )
	double ponto;
	@Column(name= "ultimaReferencia")
	int ultimaReferencia;
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
	public Item_de_Movimento(){
		
	}
	 
	public Item_de_Movimento(Pessoa id_Pessoa_Registro, Pessoa id_Pessoa_Assinante, Enum_Aux_Sim_ou_Nao isPrecoUnico, 
			 Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento ){
		 
		 this.id_Pessoa_Registro= id_Pessoa_Registro;
		 this.id_Pessoa_Assinante = id_Pessoa_Assinante;
		 this.isPrecoUnico = isPrecoUnico;
		 this.enum_Aux_Tipo_Item_de_Movimento = enum_Aux_Tipo_Item_de_Movimento;
		 setId_Empresa(1);
		 setDescricao("");
		 setReferencia(enum_Aux_Tipo_Item_de_Movimento.getReferencia());		 
		 setUltimaAtualizacao(Utilidades.retornaCalendario());
		 setValordaUnidade(0);
		 setPonto(0d);
		 setCaminhodaImagem("");
		 setCaminhodaImagem2("");
		 setCaminhoTemp("");
		 Item_de_MovimentoDAO iDao = new Item_de_MovimentoDAO();
		 setUltimaReferencia(iDao.retornaUltimaReferencia(id_Pessoa_Assinante,enum_Aux_Tipo_Item_de_Movimento));
		 DecimalFormat df = new DecimalFormat("0000");
		 setReferencia(enum_Aux_Tipo_Item_de_Movimento.getReferencia()+df.format(ultimaReferencia));
		 setTipodeImagem(Utilidades.getTipoImagem());
		 
	     
		 
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

	public Pessoa getId_Pessoa_Assinante() {
		return id_Pessoa_Assinante;
	}

	public void setId_Pessoa_Assinante(Pessoa id_Pessoa_Assinante) {
		this.id_Pessoa_Assinante = id_Pessoa_Assinante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValordaUnidade() {
		return valordaUnidade;
	}

	public void setValordaUnidade(double valordaUnidade) {
		this.valordaUnidade = valordaUnidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Enum_Aux_Tipo_Item_de_Movimento getEnum_Aux_Tipo_Item_de_Movimento() {
		return enum_Aux_Tipo_Item_de_Movimento;
	}

	public void setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento) {
		this.enum_Aux_Tipo_Item_de_Movimento = enum_Aux_Tipo_Item_de_Movimento;
	}

	public Enum_Aux_Sim_ou_Nao getIsPrecoUnico() {
		return isPrecoUnico;
	}

	public void setIsPrecoUnico(Enum_Aux_Sim_ou_Nao isPrecoUnico) {
		this.isPrecoUnico = isPrecoUnico;
	}

	public Enum_Aux_Sim_ou_Nao getIsAnual() {
		return isAnual;
	}

	public void setIsAnual(Enum_Aux_Sim_ou_Nao isAnual) {
		this.isAnual = isAnual;
	}

	public double getPonto() {
		return ponto;
	}

	public void setPonto(double ponto) {
		this.ponto = ponto;
	}

	public int getUltimaReferencia() {
		return ultimaReferencia;
	}

	public void setUltimaReferencia(int ultimaReferencia) {
		this.ultimaReferencia = ultimaReferencia;
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

	@Override
	public String toString() {
		return "Item_de_Movimento [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Assinante="
				+ id_Pessoa_Assinante + ", descricao=" + descricao + ", valordaUnidade=" + valordaUnidade
				+ ", referencia=" + referencia + ", enum_Aux_Tipo_Item_de_Movimento=" + enum_Aux_Tipo_Item_de_Movimento
				+ ", isPrecoUnico=" + isPrecoUnico + ", isAnual=" + isAnual + ", ponto=" + ponto + ", ultimaReferencia="
				+ ultimaReferencia + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((enum_Aux_Tipo_Item_de_Movimento == null) ? 0 : enum_Aux_Tipo_Item_de_Movimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((isAnual == null) ? 0 : isAnual.hashCode());
		result = prime * result + ((isPrecoUnico == null) ? 0 : isPrecoUnico.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ponto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ultimaReferencia;
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
		Item_de_Movimento other = (Item_de_Movimento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (enum_Aux_Tipo_Item_de_Movimento != other.enum_Aux_Tipo_Item_de_Movimento)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Assinante == null) {
			if (other.id_Pessoa_Assinante != null)
				return false;
		} else if (!id_Pessoa_Assinante.equals(other.id_Pessoa_Assinante))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (isAnual != other.isAnual)
			return false;
		if (isPrecoUnico != other.isPrecoUnico)
			return false;
		if (Double.doubleToLongBits(ponto) != Double.doubleToLongBits(other.ponto))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (ultimaReferencia != other.ultimaReferencia)
			return false;
		if (Double.doubleToLongBits(valordaUnidade) != Double.doubleToLongBits(other.valordaUnidade))
			return false;
		return true;
	}
	
	
	
				 
	}
