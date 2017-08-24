package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_WebRobos {
	IPTUECOLETA("IPTU E COLETA",0),
	LUZ("LUZ",1),
	SANEAMENTO("SANEAMENTO",2),
	CONDOMINIO("CONDOMINIO",3);
	
	private String descricao;
	private int id;
	
	Enum_Aux_Tipo_WebRobos(String descricao, int id){
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
