package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_Mov_Ponto {
	C("CREDITO",0, "C","C","Green",true,"Pontuar",false),
	D("DEBITO",1, "D","D","Red",false,"Utilizar",true),
	E("ESTORNO",2, "E","D","black",false,"Estornar",true);
	
	private String descricao;
	private int id;
	private String abrev;
	private String debitaCredita;
	private String cor;
	private boolean visualizaValidade;
	private String acao;
	private boolean visualizadebito;
	
	Enum_Aux_Tipo_Mov_Ponto(String descricao, int id, String abrev, String debitaCredita,
			String cor, boolean visualizaValidade,String acao,boolean visualizadebito){
		this.descricao = descricao;
		this.id = id;
		this.abrev = abrev;
		this.debitaCredita = debitaCredita;
		this.cor = cor;
		this.visualizaValidade = visualizaValidade;
		this.acao = acao;
		this.visualizadebito = visualizadebito;
	}

	public String getDescricao() {
		return descricao;
	}
	public int getId() {
		return id;
	}

	public String getAbrev() {
		return abrev;
	}
	public String getDebitaCredita() {
		return debitaCredita;
	}

	/**
	 * @return the cor
	 */
	public String getCor() {
		return cor;
	}

	/**
	 * @return the visualizaValidade
	 */
	public boolean isVisualizaValidade() {
		return visualizaValidade;
	}

	/**
	 * @return the acao
	 */
	public String getAcao() {
		return acao;
	}

	/**
	 * @return the visualizadebito
	 */
	public boolean isVisualizadebito() {
		return visualizadebito;
	}
}
