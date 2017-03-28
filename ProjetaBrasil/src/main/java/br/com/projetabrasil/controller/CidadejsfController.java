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
import br.com.projetabrasil.model.dao.EstadoDAO;
import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadejsfController extends GenericController implements Serializable {
	
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;
	private List<Pais> paises;
	private PessoaConfig pessoaConfig;
	private Estado estado;
	private Pais pais;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {		
		cidades = CidadeBusiness.listar();
		 cidade = new Cidade();
		 estado = new Estado();
		 cidade.setEstado(estado);
		 estados = new ArrayList<>();
		 paises = new ArrayList<>();
		 
		 		 
		 pais = new Pais();
		 listarPaises();
	}
	public void listarPaises(){
		PaisDAO pDAO = new PaisDAO();
		setPaises(pDAO.retornaPaisesEmOrdemAlfabetica());
	}
	
	public void listarEstadosPeloPais(){
		EstadoDAO eDAO = new EstadoDAO();		
		setEstados(eDAO.buscaEstadoPorPais(getPais()));
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		cidade = new Cidade(new Estado());		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void editar(ActionEvent event) {

		Cidade c = (Cidade) event.getComponent().getAttributes().get("registroAtual");
		cidade = new Cidade();
		cidade = c;
		pais = new Pais();
		if(cidade.getEstado()!=null && cidade.getEstado().getPais()!=null )
		setPais(cidade.getEstado().getPais());
		
			
			
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.cidade.setDescricao(Utilidades.formataString(this.cidade.getDescricao()));
		
		
		
		Cidade cidade2 = CidadeBusiness.buscaCidade(this.cidade);
		// se cidade foi encontrada e o id da cidade cadastrada for igual a nulo --> indica que já existe uma cidade cadastrada
		// se cidade foi encontrada e o id da cidade cadastrada for diferente de nulo e se o id da cidade encontrada e o id da cidade 
		//cadastrada forem diferentes --> indica que já existe uma outra cidade cadastrada para este mesmo estado; 
		//se cidade foi encontrada e o id da cidade cadastrada for diferente de nulo e se o id da cidade encontrada e o id da cidade
		// cadastrada é o mesmo id --> então eu posso alterar normalmente. 
		//
		if(cidade2!=null){
		    if(getCidade().getId()==null || !getCidade().getId().equals(cidade2.getId()) )	
			mensagensDisparar("Cidade: "+getCidade().getDescricao()+", já está cadastrada para o estado: "+getCidade().getEstado().getDescricao());		    
			return;
		}	
		this.cidade.setUltimaAtualizacao(Utilidades.retornaCalendario());		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.cidade.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.cidade.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		
		CidadeBusiness.merge(this.cidade);
		if(getCidade().getId()!= null )
		mensagensDisparar("Cidade alterada com sucesso!!!");
		else
			mensagensDisparar("Cidade cadastrada com sucesso!!! ");
			
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
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
		listarEstadosPeloPais();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}	
	
	
}

