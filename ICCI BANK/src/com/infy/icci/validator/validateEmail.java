package com.infy.icci.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class validateEmail implements Validator{
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
		String email = object.toString();
		FacesMessage message = new FacesMessage();
		message.setDetail("Invalid Email. Please enter a valid email");
		//Check if the email has the correct form
		if(!email.trim().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" +
				"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")){
			throw new ValidatorException(message);
		}
	}
}
