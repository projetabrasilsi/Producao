package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Perfil_Pagina_Atual {
	PAGINAADMINISTRADORES("PAGINAADMINISTRADORES", "PADM",0,null,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,null,false,true,"pessoas.xhtml", "administradores" ),
	PAGINASUPERVISORES("PAGINASUPERVISORES","PSUP",1,null,Enum_Aux_Perfil_Pessoa.SUPERVISORES,null,false,true,"pessoas.xhtml", "supervisores"),
	PAGINAASSINANTES("PAGINAASSINANTES","PASS",2,null,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,false,true,"pessoas.xhtml", "assinantes"),
	PAGINACLIENTES("PAGINACLIENTES","PCLI",3,null,Enum_Aux_Perfil_Pessoa.CLIENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true ,"pessoas.xhtml", "clientes"),	
	PAGINAATENDENTES("PAGINAATENDENTES","PATE",4,null,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true,"pessoas.xhtml", "atendentes"),
	PAGINAREPRESENTANTES("PAGINAREPRESENTANTES","PREP",5,null,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,Enum_Aux_Perfil_Pessoa.SUPERVISORES,false,true,"pessoas.xhtml", "representantes"),
	PAGINAREVENDEDORES("PAGINAREVENDEDORES","PRVN",6,null,Enum_Aux_Perfil_Pessoa.REVENDEDORES,Enum_Aux_Perfil_Pessoa.ATENDENTES,false,true,"pessoas.xhtml", "revendedores"),
	PAGINADISTRIBUIDORES("PAGINADISTRIBUIDORES","PDTB",6,null,Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,false,true,"pessoas.xhtml", "distribuidores"),
	PAGINAVENDAS("PAGINAVENDAS","PVEN",8,null,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,false,true,"pessoas.xhtml", "vendas"),
	PAGINAAUTENTICACAO("PAGINAAUTENTICACAO","PAUT",9,null,Enum_Aux_Perfil_Pessoa.OUTROS ,Enum_Aux_Perfil_Pessoa.OUTROS,false,false,"autenticacao.xhtml", "autenticacao"),
	PAGINAPONTO("PAGINAPONTO","PPONTO",10,null,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto.xhtml", "Configuração de pontos"),
	PAGINAPONTUACAOC("PAGINAPONTUACAOC","PMOVPONT",11,null,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto_movimento.xhtml", "de pontuações de fidelizados - Creditar"),
	PAGINAPONTUACAOD("PAGINAPONTUACAOD","PMOVPONT",12,null,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto_movimento.xhtml","de pontuações de fidelizados - Debitar"),
	PAGINAPONTUACAOE("PAGINAPONTUACAOE","PMOVPONT",13,null,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"ponto_movimento.xhtml","de pontuações de fidelizados - Estornar"),
	PAGINAITEMDESERVICO("PAGINAITEMDESERVICO","PITEMDESERV",14,null,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"item_de_movimento.xhtml","itens de serviço"),
	PAGINAITEMDEPROMOCAO("PAGINAITEMDEPROMOCAO","PITEMDEPROM",15,null,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"item_de_movimento.xhtml","itens de promoção"),
	
	PAGINAVETERINARIOS("PAGINAVETERINARIOS", "PVETERINARIOS",21,null,Enum_Aux_Perfil_Pessoa.VETERINARIOS,null,false,true,"pessoas.xhtml", "veterinários"),
	PAGINAAGROEVETERINARIA("PAGINAAGROEVETERINARIA", "PAGROEVETERINARIA",22,null,null,null,false,true,"pessoas.xhtml", "agropecuaria e clinica veterinária"),
	PAGINAPETSHOPECLINICAVETERINARIA("PAGINAPETSHOPECLINICAVETERINARIA", "PPETSHOPECLINICAVETERINARIA",23,null,null,null,false,true,"pessoas.xhtml", "petshop e clinica veterinária"),
	PAGINAAGROPECUARIA("PAGINAAGROPECUARIA", "PAGROPECUARIA",24,null,null,null,false,true,"pessoas.xhtml", "agropecuárias"),
	PAGINACLINICAVETERINARIA("PAGINACLINICAVETERINARIA", "PCLINICAVETERINARIA",25,null,null,null,false,true,"pessoas.xhtml", "clinica veterinaria"),
	PAGINAPETSHOP("PAGINAPETSHOP", "PPETSHOP",26,null,null,null,false,true,"pessoas.xhtml", "petshop"),
	
	PAGINAPAISES("PAGINAPAISES", "PPAIS",16,null,null,null,false,true,"paises.xhtml", "países" ),			
	PAGINAESTADOS("PAGINAESTADOS", "PESTADO",17,null,null,null,false,true,"estados.xhtml", "estados" ),	
	PAGINACIDADES("PAGINACIDADES", "PCIDADE",18,null,null,null,false,true,"cidades.xhtml", "cidade" ),	
	PAGINABAIRROS("PAGINABAIRROS", "PBAIRROS",19,null,null,null,false,true,"bairros.xhtml", "bairros" ),	
	PAGINALOGRADOUROS("PAGINALOGRADOUROS", "PLOGRADOUROS",20,null,null,null,false,true,"logradouros.xhtml", "logradouros" ),	
		
	PAGINAGATOS("PAGINAGATOS", "PGATOS",21,Enum_Aux_Classificacao_Objetos.GATOS,null,null,false,true,"objetos.xhtml", "gatos" ),
	PAGINACAES("PAGINACAES", "PCAES",22,Enum_Aux_Classificacao_Objetos.CAES,null,null,false,true,"objetos.xhtml", "caes" ),
	PAGINACAVALOS("PAGINACAVALOS", "PCAVALOS",23,Enum_Aux_Classificacao_Objetos.CAVALOS,null,null,false,true,"objetos.xhtml", "cavalos" ),
	PAGINACOELHOS("PAGINACOELHOS", "PCOELHOS",24,Enum_Aux_Classificacao_Objetos.COELHOS,null,null,false,true,"objetos.xhtml", "coelhos" ),
	PAGINAHAMSTERS("PAGINAHAMSTERS", "PHAMSTERS",0,Enum_Aux_Classificacao_Objetos.HAMSTERS,null,null,false,true,"objetos.xhtml", "hamsters" ),
	PAGINACARROS("PAGINACARROS", "PCARROS",25,Enum_Aux_Classificacao_Objetos.CARROS,null,null,false,true,"objetos.xhtml", "carros" ),
	PAGINACAMINHOES("PAGINACAMINHOES", "PCAMINHOES",0,Enum_Aux_Classificacao_Objetos.CAMINHOES,null,null,false,true,"objetos.xhtml", "caminhões" ),
	PAGINAMOTOS("PAGINAMOTOS", "PMOTOS",26,Enum_Aux_Classificacao_Objetos.MOTOS,null,null,false,true,"objetos.xhtml", "motos" ),
	PAGINANAUTICOS("PAGINANAUTICOS", "PNAUTICOS",27,Enum_Aux_Classificacao_Objetos.NAUTICOS,null,null,false,true,"objetos.xhtml", "nauticos" ),
	PAGINACELULARES("PAGINACELULARES", "PCELULARES",28,Enum_Aux_Classificacao_Objetos.CELULARES,null,null,false,true,"objetos.xhtml", "celulares" ),
	PAGINANOTEBOOKS("PAGINANOTEBOOKS", "PNOTEBOOKS",29,Enum_Aux_Classificacao_Objetos.NOTEBOOKS,null,null,false,true,"objetos.xhtml", "notebooks" ),
	PAGINATABLETS("PAGINATABLETS", "PTABLETS",30,Enum_Aux_Classificacao_Objetos.TABLETS,null,null,false,true,"objetos.xhtml", "tablets" ),
	PAGINABOLSAS("PAGINABOLSAS", "PBOLSAS",31,Enum_Aux_Classificacao_Objetos.BOLSAS,null,null,false,true,"objetos.xhtml", "bolsas" ),
	PAGINARELOGIOS("PAGINARELOGIOS", "PRELOGIOS",32,Enum_Aux_Classificacao_Objetos.RELOGIOS,null,null,false,true,"objetos.xhtml", "relogios" ),
	
	PAGINAOUTROS("PAGINAOUTROS","POUTROS",33,null,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,true,"pessoas.xhtml","outros"),
	PAGINAINDEX("PAGINAINDEX","PINDEX",34,null,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,false,"index.xhtml", "página principal"),
	PAGINACOMBO("PAGINACOMBO","PCOMBO",35,null,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"combo.xhtml","combo"),
	PAGINABRINDE("PAGINABRINDE","PBRINDE",36,null,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"brinde.xhtml","brindes"),
	PAGINAMOVIMENTO("PAGINAMOVIMENTO","PMOVIMENTO",37,null,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,Enum_Aux_Perfil_Pessoa.SUPERVISORES ,false,true,"movimento.xhtml","Contratos"),
	PAGINAVOUCHER("PAGINAVOUCHER","PVOUCHER",38,null,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"voucher.xhtml","Vouchers"),
	PAGINAAGENDAMENTO("PAGINAAGENDAMENTO","PAGENDA",39,null,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"agendamento.xhtml","Vouchers"),
	PAGINAPAGAMENTO("PAGINAPAGAMENTO","PPAGTO",40,null,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"pagamento.xhtml","Pagamento PayPal"),
	PAGINAALFA("PAGINAALFATA","PALFA",41,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"alfapage.xhtml","Pagina Inicial"),
	PAGINAACHEI("PAGINAACHEI","PACHE",42,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"achei.xhtml","Pagina Achei"),
	PAGINATRANSFERENCIASQRCODE("PAGINATRANSFERENCIASQRCODE","PTRANS",43,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"transferenciaqrcode.xhtml","Pagina de transferencia de QRCode"),
	PAGINALOGISTICA("PAGINALOGISTICA","PLOG",44,null,Enum_Aux_Perfil_Pessoa.LOGISTICA,Enum_Aux_Perfil_Pessoa.LOGISTICA,false,true,"pessoas.xhtml", "logistica"),
	PAGINAFUNCIONARIOS("PAGINAFUNCIONARIOS","PFUNC",45,null,Enum_Aux_Perfil_Pessoa.FUNCIONARIOS,Enum_Aux_Perfil_Pessoa.REVENDEDORES,false,true,"pessoas.xhtml", "logistica"),
	PAGINAVINCULARQRCODE("PAGINAVINCULARQRCODE","PVINC",46,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"transferenciaqrcode.xhtml","QRCodes para vínculo"),
	PAGINAVINCULARQRCODEPESSOASESINDICATOS("PAGINAVINCULARQRCODEPESSOASESINDICATOS","PVINCPESSSIND",47,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"transferenciaqrcode.xhtml","QRCodes para vínculo de Pessoas aos Sindicatos"),
	PAGINAVINCULARQRCODEPESSOAS("PAGINAVINCULARQRCODEPESSOAS","PVINCPESS",48,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"transferenciaqrcode.xhtml","QRCodes para vínculo de Pessoas"),
	PAGINAPETSVENDIDOS("PAGINAPETSVENDIDOS","PPETSVENDIDOS",49,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"pets.xhtml","PETS vinculados"),
	PAGINAQRCODE("PAGINAQRCODE","PQRCODE",50,null,Enum_Aux_Perfil_Pessoa.OUTROS,Enum_Aux_Perfil_Pessoa.OUTROS ,true,true,"qrcode.xhtml","QRCodes vinculados");
	
    private String descricao;    
	private String sigla;
	private int id;
	private Enum_Aux_Classificacao_Objetos classificacaoObjeto;										   	
	private Enum_Aux_Perfil_Pessoa PerfilPessoa;
	private Enum_Aux_Perfil_Pessoa mestre;
	private boolean necessitadoAssinante;
	private boolean renderizaCadastrodeUsuarios;
	private String url;
	private String descricao2;
	
	Enum_Aux_Perfil_Pagina_Atual(String descricao, String sigla, int id, Enum_Aux_Classificacao_Objetos classificacaoObjeto, Enum_Aux_Perfil_Pessoa perfilPessoa, 
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
		this.classificacaoObjeto = classificacaoObjeto;
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
	public Enum_Aux_Classificacao_Objetos getClassificacaoObjeto() {
		return classificacaoObjeto;
	}
}