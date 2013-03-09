/**
 * 
 */
package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * //TODO Descreva por que você esta criando esta classe ?
 * 
 * @author Tiarê Balbi Bonamini
 * @date Mar 9, 2013
 * @package br.com.caelum.notasfiscais.validator
 *
 */
@FacesValidator(value="comecaComMaiusculo")
public class ComecaComMaiusculo implements Validator {

	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		String valor = value.toString();
		if(!valor.matches("[A-Z].*")) {
			FacesMessage notification = new FacesMessage("A Primeira letra deve ser maiscula");
			throw new ValidatorException(notification);
		}
	}

}
