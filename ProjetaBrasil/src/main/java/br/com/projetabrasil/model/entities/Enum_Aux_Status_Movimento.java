package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_Movimento {
	ABERTO("ABERTO", 0, "Ab"), 
	APROVADO("FECHADO", 1, "Fc"), 
	CANCELADO("CANCELADO", 2, "Cc");

	private String descricao;
	private int id;
	private String abrev;

	Enum_Aux_Status_Movimento(String descricao, int id, String abrev) {
		this.descricao = descricao;
		this.id = id;
		this.abrev = abrev;

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

}
