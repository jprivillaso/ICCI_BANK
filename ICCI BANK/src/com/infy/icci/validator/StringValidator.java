package com.infy.icci.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class StringValidator implements Validator{

	/**
	 * @Method Name: validate
	 * @User: ricardo_406737
	 * @Return void
	 * @return
	 */
	public void validate(FacesContext facesContext, UIComponent component, Object object)
			throws ValidatorException {
		//Evaluate the context is being used otherwise return 
		if(facesContext == null || component == null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIComponent)){
			return;
		}
		//Create FacesMessage message to set the error message
		String stringEntered = object.toString();
		FacesMessage message = new FacesMessage();
		message.setDetail("String Field: enter a valid string in the field");
		if(stringEntered.trim().matches("^[0-9]+$")){
			throw new ValidatorException(message);
		}
	}

}
