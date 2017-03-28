package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_de_Contato {
	
	FIXO("FIXO",0,"(99) - 9999 9999"),
	CELULAR("CELULAR",1,"(99) - 99999 9999"),
	EMAIL("EMAIL",2,"");
	
	private String descricao;
	private int id;
	private String mascara;
	
	Enum_Aux_Tipo_de_Contato(String descricao, int id, String mascara){
		this.id = id;
		this.descricao = descricao;
		this.mascara = mascara;
		
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
