package com.infy.icci.exceptions;

public class LowBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LowBalanceException() {
		super("You don't have enough balance in your card for this transaction.");
	}

}
