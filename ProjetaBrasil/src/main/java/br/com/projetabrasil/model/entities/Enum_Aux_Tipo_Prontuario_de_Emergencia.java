package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_Prontuario_de_Emergencia {
	
	TIPOSANGUINEO("TIPO SANGUINEO",0),
	ALERGIAS("ALERGIAS",1),
	DOENCASPREVIAS("DOENCAS PREVIAS",2),
	MEDICACAOCONTROLADA("MEDICACAO CONTROLADA",3);
	
	private String descricao;
	private int id;
	
	Enum_Aux_Tipo_Prontuario_de_Emergencia(String descricao, int id) {
		this.descricao = descricao;
		this.id = id;
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
