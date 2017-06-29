package br.com.projetabrasil.controller.entitiesconfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import br.com.projetabrasil.model.entities.Enum_Aux_Sexo;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
public class PessoaConfig implements Serializable {	
	private String labelNome;
	private String labelFantasia;
	private String labelDataNascimento;
	private String labelcpf_Cnpj;
	private String labelrg_Insc;
	private String labelemail;
	private String labelfone_1;
	private String labelfone_2;
	private String labelfone_3;
	private String labelfone_4;
	private String labelfone_5;
	private String labelSexo;
	
	private String labelNomeRequiredMessage;
	private String labelFantasiaRequiredMessage;
	private String labelDataNascimentoRequiredMessage;
	private String labelcpf_CnpjRequiredMessage;
	private String labelrg_InscRequiredMessage;
	private String labelemailRequiredMessage;
	private String labelfone_1RequiredMessage;
	private String labelfone_2RequiredMessage;
	private String labelfone_3RequiredMessage;
	private String labelfone_4RequiredMessage;
	private String labelfone_5RequiredMessage;
	private String labelSexo_RequiredMessage;
	
	
	private boolean labelNomeRenderiza;
	private boolean labelFantasiaRenderiza;
	private boolean labelDataNascimentoRenderiza;
	private boolean labelcpf_CnpjRenderiza;
	private boolean labelrg_InscRenderiza;
	private boolean labelemailRenderiza;
	private boolean labelfone_1Renderiza;
	private boolean labelfone_2Renderiza;
	private boolean labelfone_3Renderiza;
	private boolean labelfone_4Renderiza;
	private boolean labelfone_5Renderiza;
	private boolean labelSexoRenderiza;
	
	private boolean labelNomeObrigatorio;
	private boolean labelFantasiaObrigatorio;
	private boolean labelDataNascimentoObrigatorio;
	private boolean labelcpf_CnpjObrigatorio;
	private boolean labelrg_InscObrigatorio;
	private boolean labelemailObrigatorio;
	private boolean labelfone_1Obrigatorio;
	private boolean labelfone_2Obrigatorio;
	private boolean labelfone_3Obrigatorio;
	private boolean labelfone_4Obrigatorio;
	private boolean labelfone_5Obrigatorio;
	private boolean labelSexoObrigatorio;
	private String maskTel;
	
	private Pessoa pessoa;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores;
	private List<Enum_Aux_Sexo > listaSexo;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;

	

	public PessoaConfig() {
		setMaskTel("(99) 99999 - 9999");
		listarTipodeIdentificadores();	
		listarSexo();
	}
	
	public void mudarLabels(Enum_Aux_Tipo_Pessoa tipoPessoa){
		
		if(tipoPessoa.equals(Enum_Aux_Tipo_Pessoa.FISICA) ){
			labelNome 							= "Nome" ;
			labelFantasia       				= "Apelido";
			labelDataNascimento 				= "Data Nascimento";
			labelcpf_Cnpj						= "CPF";
			labelrg_Insc						= "Identidade";	
			labelSexo							= "Sexo";
			labelSexoRenderiza 					= true;
			labelFantasiaRenderiza 				= false;
			labelDataNascimentoRenderiza 		= !labelFantasiaRenderiza;			
			labelFantasiaObrigatorio			= false;
			labelrg_InscObrigatorio				= false;
			labelSexoObrigatorio				= false;
			labelNomeRequiredMessage			= "Nome obrigatório";
			labelFantasiaRequiredMessage		= "Apelido obrigatório";
			labelDataNascimentoRequiredMessage	= "Data nascimento obrigatória!!!";
			labelcpf_CnpjRequiredMessage		= "CPF é obrigatório!!!";
			labelrg_InscRequiredMessage			= "RG é obrigatório!!!";
			labelSexo_RequiredMessage			= "Sexo é obrigatório!!!";
			labelcpf_CnpjRequiredMessage			= "CPF é obrigatório!!!";
			labelrg_InscRequiredMessage				= "RG é obrigatório!!!";
			labelfone_1Obrigatorio					= true;
			labelfone_2Obrigatorio					= false;
			labelfone_3Obrigatorio					= false;
			labelfone_4Obrigatorio					= false;
			labelfone_5Obrigatorio					= false;
			
		}else{
			labelNome 							= "Razão Social" ;
			labelFantasia       				= "Fantasia";
			labelDataNascimento 				= "Data Abertura";
			labelcpf_Cnpj						= "CNPJ";
			labelrg_Insc						= "Inscrição Estadual";
			labelFantasiaRenderiza 				= true;
			labelDataNascimentoRenderiza 		=!labelFantasiaRenderiza;
			labelFantasiaObrigatorio			= true;
			labelrg_InscObrigatorio				= false;
			labelSexoRenderiza 					= false;
			
			labelNomeRequiredMessage			= "Razão Social é obrigatória";
			labelFantasiaRequiredMessage		= "Nome Fantasia é obrigatória";
			labelDataNascimentoRequiredMessage	= "Data nascimento obrigatória!!!";
			labelcpf_CnpjRequiredMessage		= "CNPJ é obrigatório!!!";
			labelrg_InscRequiredMessage			= "Insc. Estadual é obrigatória!!!";
			labelfone_1Obrigatorio					= true;
			labelfone_2Obrigatorio					= true;
			labelfone_3Obrigatorio					= false;
			labelfone_4Obrigatorio					= false;
			labelfone_5Obrigatorio					= false;
			
		}
		
		
		labelemail  							= "Email";
		labelfone_1 							= "Contato - 1";
		labelfone_2 							= "Contato - 2";
		labelfone_3 							= "Contato - 3";
		labelfone_4 							= "Contato - 4";
		labelfone_5 							= "Contato - 5";
		
		
		labelemailRenderiza  					= true;
		labelfone_1Renderiza					= true;
		labelfone_2Renderiza					= true;
		labelfone_3Renderiza					= true;
		labelfone_4Renderiza					= true;
		labelfone_5Renderiza					= true;
		labelNomeRenderiza						= true;
		labelcpf_CnpjRenderiza					= true;
		labelrg_InscRenderiza					= true;
		
		
		labelNomeObrigatorio					= true;
		labelDataNascimentoObrigatorio			= false;
		labelcpf_CnpjObrigatorio				= true;		
		labelemailObrigatorio					= false;
		
		
		labelNomeRequiredMessage				= "Nome obrigatório";
		labelFantasiaRequiredMessage			= "Apelido obrigatório";
		labelDataNascimentoRequiredMessage		= "Data nascimento obrigatória!!!";
		
		
		labelemailRequiredMessage				= "Email é obrigatório!!!";
		labelfone_1RequiredMessage				= "Contato - 1 é obrigatório!!!";
		labelfone_2RequiredMessage				= "Contato - 2 é obrigatório!!!";
		labelfone_3RequiredMessage				= "Contato - 3 é obrigatório!!!";
		
	}
	
