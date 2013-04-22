package com.infy.icci.exceptions;

public class NoBalanceToBePaidException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBalanceToBePaidException(){
		super("No balance to be Paid");
	}
}
