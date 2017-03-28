package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_Pessoa {
	FISICA("FISICA",1,true), 
	JURIDICA("JURIDICA",2,true),
	OUTROS("OUTROS",3,false);
		
	private String descricao;
	private int id;
	private boolean selecionar;
			
	Enum_Aux_Tipo_Pessoa(String descricao, int id,boolean selecionar){
		this.descricao = descricao;
		this.id = id;
		this.selecionar = selecionar;
	}
	
	public String getDescricao() {
		return descricao;
	}	
	
	public int getId() {
		return id;
	}

	
	public boolean isSelecionar() {
		return selecionar;
	}
	
}
