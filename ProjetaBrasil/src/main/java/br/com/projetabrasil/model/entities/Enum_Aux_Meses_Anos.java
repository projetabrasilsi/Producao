package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Meses_Anos {
	JAN("JAN","Janeiro",1),
	FEV("FEV","Fevereiro",2),
	MAR("MAR","Março",3),
	ABR("ABR","Abril",4),
	MAI("MAI","Maio",5),
	JUN("JUN","Junho",6),
	JUL("JUL","Julho",7),
	AGO("AGO","Agosto",8),
	SET("SET","Setembro",9),
	OUT("OUT","Outubro",10),
	NOV("NOV","Novembro",11),
	DEZ("DEZ","Dezembro",12);
	
	private String descricao;
	private String extenso;
	private int id;
	
	Enum_Aux_Meses_Anos(String descricao, String extenso, int id ){
		setDescricao(descricao);
		setExtenso(extenso);
		setId(id);
		
	}
	
	public static Enum_Aux_Meses_Anos pegaAnoPeloCodigo(int id){
		if (id < 0)
			return null;
		for (Enum_Aux_Meses_Anos e : Enum_Aux_Meses_Anos.values()){
			if (e.id ==id)
				return e;
		}
		return null;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getExtenso() {
		return extenso;
	}

	public void setExtenso(String extenso) {
		this.extenso = extenso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
