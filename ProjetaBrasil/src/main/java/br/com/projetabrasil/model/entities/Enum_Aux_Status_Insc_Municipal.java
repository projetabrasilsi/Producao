package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Status_Insc_Municipal {
   AGUARDANDO("NAO PROCESSADO",0),
   BUSCANDO("BUSCANDO DAMS",1),
   DOWNLOAD("DOWNLOAD PDFS",2),
   FINALIZADO("FINALIZADO RELAÇÃO DE DAMS",2);
	
	
	private String descricao;
	private int id;
	
	Enum_Aux_Status_Insc_Municipal(String descricao, int id){
		
		this.descricao = descricao;
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
   
   
}
