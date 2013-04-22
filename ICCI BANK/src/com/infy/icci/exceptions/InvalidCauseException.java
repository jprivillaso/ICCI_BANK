package com.infy.icci.exceptions;

public class InvalidCauseException extends Exception {
	
	public InvalidCauseException() {
		super("Invalid cause: The card cannot be blocked for this reason.");
	}

}
