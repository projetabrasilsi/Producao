package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_Movimento_Ponto {
    CONFIRMADO("CONFIRMADO",0,"Ponto confimado"),
    ACONFIRMAR("ACONFIRMAR",1,"Ponto aguardando a confirmação"),
    REPROVADO("REPROVADO",2,"Ponto foi reprovado!!!");
	
	private String descricao;
	private int id;
	private String descricao2;
	
	private Enum_Aux_Status_Movimento_Ponto(String descricao, int id, String descricao2) {
		this.descricao = descricao;
		this.id = id;
		this.descricao2 = descricao2;
	}

	public String getDescricao2() {
		return descricao2;
	}

	

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}
    
}
