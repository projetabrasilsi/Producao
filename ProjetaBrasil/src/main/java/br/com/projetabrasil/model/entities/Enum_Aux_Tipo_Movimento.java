package br.com.projetabrasil.model.entities;


public enum Enum_Aux_Tipo_Movimento {
 CONTRATOS(0, "Contratos","CTRT"),
	SERVICOSADICIONAIS(1,"Serviços Adicionais","SADC");
	
	
	private int id;
	private String descricao;
	private String abrev;
	
	Enum_Aux_Tipo_Movimento(int id, String descricao, String abrev){		
		this.id = id;		
		this.descricao = descricao;
		this.abrev = abrev;
	}

	public String getAbrev() {
		return abrev;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
