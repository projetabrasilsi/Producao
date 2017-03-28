package br.com.projetabrasil.model.entities;

public enum Enum_Aux_QRCode_Tamanhos {           //Logo         Site            QRCode          BaixeApp        Codigo      Tabela
	TAM70("QRCODE_TAM70", "Tabela com Tamanho 70",50,10,0,0, 	10,14,-10,-8, 	80,80,-17,-3,	10,11,-24,-9,	9,-17,8f,	70),
	TAM90("QRCODE_TAM90","Tabela com Tamanho 90",60,15,0,0, 	10,12,-25,-11, 	92,92,-27,-4,	10,13,-28,-14,	9,-23,11f,	90),
	TAM110("QRCODE_TAM110","Tabela com Tamanho 110",70,20,0,0, 	10,11,-40,-14, 	104,104,-37,-5,	10,14,-34,-18,	7,-30,14f,	110);
	
	private String abrev;
	private String descricao;
    private int logoW;
    private int logoH;
    private int logoPadL;
    private int logoPadT;
    
    private int siteW;
    private int siteH;
    private int sitePadL;
    private int sitePadT;
    
    private int qRCodeW;
    private int qRCodeH;
    private int qRCodePadL;
    private int qRCodePadT;
    
    private int baixeAppW;
    private int baixeAppH;
    private int baixeAppPadL;
    private int baixeAppPadT;
    
    
    private int paragrafoAppPadL;
    private int paragrafoAppPadT;
    private float paragrafoFZ;
    private int tabTam;
    
    Enum_Aux_QRCode_Tamanhos(String abrev, String descricao, int logoW, int logoH, int logoPadL, int logoPadT,    	    
    	     									int siteW, int siteH, int sitePadL, int sitePadT,    	    
    	     									int qRCodeW, int qRCodeH, int qRCodePadL, int qRCodePadT,    	    
    	     									int baixeAppW, int baixeAppH, int baixeAppPadL,	int baixeAppPadT,   	    
    	     									int paragrafoAppPadL, int paragrafoAppPadT, float paragrafoFZ,
    	     									int tabTam){
    	this.abrev = abrev;
    	this.descricao = descricao ;  this.logoW = logoW ;  this.logoH = logoH ;  this.logoPadL = logoPadL ;  this.logoPadT = logoPadT;    	    
		this.siteW = siteW ;  this.siteH = siteH ;  this.sitePadL = sitePadL ;  this.sitePadT = sitePadT ;    	    
		this.qRCodeW = qRCodeW ;  this.qRCodeH = qRCodeH;  this.qRCodePadL = qRCodePadL;  this.qRCodePadT = qRCodePadT ;    	    
		this.baixeAppW = baixeAppW ;  this.baixeAppH = baixeAppH ;  this.baixeAppPadL = baixeAppPadL;	this.baixeAppPadT = baixeAppPadT ;   	    
		this.paragrafoAppPadL = paragrafoAppPadL;  this.paragrafoAppPadT = paragrafoAppPadT; this.paragrafoFZ = paragrafoFZ;
		this.tabTam = tabTam;
    	
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getLogoW() {
		return logoW;
	}

	public void setLogoW(int logoW) {
		this.logoW = logoW;
	}

	public int getLogoH() {
		return logoH;
	}

	public void setLogoH(int logoH) {
		this.logoH = logoH;
	}

	public int getLogoPadL() {
		return logoPadL;
	}

	public void setLogoPadL(int logoPadL) {
		this.logoPadL = logoPadL;
	}

	public int getLogoPadT() {
		return logoPadT;
	}

	public void setLogoPadT(int logoPadT) {
		this.logoPadT = logoPadT;
	}

	public int getSiteW() {
		return siteW;
	}

	public void setSiteW(int siteW) {
		this.siteW = siteW;
	}

	public int getSiteH() {
		return siteH;
	}

	public void setSiteH(int siteH) {
		this.siteH = siteH;
	}

	public int getSitePadL() {
		return sitePadL;
	}

	public void setSitePadL(int sitePadL) {
		this.sitePadL = sitePadL;
	}

	public int getSitePadT() {
		return sitePadT;
	}

	public void setSitePadT(int sitePadT) {
		this.sitePadT = sitePadT;
	}

	public int getqRCodeW() {
		return qRCodeW;
	}

	public void setqRCodeW(int qRCodeW) {
		this.qRCodeW = qRCodeW;
	}

	public int getqRCodeH() {
		return qRCodeH;
	}

	public void setqRCodeH(int qRCodeH) {
		this.qRCodeH = qRCodeH;
	}

	public int getqRCodePadL() {
		return qRCodePadL;
	}

	public void setqRCodePadL(int qRCodePadL) {
		this.qRCodePadL = qRCodePadL;
	}

	public int getqRCodePadT() {
		return qRCodePadT;
	}

	public void setqRCodePadT(int qRCodePadT) {
		this.qRCodePadT = qRCodePadT;
	}

	public int getBaixeAppW() {
		return baixeAppW;
	}

	public void setBaixeAppW(int baixeAppW) {
		this.baixeAppW = baixeAppW;
	}

	public int getBaixeAppH() {
		return baixeAppH;
	}

	public void setBaixeAppH(int baixeAppH) {
		this.baixeAppH = baixeAppH;
	}

	public int getBaixeAppPadL() {
		return baixeAppPadL;
	}

	public void setBaixeAppPadL(int baixeAppPadL) {
		this.baixeAppPadL = baixeAppPadL;
	}

	public int getBaixeAppPadT() {
		return baixeAppPadT;
	}

	public void setBaixeAppPadT(int baixeAppPadT) {
		this.baixeAppPadT = baixeAppPadT;
	}

	public int getParagrafoAppPadL() {
		return paragrafoAppPadL;
	}

	public void setParagrafoAppPadL(int paragrafoAppPadL) {
		this.paragrafoAppPadL = paragrafoAppPadL;
	}

	public int getParagrafoAppPadT() {
		return paragrafoAppPadT;
	}

	public void setParagrafoAppPadT(int paragrafoAppPadT) {
		this.paragrafoAppPadT = paragrafoAppPadT;
	}

	public float getParagrafoFZ() {
		return paragrafoFZ;
	}

	public void setParagrafoFZ(float paragrafoFZ) {
		this.paragrafoFZ = paragrafoFZ;
	}

	public int getTabTam() {
		return tabTam;
	}

	public void setTabTam(int tabTam) {
		this.tabTam = tabTam;
	}

	public String getAbrev() {
		return abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
    
    
    
    
	
	
}