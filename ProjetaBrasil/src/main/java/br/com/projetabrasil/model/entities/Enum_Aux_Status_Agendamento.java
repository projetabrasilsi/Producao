package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_Agendamento {
	AGENDADO("AGENDADO",0,"Agd","AGENDAR"),
	CANCELADO("CANCELADO",1,"Cld","INUTILIZAR"),
	UTILIZADO("UTILIZADO",2,"Uzd","UTILIZAR");
	
	private String descricao;
	private int id;
	private String abrev;
	private String acao;
	
	Enum_Aux_Status_Agendamento(String descricao, int id,String abrev, String acao){
		this.descricao = descricao;
		this.id = id;
		this.abrev = abrev;
		this.acao = acao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public String getAbrev() {
		return abrev;
	}

	public String getAcao() {
		return acao;
	}

}
