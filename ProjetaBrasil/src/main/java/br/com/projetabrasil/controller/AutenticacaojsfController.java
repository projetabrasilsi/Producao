package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@SessionScoped()
@ManagedBean(name = "autenticacaojsfController")
public class AutenticacaojsfController extends GenericController implements Serializable {
	public PerfilLogado perfilLogado;

	@PostConstruct
	public void iniciar() {
		perfilLogado = new PerfilLogado();
				

	}

	public void renderizar(boolean renderizaAssociado) {
		iniciar();

		perfilLogado.setRenderizaAssociado(renderizaAssociado);
		if (renderizaAssociado)
			perfilLogado.setFoco("ass");
		else {

			perfilLogado.setFoco("usuario");
		}
		redirecionaPaginas("autenticacao.xhtml", "erro no redirecionamento para página de autenticacao!!!", false);

	}

	public void cadastroAutomatico() {
		perfilLogado.setRenderizaAssociado(false);
		perfilLogado.setIdentificadorAssinante("99999999999");
		perfilLogado.setSenhaUsuario("98765432");
		autenticar(true);
	}

	public void autenticar(boolean cadAutomatico) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (perfilLogado.getIdentificadorUsuario() == null || perfilLogado.getIdentificadorUsuario().length() <= 0)
			perfilLogado.setIdentificadorUsuario(perfilLogado.getIdentificadorAssinante());

		perfilLogado.setUsLogado(
				usuarioDAO.autenticar(perfilLogado.getIdentificadorUsuario(), perfilLogado.getSenhaUsuario()));
		
		
		if (perfilLogado.getUsLogado() == null) {
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRAUTENTICACAO.getMensagem());
			return;
		}
			
		perfilLogado.listagemPerfisdousLogado();
		if (perfilLogado.getListaPerfisdousLogado() != null & perfilLogado.getListaPerfisdousLogado().size() > 1) {
			perfilLogado.escondeDialogoAltenticacacao(true);
			
			
			
		} else {
			if (perfilLogado.getListaPerfisdousLogado() != null & perfilLogado.getListaPerfisdousLogado().size() == 1) {
				if (perfilLogado.getPerfilUsLogado() == null
						|| !perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
					perfilLogado.setPerfilUsLogado(perfilLogado.getListaPerfisdousLogado().get(0));
				}
				perfilLogado.escondeDialogoAltenticacacao(false);

				if (cadAutomatico) {
					perfilLogado.setPaginaAtual(Enum_Aux_Perfil_Pagina_Atual.PAGINACLIENTES);
					perfilLogado.setPerfilUsLogado(Enum_Aux_Perfil_Pessoa.OUTROS);
					perfilLogado.setRenderizapessoaeditar(false);
					perfilLogado.setRenderizapessoanovo(false);
				}
				redirecionapaginaIndex(cadAutomatico);
				if(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.FUNCIONARIOS) ||
						perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)){
					// deve verificar quem é o AssLogado deste funcionário através do vinculo deste funcionário com o associado logado;
					// no caso seria a revenda que ele trabalha
					Pessoa_VinculoDAO pVDAO = new Pessoa_VinculoDAO();
					Pessoa_Vinculo pV = pVDAO.retornaVinculoPeloPerfil(perfilLogado);
					if(pV!=null){
						perfilLogado.setAssLogado(pV.getId_pessoa_m());
					}
				}
				
				
			}
		}
		
		
	}

	public void logout() {
		perfilLogado = new PerfilLogado();
		cancelaAutenticacao();
	}

	public void redirecionaPaginas(String pagina, String comentario, boolean redirect) {
		if (!redirect)
			Faces.navigate("/pages/" + pagina);
		else {
			try {
				Faces.redirect("./faces/pages/" + pagina);
				mensagensDisparar("autenticacao cancelada!!!");
			} catch (IOException error) {
				mensagensDisparar("erro no redirecionamento de página de autenticacao para alfapage!!!");
				error.printStackTrace();
			}
		}
	}

	public void cancelaAutenticacao() {
		redirecionaPaginas("alfapage.xhtml", "autenticacao cancelada!!!", true);
	}

	public void redirecionapaginaIndex(boolean cadastroAutomatico) {
		perfilLogado.verificaAssinante();
		Utilidades.abrirfecharDialogos("dialogoPerfil", false);

		if (!cadastroAutomatico)
			redirecionaPaginas("index.xhtml", "Erro ao tentar chamar a pagina index.!!!", true);
		else
			redirecionaPaginas("pessoas.xhtml", "Erro ao tentar chamar a pagina pessoas.!!!", true);
		
		if(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.FUNCIONARIOS) ||
				perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)){
			// deve verificar quem é o AssLogado deste funcionário através do vinculo deste funcionário com o associado logado;
			// no caso seria a revenda que ele trabalha
			Pessoa_VinculoDAO pVDAO = new Pessoa_VinculoDAO();
			Pessoa_Vinculo pV = pVDAO.retornaVinculoPeloPerfil(perfilLogado);
			if(pV!=null){
				perfilLogado.setAssLogado(pV.getId_pessoa_m());
			}
		}
		

	}

	public void renderizarAssociado(boolean renderiza) {
		if (renderiza)
			perfilLogado.setFoco("ass");
		else
			perfilLogado.setFoco("usuario");

		perfilLogado.setRenderizaAssociado(renderiza);
		redirecionaPaginas("index.xhtml", "Erro ao tentar chamar a pagina index.!!!", true);
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

}