package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.BairroBusiness;
import br.com.projetabrasil.model.business.CidadeBusiness;
import br.com.projetabrasil.model.business.EnderecoBusiness;
import br.com.projetabrasil.model.business.EstadoBusiness;
import br.com.projetabrasil.model.business.LogradouroBusiness;
import br.com.projetabrasil.model.business.PaisBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness2;
import br.com.projetabrasil.model.business.PessoaGenericBusiness;
import br.com.projetabrasil.model.dao.BairroDAO;
import br.com.projetabrasil.model.dao.CidadeDAO;
import br.com.projetabrasil.model.dao.ContatoDAO;
import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.dao.EstadoDAO;
import br.com.projetabrasil.model.dao.LogradouroDAO;
import br.com.projetabrasil.model.dao.Objetos_AcessoDAO;
import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.ProfissaoDAO;
import br.com.projetabrasil.model.dao.Prontuario_de_EmergenciaDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Enum_Aux_Estados;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Logradouro;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Prontuario_de_Emergencia;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Relacionamento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_de_Contato;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipos_Objetos;
import br.com.projetabrasil.model.entities.Enum_Aux_Ufs;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.Objetos_Acesso;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Profissao;
import br.com.projetabrasil.model.entities.Prontuario_de_Emergencia;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.CepWebService;
import br.com.projetabrasil.util.Utilidades;
import br.com.projetabrasil.util.viacep.CEP;
import br.com.projetabrasil.util.viacep.ViaCEPClient;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoajsfController extends GenericController implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private PessoaConfig pessoaConfig;
	private Enum_Aux_Tipo_Pessoa enum_Aux_Tipo_Pessoa;
	private Usuario usuario;
	private Endereco endereco;
	private List<Pais> paises;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Bairro> bairros;
	private List<Logradouro> logradouros;
	private Bairro bairro;
	private Logradouro logradouro;
	private Pais pais;
	
	
	private String descricaoContato;
	private String descricaoProntuario;
	
	private Enum_Aux_Tipo_de_Contato tipoContato;
	private List<Enum_Aux_Tipo_de_Contato> listaTipoContato;

	private Enum_Aux_Tipo_Relacionamento tipoRelacionamento;
	private List<Enum_Aux_Tipo_Relacionamento> listaTipoRelacionamento;
	
	private Enum_Aux_Tipos_Objetos tipoObjeto;
	private List<Enum_Aux_Tipos_Objetos> listaTiposObjeto;

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;

	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	private boolean skip;
	private Enum_Aux_Tipo_Logradouro tipoLogradouro;
	private List<Enum_Aux_Tipo_Logradouro> listaTiposLogradouros;
	private List<Enum_Aux_Tipo_Prontuario_de_Emergencia> listaTiposProntuarioEmergencia;
	private Enum_Aux_Tipo_Prontuario_de_Emergencia tipoProntuarioEmergencia;
	private Contato contato;
	private List<Contato> listaContatos;
	private Prontuario_de_Emergencia prontuarioEmergencia;
	private List<Prontuario_de_Emergencia> listaProntuarioEmergencia;
	private Objetos_Acesso objeto;
	private List<Objetos_Acesso> listaObjeto;
	
	private List<Profissao> profissoes;
	private String profissaoBusca;
	
	private String cidadedePesquisa;
	private String logradourodePesquisa;
	private List<CEP> listaceps;
	private CEP cepPesq;
	
	private Enum_Aux_Estados estado_Enum;
	private List<Enum_Aux_Estados> estados_Enum;

	@PostConstruct
	public void listar() {
		tipoContato = Enum_Aux_Tipo_de_Contato.CELULAR;
		tipoLogradouro = Enum_Aux_Tipo_Logradouro.RUA;
		tipoRelacionamento = Enum_Aux_Tipo_Relacionamento.PROPRIO;
		tipoProntuarioEmergencia = Enum_Aux_Tipo_Prontuario_de_Emergencia.TIPOSANGUINEO;
		tipoObjeto = Enum_Aux_Tipos_Objetos.PETS;
		
		pais = new Pais();
		setPais(buscaPais("BRASIL", "BRL"));
		endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))), new Bairro(new Cidade(new Estado(pais))));
		endereco.getLogradouro().setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA);

		pessoas = PessoaBusiness.listar(perfilLogado);
		pessoa = new Pessoa();
		usuario = new Usuario();
		usuario.setPessoa(pessoa);
		paises = new ArrayList<>();
		estados = new ArrayList<>();
		cidades = new ArrayList<>();
		bairros = new ArrayList<>();
		logradouros = new ArrayList<>();
		listaProntuarioEmergencia = new ArrayList<>();
		listaContatos = new ArrayList<>();
		listaObjeto = new ArrayList<>();
		prontuarioEmergencia = new Prontuario_de_Emergencia();
		contato = new Contato();
		objeto = new Objetos_Acesso();
		profissoes = new ArrayList<>();
		estados_Enum = new ArrayList<>();
		profissaoBusca="";

		listarTiposdeLogradouro();
		listarTiposdeRelacionamento();
		listarTiposdeContato();
		listarTiposdeProntuario();
		listarTiposdeObjeto();
		listarEstadosEnum();

	}
	
	public void chamaDialogoPesquisaCep() {
		prontuarioEmergencia = new Prontuario_de_Emergencia();
		contato = new Contato();
		listaceps = new ArrayList<>();
		setLogradourodePesquisa("");
		setCidadedePesquisa("");
		cepPesq = new CEP();
		Utilidades.abrirfecharDialogos("dialogoPesqLogradouro", true);
	}
	
	public void consultaLogradouro() throws Exception {
		String uf,cidade,logradouro;
		if (this.cidadedePesquisa.length() <= 0 || this.logradourodePesquisa.length() <= 0) {
			Utilidades.mensagensDisparar("Informe a Cidade e/ou Logradouro");
			return;
		}

		CEP cp = new CEP();
		cp.setLocalidade(cidadedePesquisa);
		cp.setLogradouro(logradourodePesquisa);
		cp.setUf(estado_Enum.getAbrev().toUpperCase());
		cp.setCep("");
		cp.setIbge("");
		cp.setBairro("");
		cp.setComplemento("");
		uf = Utilidades.removerAcentos(cp.getUf());
		cidade =  Utilidades.removerAcentos(cp.getLocalidade());
		logradouro = Utilidades.removerAcentos(cp.getLogradouro());

		ViaCEPClient client = new ViaCEPClient();			
			listaceps =  client.getEnderecos(uf.toString(), cidade.toString(),logradouro.toString()); //client.getEnderecos(cp.getUf().toUpperCase(), cp.getLocalidade(), cp.getLogradouro());
	}
	public void setarEnderecoNovo(){
		pais = new Pais();
		setPais(PaisBusiness.VerificaPaisPadrao("BRASIL","BRL",perfilLogado));
		tipoLogradouro = Enum_Aux_Tipo_Logradouro.RUA;
		endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))), new Bairro(new Cidade(new Estado(pais))));
		
	}
	
	public void ConsultaCEP() {
		String cep;
		cep = this.endereco.getCep();	
		if(cep== null)
			cep = "";
		ViaCEPClient client = new ViaCEPClient();
		CEP cp;
		try {
			cp = client.getEndereco(cep);
			if(cp != null && cp.getLocalidade().length()>0  )
				setEndereco(fazChecagemDoCEPnaBasedeDados(cp));
			else{			
				setarEnderecoNovo();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selecionaCEPdaLista(ActionEvent evento) {
		setCepPesq((CEP) evento.getComponent().getAttributes().get("registroAtualCep"));
		Utilidades.abrirfecharDialogos("dialogoPesqLogradouro", false);		
		setEndereco(fazChecagemDoCEPnaBasedeDados(cepPesq));
	}

	public Endereco fazChecagemDoCEPnaBasedeDados(CEP cep) {
		// ao setar um endereço novo -- já verificamos se o pais padrão BRASIL está cadastrdo se não estiver cadastrado,
		// ocorre o merge na base de dados e o atributo geral pais é setado - e o atributo geral endereço também é 
		// criado e setado com pais e um tipo de logradouro padrão;
		cep.setBairro(Utilidades.removerAcentos(cep.getBairro().toUpperCase()));
		cep.setCep(Utilidades.retiraCaracteres(cep.getCep()));
		cep.setComplemento(Utilidades.removerAcentos(cep.getComplemento()));
		cep.setLocalidade(Utilidades.removerAcentos(cep.getLocalidade().toUpperCase()));
		cep.setLogradouro(Utilidades.removerAcentos(cep.getLogradouro()));
		if(cep.getTipo_Logradouro() !=null && cep.getTipo_Logradouro().length()>0)
		cep.setTipo_Logradouro(Utilidades.removerAcentos(cep.getTipo_Logradouro().toUpperCase()));
		else
		cep.setTipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA.getDescricao());
		
		
		
		setarEnderecoNovo();
		Estado e = new Estado();
		e = EstadoBusiness.buscaEstadoPelaSigla(cep.getUf());
		if (e == null) {
			e = new Estado();
			e.setSigla(cep.getUf());
			e.setDescricao(Enum_Aux_Estados.valueOf(cep.getUf()).getDescricao());
			e.setId_Empresa(1);
			e.setPais(pais);
			e.setSigla(cep.getUf());
			e.setId_Pessoa_Registro(Utilidades.retornaPessoa(perfilLogado));
			e.setUltimaAtualizacao(Utilidades.retornaCalendario());
			e = EstadoBusiness.merge(e);
		}

		Cidade c = new Cidade();
		c = CidadeBusiness.buscaCidadePeloNomeEEstado(e, cep.getLocalidade());
		if (c == null && cep.getLocalidade()!=null && cep.getLocalidade().length()>0) {
			c = new Cidade();
			ViaCEPClient client = new ViaCEPClient();
			CEP cepRetorno;			
			try {
				cepRetorno = client.getEndereco(cep.getCep());
				if (cepRetorno != null)
					c.setCep(Utilidades.retiraCaracteres(cepRetorno.getCep()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			c.setDescricao(cep.getLocalidade());
			c.setEstado(e);
			c.setId_Empresa(1);
			c.setId_Pessoa_Registro(Utilidades.retornaPessoa(perfilLogado));
			c.setUltimaAtualizacao(Utilidades.retornaCalendario());
			c = CidadeBusiness.merge(c);
		}

		Logradouro l = new Logradouro();
		String ll = cep.getLogradouro();
		if (ll == null) ll = "";
		l = LogradouroBusiness.buscaLogradouroPeloNomeECidade(ll, c);
		if (l == null)  {
			l = new Logradouro( new Cidade(new Estado(pais)));
			l.setCidade(c);
			l.setDescricao(ll);
			l.setId_Empresa(1);
			l.setId_Pessoa_Registro(Utilidades.retornaPessoa(perfilLogado));
			l.setUltimaAtualizacao(Utilidades.retornaCalendario());
			l = LogradouroBusiness.merge(l);
		}

		
		
		Bairro b = new Bairro();
		String bb = cep.getBairro();
		if(bb == null) bb = "";

		b = BairroBusiness.buscaBairroPeloNomeECidade(bb, c);
		if (b == null ) {
			b = new Bairro();
			b.setCidade(c);
			b.setDescricao(cep.getBairro());
			b.setId_Empresa(1);
			b.setId_Pessoa_Registro(Utilidades.retornaPessoa(perfilLogado));
			b.setUltimaAtualizacao(Utilidades.retornaCalendario());
			b = BairroBusiness.merge(b);
		}
		String tpl = cep.getTipo_Logradouro();
		if(tpl.length()>0){			
			endereco.getLogradouro().setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.valueOf(cep.getTipo_Logradouro()));
		}else
			endereco.getLogradouro().setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA);
		
		endereco.setBairro(b);
		endereco.setLogradouro(l);
		endereco.setComplemento("");
		endereco.setId_Empresa(1);
		endereco.setCep(cep.getCep());
		endereco.setPessoa(pessoa);
		endereco.setUltimaAtualizacao(Utilidades.retornaCalendario());
		listarBairros();
		listarLogradouros();
		return endereco;
	}


	public Pais buscaPais(String descricao, String sigla) {
		Pais p = PaisBusiness.buscaPaisPeloNome(descricao);
		if (p == null) {
			p = new Pais();
			p.setDescricao(descricao);
			p.setId_Empresa(1);
			Pessoa pes = new Pessoa();
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				pes = perfilLogado.getAssLogado();
			else
				pes = perfilLogado.getUsLogado().getPessoa();
			p.setId_Pessoa_Registro(pes);
			p.setSigla(sigla);
			p.setUltimaAtualizacao(Utilidades.retornaCalendario());
			p = PaisBusiness.merge(p);
		}
		return p;
	}

	public void listarPaises() {
		PaisDAO pDAO = new PaisDAO();
		paises = pDAO.listar();
	}

	public void listarEstados(Pais p) {
		EstadoDAO eDAO = new EstadoDAO();
		estados = eDAO.buscaEstadoPorPais(p);
	}
	public void listarEstadosEnum() {
		Enum_Aux_Estados[] listagem;
		listagem = Enum_Aux_Estados.values();
		estados_Enum = new ArrayList<Enum_Aux_Estados>();
		for (Enum_Aux_Estados i : listagem) {
			estados_Enum.add(i);
		}
	}

	public void listarCidades() {
		CidadeDAO cDAO = new CidadeDAO();
		cidades = cDAO.buscaCidadePeloEstado(endereco.getLogradouro().getCidade().getEstado());
	}

	public void listarBairrosELogradouros() {
		if (endereco.getBairro() != null && endereco.getBairro().getCidade() != null)
			listarBairros();

		if (endereco.getLogradouro() != null && endereco.getLogradouro().getCidade() != null)
			listarLogradouros();
	}

	public void listarBairros() {
		BairroDAO bDAO = new BairroDAO();
		if(endereco.getBairro()!=null && endereco.getBairro().getCidade()!=null)
		bairros = bDAO.listarBairroPelaCidade(endereco.getBairro().getCidade());
		else
			if(endereco.getLogradouro()!=null && endereco.getLogradouro().getCidade()!=null)
				bairros = bDAO.listarBairroPelaCidade(endereco.getLogradouro().getCidade());
				else
		bairros = new ArrayList<>();	
	}

	public void listarLogradouros() {
		LogradouroDAO lDAO = new LogradouroDAO();
		if(endereco.getLogradouro()!=null && endereco.getLogradouro().getCidade()!=null)
		logradouros = lDAO.listaLogradouroPelaCidade(endereco.getLogradouro().getCidade());
		else
			if(endereco.getBairro()!=null && endereco.getBairro().getCidade()!=null)
			logradouros = lDAO.listaLogradouroPelaCidade(endereco.getBairro().getCidade());
		else
			logradouros = new ArrayList<>();
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public void novo(ActionEvent event) {
		
		if(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES)){			
			perfilLogado.setAssLogado(new Pessoa_VinculoDAO().retornaVinculo_Mestre(perfilLogado.getUsLogado().getPessoa(),
					Enum_Aux_Perfil_Pessoa.ADMINISTRADORES).getId_pessoa_m());
		}
		
		perfilLogadoTemp = perfilLogado;
		pais = new Pais();
		setPais(buscaPais("BRASIL", "BRL"));
		pessoa = new Pessoa();
		endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))), new Bairro(new Cidade(new Estado(pais))));

		configurarPessoa();
		pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		endereco.getLogradouro().setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA);

		tipoLogradouro = Enum_Aux_Tipo_Logradouro.RUA;
		bairro = new Bairro();
		logradouro = new Logradouro();

		tipoContato = Enum_Aux_Tipo_de_Contato.CELULAR;
		tipoRelacionamento = Enum_Aux_Tipo_Relacionamento.PROPRIO;
		tipoObjeto = Enum_Aux_Tipos_Objetos.PETS;

		listaProntuarioEmergencia = new ArrayList<>();
		listaContatos = new ArrayList<>();
		listaObjeto = new ArrayList<>();
		prontuarioEmergencia = new Prontuario_de_Emergencia();
		objeto = new Objetos_Acesso();
		contato = new Contato();

		Utilidades.abrirfecharDialogos("dialogoIdentidade", true);
	}

	public void editar(ActionEvent event) {

		Pessoa p = (Pessoa) event.getComponent().getAttributes().get("registroAtual");

		this.pessoa = p;
		pais = new Pais();
		setPais(buscaPais("BRASIL", "BRL"));

		this.endereco = new Endereco(new Bairro(), new Cidade(), new Estado(pais));

		if (perfilLogado.getPaginaAtual().isRenderizaCadastrodeUsuarios()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario = usuarioDAO.retornaUsuarioPelaPessoa(pessoa);
			if (usuario == null)
				usuario = new Usuario();
		}
		configurarPessoa();
		contato = new Contato();
		contato.setId_Pessoa(pessoa);
		prontuarioEmergencia = new Prontuario_de_Emergencia();
		prontuarioEmergencia.setId_Pessoa(pessoa);
		objeto = new Objetos_Acesso();
		objeto.setId_Pessoa(pessoa);
		if (perfilLogado.getAssLogado() != null) {
			prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			contato.setId_Pessoa(perfilLogado.getAssLogado());
			objeto.setId_Pessoa(perfilLogado.getAssLogado());
		} else {
			prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			contato.setId_Pessoa(perfilLogado.getUsLogado().getPessoa());
			objeto.setId_Pessoa(perfilLogado.getUsLogado().getPessoa());
		}
		
		listarProntuarioEmergenciadaPessoa();
		listarContatosdaPessoa();
		listarObjetodaPessoa();
		setarEndereco("editar");
		Utilidades.abrirfecharDialogos("dialogoIdentidade", true);
		
	}

	public void listarTiposdeLogradouro() {
		Enum_Aux_Tipo_Logradouro[] listagem;
		listagem = Enum_Aux_Tipo_Logradouro.values();
		listaTiposLogradouros = new ArrayList<Enum_Aux_Tipo_Logradouro>();
		for (Enum_Aux_Tipo_Logradouro i : listagem) {
			listaTiposLogradouros.add(i);
		}
	}

	public void listarTiposdeRelacionamento() {
		Enum_Aux_Tipo_Relacionamento[] listagem;
		listagem = Enum_Aux_Tipo_Relacionamento.values();
		listaTipoRelacionamento = new ArrayList<Enum_Aux_Tipo_Relacionamento>();
		for (Enum_Aux_Tipo_Relacionamento i : listagem) {
			listaTipoRelacionamento.add(i);
		}
	}

	public void listarTiposdeContato() {
		Enum_Aux_Tipo_de_Contato[] listagem;
		listagem = Enum_Aux_Tipo_de_Contato.values();
		listaTipoContato = new ArrayList<Enum_Aux_Tipo_de_Contato>();
		for (Enum_Aux_Tipo_de_Contato i : listagem) {
			listaTipoContato.add(i);
		}
	}

	public void listarTiposdeProntuario() {
		Enum_Aux_Tipo_Prontuario_de_Emergencia[] listagem;
		listagem = Enum_Aux_Tipo_Prontuario_de_Emergencia.values();
		listaTiposProntuarioEmergencia = new ArrayList<Enum_Aux_Tipo_Prontuario_de_Emergencia>();
		for (Enum_Aux_Tipo_Prontuario_de_Emergencia i : listagem) {
			listaTiposProntuarioEmergencia.add(i);
		}
	}
	
	public void listarTiposdeObjeto() {
		Enum_Aux_Tipos_Objetos[] listagem;
		listagem = Enum_Aux_Tipos_Objetos.values();
		listaTiposObjeto = new ArrayList<Enum_Aux_Tipos_Objetos>();
		for(Enum_Aux_Tipos_Objetos i : listagem){
			listaTiposObjeto.add(i);
		}
	}

	public void listarContatosdaPessoa() {
		ContatoDAO cDAO = new ContatoDAO();
		listaContatos = cDAO.listardeContatosporPessoa(pessoa);
	}

	public void listarProntuarioEmergenciadaPessoa() {
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		listaProntuarioEmergencia = pDAO.listarProntuarioporPessoa(pessoa);

	}
	
	public void listarObjetodaPessoa() {
		Objetos_AcessoDAO oDAO = new Objetos_AcessoDAO();
		listaObjeto = oDAO.listarObjetoAcessoPorPessoa(pessoa);
	}

	public void setarEndereco(String acao) {
		pais = new Pais();
		setPais(buscaPais("BRASIL", "BRL"));
		endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))),
				new Bairro(new Cidade(new Estado(new Pais()))));
		Endereco endUsuario = new Endereco(new Logradouro(new Cidade(new Estado(pais))),
				new Bairro(new Cidade(new Estado(pais))));
		EnderecoDAO eDAO = new EnderecoDAO();

		Pessoa p;
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
			p = perfilLogado.getAssLogado();
		else
			p = perfilLogado.getUsLogado().getPessoa();
		// por padrão pegamos o endereço do usuário
		endUsuario = eDAO.buscaEnderecoPorPessoa(p);
		if (endUsuario == null)
			endUsuario = new Endereco(new Logradouro(new Cidade(new Estado(pais))),
					new Bairro(new Cidade(new Estado(pais))));

		if (acao == "validar") {
			PessoaDAO pDAO = new PessoaDAO();
			p = new Pessoa();
			p = pDAO.retornaPelaIdentificacao(pessoa.getIdentificador());
			if (p != null) {
				endereco = eDAO.buscaEnderecoPorPessoa(p);
				// se a pessoa existir mas não tiver endereço --> usa o padrão
				// do usuario - caso o endereço do usuário também exista
				if (endereco == null){
					endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))),
							new Bairro(new Cidade(new Estado(pais))));					
					if(endUsuario != null && endUsuario.getId() != null){
						endereco = endUsuario;
					}
				}
				
					
			} else
			// caso pessoa não exista mas endereço do usuário exista --> usa o
			// do usuário
			if (endUsuario != null)
				endereco = endUsuario;

		} else/* se não for validar será alterar */ {
			// então ação é de alterar e a pessoa existe... só fazer para o
			// endereço
			endereco = eDAO.buscaEnderecoPorPessoa(pessoa);
			// Pessoa existe mas não tem endereço e usuário tem endereço --->
			// usa o do usuario
			if (endereco == null){
				endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))),
						new Bairro(new Cidade(new Estado(pais))));					
				if(endUsuario != null && endUsuario.getId() != null){
					endereco = endUsuario;
				}
			}

		}

		// 1 - ação --> novo
		// 1 verifica cpf do cliente
		// 1.1 - Existe
		// verifica endereço
		// 1.1.1 - EXISTE ==> usar este endereço
		// 1.1.2 - NÃO EXISTE
		// 1.1.2.1 - ENDEREÇO DO USUÁRIO EXISTE -- USAR ENDEREÇO DO USUÁRIO
		// 1.1.2.2 - ENDEREÇO DO USUÁRIO NÃO EXISTE --- DEIXAR EM BRANCO
		// 1.2 - Não existe
		// 1.2.1 - ENDEREÇO DO USUÁRIO EXISTE ==> USAR ENDEREÇO DO USUÁRIO
		// 1.2.2 - ENDEREÇO DO USUÁRIO NÃO EXISTE ==> DEIXAR EM BRANCO.

		// 2 - AÇÃO -- ALTERAR(JÁ EXISTE O CPF)
		// verifica endereço
		// 2.1 - EXISTE ==> usar este endereço
		// 2.2 - NÃO EXISTE
		// 2.2.1 - ENDEREÇO DO USUÁRIO EXISTE -- USAR ENDEREÇO DO USUÁRIO
		// 2.2.2 - ENDEREÇO DO USUÁRIO NÃO EXISTE --- DEIXAR EM BRANCO
	}

	public void merge() {
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null
				&& (perfilLogado.getUsLogado() == null || perfilLogado.getUsLogado().getPessoa() == null
						|| perfilLogado.getUsLogado().getId() == null))
			pessoa.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

		usuario.setPessoa(pessoa);

		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, true, true, true))
			return;

		if (pessoa.getId() != null
				&& perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)) {
			Pessoa_Vinculo pVin = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
			pVin.setId_pessoa_d(pessoa);
			pVin = pVinDAO.retornaVinculo_Mestre(pessoa, Enum_Aux_Perfil_Pessoa.ATENDENTES);
			if (pVin != null && !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
				Pessoa p = pVin.getId_pessoa_m();
				mensagensDisparar(
						"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
				return;
			}
		}
		
		pessoa = PessoaGenericBusiness.merge(pessoa, usuario, perfilLogado, true);
		// Endereço MERGE------------
		if (pessoa != null) {
			this.endereco.setId_Empresa(0);
			this.endereco.setUltimaAtualizacao(Utilidades.retornaCalendario());
			this.endereco.setPessoa(pessoa);
			this.endereco.setId(null);
			EnderecoBusiness.merge(this.endereco);
		}
		
		mergeListaContato();
		mergeListaProntuarioEmergencia();
		mergeListaObjeto();

		listar();

		cancela();

	}

	public void setCEP() {
		String uf, est, cid, bai, log, tl, cep;
		cep = this.endereco.getLogradouro().getCidade().getCep();
		CepWebService cp = new CepWebService(cep);
		if (cp.getResultado() == 0) {
			this.endereco.getLogradouro().getCidade().setCep("");
			endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))),
					new Bairro(new Cidade(new Estado(pais))));
			endereco.setComplemento("");
			endereco.setNumero(0);
			return;

		}

		bai = Utilidades.formataString(cp.getBairro());
		log = Utilidades.formataString(cp.getLogradouro());
		cid = Utilidades.formataString(cp.getCidade());
		uf = Utilidades.formataString(cp.getEstado());
		tl = Utilidades.formataString(cp.getTipoLogradouro().toUpperCase());
		pais = new Pais();
		setPais(buscaPais("BRASIL", "BRL"));

		endereco.getLogradouro().getCidade().getEstado().setPais(pais);

		Enum_Aux_Ufs eN = Enum_Aux_Ufs.retornaEnum(pais.getDescricao());
		if (eN != null)
			est = eN.retornaExtenso(eN, uf);
		else
			est = "";

		Estado e = new Estado();
		Cidade c = new Cidade();
		Bairro b = new Bairro();
		Logradouro l = new Logradouro();

		e = EstadoBusiness.buscaEstadoPelaSigla(uf);

		if (e == null && uf.length() > 0) {
			e = new Estado();
			e.setDescricao(est);
			e.setSigla(uf);
			e.setPais(pais);
			e.setPais(endereco.getLogradouro().getCidade().getEstado().getPais());
			e.setUltimaAtualizacao(Utilidades.retornaCalendario());
			e.setId_Empresa(1);
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				e.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			else
				e.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			e = EstadoBusiness.merge(e);
		}

		c = CidadeBusiness.buscaCidadePeloNomeEEstado(e, cid);

		if (c == null && cid.length() > 0) {
			c = new Cidade();
			c.setDescricao(cid);
			c.setCep(cep);
			c.setEstado(e);
			c.setId_Empresa(1);
			c.setUltimaAtualizacao(Utilidades.retornaCalendario());
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				c.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			else
				c.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			c = CidadeBusiness.merge(c);
		}

		b = BairroBusiness.buscaBairroPeloNomeECidade(bai, c);
		if (b == null && bai.length() > 0) {
			b = new Bairro(c);
			b.setDescricao(bai);
			b.setId(null);
			b.setId_Empresa(1);

			b.setUltimaAtualizacao(Utilidades.retornaCalendario());
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				b.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			else
				b.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

			b = BairroBusiness.merge(b);
		}

		l = LogradouroBusiness.buscaLogradouroPeloNomeECidade(log, c);
		if (l == null && log.length() > 0) {
			l = new Logradouro();
			l.setDescricao(log);
			l.setId(null);
			l.setId_Empresa(1);
			l.setCidade(c);

			l.setUltimaAtualizacao(Utilidades.retornaCalendario());
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				l.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			else
				l.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			l = LogradouroBusiness.merge(l);
		}

		if (b == null)
			b = new Bairro(new Cidade(new Estado(pais)));
		if (l == null) {
			cep = endereco.getLogradouro().getCidade().getCep();			
			l = new Logradouro(new Cidade(new Estado(pais)));					
			l.setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA);
			l.setCidade(c);
			l.getCidade().setEstado(e);
			l.getCidade().setCep(cep);
		}

		if (tl != null && tl.length() > 0)
			l.setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.valueOf(tl));

		endereco.setBairro(b);
		endereco.setLogradouro(l);
		endereco.getLogradouro().getCidade().setCep(cep);

		if (endereco.getBairro() == null)
			endereco.setBairro(new Bairro(c));

		if (endereco.getLogradouro() == null)
			endereco.setLogradouro(new Logradouro(c));
		if (tl != null && tl.length() > 0)
			endereco.getLogradouro().setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.valueOf(tl));
		
		listarBairros();
		listarLogradouros();

	}

	public void cancela() {
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage", true);

		}
	}

	public void adicionarBairro(ActionEvent event) {
		this.bairro.setCidade(endereco.getLogradouro().getCidade());
		this.bairro.setId_Empresa(1);
		this.bairro.setUltimaAtualizacao(Utilidades.retornaCalendario());

		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
			this.bairro.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			this.bairro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

		setBairro(BairroBusiness.merge(this.bairro));

		listarBairros();
		
		Utilidades.abrirfecharDialogos("dialogoCadastroB", false);
	}

	public void novoBairro(ActionEvent event) {
		this.bairro = new Bairro();
		Utilidades.abrirfecharDialogos("dialogoCadastroB", true);
	}

	public void adicionarLogradouro(ActionEvent event) {
		this.logradouro.setCidade(endereco.getLogradouro().getCidade());
		this.logradouro.setId_Empresa(1);
		this.logradouro.setUltimaAtualizacao(Utilidades.retornaCalendario());
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
			this.logradouro.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			this.logradouro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

		System.out.println(this.logradouro.toString());
		LogradouroBusiness.merge(this.logradouro);
		
		listarLogradouros();
		
		Utilidades.abrirfecharDialogos("dialogoCadastroL", false);
	}

	public void novoLogradouro(ActionEvent event) {
		this.logradouro = new Logradouro();
		Utilidades.abrirfecharDialogos("dialogoCadastroL", true);
	}

	public void mudaLabel() {
		pessoaConfig.mudarLabels(pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa());
	}

	public void buscaPessoa() {
		/*
		 * o padrão aqui é física ou jurídica mas quando ele chama
		 * PessoasBusiness.buscaPessoa ele pode buscar perfil OUTROS
		 */
		pessoa.setCpf_Cnpj(Utilidades.retiraCaracteres(pessoa.getCpf_Cnpj()));
		pessoa.setIdentificador(pessoa.getCpf_Cnpj());
		Enum_Aux_Tipo_Identificador tipoIdent = pessoa.getEnum_Aux_Tipo_Identificador();
		usuario.setPessoa(pessoa);
		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, true, true, true))
			return;

		String identificador;
		identificador = pessoa.getIdentificador();
		pessoa = PessoaBusiness.buscaPessoa(pessoa);

		if (pessoa.getIdentificador() == null || pessoa.getIdentificador().length() <= 0) {
			pessoa.setIdentificador(identificador);
			pessoa.setCpf_Cnpj(identificador);
		} else {
			if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAATENDENTES)) {

				Pessoa_Vinculo pVin = new Pessoa_Vinculo();
				Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
				pVin = pVinDAO.retornaVinculo_Mestre(pessoa, Enum_Aux_Perfil_Pessoa.ATENDENTES);
				if (pVin != null
						&& !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
					Pessoa p = pVin.getId_pessoa_m();
					mensagensDisparar(
							"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
					return;
				}

			}
			//SOMENTE LISTA SE PESSOA EXISTIR
			listarProntuarioEmergenciadaPessoa();
			listarContatosdaPessoa();
			listarObjetodaPessoa();
		}
		if (pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa().equals(Enum_Aux_Tipo_Pessoa.OUTROS))
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		if (!pessoa.getEnum_Aux_Tipo_Identificador().equals(tipoIdent))
			pessoa.setEnum_Aux_Tipo_Identificador(tipoIdent);
		
		setarEndereco("validar");
		listarLogradouros();
		listarBairros();

		mudaLabel();
		PessoaGenericBusiness.chamaDialogoCastro();
	}

	public void excluir(ActionEvent evento) {
		setPessoa(PessoaBusiness.registroAtualdaLista(evento));	
		PessoaBusiness.excluir(pessoa);
		
	}

	public void excluirContato(ActionEvent evento) {

		setContato((Contato) evento.getComponent().getAttributes().get("registroAtualContato"));
		ContatoDAO cDAO = new ContatoDAO();
		cDAO.excluir(getContato());
		listaContatos.remove(contato);
		

	}

	public void excluirProntuario(ActionEvent evento) {

		setProntuarioEmergencia((Prontuario_de_Emergencia) evento.getComponent().getAttributes().get("registroAtualProntuario"));
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		pDAO.excluir(getProntuarioEmergencia());
		listaProntuarioEmergencia.remove(prontuarioEmergencia);
		

	}
	
	public void excluirObjeto(ActionEvent evento) {
		
		setObjeto((Objetos_Acesso) evento.getComponent().getAttributes().get("registroAtualObjeto"));
		Objetos_AcessoDAO oDAO = new Objetos_AcessoDAO();
		oDAO.excluir(getObjeto());
		listaObjeto.remove(objeto);
		
	}

	public void incluirContatoNaLista() {
		contato = new Contato();
		contato.setId_Pessoa(pessoa);
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
			contato.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			contato.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		contato.setId_Empresa(1);
		contato.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//PREENCHER ATRIBUTOS À BAIXO
		contato.setTipoContato(this.tipoContato);
		contato.setTipoRelacionamento(this.tipoRelacionamento);
		contato.setContato(this.descricaoContato);
		
		listaContatos.add(contato);
		
		this.descricaoContato= "";

	}

	public void incluirProntuarioEmergenciaNaLista() {
		prontuarioEmergencia = new Prontuario_de_Emergencia();
		prontuarioEmergencia.setId_Pessoa(pessoa);
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
			prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		prontuarioEmergencia.setId_Empresa(1);
		prontuarioEmergencia.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//PREENCHER ATRIBUTOS À BAIXO
		prontuarioEmergencia.setTipo_Prontuario_Emergencia(this.tipoProntuarioEmergencia);
		prontuarioEmergencia.setDescricao(this.descricaoProntuario);		
		
		listaProntuarioEmergencia.add(prontuarioEmergencia);
		
	}
	
	public void incluirObjetoNaLista() {
		objeto = new Objetos_Acesso();
		objeto.setId_Pessoa(pessoa);
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
			objeto.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			objeto.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		objeto.setId_Empresa(1);
		objeto.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		objeto.setEnum_Aux_Tipos_Objetos(tipoObjeto);
		
		listaObjeto.add(objeto);
	}
	
	public void mergeListaContato() {
		for (Contato c : listaContatos) {
			if(c.getId_Pessoa()==null || c.getId_Pessoa().getId()==null)
				c.setId_Pessoa(pessoa);
			ContatoDAO cDAO = new ContatoDAO();
			cDAO.merge(c);
		}

	}

	public void mergeListaProntuarioEmergencia() {
		for (Prontuario_de_Emergencia c : listaProntuarioEmergencia) {
			if(c.getId_Pessoa()==null || c.getId_Pessoa().getId()==null)
				c.setId_Pessoa(pessoa);
			Prontuario_de_EmergenciaDAO cDAO = new Prontuario_de_EmergenciaDAO();
			cDAO.merge(c);
		}

	}
	
	public void mergeListaObjeto() {
		for (Objetos_Acesso o : listaObjeto) {
			if(o.getId_Pessoa()==null || o.getId_Pessoa().getId()==null)
				o.setId_Pessoa(pessoa);
			Objetos_AcessoDAO oDAO = new Objetos_AcessoDAO();
			oDAO.merge(o);
		}
		
	}
	
	public boolean renderizaSalvar(String tipoSalvar) {
		if(renderizaObjeto()){
			if(tipoSalvar.equals("SALVARCONTATO")){
				return false;
			}
			if(tipoSalvar.equals("SALVARPRONTUARIO")){
				return false;
			}
			if(tipoSalvar.equals("SALVAROBJETO")){
				return true;
			}
		}
		
		if(renderizaProntuario()){
			if(tipoSalvar.equals("SALVARCONTATO")){
				return false;
			}
			if(tipoSalvar.equals("SALVARPRONTUARIO")){
				return true;
			}
		}
		
		if(tipoSalvar.equals("SALVARCONTATO")){
			return true;
		}
		
		return false;
	}
	
	public boolean renderizaProntuario() {
		if(pessoa.getEnum_Aux_Tipo_Identificador().equals(Enum_Aux_Tipo_Identificador.CPF)){
			return true;
		}
		return false;
	}
	
	public boolean renderizaObjeto() {
		if(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.SUPERVISORES)){
			return true;
		}
		return false;
	}
	
	public boolean renderizaFoto() {
		if(pessoa.getEnum_Aux_Tipo_Identificador().equals(Enum_Aux_Tipo_Identificador.CPF)){
			return true;
		}
		return false;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public Enum_Aux_Tipo_Pessoa getEnum_Aux_Tipo_Pessoa() {
		return enum_Aux_Tipo_Pessoa;
	}

	public void setEnum_Aux_Tipo_Pessoa(Enum_Aux_Tipo_Pessoa enum_Aux_Tipo_Pessoa) {
		this.enum_Aux_Tipo_Pessoa = enum_Aux_Tipo_Pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the perfilLogado
	 */
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public PerfilLogado getPerfilLogadoTemp() {
		return perfilLogadoTemp;
	}

	public void setPerfilLogadoTemp(PerfilLogado perfilLogadoTemp) {
		this.perfilLogadoTemp = perfilLogadoTemp;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Enum_Aux_Tipo_de_Contato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(Enum_Aux_Tipo_de_Contato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public List<Enum_Aux_Tipo_de_Contato> getListaTipoContato() {
		return listaTipoContato;
	}

	public void setListaTipoContato(List<Enum_Aux_Tipo_de_Contato> listaTipoContato) {
		this.listaTipoContato = listaTipoContato;
	}

	public Enum_Aux_Tipo_Relacionamento getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public List<Enum_Aux_Tipo_Relacionamento> getListaTipoRelacionamento() {
		return listaTipoRelacionamento;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public void setTipoRelacionamento(Enum_Aux_Tipo_Relacionamento tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public void setListaTipoRelacionamento(List<Enum_Aux_Tipo_Relacionamento> listaTipoRelacionamento) {
		this.listaTipoRelacionamento = listaTipoRelacionamento;
	}

	public List<Enum_Aux_Tipo_Logradouro> getListaTiposLogradouros() {
		return listaTiposLogradouros;
	}

	public void setListaTiposLogradouros(List<Enum_Aux_Tipo_Logradouro> listaTiposLogradouros) {
		this.listaTiposLogradouros = listaTiposLogradouros;
	}

	public Enum_Aux_Tipo_Logradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(Enum_Aux_Tipo_Logradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public List<Enum_Aux_Tipo_Prontuario_de_Emergencia> getListaTiposProntuarioEmergencia() {
		return listaTiposProntuarioEmergencia;
	}

	public void setListaTiposProntuarioEmergencia(
			List<Enum_Aux_Tipo_Prontuario_de_Emergencia> listaTiposProntuarioEmergencia) {
		this.listaTiposProntuarioEmergencia = listaTiposProntuarioEmergencia;
	}

	public Enum_Aux_Tipo_Prontuario_de_Emergencia getTipoProntuarioEmergencia() {
		return tipoProntuarioEmergencia;
	}

	public void setTipoProntuarioEmergencia(Enum_Aux_Tipo_Prontuario_de_Emergencia tipoProntuarioEmergencia) {
		this.tipoProntuarioEmergencia = tipoProntuarioEmergencia;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public Prontuario_de_Emergencia getProntuarioEmergencia() {
		return prontuarioEmergencia;
	}

	public void setProntuarioEmergencia(Prontuario_de_Emergencia prontuarioEmergencia) {
		this.prontuarioEmergencia = prontuarioEmergencia;
	}

	public List<Prontuario_de_Emergencia> getListaProntuarioEmergencia() {
		return listaProntuarioEmergencia;
	}

	public void setListaProntuarioEmergencia(List<Prontuario_de_Emergencia> listaProntuarioEmergencia) {
		this.listaProntuarioEmergencia = listaProntuarioEmergencia;
	}

	public String getDescricaoContato() {
		return descricaoContato;
	}

	public void setDescricaoContato(String descricaoContato) {
		this.descricaoContato = descricaoContato;
	}

	public String getDescricaoProntuario() {
		return descricaoProntuario;
	}

	public void setDescricaoProntuario(String descricaoProntuario) {
		this.descricaoProntuario = descricaoProntuario;
	}
	
	public List<Profissao> getProfissoes() {
		return profissoes;
	}
	
	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public String getProfissaoBusca() {
		return profissaoBusca;
	}

	public void setProfissaoBusca(String profissaoBusca) {
		this.profissaoBusca = profissaoBusca;
	}

	public void listarProfissoesFiltradas(){
		ProfissaoDAO proDAO = new ProfissaoDAO();
		this.profissoes =  proDAO.listardeProfissoes(profissaoBusca);
	}

	public void defineProfissao(ActionEvent event){
		Profissao p = (Profissao) event.getComponent().getAttributes().get("registroProfissaoAtual");
		if(p==null){
			return;
		}else{
			this.pessoa.setId_Profissao(p);
			Utilidades.abrirfecharDialogos("dialogoProfissoes", false);
		}
	}

	public Enum_Aux_Tipos_Objetos getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(Enum_Aux_Tipos_Objetos tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public List<Enum_Aux_Tipos_Objetos> getListaTiposObjeto() {
		return listaTiposObjeto;
	}

	public void setListaTiposObjeto(List<Enum_Aux_Tipos_Objetos> listaTiposObjeto) {
		this.listaTiposObjeto = listaTiposObjeto;
	}

	public Objetos_Acesso getObjeto() {
		return objeto;
	}

	public void setObjeto(Objetos_Acesso objeto) {
		this.objeto = objeto;
	}

	public List<Objetos_Acesso> getListaObjeto() {
		return listaObjeto;
	}

	public void setListaObjeto(List<Objetos_Acesso> listaObjeto) {
		this.listaObjeto = listaObjeto;
	}

	public String getCidadedePesquisa() {
		return cidadedePesquisa;
	}

	public void setCidadedePesquisa(String cidadedePesquisa) {
		this.cidadedePesquisa = cidadedePesquisa;
	}

	public String getLogradourodePesquisa() {
		return logradourodePesquisa;
	}

	public void setLogradourodePesquisa(String logradourodePesquisa) {
		this.logradourodePesquisa = logradourodePesquisa;
	}

	public List<CEP> getListaceps() {
		return listaceps;
	}

	public void setListaceps(List<CEP> listaceps) {
		this.listaceps = listaceps;
	}

	public CEP getCepPesq() {
		return cepPesq;
	}

	public void setCepPesq(CEP cepPesq) {
		this.cepPesq = cepPesq;
	}

	public Enum_Aux_Estados getEstado_Enum() {
		return estado_Enum;
	}

	public void setEstado_Enum(Enum_Aux_Estados estado_Enum) {
		this.estado_Enum = estado_Enum;
	}

	public List<Enum_Aux_Estados> getEstados_Enum() {
		return estados_Enum;
	}

	public void setEstados_Enum(List<Enum_Aux_Estados> estados_Enum) {
		this.estados_Enum = estados_Enum;
	}

	
	

}
