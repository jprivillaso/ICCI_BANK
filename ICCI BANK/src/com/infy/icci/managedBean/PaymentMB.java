package com.infy.icci.managedBean;

import java.util.Calendar;
import com.infy.icci.exceptions.CannotUnblockCardException;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.PaymentTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

/**
 * 
 * @author Carolina_406764
 * 
 */

public class PaymentMB {
	// Declare data Members
	private String[] cardNo;
	// Default paymentType to 'Full'
	private Character paymentType = 'F';
	private Integer customerId;
	private String customerName;
	private Double balanceAmount;
	private Calendar lastPaymentDate;
	private String cardStatus;
	private Boolean disable;
	private String message;
	private Double amountPaid;
	private Double balance;
	private boolean renderForm;
	private boolean renderMessage;


	/**
	 * @author Carolina_406764
	 * Constructor PaymentMB
	 * @Description: Initialized cardNo string and renders.
	 */

	public PaymentMB() {
		this.cardNo = new String[4];
		for (int i = 0; i < cardNo.length; i++) {
			cardNo[i] = "";
		}
		this.renderForm = false;
		this.renderMessage = false;
	}

	/**
	 * @User Carolina_406764
	 * @Method getCardNo
	 * @return cardNo
	 */
	
	public String[] getCardNo() {
		return cardNo;
	}

	/**
	 * @User Carolina_406764
	 * @Method setCardNo
	 * @param cardNo
	 *            the cardNo to set
	 */

