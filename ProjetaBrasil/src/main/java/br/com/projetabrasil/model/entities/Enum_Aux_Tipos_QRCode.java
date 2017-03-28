package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipos_QRCode {
	QRPET("QRPET","QRCode para Pets",0,"file:///C:\\imagens\\QRCODE-NOVOS\\logo-acheipet.svg"),
	QRPHONE("QRPHONE","QRCode para Telefones",1,"file:///C:\\imagens\\QRCODE-NOVOS\\logo-acheiphone.svg"),
	QRCAR("QRCAR","QRCode para Carros",2,"file:///C:\\imagens\\QRCODE-NOVOS\\logo-acheicar.svg"),
	QROJBETICS("QROBJETICS","QRCode para Objetos Varias",3,"file:///C:\\imagens\\QRCODE-NOVOS\\logo-acheiobjects.svg");
	
	private String abrev;
	private String descricao;
	private int id;
	private String arquivo;
	
	 Enum_Aux_Tipos_QRCode(String abrev,  String descricao, int id, String arquivo){		
		this.abrev = abrev; 
		this.descricao = descricao; 
		this.id = id;
		this.arquivo = arquivo;		
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

	public String getArquivo() {
		return arquivo;
	}	

}


