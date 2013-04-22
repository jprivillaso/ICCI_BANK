package com.infy.icci.exceptions;

public class WrongDateException extends Exception {
	
	public WrongDateException(){
		super("The From date can't be greater than the To date");
	}
}
