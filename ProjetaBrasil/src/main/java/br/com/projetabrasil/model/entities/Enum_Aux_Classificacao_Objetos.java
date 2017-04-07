package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Classificacao_Objetos {
	
	CAES("CAES",0,Enum_Aux_Tipos_Objetos.PETS),
	GATOS("GATOS",1,Enum_Aux_Tipos_Objetos.PETS),
	CAVALOS("CAVALOS",2,Enum_Aux_Tipos_Objetos.PETS),
	COELHOS("COELHOS",3,Enum_Aux_Tipos_Objetos.PETS),
	HAMSTERS("HAMSTER",4,Enum_Aux_Tipos_Objetos.PETS),
	BICILETAS("BICICLETAS",5,Enum_Aux_Tipos_Objetos.MOBILILIDADE),
	CARROS("CARROS",6,Enum_Aux_Tipos_Objetos.MOBILILIDADE),
	CAMINHOES("CAMINHOES",7,Enum_Aux_Tipos_Objetos.MOBILILIDADE),
	NAUTICOS("NAUTICOS",8,Enum_Aux_Tipos_Objetos.MOBILILIDADE),
	MOTOS("MOTOS",9,Enum_Aux_Tipos_Objetos.MOBILILIDADE),
	CELULARES("CELULARES",10,Enum_Aux_Tipos_Objetos.ELETRONICOS),
	TABLETS("TABLETS",11,Enum_Aux_Tipos_Objetos.ELETRONICOS),
	NOTEBOOKS("NOTEBOOKS",12,Enum_Aux_Tipos_Objetos.ELETRONICOS),
	BOLSAS("BOLSAS",13,Enum_Aux_Tipos_Objetos.OUTROS),
	MALAS("MALAS",14,Enum_Aux_Tipos_Objetos.OUTROS),
	RELOGIOS("RELOGIOS",15,Enum_Aux_Tipos_Objetos.OUTROS);
	
	private String descricao;
	private int id;
	private Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objetos;
	
	Enum_Aux_Classificacao_Objetos(String descricao, int id, Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objetos){
		this.descricao = descricao;
		this.id = id;
		this.enum_Aux_Tipos_Objetos = enum_Aux_Tipos_Objetos;
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

	public Enum_Aux_Tipos_Objetos getEnum_Aux_Tipos_Objetos() {
		return enum_Aux_Tipos_Objetos;
	}

	public void setEnum_Aux_Tipos_Objetos(Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objetos) {
		this.enum_Aux_Tipos_Objetos = enum_Aux_Tipos_Objetos;
	}

}
