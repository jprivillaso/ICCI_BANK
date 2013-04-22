package com.infy.icci.managedBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.exceptions.InvalidCustomerIdException;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CustomerMB {

	private String customerId;
	private String userName;
	private String randomCode;
	private String enteredCode;
	private String message;
	
	/**
	 * @User Juan_406762
	 * @Method CustomerMB
	 */
	
	public CustomerMB() {
		/* Get the user name in the session object and set the data member userName
		 * with this value. 
		 */
		HttpSession session = (HttpSession) 
			FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		this.setUserName((String) session.getAttribute("userName"));
		/* Set the data member randomCode with a randomly generated alphanumeric code */
		this.setRandomCode(Integer.toHexString((int) Double.doubleToLongBits(Math.random())));
	}
	
	/**
	 * @User Juan_406762
	 * @Method validateCustomer
	 * @return success or fail (the navigation rule) 
	 */
	
	public String validateCustomer() {
		try {
			CustomerTO to = new CustomerTO();
			/* Set the customerId property of the CustomerTO instance */
			to.setCustomerId(Integer.parseInt(this.getCustomerId()));
			/* Invoke the method getCustomerDetails(CustomerTO) of the wrapper class. Send the to object
			 * as the parameter of the function
			 */
			to = new InfyCreditCardWrapper().getCustomerDetails(to);
			/* Validate the customerId and the enteredCode */
			if(!this.getUserName().equals(to.getUserName())) {
				this.setMessage("Invalid Customer Id");
				return "fail";
			}
			if(!this.getEnteredCode().equals(this.getRandomCode())) {
				this.setMessage("Invalid Code");
				return "fail";
			}
			else {
				/* Set the customerId property in the session object for a posterior validation */
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("customerId", this.getCustomerId());
				return "success";
			}
		} catch (InvalidCustomerIdException exception) {
			this.setMessage(exception.getMessage());
			return "fail";
		}
	}
	
	/**
	 * @User ricardo_406737
	 * @Method getCustomerId
	 * @return customerId 
	 */
	
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCustomerId
	 * @param customerId the customerId to set
	 */
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * @Method getRandomCode
	 * @return randomCode 
	 */
	
	public String getRandomCode() {
		return randomCode;
	}
	/**
	 * @User ricardo_406737
	 * @Method setRandomCode
	 * @param randomCode the randomCode to set
	 */
	
	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}
	/**
	 * @User ricardo_406737
	 * @Method getEnteredCode
	 * @return enteredCode 
	 */
	
	public String getEnteredCode() {
		return enteredCode;
	}
	/**
	 * @User ricardo_406737
	 * @Method setEnteredCode
	 * @param enteredCode the enteredCode to set
	 */
	
	public void setEnteredCode(String enteredCode) {
		this.enteredCode = enteredCode;
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
