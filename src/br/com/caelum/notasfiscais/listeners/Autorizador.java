/**
 * 
 */
package br.com.caelum.notasfiscais.listeners;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.notasfiscais.mb.LoginBean;

/**
 * //TODO Descreva por que você esta criando esta classe ?
 * 
 * @author Tiarê Balbi Bonamini
 * @date Feb 23, 2013
 * @package br.com.caelum.notasfiscais.listeners
 *
 */
public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = -4045948358701216063L;

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		String url=context.getViewRoot().getViewId();
		
		if(url.equals("/login.xhtml")) {
			return;
		}
		
		LoginBean bean = context.getApplication().evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
		
		if(!bean.isLogado()) {
			 NavigationHandler handler = context.getApplication().getNavigationHandler();
			 handler.handleNavigation(context, null, "login?faces-redirect=true");
			 context.renderResponse();
		}
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent arg0) {

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
