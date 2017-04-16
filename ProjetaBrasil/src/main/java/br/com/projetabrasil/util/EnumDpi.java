package br.com.projetabrasil.util;

public enum EnumDpi {
 DPI300("Tamanho real de impressão",300),
 DPI275("275",275),
 DPI250("250",250),
 DPI225("225",255),
 DPI200("200",200),
 DPI175("175",175),
 DPI150("150",150),
 DPI072("Tamanho que se ve na tela",72);
 
 private String descricao;
 private int dpi;
 
 EnumDpi(String descricao, int dpi ){
	 this.descricao = descricao;
	 this.dpi = dpi;
 }

public String getDescricao() {
	return descricao;
}

public int getDpi() {
	return dpi;
}
 
 
	
}
