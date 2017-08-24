package br.com.projetabrasil.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class AtualizacaoAutomaticaDeMensagens implements PhaseListener {

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":mensagens");
	}

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}