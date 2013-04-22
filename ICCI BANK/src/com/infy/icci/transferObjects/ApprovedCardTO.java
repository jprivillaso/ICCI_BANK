package com.infy.icci.transferObjects;

import java.util.Calendar;

public class ApprovedCardTO {

	private String customerName;
	private double schemeAmt;
	private String paymentType;
	private Calendar dateOfBlock;
	/**
	 * @User Carolina_406764
	 * @Method getCustomerName
	 * @return customerName 
	 */
	
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @User Carolina_406764
	 * @Method setCustomerName
	 * @param customerName the customerName to set
	 */
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @User Carolina_406764
	 * @Method getSchemeAmt
	 * @return schemeAmt 
	 */
	
	public double getSchemeAmt() {
		return schemeAmt;
	}
	/**
	 * @User Carolina_406764
	 * @Method setSchemeAmt
	 * @param schemeAmt the schemeAmt to set
	 */
	
	public void setSchemeAmt(double schemeAmt) {
		this.schemeAmt = schemeAmt;
	}
	/**
	 * @User Carolina_406764
	 * @Method getPaymentType
	 * @return paymentType 
	 */
	
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @User Carolina_406764
	 * @Method setPaymentType
	 * @param paymentType the paymentType to set
	 */
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @User Carolina_406764
	 * @Method getDateOfBlock
	 * @return dateOfBlock 
	 */
	
	public Calendar getDateOfBlock() {
		return dateOfBlock;
	}
	/**
	 * @User Carolina_406764
	 * @Method setDateOfBlock
	 * @param dateOfBlock the dateOfBlock to set
	 */
	
	public void setDateOfBlock(Calendar dateOfBlock) {
		this.dateOfBlock = dateOfBlock;
	}
}
