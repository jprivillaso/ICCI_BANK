package com.infy.icci.transferObjects;

import java.util.Calendar;

public class CardTO {

	private long cardNo;
	private int pin;
	private char schemeId;
	private double balanceAmount;
	private double cardAmount;
	private Calendar dateOfRegistration;
	private int customerId;
	
	/**
	 * 
	* Constructor CardTO
	 */
	
	public CardTO() {

	}
	
	public CardTO(long cardNo, int pin, char schemeId, double balanceAmount,
			double cardAmount, Calendar dateOfRegistration, int customerId) {
		this.cardNo = cardNo;
		this.pin = pin;
		this.schemeId = schemeId;
		this.balanceAmount = balanceAmount;
		this.cardAmount = cardAmount;
		this.dateOfRegistration = dateOfRegistration;
		this.customerId = customerId;
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
	 * @Method getPin
	 * @return pin 
	 */
	
	public int getPin() {
		return pin;
	}

	/**
	 * @User Carolina_406764
	 * @Method setPin
	 * @param pin the pin to set
	 */
	
	public void setPin(int pin) {
		this.pin = pin;
	}

	/**
	 * @User Carolina_406764
	 * @Method getSchemeId
	 * @return schemeId 
	 */
	
	public char getSchemeId() {
		return schemeId;
	}

	/**
	 * @User Carolina_406764
	 * @Method setSchemeId
	 * @param schemeId the schemeId to set
	 */
	
	public void setSchemeId(char schemeId) {
		this.schemeId = schemeId;
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

	/**
	 * @User Carolina_406764
	 * @Method getCardAmount
	 * @return cardAmount 
	 */
	
	public double getCardAmount() {
		return cardAmount;
	}

	/**
	 * @User Carolina_406764
	 * @Method setCardAmount
	 * @param cardAmount the cardAmount to set
	 */
	
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}

	/**
	 * @User Carolina_406764
	 * @Method getDateOfRegistration
	 * @return dateOfRegistration 
	 */
	
	public Calendar getDateOfRegistration() {
		return dateOfRegistration;
	}

	/**
	 * @User Carolina_406764
	 * @Method setDateOfRegistration
	 * @param dateOfRegistration the dateOfRegistration to set
	 */
	
	public void setDateOfRegistration(Calendar dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	/**
	 * @User Carolina_406764
	 * @Method getCustomerId
	 * @return customerId 
	 */
	
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @User Carolina_406764
	 * @Method setCustomerId
	 * @param customerId the customerId to set
	 */
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
