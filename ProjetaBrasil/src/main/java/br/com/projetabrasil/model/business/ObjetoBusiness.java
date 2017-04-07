package br.com.projetabrasil.model.business;

import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.entities.Objeto;

public class ObjetoBusiness {
	
	public static Objeto merge(Objeto objeto) {
		ObjetoDAO oDAO = new ObjetoDAO();
		objeto = oDAO.merge(objeto);
		return objeto;
	}
}
