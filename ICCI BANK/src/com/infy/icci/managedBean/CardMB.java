package com.infy.icci.managedBean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CardMB {

	private long cardNo;
	private String cardNo1;
	private String cardNo2;
	private String cardNo3;
	private String cardNo4;
	private String pin;
	private Date dateOfRegistration;
	private double cardAmount;
	private double balanceAmount;
	private String message;
	
	/**
	 * @User Juan_406762
	 * @Method checkCardDetails
	 * @return success, chooseScheme or fail (the navigation rule) 
	 */
	
	public String checkCardDetails() {
		this.setCardNo(Long.parseLong(cardNo1+cardNo2+cardNo3+cardNo4));
		try {
			CardTO to = new CardTO();
			/* Set the cardNo property of the CardTO instance */
			to.setCardNo(this.getCardNo());
			/* Invoke the method getCardDetails(CardTo) of the wrapper class. Send the to object
			 * as the parameter of the function
			 */
			to = new InfyCreditCardWrapper().getCardDetails(to);
			/* Validate the card No. and the pin */
			if(to.getPin() == Integer.parseInt(this.getPin())) {	
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				String customerIdTemp = (String) session.getAttribute("customerId");
				Integer custId = to.getCustomerId();
				if(!customerIdTemp.equals(custId.toString())) {
					this.setMessage("Invalid Card No.");
					return "fail";
				}
				/* Set the cardNo property in the session object */
				session.setAttribute("cardNo", to.getCardNo());
				if(to.getSchemeId() != 'N') {
					return "success";
				}
				else {
					return "chooseScheme";
				}
			}
			else {
				this.setMessage("Card No. or Pin are incorrect");
				return "fail";
			}
		} catch (InvalidCardNoException exception) {
			this.setMessage(exception.getMessage());
			return "fail";
		}
	}
	
	/**
	 * @User ricardo_406737
	 * @Method getCardNo
	 * @return cardNo 
	 */	
	
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardNo1
	 * @return cardNo1 
	 */
	
	public String getCardNo1() {
		return cardNo1;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo1
	 * @param cardNo1 the cardNo1 to set
	 */
	
	public void setCardNo1(String cardNo1) {
		this.cardNo1 = cardNo1;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardNo2
	 * @return cardNo2 
	 */
	
	public String getCardNo2() {
		return cardNo2;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo2
	 * @param cardNo2 the cardNo2 to set
	 */
	
	public void setCardNo2(String cardNo2) {
		this.cardNo2 = cardNo2;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardNo3
	 * @return cardNo3 
	 */
	
	public String getCardNo3() {
		return cardNo3;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo3
	 * @param cardNo3 the cardNo3 to set
	 */
	
	public void setCardNo3(String cardNo3) {
		this.cardNo3 = cardNo3;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardNo4
	 * @return cardNo4 
	 */
	
	public String getCardNo4() {
		return cardNo4;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo4
	 * @param cardNo4 the cardNo4 to set
	 */
	
	public void setCardNo4(String cardNo4) {
		this.cardNo4 = cardNo4;
	}
	/**
	 * @User ricardo_406737
	 * @Method getPin
	 * @return pin 
	 */
	
	public String getPin() {
		return pin;
	}
	/**
	 * @User ricardo_406737
	 * @Method setPin
	 * @param pin the pin to set
	 */
	
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * @User ricardo_406737
	 * @Method getDateOfRegistration
	 * @return dateOfRegistration 
	 */
	
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	/**
	 * @User ricardo_406737
	 * @Method setDateOfRegistration
	 * @param dateOfRegistration the dateOfRegistration to set
	 */
	
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardAmount
	 * @return cardAmount 
	 */
	
	public double getCardAmount() {
		return cardAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardAmount
	 * @param cardAmount the cardAmount to set
	 */
	
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method getBalanceAmount
	 * @return balanceAmount 
	 */
	
	public double getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method setBalanceAmount
	 * @param balanceAmount the balanceAmount to set
	 */
	
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
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
