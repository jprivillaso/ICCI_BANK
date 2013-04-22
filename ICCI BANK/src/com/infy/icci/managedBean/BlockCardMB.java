package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class BlockCardMB {

	private String[] cardNo = new String[4];
	private String cause;
	private boolean disable;
	private List<SelectItem> causes;
	private String message;
	
	/**
	 * @User Juan_406762
	 * @Method BlockCardMB
	 */
	
	public BlockCardMB() {
		/* Initialize the required variables */
		disable = true;
		causes = new ArrayList<SelectItem>();
		/* Populate the causes List with the valid causes*/
		causes.add(new SelectItem("Theft", "Theft"));
		causes.add(new SelectItem("Payment not Done", "Payment not Done"));
		causes.add(new SelectItem("Other", "Other"));
	}
	
	/**
	 * @User Juan_406762
	 * @Method blockCard
	 * @return success or fail (the navigation rule)
	 */
	
	public String blockCard() {
		try 
		{
			BlockedCardTO to = new BlockedCardTO();
			Long cardNoTemp = new Long(cardNo[0]+cardNo[1]+cardNo[2]+cardNo[3]);
			/* Set the values of the BlockCardTO instance */
			to.setCardNo(cardNoTemp);
			to.setDescription(this.getCause());
			to.setDateOfBlock(new GregorianCalendar());
			to.setStatus('B');
			/* Invoke the method checkCardBlocked(BlockedCardTO) of the wrapper class. Send the to object
			 * as the parameter of the function
			 */
			BlockedCardTO toTemp = new InfyCreditCardWrapper().checkCardBlocked(to);
			this.setMessage("Card with number: "+toTemp.getCardNo()+" successfully blocked. " +
					"BlockId: "+toTemp.getBlockId());
			return "success";
		}
		catch(Exception exception) {
			this.setMessage(exception.getMessage());
			return "fail";
		}
	}
	
	/**
	 * @User Juan_406762
	 * @Method checkCause
	 * @Description Enable or disable the reason field depending on
	 * 				the cause for blocking selected
	 * @return void
	 */
	
	public void checkCause(ValueChangeEvent event) {
		String reason = (String) event.getNewValue();
		if(reason.equals("Other")) {
			disable = false;
		}
		else {
			disable = true;
		}
		/* Set the cause property with the reason selected */
		this.setCause(reason);
	}
	
	/**
	 * @User ricardo_406737
	 * @Method getCardNo
	 * @return cardNo 
	 */
	
	public String[] getCardNo() {
		return cardNo;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	
	public void setCardNo(String[] cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCause
	 * @return cause 
	 */
	
	public String getCause() {
		return cause;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCause
	 * @param cause the cause to set
	 */
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * @User ricardo_406737
	 * @Method isDisable
	 * @return disable 
	 */
	
	public boolean isDisable() {
		return disable;
	}
	/**
	 * @User ricardo_406737
	 * @Method setDisable
	 * @param disable the disable to set
	 */
	
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCauses
	 * @return causes 
	 */
	
	public List<SelectItem> getCauses() {
		return causes;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCauses
	 * @param causes the causes to set
	 */
	
	public void setCauses(List<SelectItem> causes) {
		this.causes = causes;
	}
	/**
	 * @User ricardo_406737
	 * @Method getMessage
	 * @return message 
	 */
	
	public String getMessage() {
		return message;
	}
	/**
	 * @User ricardo_406737
	 * @Method setMessage
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
}
