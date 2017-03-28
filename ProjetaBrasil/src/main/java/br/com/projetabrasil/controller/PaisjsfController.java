package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.PaisBusiness;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PaisjsfController extends GenericController implements Serializable {
	
	private Pais pais;
	private List<Pais> paises;
	private PessoaConfig pessoaConfig;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {	
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
		paises = PaisBusiness.listar();
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		pais = new Pais();		
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.pais.setDescricao(Utilidades.formataString(this.pais.getDescricao()));
		Pais pais2 = PaisBusiness.buscaPaisPeloNome(this.pais.getDescricao());  //this.pais é diferente de pais
		if(this.pais.getId() != null){ // Se this.pais.getId() for igual à nulo isso significa que o usuário clicou em novo, caso contrário ele está realizando uma edição
			if(pais2 != null){
				if(!this.pais.getId().equals(pais2.getId())){
					mensagensDisparar("Tente novamente, pais já cadastrado: " + pais2.getDescricao());
					return;
				}
			}else{
				mensagensDisparar("Pais alterado com sucesso!!!");
			}
		}else{
			if(pais2 != null){
				this.pais.setId(pais2.getId());
				mensagensDisparar("Pais alterado com sucesso!!!");
			}else{
				mensagensDisparar("Pais cadastrado com sucesso!!!");
			}
		}
		
		this.pais.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.pais.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.pais.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		PaisBusiness.merge(this.pais);
		listar(); 
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

	}
	
	
	public void editar(ActionEvent event) {

		Pais p = (Pais) event.getComponent().getAttributes().get("registroAtual");
		pais = new Pais();
		pais = p;		
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
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
	
	
	
	
}