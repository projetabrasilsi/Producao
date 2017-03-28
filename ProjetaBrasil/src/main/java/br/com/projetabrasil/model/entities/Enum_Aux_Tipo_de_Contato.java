package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_de_Contato {
	
	FIXO("FIXO",0),
	CELULAR("CELULAR",1),
	EMAIL("EMAIL",2);
	
	private String descricao;
	private int id;
	
	Enum_Aux_Tipo_de_Contato(String descricao, int id){
		this.id = id;
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}
	

}
