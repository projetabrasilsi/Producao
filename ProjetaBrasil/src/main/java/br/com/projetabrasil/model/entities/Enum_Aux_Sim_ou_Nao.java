package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Sim_ou_Nao {
	SIM("S", true,0,"Sim" ),
	NAO("N", false,1,"Nao");
	
	private String  abrev;
	private boolean sN;
	private int id;
	private String descricao;
	
	Enum_Aux_Sim_ou_Nao(String abrev, boolean sN, int id,String descricao){
		this.abrev = abrev;
		this.sN = sN;
		this.id = id;
		this.descricao = descricao;
		
	}

	public boolean issN() {
		return sN;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getAbrev() {
		return abrev;
	}

}
