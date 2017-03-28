package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_Pagamento {
	AAPROVAR("AAPROVAR", 0, "Aguardando aprovação"), 
	ADEPOSITAR("ADEPOSITAR", 1, "Aguardando Deposito"), 
	DEPOSITADO("DEPOSITADO", 2, "Ocorreu o Pagamento"),
	CANCELADO("CANCELADO", 3, "Ocorreu o cancelamento");
	private String descricao;
	private int id;
	private String descricao2;

	Enum_Aux_Status_Pagamento(String descricao,int id, String descricao2) {
		this.descricao = descricao;
		this.id = id;
		this.descricao2 = descricao2;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao2() {
		return descricao2;
	}
}