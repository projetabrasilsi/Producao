package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.UsuarioBusiness;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuariojsfController extends GenericController implements Serializable {
	private Usuario usuario;	
	private PessoaConfig pessoaConfig;	
	private List<Usuario> usuarios;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado.usLogado}")
	private Usuario usLogado;
	

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		Pessoa pessoa = new Pessoa();		
		pessoa = pessoaConfig.getPessoa();		
		pessoa.setUltimaAtualizacao(Utilidades.retornaCalendario());
		pessoa.setId_Empresa(1);
		pessoa.setId_Pessoa_Registro(usLogado.getPessoa());
		usuario = new Usuario();
	}
	public void merge() {
		UsuarioBusiness.merge(usuario);
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	/**
	 * @return the usLogado
	 */
	public Usuario getUsLogado() {
		return usLogado;
	}

	/**
	 * @param usLogado the usLogado to set
	 */
	public void setUsLogado(Usuario usLogado) {
		this.usLogado = usLogado;
	}

	
}
