package com.infy.icci.exceptions;

public class CardAlreadyBlockedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardAlreadyBlockedException(){
		super("The card is already blocked");
	}
}
