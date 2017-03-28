package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Dia_da_Semana {
	
	DOMINGO("DOMINGO",0,"Domingo","0"),
	SEGUNDA("SEGUNDA",1,"Segunda","1"),
	TERCA("TERCA",2,"Terça","2"),
	QUARTA("QUARTA",3,"Quarta","3"),
	QUINTA("QUINTA",4,"Quinta","4"),
	SEXTA("SEXTA",5,"Sexta","5"),
	SABADO("SABADO",6,"Sábado","6");
	
	private String Descricao;
	private int id;
	private String Extenso;
	private String diaSemana;
	private Enum_Aux_Dia_da_Semana(String descricao, int id, String extenso, String diaSemana) {
		Descricao = descricao;
		this.id = id;
		Extenso = extenso;
		this.diaSemana = diaSemana;
	}
	public String getDescricao() {
		return Descricao;
	}
	public int getId() {
		return id;
	}
	public String getExtenso() {
		return Extenso;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	
	public static Enum_Aux_Dia_da_Semana retornapeloId(int i){
		for (Enum_Aux_Dia_da_Semana e: Enum_Aux_Dia_da_Semana.values()) {
            if (i == e.id) return e;
        }
        throw new IllegalArgumentException("dia da semana inválido");
	}
	
	
	

}
