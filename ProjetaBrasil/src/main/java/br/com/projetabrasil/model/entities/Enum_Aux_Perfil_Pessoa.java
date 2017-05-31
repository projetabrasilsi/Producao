package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Perfil_Pessoa {
	
	ADMINISTRADORES("Administradores",0,true,"ADM",false,false,false),	
	ASSINANTES("Assinantes",1,true,"ASS",true,true,false), 
	CLIENTES("Clientes",2,false,"CLT",true,true,false),
	ATENDENTES("Atendentes",3,true,"ATE",true,true,false),
	REPRESENTANTES("Representantes",4,true,"REP",true,true,true),
	REVENDEDORES("Revendedores",5,true,"RVN",true,true,true),
	DISTRIBUIDORES("Distribuidores",6,true,"DTB",true,true,true),
	SUPERVISORES("Supervisores",7,true,"SUP",true,false,true),
	OUTROS("Outros",8,true,"OUT",false,false,false),
	VENDAS("Vendas",9,true,"VEN",true,false,false),
	LOGISTICA("Logistica",10,true,"LOG",true,false,false),
	FUNCIONARIOS("Funcionarios",11,true,"FUN",true,true,false),
	SINDICATOS("SINDICATOS",12,true,"SIN",false,false,true),
	
	VETERINARIOS("Veterinários",12,true,"VET",true,true,true),
	AGROEVETERINARIA("Agropecuaria e Clinica Veterinária",12,true,"AGROVET",true,true,false),
	PETSHOPECLINICAVETERINARIA("PetShop e Clinica Veterinária",12,true,"PETSHOPVET",true,true,false),
	AGROPECUARIA("Agropecuaria",12,true,"AGRO",true,true,false),
	CLINICAVETERINARIA("Clinica Veterinária",12,true,"CLINIVET",true,true,false),
	PETSHOP("PetShop",12,true,"PETSHOP",true,true,false);
	
	
	private String descricao;
	private int id;
	private boolean renderizaSenha;
	private String sigla;
	private boolean temPerfilMestre;
	private boolean possuiVinculo;
	private boolean possuiDescentes;
	
	Enum_Aux_Perfil_Pessoa(String descricao, int id, boolean renderizaSenha, 
			String sigla,boolean temPerfilMestre, boolean possuiVinculo,boolean possuiDescentes){
		this.id = id;
		this.descricao = descricao;		
		this.renderizaSenha = renderizaSenha;
		this.sigla = sigla;
		this.temPerfilMestre = temPerfilMestre;
		this.possuiVinculo = possuiVinculo;
		this.possuiDescentes = possuiDescentes;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public int getId() {
		return id;
	}

	public boolean isRenderizaSenha() {
		return renderizaSenha;
	}

	

	public String getSigla() {
		return sigla;
	}

	public boolean isTemPerfilMestre() {
		return temPerfilMestre;
	}

	public boolean isPossuiVinculo() {
		return possuiVinculo;
	}

	/**
	 * @return the possuiDescentes
	 */
	public boolean isPossuiDescentes() {
		return possuiDescentes;
	}

		

		
}
