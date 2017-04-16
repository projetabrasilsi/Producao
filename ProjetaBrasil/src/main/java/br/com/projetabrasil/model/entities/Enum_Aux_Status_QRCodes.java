package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_QRCodes {
	LIVRES("LIVRES","Lvr",0),
	LOGISTICA("LOGISTICA","Lgt",1),
	DISTRIBUIDOS("DISTRIBUIDOS","dtb",2),
	REPRESENTADOS("REPRESENTADO","Rpd",3),
	REVENDIDOS("REVENDIDOS","Rvd",4),
	VENDIDOS("VENDIDOS","vnd",5);
	
	
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
