package com.infy.icci.exceptions;

/**
 * 
* Project Name: ICCI BANK
* User: juan_406752
* Date: Oct 18, 2012
 */
public class SchemeAlreadyChosenException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	* Constructor
	* This constructor calls the super and send the
	* exception message
	*/
	public SchemeAlreadyChosenException(){
		super("Scheme Already Chosen");
	}
}
