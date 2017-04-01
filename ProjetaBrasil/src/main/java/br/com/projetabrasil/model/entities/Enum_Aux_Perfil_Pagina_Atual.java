package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Perfil_Pagina_Atual {
	PAGINAADMINISTRADORES("PAGINAADMINISTRADORES", "PADM",0,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,null,false,true,"pessoas.xhtml", "administradores" ),
	PAGINASUPERVISORES("PAGINASUPERVISORES","PSUP",1,Enum_Aux_Perfil_Pessoa.SUPERVISORES,null,false,true,"pessoas.xhtml", "supervisores"),
	PAGINAASSINANTES("PAGINAASSINANTES","PASS",2,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,false,true,"pessoas.xhtml", "assinantes"),
	PAGINACLIENTES("PAGINACLIENTES","PCLI",3,Enum_Aux_Perfil_Pessoa.CLIENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true ,"pessoas.xhtml", "clientes"),	
	PAGINAATENDENTES("PAGINAATENDENTES","PATE",4,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true,"pessoas.xhtml", "atendentes"),
	PAGINAREPRESENTANTES("PAGINAREPRESENTANTES","PREP",5,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,Enum_Aux_Perfil_Pessoa.SUPERVISORES,false,true,"pessoas.xhtml", "representantes"),
	PAGINAVENDAS("PAGINAVENDAS","PVEN",6,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,false,true,"pessoas.xhtml", "vendas"),
	PAGINAAUTENTICACAO("PAGINAAUTENTICACAO","PAUT",7,Enum_Aux_Perfil_Pessoa.OUTROS ,Enum_Aux_Perfil_Pessoa.OUTROS,false,false,"autenticacao.xhtml", "autenticacao"),
	PAGINAPONTO("PAGINAPONTO","PPONTO",8, Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto.xhtml", "Configuração de pontos"),
	PAGINAPONTUACAOC("PAGINAPONTUACAOC","PMOVPONT",9, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto_movimento.xhtml", "de pontuações de fidelizados - Creditar"),
	PAGINAPONTUACAOD("PAGINAPONTUACAOD","PMOVPONT",10, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto_movimento.xhtml","de pontuações de fidelizados - Debitar"),
	PAGINAPONTUACAOE("PAGINAPONTUACAOE","PMOVPONT",11, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto_movimento.xhtml","de pontuações de fidelizados - Estornar"),
	PAGINAITEMDESERVICO("PAGINAITEMDESERVICO","PITEMDESERV",12,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"item_de_movimento.xhtml","itens de serviço"),
	PAGINAITEMDEPROMOCAO("PAGINAITEMDEPROMOCAO","PITEMDEPROM",13,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"item_de_movimento.xhtml","itens de promoção"),
	
	PAGINAPAISES("PAGINAPAISES", "PPAIS",0,null,null,false,true,"paises.xhtml", "países" ),			
	PAGINAESTADOS("PAGINAESTADOS", "PESTADO",0,null,null,false,true,"estados.xhtml", "estados" ),	
	PAGINACIDADES("PAGINACIDADES", "PCIDADE",0,null,null,false,true,"cidades.xhtml", "cidade" ),	
	PAGINABAIRROS("PAGINABAIRROS", "PBAIRROS",0,null,null,false,true,"bairros.xhtml", "bairros" ),	
	PAGINALOGRADOUROS("PAGINALOGRADOUROS", "PLOGRADOUROS",0,null,null,false,true,"logradouros.xhtml", "logradouros" ),	
	
	PAGINAOUTROS("PAGINAOUTROS","POUTROS",14,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,true,"pessoas.xhtml","outros"),
	PAGINAINDEX("PAGINAINDEX","PINDEX",15,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,false,"index.xhtml", "página principal"),
	PAGINACOMBO("PAGINACOMBO","PCOMBO",16,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"combo.xhtml","combo"),
	PAGINABRINDE("PAGINABRINDE","PBRINDE",17,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"brinde.xhtml","brindes"),
	PAGINAMOVIMENTO("PAGINAMOVIMENTO","PMOVIMENTO",18,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,Enum_Aux_Perfil_Pessoa.SUPERVISORES ,false,true,"movimento.xhtml","Contratos"),
	PAGINAVOUCHER("PAGINAVOUCHER","PVOUCHER",19,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"voucher.xhtml","Vouchers"),
	PAGINAAGENDAMENTO("PAGINAAGENDAMENTO","PAGENDA",20,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"agendamento.xhtml","Vouchers"),
	PAGINAPAGAMENTO("PAGINAPAGAMENTO","PPAGTO",21,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"pagamento.xhtml","Pagamento PayPal"),
	PAGINAALFA("PAGINAALFATA","PALFA",22,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"alfapage.xhtml","Pagina Inicial"),
	PAGINAACHEI("PAGINAACHEI","PACHE",23,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"achei.xhtml","Pagina Achei");
	
    private String descricao;    
	private String sigla;
	private int id;
	private Enum_Aux_Perfil_Pessoa PerfilPessoa;
	private Enum_Aux_Perfil_Pessoa mestre;
	private boolean necessitadoAssinante;
	private boolean renderizaCadastrodeUsuarios;
	private String url;
	private String descricao2;
	
	Enum_Aux_Perfil_Pagina_Atual(String descricao, String sigla, int id, Enum_Aux_Perfil_Pessoa perfilPessoa, 
			Enum_Aux_Perfil_Pessoa mestre, boolean necessitadoAssinante, boolean renderizaCadastrodeUsuarios, String url, String descricao2){
		this.descricao = descricao;
		this.sigla = sigla;
		this.id = id;
		this.mestre = mestre;
		this.necessitadoAssinante = necessitadoAssinante;
		this.PerfilPessoa = perfilPessoa;
		this.renderizaCadastrodeUsuarios = renderizaCadastrodeUsuarios;
		this.url = url;
		this.descricao2 = descricao2;
	}
	public String getSigla() {
		return sigla;
	}
	public int getId() {
		return id;
	}
	public Enum_Aux_Perfil_Pessoa getPerfilPessoa() {
		return PerfilPessoa;
	}
	public Enum_Aux_Perfil_Pessoa getMestre() {
		return mestre;
	}	
	public boolean isNecessitadoAssinante() {
		return necessitadoAssinante;
	}
	public boolean isRenderizaCadastrodeUsuarios() {
		return renderizaCadastrodeUsuarios;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getUrl() {
		return url;
	}
	public String getDescricao2() {
		return descricao2;
	}

	
}
