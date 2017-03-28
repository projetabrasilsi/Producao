import org.junit.Test;

import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Ler_Excel;
import br.com.projetabrasil.model.entities.Usuario;

public class TestesImportantes {
	
	@Test
	public void inclusaodeProfissoes(){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("89230906115", "P2a3u0l9");
		Ler_Excel lEx = new Ler_Excel();
		if(us!=null)
		lEx.inserirProfissaoBD(us.getPessoa());
		
	}

}
