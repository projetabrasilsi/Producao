package br.com.projetabrasil.model.entities;

public enum Enum_Aux_QRCode_TamanhosArea {           //Logo         Site            QRCode          BaixeApp        Codigo      Tabela
	TAM60PX("QRCODE_AREA60", 60,5.5f),
	TAM65PX("QRCODE_AREA65", 65,5.5f),
	TAM70PX("QRCODE_AREA70", 70,5.5f),
	TAM72PX("QRCODE_AREA72", 72,5.5f),
	TAM73PX("QRCODE_AREA73", 73,5.5f),
	TAM74PX("QRCODE_AREA74", 74,5.5f),
	TAM75PX("QRCODE_AREA75", 75,5.5f),
	TAM76PX("QRCODE_AREA76", 76,5.5f),
	TAM77PX("QRCODE_AREA77", 77,5.5f),
	TAM78PX("QRCODE_AREA78", 78,5.5f),
	TAM79PX("QRCODE_AREA79", 79,5.5f),
	TAM80PX("QRCODE_AREA80", 80,5.5f),
	TAM85PX("QRCODE_AREA85", 85,5.5f),
	TAM90PX("QRCODE_AREA90", 90,5.5f),
	TAM95PX("QRCODE_AREA95", 95,5.5f),
	TAM100PX("QRCODE_AREA100", 100,5.5f),
	TAM105PX("QRCODE_AREA105", 105,5.5f),
	TAM110PX("QRCODE_AREA110", 110,5.5f);
	
	
	private String descricao;
    private int tamQrCodeArea;
    private float padding;
    
    
    
    Enum_Aux_QRCode_TamanhosArea(String descricao,int tamQrCodeArea, float padding){
    	
    	this.descricao = descricao ; 
    	
    	this.tamQrCodeArea = tamQrCodeArea;
    	this.padding = padding;
    }


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getTamQrCodeArea() {
		return tamQrCodeArea;
	}


	public void setTamQrCodeArea(int tamQrCodeArea) {
		this.tamQrCodeArea = tamQrCodeArea;
	}


	public float getPadding() {
		return padding;
	}


	public void setPadding(float padding) {
		this.padding = padding;
	}


			
	
}
