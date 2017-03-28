package br.com.projetabrasil.model.business;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import br.com.projetabrasil.model.dao.PontoDAO;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Ponto;

@SuppressWarnings("serial")
public class PontoBusiness implements Serializable {
	private static Ponto ponto_Existente;

	public static Ponto pontuacaoExiste(Pessoa assinante) {
		PontoDAO pConfDAO = new PontoDAO();
		return pConfDAO.retornarPonto(assinante);
	}

	public static Ponto registroAtualdaLista(ActionEvent evento) {
		Ponto p =  (Ponto) evento.getComponent().getAttributes().get("registroAtual");
		return p;
	}

	public static Ponto getPonto_Existente() {
		return ponto_Existente;
	}

	public static void setPonto_Existente(Ponto ponto_Existente) {
		PontoBusiness.ponto_Existente = ponto_Existente;
	}
}
