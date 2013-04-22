package com.infy.icci.transferObjects;

public class SchemeTO {

	private char schemeId;
	private double schemeAmount;
	private double minimumAmount;
	private double interestRate;
	
	/**
	 * @User juan_406752
	 * @Method getSchemeId
	 * @return schemeId 
	 */	
	public char getSchemeId() {
		return schemeId;
	}
	
	/**
	 * @User juan_406752
	 * @Method setSchemeId
	 * @param the schemeId to set
	 */	
	public void setSchemeId(char schemeId) {
		this.schemeId = schemeId;
	}
	
	/**
	 * @User juan_406752
	 * @Method getSchemeAmount
	 * @return schemeAmount 
	 */	
	public double getSchemeAmount() {
		return schemeAmount;
	}
	
	/**
	 * @User juan_406752
	 * @Method setSchemeAmount
	 * @param the schemeAmount to set
	 */	
	public void setSchemeAmount(double schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	
	/**
	 * @User juan_406752
	 * @Method getMinimumAmount
	 * @return minimumAmount 
	 */	
	public double getMinimumAmount() {
		return minimumAmount;
	}
	
	/**
	 * @User juan_406752
	 * @Method setMinimumAmount
	 * @param the minimumAmount to set
	 */	
	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
	
	/**
	 * @User juan_406752
	 * @Method getInterestRate
	 * @return interestRate 
	 */	
	public double getInterestRate() {
		return interestRate;
	}
	
	/**
	 * @User juan_406752
	 * @Method setInterestRate
	 * @param the interestRate to set
	 */	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}
