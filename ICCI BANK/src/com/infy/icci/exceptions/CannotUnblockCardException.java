package com.infy.icci.exceptions;

public class CannotUnblockCardException extends Exception {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CannotUnblockCardException(){
		super("The card can't be unblocked");
	}
}
