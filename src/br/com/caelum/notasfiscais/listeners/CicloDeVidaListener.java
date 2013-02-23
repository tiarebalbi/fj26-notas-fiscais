/**
 * 
 */
package br.com.caelum.notasfiscais.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Teste com o ciclo de vida do JSF
 * 
 * @author Tiarê Balbi Bonamini
 * @date Feb 23, 2013
 * @package br.com.caelum.notasfiscais.listeners
 *
 */
public class CicloDeVidaListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("Depos da fase: " + arg0.getPhaseId());
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("Antes da fase: " + arg0.getPhaseId());
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
