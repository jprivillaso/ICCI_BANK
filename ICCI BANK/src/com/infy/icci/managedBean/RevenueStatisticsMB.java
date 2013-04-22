package com.infy.icci.managedBean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class RevenueStatisticsMB {

	private Calendar fromDate;
	private Calendar toDate;
	private double profit;
	private double expenditure;
	private double revenue;
	private String message;
	/**
	 * 
	 * Constructor
	 */
	public RevenueStatisticsMB() {
		this.setProfit(0.0);
		this.setExpenditure(0.0);
		this.setRevenue(0.0);
		fromDate = new GregorianCalendar();
		toDate = new GregorianCalendar();
	}
	
	/**
	 * 
	* @Method Name: getDetails
	* @Description: Gets the finance details of the company.
	* The method invocation returns a List of financial details like profit, expenditure and revenue.
	* @User: leslie_406760
	* @Return Type: String
	* @return
	 */
	public String getDetails(){
		InfyCreditCardWrapper wrapper =  new InfyCreditCardWrapper();
		try {			
			List<Double> list = wrapper.getRevenueDetails(this.fromDate, this.toDate);
			this.setRevenue(list.get(0));
			this.setExpenditure(list.get(1));
			this.setProfit(list.get(2));
			return "success";
		} catch (Exception e) {
			this.setMessage(e.getMessage());
			this.setProfit(0.0);
			this.setExpenditure(0.0);
			this.setRevenue(0.0);
			return "failure";
		}
	}
	
	/**
	 * @User ricardo_406737
	 * @Method getFromDate
	 * @return fromDate 
	 */
	
	public Calendar getFromDate() {
		return fromDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method setFromDate
	 * @param fromDate the fromDate to set
	 */
	
	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method getToDate
	 * @return toDate 
	 */
	
	public Calendar getToDate() {
		return toDate;
	}
	/**
	 * @User leslie_406760
	 * @Method getMessage
	 * @return message 
	 */
	
	public String getMessage() {
		return message;
	}

	/**
	 * @User leslie_406760
	 * @Method setMessage
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @User ricardo_406737
	 * @Method setToDate
	 * @param toDate the toDate to set
	 */
	
	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method getProfit
	 * @return profit 
	 */
	
	public double getProfit() {
		return profit;
	}
	/**
	 * @User ricardo_406737
	 * @Method setProfit
	 * @param profit the profit to set
	 */
	
	public void setProfit(double profit) {
		this.profit = profit;
	}
	/**
	 * @User ricardo_406737
	 * @Method getExpenditure
	 * @return expenditure 
	 */
	
	public double getExpenditure() {
		return expenditure;
	}
	/**
	 * @User ricardo_406737
	 * @Method setExpenditure
	 * @param expenditure the expenditure to set
	 */
	
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	/**
	 * @User ricardo_406737
	 * @Method getRevenue
	 * @return revenue 
	 */
	
	public double getRevenue() {
		return revenue;
	}
	/**
	 * @User ricardo_406737
	 * @Method setRevenue
	 * @param revenue the revenue to set
	 */
	
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
}
