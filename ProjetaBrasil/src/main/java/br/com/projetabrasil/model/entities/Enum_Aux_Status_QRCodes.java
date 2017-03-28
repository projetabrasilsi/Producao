package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_QRCodes {
	LIVRES("LIVRES","Lvr",0),
	REPRESENTADOS("REPRESENTADO","Rpd",1),
	ASSINADOS("ASSINADOS","Ass",2),
	REGISTRADOS("REGISTRADOS","Rgd",3),
	SUPERVISIONADOS("SUPERVISIONADOS","Spv",3);
	
	private String status;
	private String abreviatura;
	private int id;
	
	private Enum_Aux_Status_QRCodes(String status, String abreviatura,int id) {
		this.status = status;
		this.abreviatura = abreviatura;
		this.id = id;
		
	}

	public String getStatus() {
		return status;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public int getId() {
		return id;
	}
	

}
