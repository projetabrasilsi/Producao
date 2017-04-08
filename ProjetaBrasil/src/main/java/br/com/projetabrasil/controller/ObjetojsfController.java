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
import br.com.projetabrasil.model.business.ObjetoBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness2;
import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipos_Objetos;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ObjetojsfController extends GenericController implements Serializable {
	
	private Objeto objeto;
	private List<Objeto> objetos;
	private Pessoa pessoa;
	private PessoaConfig pessoaConfig;
	private Usuario usuario;
	
	private List<Enum_Aux_Tipos_Objetos> listaTiposObjeto;
	private Enum_Aux_Tipos_Objetos tipoObjeto;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {	
		pessoa = new Pessoa();
		usuario = new Usuario();
		objetos = new ArrayList<>();
		objeto = new Objeto();
		configurarPessoa();
		
		pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		
		listarTiposdeObjeto();
		
		Utilidades.abrirfecharDialogos("dialogoIdentidade", true);
		
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		objeto = new Objeto();		
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		
		this.objeto.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.objeto.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.objeto.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		ObjetoBusiness.merge(this.objeto);
		listar(); 
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

	}
	
	
	public void editar(ActionEvent event) {

		Objeto o = (Objeto) event.getComponent().getAttributes().get("registroAtual");
		objeto = new Objeto();
		objeto = o;		
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	
	public void cancela() {		
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage",true);				    			
		}
	}
	
	public void cancelaValidacao() {		
		Utilidades.abrirfecharDialogos("dialogoIdentidade",false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage",true);				    			
		}
		
		autenticacao.redirecionaPaginas("index.xhtml", null, true);
	}
	
	public void listarTiposdeObjeto() {
		Enum_Aux_Tipos_Objetos[] listagem;
		listagem = Enum_Aux_Tipos_Objetos.values();
		listaTiposObjeto = new ArrayList<Enum_Aux_Tipos_Objetos>();
		for(Enum_Aux_Tipos_Objetos i : listagem){
			listaTiposObjeto.add(i);
		}
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
		
		if(pessoa.getId() == null){
			mensagensDisparar("Este CPF não existe na base de dados");
			return;
		}
		
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
		}
		if (pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa().equals(Enum_Aux_Tipo_Pessoa.OUTROS))
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		if (!pessoa.getEnum_Aux_Tipo_Identificador().equals(tipoIdent))
			pessoa.setEnum_Aux_Tipo_Identificador(tipoIdent);
		
		
		ObjetoDAO oDAO = new ObjetoDAO();
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
			objetos = oDAO.lista_Objetos(pessoa);
		
		for(Objeto o : objetos){
			System.out.println(o.toString());
		}
		
		mudaLabel();
		Utilidades.abrirfecharDialogos("dialogoIdentidade", false);
	}
	
	public void mudaLabel() {
		pessoaConfig.mudarLabels(pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa());
	}
	
	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	public List<Enum_Aux_Tipos_Objetos> getListaTiposObjeto() {
		return listaTiposObjeto;
	}

	public void setListaTiposObjeto(List<Enum_Aux_Tipos_Objetos> listaTiposObjeto) {
		this.listaTiposObjeto = listaTiposObjeto;
	}

	public Enum_Aux_Tipos_Objetos getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(Enum_Aux_Tipos_Objetos tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
