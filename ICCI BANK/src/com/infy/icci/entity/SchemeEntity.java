/*
 * SchemeEntity.java
 */
package com.infy.icci.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Entity to map the ICCI_Scheme table
* Project Name: ICCI BANK
* User: juan_406752
* Date: Oct 18, 2012
*/
@Entity
@Table(name="ICCI_SCHEME")
public class SchemeEntity {

	@Id
	private char schemeId;
	private double schemeAmount;
	private double minimumAmount;
	private double interestRate;
	
	/**
	 * @User ricardo_406737
	 * @Method getSchemeId
	 * @return schemeId 
	 */
	
	public char getSchemeId() {
		return schemeId;
	}
	/**
	 * @User ricardo_406737
	 * @Method setSchemeId
	 * @param schemeId the schemeId to set
	 */
	
	public void setSchemeId(char schemeId) {
		this.schemeId = schemeId;
	}
	/**
	 * @User ricardo_406737
	 * @Method getSchemeAmount
	 * @return schemeAmount 
	 */
	
	public double getSchemeAmount() {
		return schemeAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method setSchemeAmount
	 * @param schemeAmount the schemeAmount to set
	 */
	
	public void setSchemeAmount(double schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method getMinimumAmount
	 * @return minimumAmount 
	 */
	
	public double getMinimumAmount() {
		return minimumAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method setMinimumAmount
	 * @param minimumAmount the minimumAmount to set
	 */
	
	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
	/**
	 * @User ricardo_406737
	 * @Method getInterestRate
	 * @return interestRate 
	 */
	
	public double getInterestRate() {
		return interestRate;
	}
	/**
	 * @User ricardo_406737
	 * @Method setInterestRate
	 * @param interestRate the interestRate to set
	 */
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}
