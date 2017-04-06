package br.com.projetabrasil.model.entities;

public enum Enum_Aux_Tipos_Objetos {
	PETS(/* descricao */"PETS", /* abreviacao */"Pts", /* labelDescricao */"Informe o nome carinhoso do seu Pet",
			/* labelCor */"Informe a Cor", /* id */0, /* renderPlaca */false, /* renderAno */false,
			/* renderModelo */false, /* renderCor */true, /* renderSachis */false, /* renderDtNascimento */true,
			/* renderEmai */false, /* labelEmai */"false"), 
	MOBILILIDADE(/* descricao */"MOBILIDADE", /* abreviacao */"MOB",
					/* labelDescricao */"Informe a descrição do veículo", /* labelCor */"Informe a Cor", /* id */1,
					/* renderPlaca */true, /* renderAno */true, /* renderModelo */true, /* renderCor */true,
					/* renderSachis */true, /* renderDtNascimento */false, /* renderEmai */false, /* labelEmai */""),

	ELETRONICOS(/* descricao */"ELETRONICOS", /* abreviacao */"Elt",
					/* labelDescricao */"Informe a descrição do seu Eletronico", /* labelCor */"Informe a Cor", /* id */2,
					/* renderPlaca */false, /* renderAno */false, /* renderModelo */false, /* renderCor */true,
					/* renderSachis */false, /* renderDtNascimento */false, /* renderEmai */true,
					/* labelEmai */"Informe o Emai do Celular"),
	OUTROS(/* descricao */"OUTROS", /* abreviacao */"Out",
			/* labelDescricao */"Informe a descrição do Objeto", /* labelCor */"Informe a Cor", /* id */2,
			/* renderPlaca */false, /* renderAno */false, /* renderModelo */false, /* renderCor */true,
			/* renderSachis */false, /* renderDtNascimento */false, /* renderEmai */true,
			/* labelEmai */"Informe o Emai do Celular");

	private String descricao;
	private String abreviacao;
	private String labelDescricao;
	private String labelCor;
	private int id;
	private boolean renderPlaca;
	private boolean renderAno;
	private boolean renderModelo;
	private boolean renderCor;
	private boolean renderSachis;
	private boolean renderDtNascimento;
	private boolean renderEmai;
	private String labelEmai;
	

	private Enum_Aux_Tipos_Objetos(String descricao, String abreviacao, String labelDescricao, String labelCor, int id,
			boolean renderPlaca, boolean renderAno, boolean renderModelo, boolean renderCor, boolean renderSachis,
			boolean renderDtNascimento, boolean renderEmai, String labelEmai) {
		this.descricao = descricao;
		this.abreviacao = abreviacao;
		this.labelDescricao = labelDescricao;
		this.labelCor = labelCor;
		this.id = id;
		this.renderPlaca = renderPlaca;
		this.renderAno = renderAno;
		this.renderModelo = renderModelo;
		this.renderCor = renderCor;
		this.renderSachis = renderSachis;
		this.renderDtNascimento = renderDtNascimento;
		this.renderEmai = renderEmai;
		this.labelEmai = labelEmai;

	}

	public String getDescricao() {
		return descricao;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public String getLabelDescricao() {
		return labelDescricao;
	}

	public String getLabelCor() {
		return labelCor;
	}

	public int getId() {
		return id;
	}

	public boolean isRenderPlaca() {
		return renderPlaca;
	}

	public boolean isRenderAno() {
		return renderAno;
	}

	public boolean isRenderModelo() {
		return renderModelo;
	}

	public boolean isRenderCor() {
		return renderCor;
	}

	public boolean isRenderSachis() {
		return renderSachis;
	}

	public boolean isRenderDtNascimento() {
		return renderDtNascimento;
	}

	public boolean isRenderEmai() {
		return renderEmai;
	}

	public String getLabelEmai() {
		return labelEmai;
	}

	@Override
	public String toString() {
		return "descricao = " + descricao + "abreviacao = " + abreviacao + "labelDescricao = " + labelDescricao
				+ "labelCor = " + labelCor + "id = " + id + "renderPlaca = " + renderPlaca + "renderAno = " + renderAno
				+ "renderModelo = " + renderModelo + "renderCor = " + renderCor + "renderSachis = " + renderSachis
				+ "renderDtNascimento = " + renderDtNascimento + "renderEmai = " + renderEmai + "labelEmai = "
				+ labelEmai;
	}

}
