/*
 * TransactionEntity.java
 */
package com.infy.icci.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Entity to map the ICCI_Transaction table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_Transaction")
public class TransactionEntity {
	/**
	 * transactionId sequence
	 */
	@Id
	//Create the sequence in the data base
	@SequenceGenerator(name="transactionId_seq", sequenceName="transactionId_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transactionId_seq")
	private int transactionId;
	@Temporal(TemporalType.DATE)
	private Calendar dateOfTransaction;
	private String description;
	private double amount;
	private long cardNo;
	
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
}
