package com.infy.icci.exceptions;

public class NoRevenueFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRevenueFoundException(){
		super("Not found payments in those dates");
	}
}
