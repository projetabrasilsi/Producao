package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_Relacionamento {
	
	PROPRIO("PROPRIO",0),
	ESPOSO("ESPOSO",1),
	ESPOSA("ESPOSA",2),
	ENTIADOS("ENTIADOS",3),
	TIOS("TIOS",4),
	AVOS("AVOS",5),
	FILHOS("FILHOS",6),
	PRIMOS("PRIMOS",7),
	AMIGOS("AMIGOS",8),
	CONHECIDOS("CONHECIDOS",9),
	VIZINHOS("VIZINHOS",10),
	OUTROS("OUTROS",11),
	CHEFE("CHEFE",12),
	COLEGATRABALHO("COLEGAS DE TRABALHO",12);
	
	private String descricao;
	private int id;
	Enum_Aux_Tipo_Relacionamento(String descricao, int id){
		this.descricao = descricao;
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public int getId() {
		return id;
	}
	
	
	

}
