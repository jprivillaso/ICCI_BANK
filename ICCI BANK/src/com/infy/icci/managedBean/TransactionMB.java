package com.infy.icci.managedBean;

import java.util.Calendar;

import com.infy.icci.transferObjects.TransactionTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class TransactionMB {

	private int transactionId;
	private Calendar dateOfTransaction;
	private String description;
	private double amount;
	private long cardNo;
	private double balanceAmount;
	private String errorMessage;
	
	/**
	 * 
	* @Method Name: saveTransaction
	* @Description: sends the information to model to save the transaction made by the customer
	* @User: ricardo_406737
	* @Return Type: String
	* @param 
	* @return
	 */
	public String saveTransaction(){
		try{
			//Setting the parameters of the TransactionTO used to send information to InfyCreditCardWrapper
			TransactionTO transaction = new TransactionTO();
			transaction.setAmount(this.getAmount());
			transaction.setDescription(this.getDescription());
			//Calling saveTransaction method from InfyCreditCardWrapper
			transaction = new InfyCreditCardWrapper().saveTransaction(transaction);
			//updating the bean information with the information received from InfyCreditCardWrapper
			this.setTransactionId(transaction.getTransactionId());
			this.setDateOfTransaction(transaction.getDateOfTransaction());
			this.setBalanceAmount(transaction.getBalanceAmount());
			return "success";
		}catch(Exception exception){
			exception.printStackTrace();
			this.setErrorMessage(exception.getMessage());
			return "fail";
		}
	}
	
	
	/**
	 * @User ricardo_406737
	 * @Method getTransactionId
	 * @return transactionId 
	 */
	
	public int getTransactionId() {
		return transactionId;
	}
	/**
	 * @User ricardo_406737
	 * @Method setTransactionId
	 * @param transactionId the transactionId to set
	 */
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @User ricardo_406737
	 * @Method getDateOfTransaction
	 * @return dateOfTransaction 
	 */
	
	public Calendar getDateOfTransaction() {
		return dateOfTransaction;
	}
	/**
	 * @User ricardo_406737
	 * @Method setDateOfTransaction
	 * @param dateOfTransaction the dateOfTransaction to set
	 */
	
	public void setDateOfTransaction(Calendar dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	/**
	 * @User ricardo_406737
	 * @Method getDescription
	 * @return description 
	 */
	
	public String getDescription() {
		return description;
	}
	/**
	 * @User ricardo_406737
	 * @Method setDescription
	 * @param description the description to set
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @User ricardo_406737
	 * @Method getAmount
	 * @return amount 
	 */
	
	public double getAmount() {
		return amount;
	}
	/**
	 * @User ricardo_406737
	 * @Method setAmount
	 * @param amount the amount to set
	 */
	
	public void setAmount(double amount) {
		this.amount = amount;
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
	 * @Method getErrorMessage
	 * @return errorMessage 
	 */
	
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @User ricardo_406737
	 * @Method setErrorMessage
	 * @param errorMessage the errorMessage to set
	 */
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
