package com.infy.icci.transferObjects;

import java.util.Calendar;

public class TransactionTO {

	private int transactionId;
	private Calendar dateOfTransaction;
	private String description;
	private double amount;
	private long cardNo;
	private double balanceAmount;
	
	/**
	 * @User Carolina_406764
	 * @Method getTransactionId
	 * @return transactionId 
	 */
	
	public int getTransactionId() {
		return transactionId;
	}
	/**
	 * @User Carolina_406764
	 * @Method setTransactionId
	 * @param transactionId the transactionId to set
	 */
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @User Carolina_406764
	 * @Method getDateOfTransaction
	 * @return dateOfTransaction 
	 */
	
	public Calendar getDateOfTransaction() {
		return dateOfTransaction;
	}
	/**
	 * @User Carolina_406764
	 * @Method setDateOfTransaction
	 * @param dateOfTransaction the dateOfTransaction to set
	 */
	
	public void setDateOfTransaction(Calendar dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	/**
	 * @User Carolina_406764
	 * @Method getDescription
	 * @return description 
	 */
	
	public String getDescription() {
		return description;
	}
	/**
	 * @User Carolina_406764
	 * @Method setDescription
	 * @param description the description to set
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @User Carolina_406764
	 * @Method getAmount
	 * @return amount 
	 */
	
	public double getAmount() {
		return amount;
	}
	/**
	 * @User Carolina_406764
	 * @Method setAmount
	 * @param amount the amount to set
	 */
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @User Carolina_406764
	 * @Method getCardNo
	 * @return cardNo 
	 */
	
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @User Carolina_406764
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User Carolina_406764
	 * @Method getBalanceAmount
	 * @return balanceAmount 
	 */
	
	public double getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * @User Carolina_406764
	 * @Method setBalanceAmount
	 * @param balanceAmount the balanceAmount to set
	 */
	
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
