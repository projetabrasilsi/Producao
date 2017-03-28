package br.com.projetabrasil.model.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Enum_Aux_Tipo_Identificador {
	
	CPF("CPF",Enum_Aux_Tipo_Pessoa.FISICA,1,"999.999.999-99","Informe o CPF","CPF é obrigatório!!!"),
	CNPJ("CNPJ",Enum_Aux_Tipo_Pessoa.JURIDICA,2,"99.999/999-9999-99","Informe o CNPJ","CNPJ é obrigatório!!!"),
	TELEFONE("TELEFONE",Enum_Aux_Tipo_Pessoa.OUTROS,3,"(99) 99999-9999","Informe o Telefone","Telefone é obrigatório!!!"),
	EMAIL("EMAIL",Enum_Aux_Tipo_Pessoa.OUTROS,4,"","Informe o Email","Email é obrigatório!!!");
	
	
	
	@Enumerated ( EnumType.STRING )
	private String descricao;	
	private Enum_Aux_Tipo_Pessoa aux_tipo_pessoa;
	private String mask;
	private int id;
	private String label;
	private String mensagemObrigatoria;
	
	
	
	Enum_Aux_Tipo_Identificador(String descricao,Enum_Aux_Tipo_Pessoa aux_tipo_pessoa, 
		int id, String mask, String label, String mensagemObrigatoria){
		this.aux_tipo_pessoa = aux_tipo_pessoa;
		this.id = id;		
		this.descricao = descricao;
		this.mask = mask;
		this.label = label;
		this.mensagemObrigatoria=mensagemObrigatoria;
	}
	public int getId() {
		return this.id;
	}
	public Enum_Aux_Tipo_Pessoa getAux_tipo_pessoa() {
		return this.aux_tipo_pessoa;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getMask() {
		return mask;
	}
	public String getLabel() {
		return label;
	}
	public String getMensagemObrigatoria(){
		return mensagemObrigatoria;
	}
	
	
}
