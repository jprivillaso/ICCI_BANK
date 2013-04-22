package com.infy.icci.transferObjects;

import java.util.Calendar;

/**
 * 
* @author Carolina_406764
*
 */

public class PaymentTO {

	private int paymentId;
	private long cardNo;
	private char paymentType;
	private double amountPaid;
	private Calendar dateOfPayment;
	private String cardStatus;
	/**
	 * @User Carolina_406764
	 * @Method getPaymentId
	 * @return paymentId 
	 */
	
	public int getPaymentId() {
		return paymentId;
	}
	/**
	 * @User Carolina_406764
	 * @Method setPaymentId
	 * @param paymentId the paymentId to set
	 */
	
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	 * @Method getPaymentType
	 * @return paymentType 
	 */
	
	public char getPaymentType() {
		return paymentType;
	}
	/**
	 * @User Carolina_406764
	 * @Method setPaymentType
	 * @param paymentType the paymentType to set
	 */
	
	public void setPaymentType(char paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @User Carolina_406764
	 * @Method getAmountPaid
	 * @return amountPaid 
	 */
	
	public double getAmountPaid() {
		return amountPaid;
	}
	/**
	 * @User Carolina_406764
	 * @Method setAmountPaid
	 * @param amountPaid the amountPaid to set
	 */
	
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	/**
	 * @User Carolina_406764
	 * @Method getDateOfPayment
	 * @return dateOfPayment 
	 */
	
	public Calendar getDateOfPayment() {
		return dateOfPayment;
	}
	/**
	 * @User Carolina_406764
	 * @Method setDateOfPayment
	 * @param dateOfPayment the dateOfPayment to set
	 */
	
	public void setDateOfPayment(Calendar dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
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
	 * @param cardStatus the cardStatus to set
	 */
	
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
}
