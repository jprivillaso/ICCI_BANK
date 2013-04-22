/*
 * PaymentEntity.java
 */

package com.infy.icci.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity to map the ICCI_Payment table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_PAYMENT")
public class PaymentEntity {
	/**
	 * PaymentId is generated using a sequence
	 */
	@Id
	@SequenceGenerator(name="paymentId_seq", sequenceName="paymentId_seq_DB", initialValue=6005, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="paymentId_seq")
	private int paymentId;
	/**
	 * Relation Payment-Card
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cardNo")
	private CardEntity card;
	private char paymentType;
	private double amountPaid;
	@Temporal(TemporalType.DATE)	
	private Calendar dateOfpayment;
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
	 * @Method getCard
	 * @return card 
	 */
	public CardEntity getCard() {
		return card;
	}
	/**
	 * @User Carolina_406764
	 * @Method setCard
	 * @param card the card to set
	 */
	public void setCard(CardEntity card) {
		this.card = card;
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
	 * @Method getDateOfpayment
	 * @return dateOfpayment 
	 */
	public Calendar getDateOfpayment() {
		return dateOfpayment;
	}
	/**
	 * @User Carolina_406764
	 * @Method setDateOfpayment
	 * @param dateOfpayment the dateOfpayment to set
	 */
	public void setDateOfpayment(Calendar dateOfpayment) {
		this.dateOfpayment = dateOfpayment;
	}
}