	public Pessoa ConfiguraPessoa(Enum_Aux_Tipo_Identificador tipodeIdentificador, Usuario usuarioLogado, Pessoa pessoa, boolean editado) {
		Pessoa p= ConfiguraPessoa(tipodeIdentificador,pessoa,editado);
		
		p.setId_Pessoa_Registro(usuarioLogado.getPessoa());
		
		return p;
	}
	
	public Pessoa ConfiguraPessoa(Enum_Aux_Tipo_Identificador tipodeIdentificador, Pessoa pessoa, boolean editado) {
		if (!editado)
		pessoa = new Pessoa();
		pessoa.setId_Empresa(1);		
		pessoa.setUltimaAtualizacao(Utilidades.retornaCalendario());
		if(tipodeIdentificador!=null)
		pessoa.setEnum_Aux_Tipo_Identificador(tipodeIdentificador);
		else
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		if (pessoa.getCpf_Cnpj()==null)
			pessoa.setCpf_Cnpj("");
		return pessoa;
	}
	public void listarTipodeIdentificadores(){		
		Enum_Aux_Tipo_Identificador[] identificadores;
		identificadores = Enum_Aux_Tipo_Identificador.values();
		listaTipodeIdentificadores = new ArrayList<Enum_Aux_Tipo_Identificador>();
		for (Enum_Aux_Tipo_Identificador identificador : identificadores) {
			if (identificador.getAux_tipo_pessoa().isSelecionar())
				listaTipodeIdentificadores.add(identificador);
		}
	}
	public void listarSexo(){		
		Enum_Aux_Sexo[] sexos;
		sexos = Enum_Aux_Sexo.values();
		listaSexo  = new ArrayList<Enum_Aux_Sexo>();
		for (Enum_Aux_Sexo sexo : sexos) {
			if (sexo.isPessoa_Fisica())
				listaSexo.add(sexo);
		}
	}

	public void setDados(Enum_Aux_Tipo_Identificador identificador) {
		if (pessoa.getId() == null) {
			pessoa.setEnum_Aux_Tipo_Identificador(identificador);
			pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			pessoa.setId_Empresa(1);
			pessoa.setIdentificador("");
			pessoa.setUltimaAtualizacao(Utilidades.retornaCalendario());
		}
	}
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
		
	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadores() {
		return listaTipodeIdentificadores;
	}
	
	public void setListaTipodeIdentificadores(List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores) {
		this.listaTipodeIdentificadores = listaTipodeIdentificadores;
	}
	public String getLabelNome() {
		return labelNome;
	}
	public String getLabelDataNascimento() {
		return labelDataNascimento;
	}
	public String getLabelcpf_Cnpj() {
		return labelcpf_Cnpj;
	}

