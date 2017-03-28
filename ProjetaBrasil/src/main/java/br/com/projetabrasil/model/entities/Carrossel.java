package br.com.projetabrasil.model.entities;

import java.util.Date;

public class Carrossel {
	private  String empresa;
	private  String endImagem;
	private  String promocao;
	private  Date validade;
	private  double valor;
	private  String codigoPromocional;
	
	public  Carrossel(String empresa, String endImagem, String promocao, Date validade, double valor, String codigoPromocional){
		 this.empresa= empresa;
		 this.endImagem = endImagem;
		 this.promocao = promocao;
		 this.validade = validade;
		 this.valor = valor;
		 this.codigoPromocional = codigoPromocional;
	 }
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEndImagem() {
		return endImagem;
	}
	public void setEndImagem(String endImagem) {
		this.endImagem = endImagem;
	}
	public String getPromocao() {
		return promocao;
	}
	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}
	@Override
	public String toString() {
		return "Carrossel [empresa=" + empresa + ", endImagem=" + endImagem + ", promocao=" + promocao + ", validade="
				+ validade + ", valor=" + valor + ", codigoPromocional=" + codigoPromocional + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPromocional == null) ? 0 : codigoPromocional.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((endImagem == null) ? 0 : endImagem.hashCode());
		result = prime * result + ((promocao == null) ? 0 : promocao.hashCode());
		result = prime * result + ((validade == null) ? 0 : validade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Carrossel other = (Carrossel) obj;
		if (codigoPromocional == null) {
			if (other.codigoPromocional != null)
				return false;
		} else if (!codigoPromocional.equals(other.codigoPromocional))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (endImagem == null) {
			if (other.endImagem != null)
				return false;
		} else if (!endImagem.equals(other.endImagem))
			return false;
		if (promocao == null) {
			if (other.promocao != null)
				return false;
		} else if (!promocao.equals(other.promocao))
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getCodigoPromocional() {
		return codigoPromocional;
	}
	public void setCodigoPromocional(String codigoPromocional) {
		this.codigoPromocional = codigoPromocional;
	}

}
