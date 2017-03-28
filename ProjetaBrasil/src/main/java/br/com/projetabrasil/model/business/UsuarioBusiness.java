package br.com.projetabrasil.model.business;

import java.io.Serializable;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Usuario;

@SuppressWarnings("serial")
public class UsuarioBusiness implements Serializable {
	private static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	@SuppressWarnings("unused")
	public static Usuario merge(Usuario usuario){		
			Usuario us = usuario;
			
			try {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				
				us = usuarioDAO.retornaUsuarioPelaPessoa(us.getPessoa());
				if(us==null)
					us = usuario;				
				us.setConfSenha(usuario.getConfSenha());				
				us.setSenhaSemCript(usuario.getSenhaSemCript());
				
				
				
				if (us==null) 
				us = usuario;
				us.setAtivo(true);
				us = (Usuario) usuarioDAO.merge(us);
				if (us.getId() != null)
					mensagensDisparar("Usuário: " + Enum_Aux_Tipo_Mensagem.ALTERACAO.getMensagem());
				else
					mensagensDisparar("Usuário: " + Enum_Aux_Tipo_Mensagem.INCLUSAO.getMensagem());

			} catch (RuntimeException erro) {
                 
				if (us.getId() != null)
					mensagensDisparar("Usuário - " + Enum_Aux_Tipo_Mensagem.ERRALTERACAO.getMensagem());
				else
					mensagensDisparar("Usuário - " + Enum_Aux_Tipo_Mensagem.ERRINCLUSAO.getMensagem());
				us = null;
				erro.printStackTrace();
			}
			return us;
	}
	
	public static boolean confereSenha(Usuario usuario) {
		boolean retorno = usuario.getSenhaSemCript().equals(usuario.getConfSenha());
		if (!retorno)
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRSENHADIFERENTE.getMensagem());
		return retorno;
	}
	public static Usuario retornaUsuario(Usuario usuario, Pessoa pessoa){
		UsuarioDAO usDAO = new UsuarioDAO();
		usuario = usDAO.retornaUsuarioPelaPessoa(pessoa);
		if (usuario == null)
		usuario = new Usuario();
		
		return usuario;
	}

	

}
