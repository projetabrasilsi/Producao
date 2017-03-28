package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.EstadoBusiness;
import br.com.projetabrasil.model.business.PaisBusiness;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadojsfController extends GenericController implements Serializable {
	
	private Estado estado;
	private List<Estado> estados;
	private List<Pais> paises;
	private PessoaConfig pessoaConfig;
	
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {
		
		estados = EstadoBusiness.listar();
		paises = PaisBusiness.listar();
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		estado = new Estado();		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void editar(ActionEvent event) {
       try{
		estado = (Estado) event.getComponent().getAttributes().get("registroAtual");
		
		
		
				
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
       }catch(RuntimeException erro){
    	   Messages.addFlashGlobalError("Ocorreu um erro ao selecionar a cidade");
       }
	}
	
	public void merge() {
		this.estado.setDescricao(Utilidades.formataString(this.estado.getDescricao()));
		Estado estado2 = EstadoBusiness.buscaEstado(this.estado);  //this.pais é diferente de pais
		if(this.estado.getId() != null){ // Se this.pais.getId() for igual à nulo isso significa que o usuário clicou em novo, caso contrário ele está realizando uma edição
			if(estado2 != null){
				if(!this.estado.getId().equals(estado2.getId())){
					mensagensDisparar("Tente novamente, estado já cadastrado: " + estado2.getDescricao());
					listar();
					return;
				}else{
					mensagensDisparar("Estado alterado com sucesso!!!");
				}
			}else{
				mensagensDisparar("Estado alterado com sucesso!!!");
			}
		}else{
			if(estado2 != null){
				mensagensDisparar("Estado já cadastrado!!!");
				return;
			}else{
				mensagensDisparar("Estado cadastrado com sucesso!!!");
			}
		}
		
		this.estado.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.estado.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.estado.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		EstadoBusiness.merge(this.estado);
		listar(); //ATENÇÃO, REVER LINHA NO MOMENTO DA IMPLANTAÇÃO DO FRONT!!!! - 02/03/2017
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
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	
}
