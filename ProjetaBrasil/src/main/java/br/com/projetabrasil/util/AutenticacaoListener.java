package br.com.projetabrasil.util;

import java.io.IOException;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.projetabrasil.controller.AutenticacaojsfController;
import br.com.projetabrasil.model.entities.Usuario;


@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		String paginaCorrente = Faces.getViewId();
		boolean ehPaginadeAtutenticacao = paginaCorrente.contains("autenticacao.xhtml");
		boolean ehPaginaAlfa = paginaCorrente.contains("alfapage.xhtml");
		boolean ehPaginaAchei = paginaCorrente.contains("achei.xhtml");

		if (!ehPaginadeAtutenticacao && !ehPaginaAchei) {
			if (!ehPaginaAlfa) {
				AutenticacaojsfController autenticacaojsfController = Faces
						.getSessionAttribute("autenticacaojsfController");
				if (autenticacaojsfController == null) {
					    try{
					    	Faces.redirect("pages/alfapage.xhtml");
							return;	
					    }catch(IOException error){
					    	
					    	error.printStackTrace();
					    }
					    
						
										
				}
				Usuario usuario = autenticacaojsfController.getPerfilLogado().getUsLogado();
				if (usuario == null || autenticacaojsfController.getPerfilLogado().getPaginaAtual() == null) {
					
					try{
				    	Faces.redirect("pages/alfapage.xhtml");
						return;	
				    }catch(IOException error){
				    	
				    	error.printStackTrace();
				    }
				    
					
				}
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		//
		return PhaseId.RESTORE_VIEW;
	}

}
