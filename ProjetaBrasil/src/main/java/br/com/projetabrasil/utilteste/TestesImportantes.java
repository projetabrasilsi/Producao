package br.com.projetabrasil.utilteste;
import org.junit.Test;

import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Ler_Excel;
import br.com.projetabrasil.model.entities.Usuario;

public class TestesImportantes {
	
	@Test
	public void inclusaodeProfissoes(){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("10554498928", "11111111");
		Ler_Excel lEx = new Ler_Excel();
		if(us!=null)
		lEx.inserirProfissaoBD(us.getPessoa());
		
	}

}
