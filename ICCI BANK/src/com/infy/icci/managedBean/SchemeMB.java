package com.infy.icci.managedBean;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;


public class SchemeMB {
	
	/*
	 * Data members
	 */
	private char schemeId;
	private Double schemeAmount;
	private Double minimumAmount;
	private Double interestRate;
	private String message;
	private boolean checked = false;
	
	/**
	* 
	* @Method Name: display
	* @Description: This method displays the details of the 
	* scheme in the JSP page by setting the parameters of 
	* the class and bind them to the fields in the JSP page.
	* @User: juan_406752
	* @Return Type: void
	* @param event
	*/
	public void display(ValueChangeEvent event) throws Exception{
		
		try {
			String result = event.getNewValue().toString();
			this.schemeId = result.charAt(0);
			
			/*
			 * Setting the session Id in session in order to be used in the 
			 * next jsp Page
			 */
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("schemeId", this.schemeId);
			
			/*
			 * Creating an object of the transfer object
			 */
			SchemeTO schemeTO = new SchemeTO();
			schemeTO.setSchemeId(this.schemeId);
			
			/*
			 * Pass a transfer object to the wrapper class
			 */
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			SchemeTO schemeTO2 = wrapper.getSchemeDetails(schemeTO);
			
			/*
			 * Set the properties of the class with the transfer object
			 * schemeTo2 that was returned from wrapper class 
			 */
			this.setInterestRate(schemeTO2.getInterestRate());
			this.setMinimumAmount(schemeTO2.getMinimumAmount());
			this.setSchemeAmount(schemeTO2.getSchemeAmount());			
			
			/*
			 * Set successfully message
			 */
			this.setMessage("Data Displayed Successfully");
		} catch (Exception e) {
			this.setMessage(e.getMessage());
		}
	}
	
	/**
	* 
	* @Method Name: updateSchemeDetails
	* @Description: This method updates the scheme details for the customer 
	* if he does not have any scheme for this card.
	* @User: juan_406752
	* @Return Type: String
	* @return a String indicating a success or failure update
	*/
	public String updateSchemeDetails(){
		
		try {
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();			
						
			/*
			 * Getting the card number from the session
			 */
			HttpSession session = (HttpSession)FacesContext.
				getCurrentInstance().getExternalContext().getSession(false);
			
			/*
			 * Casting the objects gotten from the session
			 */
			Long cardNo = (Long)session.getAttribute("cardNo");
			String userName = session.getAttribute("userName").toString();
			
			/*
			 * Creating a customerTo object to be passed as a parameter
			 * to the wrapper
			 */
			CustomerTO customerTO = new CustomerTO();
			customerTO.setUserName(userName);
						
			/*
			 * Set the card number to the cardTo object
			 */
			CardTO card = new CardTO();
			//card.setCardNo(4574835698119810L);
			card.setCardNo(cardNo);			
			
			/*
			 * Check if the customer has an scheme chosen
			 */
			boolean result = wrapper.schemeChosen(customerTO);
					
			
			/*
			 * If result is false, it means that the user has not an
			 * scheme assigned
			 */
			if(result == false){	
				/*
				 * Update the scheme details of the user
				 */
				wrapper.updateSchemeDetails(card);
			}
			
					
			/*
			 * Setting a success message in the attribute of the class
			 */
			this.setMessage("Update Successful");
			
			return "success";
		} catch (Exception e) {
			this.setMessage(e.getMessage());
			return "fail";
		}		
	}
	
	/**
	* @Method Name: isChecked
	* @Description: This method checks if the check box is
	* selected
	* @User: juan_406752
	* @Return Type: void
	* @param event
	*/
	public void isChecked(ValueChangeEvent event){
		
		
		Object checkBox = (Object)event.getNewValue();
		
		/**
		 * Checks if the check box is checked, if so then sets the checked 
		 * data member of the class as true indicating that the submit 
		 * button can be displayed in the JSP page
		 */
		if(checkBox.equals("true")){
			this.setChecked(true);			
		}else{
			this.setChecked(false);
		}
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	/**
	 * @User juan_406752
	 * @Method isChecked
	 * @return checked 
	 */
	
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @User juan_406752
	 * @Method setChecked
	 * @param the checked to set
	 */
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @User juan_406752
	 * @Method getSchemeId
	 * @return schemeId 
	 */
	
	public char getSchemeId() {
		return schemeId;
	}

	/**
	 * @User juan_406752
	 * @Method setSchemeId
	 * @param the schemeId to set
	 */
	
	public void setSchemeId(char schemeId) {
		this.schemeId = schemeId;
	}

	/**
	 * @User juan_406752
	 * @Method getSchemeAmount
	 * @return schemeAmount 
	 */
	
	public Double getSchemeAmount() {
		return schemeAmount;
	}

	/**
	 * @User juan_406752
	 * @Method setSchemeAmount
	 * @param the schemeAmount to set
	 */
	
	public void setSchemeAmount(Double schemeAmount) {
		this.schemeAmount = schemeAmount;
	}

	/**
	 * @User juan_406752
	 * @Method getMinimumAmount
	 * @return minimumAmount 
	 */
	
	public Double getMinimumAmount() {
		return minimumAmount;
	}

	/**
	 * @User juan_406752
	 * @Method setMinimumAmount
	 * @param the minimumAmount to set
	 */
	
	public void setMinimumAmount(Double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	/**
	 * @User juan_406752
	 * @Method getInterestRate
	 * @return interestRate 
	 */
	
	public Double getInterestRate() {
		return interestRate;
	}

	/**
	 * @User juan_406752
	 * @Method setInterestRate
	 * @param the interestRate to set
	 */
	
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @User juan_406752
	 * @Method getMessage
	 * @return message 
	 */
	
	public String getMessage() {
		return message;
	}

	/**
	 * @User juan_406752
	 * @Method setMessage
	 * @param the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
