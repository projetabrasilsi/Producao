package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Cidades_Web_Scrapping {
	
	FLORIANOPOLIS("FLORIANOPOLIS",0),
	SAOJOSE("SAOJOSE",1),
	PALHOCA("PALHOCA",2),
    BIGUACU("BIGUACU",3);
	
	private String descricao;
	private int id;
	
	Enum_Aux_Cidades_Web_Scrapping(String descricao, int id){
		setDescricao(descricao);
		setId(id);		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