	public String getLabelrg_Insc() {
		return labelrg_Insc;
	}
	public String getLabelemail() {
		return labelemail;
	}
	public String getLabelfone_1() {
		return labelfone_1;
	}
	public String getLabelfone_2() {
		return labelfone_2;
	}
	public String getLabelfone_3() {
		return labelfone_3;
	}
	public String getLabelfone_4() {
		return labelfone_4;
	}
	public String getLabelfone_5() {
		return labelfone_5;
	}
	public String getLabelFantasia() {
		return labelFantasia;
	}
	public boolean isLabelNomeRenderiza() {
		return labelNomeRenderiza;
	}
	public boolean isLabelFantasiaRenderiza() {
		return labelFantasiaRenderiza;
	}
	public boolean isLabelDataNascimentoRenderiza() {
		return labelDataNascimentoRenderiza;
	}
	public boolean isLabelcpf_CnpjRenderiza() {
		return labelcpf_CnpjRenderiza;
	}
	public boolean isLabelrg_InscRenderiza() {		
		return labelrg_InscRenderiza;
	}
	public boolean isLabelemailRenderiza() {
		return labelemailRenderiza;
	}
	public boolean isLabelfone_1Renderiza() {
		return labelfone_1Renderiza;
	}
	public boolean isLabelfone_2Renderiza() {
		return labelfone_2Renderiza;
	}
	public boolean isLabelfone_3Renderiza() {
		return labelfone_3Renderiza;
	}
	public boolean isLabelfone_4Renderiza() {
		return labelfone_4Renderiza;
	}
	public boolean isLabelfone_5Renderiza() {
		return labelfone_5Renderiza;
	}

	public boolean isLabelNomeObrigatorio() {
		return labelNomeObrigatorio;
	}
	public boolean isLabelFantasiaObrigatorio() {
		return labelFantasiaObrigatorio;
	}

	public boolean isLabelDataNascimentoObrigatorio() {
		return labelDataNascimentoObrigatorio;
	}
	public boolean isLabelcpf_CnpjObrigatorio() {
		return labelcpf_CnpjObrigatorio;
	}
	public boolean isLabelrg_InscObrigatorio() {
		return labelrg_InscObrigatorio;
	}

	public boolean isLabelemailObrigatorio() {
		return labelemailObrigatorio;
	}

	public boolean isLabelfone_1Obrigatorio() {
		return labelfone_1Obrigatorio;
	}
	public boolean isLabelfone_2Obrigatorio() {
		return labelfone_2Obrigatorio;
	}
	public boolean isLabelfone_3Obrigatorio() {
		return labelfone_3Obrigatorio;
	}
	public boolean isLabelfone_4Obrigatorio() {
		return labelfone_4Obrigatorio;
	}
	public boolean isLabelfone_5Obrigatorio() {
		return labelfone_5Obrigatorio;
	}

	public String getLabelNomeRequiredMessage() {
		return labelNomeRequiredMessage;
	}
	public String getLabelFantasiaRequiredMessage() {
		return labelFantasiaRequiredMessage;
	}
	public String getLabelDataNascimentoRequiredMessage() {
		return labelDataNascimentoRequiredMessage;
	}

	public String getLabelcpf_CnpjRequiredMessage() {
		return labelcpf_CnpjRequiredMessage;
	}
	public String getLabelrg_InscRequiredMessage() {
		return labelrg_InscRequiredMessage;
	}
	public String getLabelemailRequiredMessage() {
		return labelemailRequiredMessage;
	}

	public String getLabelfone_1RequiredMessage() {
		return labelfone_1RequiredMessage;
	}
	public String getLabelfone_2RequiredMessage() {
		return labelfone_2RequiredMessage;
	}

	public String getLabelfone_3RequiredMessage() {
		return labelfone_3RequiredMessage;
	}
	public String getLabelfone_4RequiredMessage() {
		return labelfone_4RequiredMessage;
	}
	public String getLabelfone_5RequiredMessage() {
		return labelfone_5RequiredMessage;
	}

	public String getMaskTel() {
		return maskTel;
	}
	private void setMaskTel(String maskTel) {
		this.maskTel = maskTel;
	}

	
	public String getLabelSexo_RequiredMessage() {
		return labelSexo_RequiredMessage;
	}

	public boolean isLabelSexoRenderiza() {
		return labelSexoRenderiza;
	}

	/**
	 * @return the labelSexo
	 */
	public String getLabelSexo() {
		return labelSexo;
	}

	/**
	 * @return the labelSexoObrigatorio
	 */
	public boolean isLabelSexoObrigatorio() {
		return labelSexoObrigatorio;
	}

	/**
	 * @return the listaSexo
	 */
	public List<Enum_Aux_Sexo> getListaSexo() {
		return listaSexo;
	}

	/**
	 * @return the perfilLogado
	 */
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

}
