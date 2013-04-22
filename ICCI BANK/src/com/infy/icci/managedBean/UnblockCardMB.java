/*
 * UnblockCardMB.java
 */
package com.infy.icci.managedBean;

import com.infy.icci.exceptions.CardNotBlockedException;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

/**
* 
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 10, 2012
 */
public class UnblockCardMB {
	
	private String[] cardNo;
	private String message;
	
	/**
	* Constructor
	*/
	public UnblockCardMB(){
		cardNo=new String[4];
		for (int i = 0; i < cardNo.length; i++) {
			cardNo[i]="";
		}
	}
	/**
	 * 
	* @Method Name: unblockCard
	* @Description: Method to get the cardNo and unBlocked it.
	* @User: andres_406763
	* @Return Type: String
	* @return success or failure
	 */
	public String unblockCard(){

		//Concatenate the cardNo
		String cardNoTemp = cardNo[0]+cardNo[1]+cardNo[2]+cardNo[3];
		try{
			long cardNo = Long.parseLong(cardNoTemp);
			//Create new Transfer object
			BlockedCardTO to = new BlockedCardTO();
			//Set the cardNo
			to.setCardNo(cardNo);
			//Instantiate wrapper
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			BlockedCardTO resultTO= wrapper.unblockCard(to);
			message="Card with cardNo: "+resultTO.getCardNo()+
											" unblocked successfully";
			return "success";
		}
		catch(InvalidCardNoException e){
			message=e.getMessage();
			return "fail";
		}
		catch(CardNotBlockedException e){
			message=e.getMessage();
			return "fail";
		}
		catch(NumberFormatException e){
			message= "Numeric Field: Please enter only numbers for Card No.";
			return "fail";
		}
		catch(Exception e){
			message= e.getMessage();
			return "fail";
		}
	}
	
	/**
	 * @User andres_406763
	 * @Method getCardNo
	 * @return cardNo 
	 */
	public String[] getCardNo() {
		return cardNo;
	}
	
	/**
	 * @User andres_406763
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String[] cardNo) {
		this.cardNo = cardNo;
	}
	
	/**
	 * @User andres_406763
	 * @Method getMessage
	 * @return message 
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @User andres_406763
	 * @Method setMessage
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
