package com.infy.icci.exceptions;

public class InvalidLoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidLoginException() {
		super("Invalid User Name or Password.");
	}

}
