package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Credita_Debita {
	C("C","CREDITO",0),
	D("D","DEBITO",1);
	
	private String abrev;
	private String descricao;
	private int id;
	
	Enum_Aux_Credita_Debita(String abrev, String descricao, int id){
		this.abrev = abrev;
		this.descricao = descricao;
		this.id = id;
	}

	public String getAbrev() {
		return abrev;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}
	

}
