package br.com.projetabrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.projetabrasil.util.Utilidades;



@SuppressWarnings("serial")
@Entity
@Table(name="Ponto")
public class Ponto extends Item_de_Movimento implements Serializable{
	@Column(name="pontuacaoMinima")	
	private int pontuacaoMinima;
	@Column(name="unidadeporPonto")	
	private int unidadeporPonto;
	
	@Column(name="diasValidade")	
	private int diasValidade;
	
	
	@Column(name="valorUnidadeTroca",precision=18,scale=4)
	private double valorUnidadeTroca;
	/*obsoleto*/
	@Column(name="valorUnidadeDevolucao",precision=18,scale=4)
	private double valorUnidadeDevolucao;
	
	
	public Ponto(){
		
	}
	
	public Ponto(Pessoa Id_Pessoa_Registro, Pessoa Id_Pessoa_Assinante, Enum_Aux_Tipo_Item_de_Movimento
			enum_Aux_Tipo_Item_de_Movimento){
		
		setId_Pessoa_Registro(Id_Pessoa_Registro);
		setId_Pessoa_Assinante(Id_Pessoa_Assinante);
		setEnum_Aux_Tipo_Item_de_Movimento(enum_Aux_Tipo_Item_de_Movimento);
		setUltimaAtualizacao(Utilidades.retornaCalendario());
		setCaminhodaImagem("");
		setCaminhodaImagem2("");
		setCaminhoTemp("");
		setDescricao("");
		setDiasValidade(360);
		setId_Empresa(1);
		setIsAnual(Enum_Aux_Sim_ou_Nao.SIM);
		setIsPrecoUnico(Enum_Aux_Sim_ou_Nao.NAO);
		setPonto(1);
		setPontuacaoMinima(1);
		setReferencia("");
		setTipodeImagem(Utilidades.tipodeImagem());
		setUltimaReferencia(0);
		setUnidadeporPonto(1);
		setValordaUnidade(1);		
		setValorUnidadeDevolucao(2);
		setValorUnidadeTroca(1);
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
	@Override
	public String toString() {
		return "Ponto [pontuacaoMinima=" + pontuacaoMinima + ", unidadeporPonto=" + unidadeporPonto + ", diasValidade="
				+ diasValidade + ", valorUnidadeTroca=" + valorUnidadeTroca + ", valorUnidadeDevolucao="
				+ valorUnidadeDevolucao + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + diasValidade;
		result = prime * result + pontuacaoMinima;
		result = prime * result + unidadeporPonto;
		long temp;
		temp = Double.doubleToLongBits(valorUnidadeDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnidadeTroca);
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
		Ponto other = (Ponto) obj;
		if (diasValidade != other.diasValidade)
			return false;
		if (pontuacaoMinima != other.pontuacaoMinima)
			return false;
		if (unidadeporPonto != other.unidadeporPonto)
			return false;
		if (Double.doubleToLongBits(valorUnidadeDevolucao) != Double.doubleToLongBits(other.valorUnidadeDevolucao))
			return false;
		if (Double.doubleToLongBits(valorUnidadeTroca) != Double.doubleToLongBits(other.valorUnidadeTroca))
			return false;
		return true;
	}



	

				
}
	
	
	
	
	
