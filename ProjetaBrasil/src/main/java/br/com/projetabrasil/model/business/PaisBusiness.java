package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.entities.Pais;



@SuppressWarnings("serial")
public class PaisBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public static List<Pais> listar() {
		List<Pais> paises = null;
		
		try {
			PaisDAO paisDAO = new PaisDAO();			
			paises = paisDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return paises;
		
	}

	public static Pais buscaPaisPeloNome(String descricao) {
		PaisDAO pDAO = new PaisDAO();
		Pais pais = pDAO.buscaPaisPeloNome(descricao);
		return pais;
	}
	

	public static Pais merge(Pais pais) {
		PaisDAO pDAO = new PaisDAO();
		pais = pDAO.merge(pais);
		return pais;
	}
	
	
}
