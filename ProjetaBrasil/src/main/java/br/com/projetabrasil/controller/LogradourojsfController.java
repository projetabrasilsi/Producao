package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.CidadeBusiness;
import br.com.projetabrasil.model.business.EstadoBusiness;
import br.com.projetabrasil.model.business.LogradouroBusiness;
import br.com.projetabrasil.model.business.PaisBusiness;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Logradouro;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LogradourojsfController extends GenericController implements Serializable {
	
	private Logradouro logradouro;
	private List<Logradouro> logradouros;
	private PessoaConfig pessoaConfig;
	
	private List<Pais> paises;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	
	//private Cidade aQualPertence;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	
	private List<Enum_Aux_Tipo_Logradouro> lista;
	
	@PostConstruct
	public void listar() {
		logradouro = new Logradouro(new Cidade(new Estado(new Pais())));
		logradouros = LogradouroBusiness.listaLogradouros();
		paises = new ArrayList<>();
		estados = new ArrayList<>();
		cidades = new ArrayList<>();		
		paises = PaisBusiness.listar();
		
	}
	public void listarEstadosPeloPais(){
		estados = EstadoBusiness.buscaEstadoPeloPais(logradouro.getCidade().getEstado().getPais());
	}
	
	public void listarCidadesPeloEstado(){
		cidades = CidadeBusiness.listarPeloEstado(logradouro.getCidade().getEstado());
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		logradouro = new Logradouro(new Cidade(new Estado(new Pais())));	
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void editar(ActionEvent event) {

		Logradouro l = (Logradouro) event.getComponent().getAttributes().get("registroAtual");
		logradouro = new Logradouro(new Cidade(new Estado(new Pais())));
		logradouro = l;
		listarEstadosPeloPais();
		listarCidadesPeloEstado();
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.logradouro.setDescricao(Utilidades.formataString(this.logradouro.getDescricao()));
		
		Logradouro logradouro2 = LogradouroBusiness.buscaLogradouroPeloNomeECidade(this.logradouro.getDescricao(), this.logradouro.getCidade());  //this.pais é diferente de pais
		if(logradouro2 != null){
			if(this.logradouro.getId()==null || !logradouro2.getId().equals(this.logradouro.getId())){
			mensagensDisparar("Logradouro "+ logradouro2.getDescricao()+", já está cadastrado para a cidade " + logradouro2.getCidade().getDescricao());
			Utilidades.abrirfecharDialogos("dialogoCadastro",false);			
			}
			return;
		}
		
		
		this.logradouro.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.logradouro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.logradouro.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		LogradouroBusiness.merge(this.logradouro);
		if(this.logradouro.getId()==null)
			Utilidades.mensagensDisparar("Logradouro cadastrado com Sucessso!");
		else
			Utilidades.mensagensDisparar("Logradouro alterado com Sucessso!");
		logradouro = new Logradouro(new Cidade(new Estado(new Pais())));
		 
		
		listar(); 
		
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

	}
	
	
	public void cancela() {		
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage",true);				    			
		}
	}
	
	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public PerfilLogado getPerfilLogadoTemp() {
		return perfilLogadoTemp;
	}

	public void setPerfilLogadoTemp(PerfilLogado perfilLogadoTemp) {
		this.perfilLogadoTemp = perfilLogadoTemp;
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
	

	public List<Enum_Aux_Tipo_Logradouro> getLista() {
		return lista;
	}

	public void setLista(List<Enum_Aux_Tipo_Logradouro> lista) {
		this.lista = lista;
	}

	public List<Enum_Aux_Tipo_Logradouro> listarTiposLogradouros(){
		Enum_Aux_Tipo_Logradouro[] enums =  Enum_Aux_Tipo_Logradouro.values();
		
		lista = new ArrayList<Enum_Aux_Tipo_Logradouro>();
		
		for(int i=0; i < enums.length ; i++){
			lista.add(enums[i]);
		}
		
		return lista;
	}


	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
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
	
	
	
	
}
