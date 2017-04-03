package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Estados {
	AC("AC", "Acre",0),
	AL("AL", "Alagoas",1),
	AP("AP", "Amapa",2),
	AM("AM", "Amazonas",3),
	BA("BA", "Bahia",4),
	CE("CE", "Ceara",5),
	DF("DF", "Distrito Federal",6),
	ES("ES", "Espirito Santo",7),
	GO("GO", "Goias",8),
	MA("MA", "Maranhao",9),
	MT("MT", "Mato Grosso",10),
	MS("MS", "Mato Grosso do Sul",11),
	MG("MG", "Minas Gerais",12),
	PA("PA", "Para",13),
	PB("PB", "Paraiba",14),
	PR("PR", "Parana",15),
	PE("PE", "Pernanbuco",16),
	PI("PI", "Piaiu",17),
	RJ("RJ", "Rio de Janeiro",18),
	RN("RN", "Rio Grande do Norte",19),
	RS("RS", "Rio Grande do Sul",20),
	RO("RO", "Rondonia",21),
	RR("RR", "Roraima",22),
	SC("SC", "Santa Catarina",23),
	SP("SP", "Sao Paulo",24),
	SE("SE", "Sergipe",25),
	TO("TO", "Tocantins",26);
	
	private String abrev;
	private String descricao;
	private int id;
	
	Enum_Aux_Estados(String abrev, String descricao, int id){
		this.abrev = abrev;
		this.descricao = descricao;
		this.id = id;
	}
	
	public String retornaDescricao(String uf){
		return Enum_Aux_Estados.valueOf(uf).descricao;
		
	}

	public String getAbrev() {
		return abrev;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}
	
	

	
}
