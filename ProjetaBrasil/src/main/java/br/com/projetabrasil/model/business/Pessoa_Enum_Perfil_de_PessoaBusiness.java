package br.com.projetabrasil.model.business;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;

public class Pessoa_Enum_Perfil_de_PessoaBusiness {

	private static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public static Pessoa_Enum_Aux_Perfil_Pessoa merge(Pessoa_Enum_Aux_Perfil_Pessoa pessoa_Enum_Aux_Perfil_Pessoa) {
		Pessoa_Enum_Aux_Perfil_Pessoa pPerfil = pessoa_Enum_Aux_Perfil_Pessoa;
		

		try {
			Pessoa_Enum_Aux_Perfil_PessoasDAO perfilDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
			pPerfil = perfilDAO.isPerfilExiste(pPerfil);
			if(pPerfil== null)
				pPerfil = pessoa_Enum_Aux_Perfil_Pessoa;
				
			
			pPerfil = perfilDAO.merge(pPerfil);
			if (pPerfil.getId()==null)
  		    mensagensDisparar("Perfil  - "+ pPerfil.getEnum_Aux_Perfil_Pessoa().getDescricao()+", " +Enum_Aux_Tipo_Mensagem.INCLUSAO.getMensagem());
			else
			mensagensDisparar("Perfil  "+ pPerfil.getEnum_Aux_Perfil_Pessoa().getDescricao()+", "+Enum_Aux_Tipo_Mensagem.ALTERACAO.getMensagem());
			
		} catch (RuntimeException erro) {
			if (pPerfil.getId()==null)
	  		    mensagensDisparar("Perfil  - "+ pPerfil.getEnum_Aux_Perfil_Pessoa().getDescricao()+", " +Enum_Aux_Tipo_Mensagem.ERRINCLUSAO.getMensagem());
				else
				mensagensDisparar("Perfil  "+ pPerfil.getEnum_Aux_Perfil_Pessoa().getDescricao()+", "+Enum_Aux_Tipo_Mensagem.ERRINCLUSAO.getMensagem());
            pPerfil = null; 			

			erro.printStackTrace();

		}
		return pPerfil;
	}
	

}
