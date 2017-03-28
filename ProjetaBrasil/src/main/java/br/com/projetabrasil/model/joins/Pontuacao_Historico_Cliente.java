package br.com.projetabrasil.model.joins;

import br.com.projetabrasil.model.entities.Pessoa;

public class Pontuacao_Historico_Cliente {
	private Pessoa id_Assinante;
	private double pontos;
	public Pessoa getId_Assinante() {
		return id_Assinante;
	}
	public void setId_Assinante(Pessoa id_Assinante) {
		this.id_Assinante = id_Assinante;
	}
	public double getPontos() {
		return pontos;
	}
	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

}
