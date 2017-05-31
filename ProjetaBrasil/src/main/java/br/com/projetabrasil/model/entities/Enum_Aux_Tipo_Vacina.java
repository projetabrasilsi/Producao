package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipo_Vacina {
	VACINA1("Vacina 1", 15, 5),
	VACINA2("Vacina 2", 30, 12);
	
	String descricao;
	int nDias, nDozes;
	
	Enum_Aux_Tipo_Vacina(String descricao, int nDias, int nDozes) {
		this.descricao = descricao;
		this.nDias = nDias;
		this.nDozes = nDozes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getnDias() {
		return nDias;
	}

	public void setnDias(int nDias) {
		this.nDias = nDias;
	}

	public int getnDozes() {
		return nDozes;
	}

	public void setnDozes(int nDozes) {
		this.nDozes = nDozes;
	}

	
}
