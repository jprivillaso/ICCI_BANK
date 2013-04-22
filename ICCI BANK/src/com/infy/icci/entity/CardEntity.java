/*
 * CardEntity.java
 */
package com.infy.icci.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Entity to map the ICCI_Card table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_CARD")
public class CardEntity {
	
	@Id
	private long cardNo;
	private int pin;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="schemeId")
	private SchemeEntity scheme;
	private double balanceAmount;
	private double cardAmount;
	@Temporal(TemporalType.DATE)
	private Calendar dateOfRegistration;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customerId")
	private CustomerEntity customer;
	/**
	 * @User andres_406763
	 * @Method getCardNo
	 * @return cardNo 
	 */
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @User andres_406763
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User andres_406763
	 * @Method getPin
	 * @return pin 
	 */
	public int getPin() {
		return pin;
	}
	/**
	 * @User andres_406763
	 * @Method setPin
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
	/**
	 * @User andres_406763
	 * @Method getScheme
	 * @return scheme 
	 */
	public SchemeEntity getScheme() {
		return scheme;
	}
	/**
	 * @User andres_406763
	 * @Method setScheme
	 * @param scheme the scheme to set
	 */
	public void setScheme(SchemeEntity scheme) {
		this.scheme = scheme;
	}
	/**
	 * @User andres_406763
	 * @Method getBalanceAmount
	 * @return balanceAmount 
	 */
	public double getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * @User andres_406763
	 * @Method setBalanceAmount
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	/**
	 * @User andres_406763
	 * @Method getCardAmount
	 * @return cardAmount 
	 */
	public double getCardAmount() {
		return cardAmount;
	}
	/**
	 * @User andres_406763
	 * @Method setCardAmount
	 * @param cardAmount the cardAmount to set
	 */
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	/**
	 * @User andres_406763
	 * @Method getDateOfRegistration
	 * @return dateOfRegistration 
	 */
	public Calendar getDateOfRegistration() {
		return dateOfRegistration;
	}
	/**
	 * @User andres_406763
	 * @Method setDateOfRegistration
	 * @param dateOfRegistration the dateOfRegistration to set
	 */
	public void setDateOfRegistration(Calendar dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	/**
	 * @User andres_406763
	 * @Method getCustomer
	 * @return customer 
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}
	/**
	 * @User andres_406763
	 * @Method setCustomer
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
}
