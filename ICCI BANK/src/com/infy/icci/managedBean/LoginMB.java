package com.infy.icci.managedBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.exceptions.InvalidLoginException;
import com.infy.icci.transferObjects.LoginTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class LoginMB {

	private String userName;
	private String password;
	private String message;
	
	/**
	 * @User Juan_406762
	 * @Method validateLogin
	 * @return successCR, successCustomer or fail (the navigation rule) 
	 */
	
	public String validateLogin() {
		try {
			LoginTO to = new LoginTO();
			/* Set the values of the LoginTO instance */
			to.setUserId(this.getUserName());
			to.setPassword(this.getPassword());
			/* Invoke the method validateLogin(LoginTO) of the wrapper class. Send the to object
			 * as the parameter of the function
			 */
			to = new InfyCreditCardWrapper().validateLogin(to);
			/* Create a session object and set the userName in the session object */
			HttpSession session = (HttpSession) 
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("userName", to.getUserId());
			/* Set the maximum time which the user can be inactive */
			session.setMaxInactiveInterval(300);
			/* If the user is a CR, go to the CR home page, 
			 * else go to the Customer login page  
			 */
			if(to.getUserId().equals("dav222"))
				return "successCR";
			else
				return "successCustomer";
		}
		catch (InvalidLoginException exception) {
			this.setMessage(exception.getMessage());
			return "fail";
		}
	}
	
	/**
	 * @User ricardo_406737
	 * @Method getUserName
	 * @return userName 
	 */
	
	public String getUserName() {
		return userName;
	}
	/**
	 * @User ricardo_406737
	 * @Method setUserName
	 * @param userName the userName to set
	 */
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @User ricardo_406737
	 * @Method getPassword
	 * @return password 
	 */
	
	public String getPassword() {
		return password;
	}
	/**
	 * @User ricardo_406737
	 * @Method setPassword
	 * @param password the password to set
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @User ricardo_406737
	 * @Method getMessage
	 * @return message 
	 */
	
	public String getMessage() {
		return message;
	}
	/**
	 * @User ricardo_406737
	 * @Method setMessage
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}	
}
