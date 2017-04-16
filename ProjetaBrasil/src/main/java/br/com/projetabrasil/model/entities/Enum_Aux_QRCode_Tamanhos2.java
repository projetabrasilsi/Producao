package br.com.projetabrasil.model.entities;

public enum Enum_Aux_QRCode_Tamanhos2 {           //QRCode          	Codigo      Tabela
	TAM70("QRCODE_TAM70", "Tabela com Tamanho 70"   ,80,80,-17,-3,		9,-17,8f,	70),
	TAM90("QRCODE_TAM90","Tabela com Tamanho 90"	,92,92,-27,-4,		9,-23,11f,	90),
	TAM110("QRCODE_TAM110","Tabela com Tamanho 110" ,104,104,-37,-5,	7,-30,14f,	110);
	
	private String abrev;
	private String descricao;
   
    
    private int qRCodeW;
    private int qRCodeH;
    private int qRCodePadL;
    private int qRCodePadT;
    
    
    
    
    private int paragrafoAppPadL;
    private int paragrafoAppPadT;
    private float paragrafoFZ;
    private int tabTam;
    
    Enum_Aux_QRCode_Tamanhos2(String abrev, String descricao, 	    
    	     									int qRCodeW, int qRCodeH, int qRCodePadL, int qRCodePadT,    	    
    	     									   	    
    	     									int paragrafoAppPadL, int paragrafoAppPadT, float paragrafoFZ,
    	     									int tabTam){
    	this.abrev = abrev;
    	this.descricao = descricao ;      	    
		    	    
		this.qRCodeW = qRCodeW ;  this.qRCodeH = qRCodeH;  this.qRCodePadL = qRCodePadL;  this.qRCodePadT = qRCodePadT ;   	    
		   	    
		this.paragrafoAppPadL = paragrafoAppPadL;  this.paragrafoAppPadT = paragrafoAppPadT; this.paragrafoFZ = paragrafoFZ;
		this.tabTam = tabTam;
    	
    }

	public String getAbrev() {
		return abrev;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getqRCodeW() {
		return qRCodeW;
	}

	public int getqRCodeH() {
		return qRCodeH;
	}

	public int getqRCodePadL() {
		return qRCodePadL;
	}

	public int getqRCodePadT() {
		return qRCodePadT;
	}

	public int getParagrafoAppPadL() {
		return paragrafoAppPadL;
	}

	public int getParagrafoAppPadT() {
		return paragrafoAppPadT;
	}

	public float getParagrafoFZ() {
		return paragrafoFZ;
	}

	public int getTabTam() {
		return tabTam;
	}

		
	
}