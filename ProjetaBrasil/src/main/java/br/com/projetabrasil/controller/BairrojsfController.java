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
import br.com.projetabrasil.model.business.BairroBusiness;
import br.com.projetabrasil.model.dao.CidadeDAO;
import br.com.projetabrasil.model.dao.EstadoDAO;
import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class BairrojsfController extends GenericController implements Serializable {
	
	private Bairro bairro;
	private List<Bairro> bairros;
	private PessoaConfig pessoaConfig;
	private Estado estado;
	private Cidade cidade;
	private Pais pais;
	private List<Cidade> cidades;
	private List<Estado> estados;
	private List<Pais> paises;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {
		bairros = BairroBusiness.listar();
		cidades = new ArrayList<>();
		estados = new ArrayList<>();
		bairro = new Bairro(new Cidade(new Estado(new Pais())));
		listarPaises();
	}
	
	public void novo(ActionEvent event) {
		bairro = new Bairro(new Cidade(new Estado(new Pais())));
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	public void listarPaises(){
		PaisDAO pDAO = new PaisDAO();
		paises=pDAO.listar();
	}
	public void listarEstados(){
		EstadoDAO eDAO = new EstadoDAO();
		estados= eDAO.buscaEstadoPorPais(bairro.getCidade().getEstado().getPais());
	}
	public void listarCidades(){
		CidadeDAO cDAO = new CidadeDAO();
		cidades = cDAO.buscaCidadePeloEstado(bairro.getCidade().getEstado());
	}
	
	public void editar(ActionEvent event) {

		bairro = (Bairro) event.getComponent().getAttributes().get("registroAtual");
		if(bairro.getCidade()==null)
		bairro.setCidade(new Cidade());
		if(bairro.getCidade().getEstado() == null)
			bairro.getCidade().setEstado( new Estado());
		if (bairro.getCidade().getEstado().getPais() == null)
			bairro.getCidade().getEstado().setPais(new Pais());
		
		listarCidades();
		listarEstados();
		listarPaises();
		
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.bairro.setDescricao(Utilidades.formataString(this.bairro.getDescricao()));
		Bairro bairro2 = BairroBusiness.buscaBairroPeloNomeECidade(this.bairro.getDescricao(),this.bairro.getCidade());  //this.pais é diferente de pais
		if(bairro2 != null){
			if(bairro.getId()==null || !bairro.getId().equals(bairro2.getId()))
			mensagensDisparar("Bairro "+bairro.getDescricao()+", já está cadastrado para a cidade "+bairro.getCidade().getDescricao());
			Utilidades.abrirfecharDialogos("dialogoCadastro",false);
			return;
		}
		this.bairro.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.bairro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.bairro.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		BairroBusiness.merge(this.bairro);
		if(bairro.getId()==null)
			Utilidades.mensagensDisparar("Bairro cadastrado com Sucesso!");
		else
			Utilidades.mensagensDisparar("Bairro alterado com Sucesso!");
		
		
		
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

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
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

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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
