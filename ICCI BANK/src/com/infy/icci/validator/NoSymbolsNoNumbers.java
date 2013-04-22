package com.infy.icci.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NoSymbolsNoNumbers implements Validator{
	/**
	* @Method Name: validate
	* @User: Alejandro_406765
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
		message.setDetail("Invalid Name. Please enter a valid name");
		// throw error when the string matches with symbols and numbers
		if(!stringEntered.trim().matches("^[A-Za-z]+$")){
			throw new ValidatorException(message);
		}
	}

}
