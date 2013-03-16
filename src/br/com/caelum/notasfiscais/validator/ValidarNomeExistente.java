/**
 * 
 */
package br.com.caelum.notasfiscais.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.faces.validation.InputField;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 16, 2013
 * @package br.com.caelum.notasfiscais.validator
 *
 */
@FacesValidator("nomeExistente")
public class ValidarNomeExistente implements Validator, Serializable {

	private static final long serialVersionUID = 8883540784345726420L;
	@Inject
	private EntityManager em;
	
	@Inject
	@InputField
	private String nome;
	
	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		if(arg2 == null) {
			throw new ValidatorException(new FacesMessage("error", "Não foi possível identificar o valor preenchido."));
		}
		
		Query query = em.createQuery("select count(p) from Produto p where p.nome like :nome");
		query.setParameter("nome", this.nome);
		Long total = (Long) query.getSingleResult();
		
		if(total != 0) {
			throw new ValidatorException(new FacesMessage("O Nome do Produto já se encontra cadastrado"));
		}
	}
}
