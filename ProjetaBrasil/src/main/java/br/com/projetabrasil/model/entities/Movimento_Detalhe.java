package br.com.projetabrasil.model.entities;

import java.io.Serializable;

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

@SuppressWarnings("serial")
@Entity
public class Movimento_Detalhe extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name = "pk_movimento_detalhe", sequenceName = "mess_sounds_movimento_detalhe", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_movimento_detalhe")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;
	
	@ManyToOne
	@JoinColumn(name = "id_Pessoa_Assinante", nullable = false)
	private Pessoa id_Pessoa_Assinante;
	@ManyToOne
	@JoinColumn(name = "id_Movimento_Mestre", nullable = false)
	private Movimento_Mestre id_Movimento_Mestre;
	
	@ManyToOne
	@JoinColumn(name = "id_Combo_Mestre")
	private Combo_Mestre id_Combo_Mestre;
	
	@ManyToOne
	@JoinColumn(name = "id_Combo_Detalhe")
	private Combo_Detalhe id_Combo_Detalhe;
	@ManyToOne
	@JoinColumn(name = "id_Item_de_Movimento", nullable = false)
	private Item_de_Movimento id_Item_de_Movimento;
	@Enumerated(EnumType.STRING)
	@Column(name="cD", length=10,nullable=false )
	private Enum_Aux_Credita_Debita cD;

	@ManyToOne
	@JoinColumn(name = "id_ComboMestre")
	private Combo_Mestre id_ComboMestre;
	@Column(name = "referencia", length = 20, nullable = false)
	private String referencia;
	@Column(name = "descricao", length = 90, nullable = false)
	private String descricao;
	@Column(name = "valorUnidade", precision = 18, scale = 4, nullable = false)
	private double valorUnidade;
	@Column(name = "qtde", precision = 18, scale = 4, nullable = false)
	private double qtde;
	@Column(name = "total", precision = 18, scale = 4, nullable = false)
	private double total;
	@Column(name = "desconto", precision = 18, scale = 4, nullable = false)
	private double desconto;
	@Column(name = "totalLiquido", precision = 18, scale = 4, nullable = false)
	private double totalLiquido;
	

	@Override
	public String toString() {
		return "Movimento_Detalhe [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Assinante="
				+ id_Pessoa_Assinante + ", id_Movimento_Mestre=" + id_Movimento_Mestre + ", id_Combo_Mestre="
				+ id_Combo_Mestre + ", id_Combo_Detalhe=" + id_Combo_Detalhe + ", id_Item_de_Movimento="
				+ id_Item_de_Movimento + ", cD=" + cD + ", id_ComboMestre=" + id_ComboMestre + ", referencia="
				+ referencia + ", descricao=" + descricao + ", valorUnidade=" + valorUnidade + ", qtde=" + qtde
				+ ", total=" + total + ", desconto=" + desconto + ", totalLiquido=" + totalLiquido + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cD == null) ? 0 : cD.hashCode());
		long temp;
		temp = Double.doubleToLongBits(desconto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_ComboMestre == null) ? 0 : id_ComboMestre.hashCode());
		result = prime * result + ((id_Combo_Detalhe == null) ? 0 : id_Combo_Detalhe.hashCode());
		result = prime * result + ((id_Combo_Mestre == null) ? 0 : id_Combo_Mestre.hashCode());
		result = prime * result + ((id_Item_de_Movimento == null) ? 0 : id_Item_de_Movimento.hashCode());
		result = prime * result + ((id_Movimento_Mestre == null) ? 0 : id_Movimento_Mestre.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		temp = Double.doubleToLongBits(qtde);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalLiquido);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnidade);
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
		Movimento_Detalhe other = (Movimento_Detalhe) obj;
		if (cD != other.cD)
			return false;
		if (Double.doubleToLongBits(desconto) != Double.doubleToLongBits(other.desconto))
			return false;
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
		if (id_ComboMestre == null) {
			if (other.id_ComboMestre != null)
				return false;
		} else if (!id_ComboMestre.equals(other.id_ComboMestre))
			return false;
		if (id_Combo_Detalhe == null) {
			if (other.id_Combo_Detalhe != null)
				return false;
		} else if (!id_Combo_Detalhe.equals(other.id_Combo_Detalhe))
			return false;
		if (id_Combo_Mestre == null) {
			if (other.id_Combo_Mestre != null)
				return false;
		} else if (!id_Combo_Mestre.equals(other.id_Combo_Mestre))
			return false;
		if (id_Item_de_Movimento == null) {
			if (other.id_Item_de_Movimento != null)
				return false;
		} else if (!id_Item_de_Movimento.equals(other.id_Item_de_Movimento))
			return false;
		if (id_Movimento_Mestre == null) {
			if (other.id_Movimento_Mestre != null)
				return false;
		} else if (!id_Movimento_Mestre.equals(other.id_Movimento_Mestre))
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
		if (Double.doubleToLongBits(qtde) != Double.doubleToLongBits(other.qtde))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		if (Double.doubleToLongBits(totalLiquido) != Double.doubleToLongBits(other.totalLiquido))
			return false;
		if (Double.doubleToLongBits(valorUnidade) != Double.doubleToLongBits(other.valorUnidade))
			return false;
		return true;
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

	public Combo_Mestre getId_Combo_Mestre() {
		return id_Combo_Mestre;
	}

	public void setId_Combo_Mestre(Combo_Mestre id_Combo_Mestre) {
		this.id_Combo_Mestre = id_Combo_Mestre;
	}

	public Combo_Detalhe getId_Combo_Detalhe() {
		return id_Combo_Detalhe;
	}

	public void setId_Combo_Detalhe(Combo_Detalhe id_Combo_Detalhe) {
		this.id_Combo_Detalhe = id_Combo_Detalhe;
	}

	

	public Combo_Mestre getId_ComboMestre() {
		return id_ComboMestre;
	}

	public void setId_ComboMestre(Combo_Mestre id_ComboMestre) {
		this.id_ComboMestre = id_ComboMestre;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorUnidade() {
		return valorUnidade;
	}

	public void setValorUnidade(double valorUnidade) {
		this.valorUnidade = valorUnidade;
	}

	public double getQtde() {
		return qtde;
	}

	public void setQtde(double qtde) {
		this.qtde = qtde;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(double totalLiquido) {
		this.totalLiquido = totalLiquido;
	}

	public Enum_Aux_Credita_Debita getcD() {
		return cD;
	}

	public void setcD(Enum_Aux_Credita_Debita cD) {
		this.cD = cD;
	}

	public Movimento_Mestre getId_Movimento_Mestre() {
		return id_Movimento_Mestre;
	}

	public void setId_Movimento_Mestre(Movimento_Mestre id_Movimento_Mestre) {
		this.id_Movimento_Mestre = id_Movimento_Mestre;
	}

	public Item_de_Movimento getId_Item_de_Movimento() {
		return id_Item_de_Movimento;
	}

	public void setId_Item_de_Movimento(Item_de_Movimento id_Item_de_Movimento) {
		this.id_Item_de_Movimento = id_Item_de_Movimento;
	}

}