	public void setCardNo(String[] cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @User Carolina_406764
	 * @Method getPaymentType
	 * @return paymentType
	 */

	public Character getPaymentType() {
		return paymentType;
	}

	/**
	 * @User Carolina_406764
	 * @Method setPaymentType
	 * @param paymentType
	 *            the paymentType to set
	 */

	public void setPaymentType(Character paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @User Carolina_406764
	 * @Method getCustomerId
	 * @return customerId
	 */

	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @User Carolina_406764
	 * @Method setCustomerId
	 * @param customerId
	 *            the customerId to set
	 */

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @User Carolina_406764
	 * @Method getCustomerName
	 * @return customerName
	 */

	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @User Carolina_406764
	 * @Method setCustomerName
	 * @param customerName
	 *            the customerName to set
	 */

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @User Carolina_406764
	 * @Method getBalanceAmount
	 * @return balanceAmount
	 */

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * @User Carolina_406764
	 * @Method setBalanceAmount
	 * @param balanceAmount
	 *            the balanceAmount to set
	 */

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * @User Carolina_406764
	 * @Method getLastPaymentDate
	 * @return lastPaymentDate
	 */

	public Calendar getLastPaymentDate() {
		return lastPaymentDate;
	}

	/**
	 * @User Carolina_406764
	 * @Method setLastPaymentDate
	 * @param lastPaymentDate
	 *            the lastPaymentDate to set
	 */

	public void setLastPaymentDate(Calendar lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	/**
	 * @User Carolina_406764
	 * @Method getCardStatus
	 * @return cardStatus
	 */

	public String getCardStatus() {
		return cardStatus;
	}

	/**
	 * @User Carolina_406764
	 * @Method setCardStatus
	 * @param cardStatus
	 *            the cardStatus to set
	 */

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	/**
	 * @User Carolina_406764
	 * @Method getDisable
	 * @return disable
	 */

	public Boolean getDisable() {
		return disable;
	}

	/**
	 * @User Carolina_406764
	 * @Method setDisable
	 * @param disable
	 *            the disable to set
	 */

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	/**
	 * @User Carolina_406764
	 * @Method getMessage
	 * @return message
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * @User Carolina_406764
	 * @Method setMessage
	 * @param message
	 *            the message to set
	 */

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @User Carolina_406764
	 * @Method getAmountPaid
	 * @return amountPaid
	 */

	public Double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @User Carolina_406764
	 * @Method setAmountPaid
	 * @param amountPaid
	 *            the amountPaid to set
	 */

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @User Carolina_406764
	 * @Method getBalance
	 * @return balance
	 */

	public Double getBalance() {
		return balance;
	}

	/**
	 * @User Carolina_406764
	 * @Method setBalance
	 * @param balance
	 *            the balance to set
	 */

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	

	/**
	 * @User Carolina_406764
	 * @Method isRenderForm
	 * @return renderForm 
	 */
	
	public boolean isRenderForm() {
		return renderForm;
	}

	/**
	 * @User Carolina_406764
	 * @Method setRenderForm
	 * @param renderForm the renderForm to set
	 */
	
	public void setRenderForm(boolean renderForm) {
		this.renderForm = renderForm;
	}

	/**
	 * @User Carolina_406764
	 * @Method isRenderMessage
	 * @return renderMessage 
	 */
	
	public boolean isRenderMessage() {
		return renderMessage;
	}

	/**
	 * @User Carolina_406764
	 * @Method setRenderMessage
	 * @param renderMessage the renderMessage to set
	 */
	
	public void setRenderMessage(boolean renderMessage) {
		this.renderMessage = renderMessage;
	}

	/**
	 * 
	 * @Method Name: getPaymentInformation
	 * @Description: Used to retrieve the payment details of the entered card
	 *               number
	 * @User: Carolina_406764
	 * @Return Type: String
	 * @return success or failure
	 * @throws NumberFormatException, InvalidCardNoException, Exception
	 */

	public String getPaymentInformation() {
		this.message="";
		/*Validate if the Card Number is empty in any part of the String and set the
		corresponding message*/
		if (cardNo[0].equals("") || cardNo[1].equals("")
				|| cardNo[2].equals("") || cardNo[3].equals("")) {
			this.setMessage("Mandatory Field: Please enter the value for Card No");
			//Show the message
			this.setRenderMessage(true);
			this.renderForm = false;
			return "failure";
		}
		//Checks if cardNo is invalid
		if ((cardNo[0].length()!=4)||(cardNo[1].length()!=4)||(cardNo[2].length()!=4)||(cardNo[3].length()!=4)){
			this.setMessage("Card No must be 16 digits");
			//Show the message
			this.setRenderMessage(true);
			this.renderForm = false;
			return "failure";
		}
		try {
			//Disable message
			this.setRenderMessage(false);
			// Create instance of CardTO
			CardTO cardTO = new CardTO();
			// Concatenate the string into CardNo
			cardTO.setCardNo(new Long(
					(cardNo[0] + cardNo[1] + cardNo[2] + cardNo[3])));
			// Create instance of InfyCreditCardWrapper
			InfyCreditCardWrapper wrapperClass = new InfyCreditCardWrapper();
			// Call getCardDetails and save them into to
			cardTO=wrapperClass.getCardDetails(cardTO);
			// Create instance of PaymentTO
			PaymentTO paymentTO = new PaymentTO();
			// Call getPaymentDetails and save them into paymentTO
			paymentTO = wrapperClass.getPaymentDetails(cardTO);
			// Checks if Card No is not empty and assign render=true
			if (paymentTO != null) {
				this.renderForm = true;
			}
			// Create instance of CustomerTO
			CustomerTO customerTO = new CustomerTO();
			// Assign values to CustomerId of CustomerTO
			customerTO.setCustomerId(cardTO.getCustomerId());
			// Assign a value to customerId data member
			this.setCustomerId(customerTO.getCustomerId());
			// Call getCustomerDetails and save them into customerTO
			customerTO = wrapperClass.getCustomerDetails((customerTO));
			// Assign values to the respective dataMembers
			this.setCustomerId(customerTO.getCustomerId());
			this.setCustomerName(customerTO.getName());
			this.setBalanceAmount(cardTO.getBalanceAmount());
			// Checks if customer has made a payment before and set that date
			if(paymentTO.getDateOfPayment()==null){
				this.lastPaymentDate= Calendar.getInstance();
				lastPaymentDate.add(Calendar.YEAR, 100);	
			}
			else{ 
				this.setLastPaymentDate(paymentTO.getDateOfPayment());
			}
			// Assign a value to CardStatus data member
			this.setCardStatus(paymentTO.getCardStatus());
			//Do not display message
			this.setRenderMessage(false);
			return "ok";
		//Catch for InvalidCardNoException
		} catch (InvalidCardNoException exception) {
			//Show the message
			this.setRenderMessage(true);
			this.setMessage(exception.getMessage());
			this.renderForm = false;
			exception.printStackTrace();
			return "failure";
		}
		//Catch for NumberFormatException
		catch (NumberFormatException exception) {
			//Show the message
			this.setRenderMessage(true);
			this.setMessage("Numeric Field : Please enter only numbers for Card No.");
			this.renderForm = false;
			exception.printStackTrace();
			return "failure";
		}
		//Catch for Exception
		catch (Exception exception) {
			//Show the message
			this.setRenderMessage(true);
			this.setMessage(exception.getMessage());
			this.renderForm = false;
			exception.printStackTrace();
			return "failure";
		}
	}

	/**
	 * @Method Name: calculateBalance
	 * @Description: Used to calculate the balance amount after payment
	 * @User: Carolina_406764
	 * @Return Type: String
	 * @return success or failure
	 */

	public String calculateBalance()throws Exception{
		// Create instance of InfyCreditCardWrapper
		InfyCreditCardWrapper wrapperClass = new InfyCreditCardWrapper();
		// Create instance of CardTO
		CardTO cardTO = new CardTO();
		// Concatenate the string into CardNo
		cardTO.setCardNo(new Long(
				(cardNo[0] + cardNo[1] + cardNo[2] + cardNo[3])));
		// Call getCardDetails and save them into to
		cardTO = wrapperClass.getCardDetails(cardTO);
		// Create instance of SchemeTO
		SchemeTO schemeTO = new SchemeTO();
		schemeTO.setSchemeId(cardTO.getSchemeId());
		//call getSchemeDetails and save them into schemeTO
		schemeTO = wrapperClass.getSchemeDetails(schemeTO);
		double creditAmount = cardTO.getBalanceAmount();
		double interest = (creditAmount * (schemeTO.getInterestRate()/100));
		double interestMinimum = ((schemeTO.getMinimumAmount()*(schemeTO.getInterestRate()/100)));
		// Checks when button Make Payment should be disabled
		if(this.balanceAmount != 0){
			this.disable = false;
		}
		else{
			this.disable = true;
		}
		// Checks if paymentType is Full ('F') and calculate AmountPaid and Balance
		if(this.getPaymentType() == 'F'){
			this.setAmountPaid(creditAmount+interest);
			this.setBalance(0.0);
		}
		// Checks if the BalanceAmount>= to minimumAmount to be paid
		else if(this.getBalanceAmount()>=schemeTO.getMinimumAmount()){			
				this.setAmountPaid(schemeTO.getMinimumAmount());
				double calcBalance= (getBalanceAmount()-getAmountPaid());
				this.setBalance(calcBalance+interestMinimum);
		}
		else{
			//Checks if the BalanceAmount< to minimumAmount to be paid and disable button pay
			this.setAmountPaid(schemeTO.getMinimumAmount());
			this.setBalance(this.getBalanceAmount());
			this.disable = true;
		}
		return "ok";
	}
	/**
	 * 
	 * @Method Name: makePayment
	 * @Description: The action listener used to make payment
	 * @User: Carolina_406764
	 * @Return Type: String
	 * @return success or failure
	 * @throws Exception
	 */
	public String makePayment() {
		// Create instance of InfyCreditCardWrapper
		InfyCreditCardWrapper wrapperClass = new InfyCreditCardWrapper();
		// Create instance of PaymentTO
		PaymentTO paymentTO = new PaymentTO();
		// Concatenate the string into CardNo
		paymentTO.setCardNo(new Long(
				(cardNo[0] + cardNo[1] + cardNo[2] + cardNo[3])));
		// Assign a value to the respective paymentTO data members
		paymentTO.setCardStatus(this.cardStatus);
		paymentTO.setDateOfPayment(Calendar.getInstance());
		paymentTO.setAmountPaid(this.amountPaid);
		paymentTO.setPaymentType(this.paymentType);
		//call makePayment from wrapperClass and save it into paymentTO
		try{
			paymentTO = wrapperClass.makePayment(paymentTO);
			//Set a message for successful payment printing paymentId
			setMessage("Payment Successful...Payment Id: " + paymentTO.getPaymentId());
			//Disable message
			setRenderMessage(false);
			return "success";
		}
		catch(CannotUnblockCardException e){
			//Show the message
			setRenderMessage(true);
			setMessage(e.getMessage());
			return "failure";
		}
		catch(InvalidCardNoException e){
			//Show the message
			setRenderMessage(true);
			setMessage(e.getMessage());
			return "failure";
		}
		catch(Exception e){
			//Show the message
			setRenderMessage(true);
			setMessage(e.getMessage());
			return "failure";
		}
	}

}
