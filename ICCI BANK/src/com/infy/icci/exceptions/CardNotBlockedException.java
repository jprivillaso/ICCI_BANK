package com.infy.icci.exceptions;

public class CardNotBlockedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardNotBlockedException(){
		super("The card is not blocked");
	}
}
