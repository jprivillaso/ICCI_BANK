package com.infy.icci.managedBean;

import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.PaymentTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class ViewCardDetailsMB {

	private long cardNo;
	private String customerName;
	private double balanceAmount;
	private Calendar lastPaymentDate;
	private String cardStatus;
	private char schemeId;
	private String message;
	private double schemeAmount;
	private double cardAmount;
	private double rateOfInterest;
	
	/**
	 * @Description: To retrieve the details of the card whose card number is obtained from session object
	 * @User: leslie_406760
	 * Constructor
	 */
	public ViewCardDetailsMB() {
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Long cardNumber = (Long)session.getAttribute("cardNo");
		CardTO cardTO = new CardTO();
		cardTO.setCardNo(cardNumber);
		//cardTO.setCardNo(3678220123987890L);
		SchemeTO schemeTO = new SchemeTO();		
		PaymentTO paymentTO = new PaymentTO();		
		CustomerTO customerTO = new CustomerTO();
		
		try {
			cardTO = wrapper.getCardDetails(cardTO);			
			schemeTO.setSchemeId(cardTO.getSchemeId());
			schemeTO = wrapper.getSchemeDetails(schemeTO);			
			customerTO.setCustomerId(cardTO.getCustomerId());
			customerTO = wrapper.getCustomerDetails(customerTO);			
			paymentTO.setCardNo(cardTO.getCardNo());
			paymentTO = wrapper.getPaymentDetails(cardTO);
			
			if (cardTO == null ||schemeTO == null ||customerTO == null ||paymentTO == null ) {
				this.setMessage("Card Details not found");
			}else{
				this.setCardNo(cardTO.getCardNo());
				this.setBalanceAmount(cardTO.getBalanceAmount());
				this.setCardAmount(cardTO.getCardAmount());
				this.setSchemeId(schemeTO.getSchemeId());
				this.setSchemeAmount(schemeTO.getSchemeAmount());
				this.setRateOfInterest(schemeTO.getInterestRate());
				this.setCustomerName(customerTO.getName());
				if (paymentTO.getDateOfPayment()!= null) {
					this.setLastPaymentDate(paymentTO.getDateOfPayment());					
				}else{
					this.lastPaymentDate = Calendar.getInstance();
					lastPaymentDate.add(Calendar.YEAR, 100);
				}
				this.setCardStatus(paymentTO.getCardStatus());					
			}			
		} catch (Exception e) {
			this.setMessage(e.getMessage());
		}
		
	}
	/**
	 * 
	* @Method Name: getCardDetails
	* @Description: 
	* @User: leslie_406760
	* @Return Type: String
	* @return
	 */
	/*public String getCardDetails(){
		return "success";	
	}*/
	
	/**
	 * @User leslie_406760
	 * @Method getCardNo
	 * @return cardNo 
	 */
	
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @User leslie_406760
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User leslie_406760
	 * @Method getCustomerName
	 * @return customerName 
	 */
	
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @User leslie_406760
	 * @Method setCustomerName
	 * @param customerName the customerName to set
	 */
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @User leslie_406760
	 * @Method getBalanceAmount
	 * @return balanceAmount 
	 */
	
	public double getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * @User leslie_406760
	 * @Method setBalanceAmount
	 * @param balanceAmount the balanceAmount to set
	 */
	
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	/**
	 * @User leslie_406760
	 * @Method getLastPaymentDate
	 * @return lastPaymentDate 
	 */
	
	public Calendar getLastPaymentDate() {
		return lastPaymentDate;
	}
	/**
	 * @User leslie_406760
	 * @Method setLastPaymentDate
	 * @param lastPaymentDate the lastPaymentDate to set
	 */
	
	public void setLastPaymentDate(Calendar lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	/**
	 * @User leslie_406760
	 * @Method getCardStatus
	 * @return cardStatus 
	 */
	
	public String getCardStatus() {
		return cardStatus;
	}
	/**
	 * @User leslie_406760
	 * @Method setCardStatus
	 * @param cardStatus the cardStatus to set
	 */
	
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	/**
	 * @User leslie_406760
	 * @Method getSchemeId
	 * @return schemeId 
	 */
	
	public char getSchemeId() {
		return schemeId;
	}
	/**
	 * @User leslie_406760
	 * @Method setSchemeId
	 * @param schemeId the schemeId to set
	 */
	
	public void setSchemeId(char schemeId) {
		this.schemeId = schemeId;
	}
	/**
	 * @User leslie_406760
	 * @Method getMessage
	 * @return message 
	 */
	
	public String getMessage() {
		return message;
	}
	/**
	 * @User leslie_406760
	 * @Method setMessage
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @User leslie_406760
	 * @Method getSchemeAmount
	 * @return schemeAmount 
	 */
	
	public double getSchemeAmount() {
		return schemeAmount;
	}
	/**
	 * @User leslie_406760
	 * @Method setSchemeAmount
	 * @param schemeAmount the schemeAmount to set
	 */
	
	public void setSchemeAmount(double schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	/**
	 * @User leslie_406760
	 * @Method getCardAmount
	 * @return cardAmount 
	 */
	
	public double getCardAmount() {
		return cardAmount;
	}
	/**
	 * @User leslie_406760
	 * @Method setCardAmount
	 * @param cardAmount the cardAmount to set
	 */
	
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	/**
	 * @User leslie_406760
	 * @Method getRateOfInterest
	 * @return rateOfInterest 
	 */
	
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	/**
	 * @User leslie_406760
	 * @Method setRateOfInterest
	 * @param rateOfInterest the rateOfInterest to set
	 */
	
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
}
