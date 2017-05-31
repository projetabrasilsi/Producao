package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_Pet {
	OK("Ok"),
	PERDIDO("Perdido"),
	FALECIDO("Falecido");
	
	private String descricao;
	
	Enum_Aux_Status_Pet(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
