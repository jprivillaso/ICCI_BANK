package com.infy.icci.managedBean;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;
/**
 * @author Alejandro_406765
 */
public class CardApplicationMB {
	//Data members
	private String name;
	private String email;
	private String address;
	private String phone;
	private int applicationId;
	private String randomCharacter;
	private String enteredCharacter;
	private List<CardApplicationTO> cardTOList;
	private String message;
	
	/**
	* @Method Name: CardApplicationMB
	* @Description: Constructor setting the values of cardTOList 
	* 				to be used in view ApproveCard.jsp
	* @User: Alejandro_406765
	*/
	public CardApplicationMB(){
		//Generate the random Character
		SecureRandom sRandom = new SecureRandom();
		String alphanumericSequence = new BigInteger(130,sRandom).toString(32);
		//Cut the string return in the 8th number
		setRandomCharacter(alphanumericSequence.substring(0,8));
		//Set values of cardTOList List
		cardTOList = getCardTOlist();	
	}
	/**
	* @Method Name: getCardTOlist
	* @Description: Method implemented to get the values of list 
	* 				cardTOList from InfyCreditCardWrapper 
	* @User: Alejandro_406765
	* @Return Type: List<CardApplicationTO>
	* @param void
	*/
	public List<CardApplicationTO> getCardTOlist(){
		//List to be return with the new values
		List<CardApplicationTO> resultList = new ArrayList<CardApplicationTO>();
		try{
			// Create instance of wrapperService
			InfyCreditCardWrapper wrapperService = new InfyCreditCardWrapper(); 
			/*Get list with customers applying for a card from method 
			retrieveAppliedCardDetails*/
			resultList = wrapperService.retrieveAppliedCardDetails();
			return resultList;
		}
		catch(Exception e){
			this.setMessage(e.getMessage());
			return null;
		}
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardTOList
	 * @return cardTOList
	 */
	public List<CardApplicationTO> getCardTOList() {
		return cardTOList;
	}
	/**
	 * @User ricardo_406737
	 * @Method getName
	 * @return name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * @User ricardo_406737
	 * @Method setName
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @User ricardo_406737
	 * @Method getEmail
	 * @return email 
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @User ricardo_406737
	 * @Method setEmail
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @User ricardo_406737
	 * @Method getAddress
	 * @return address 
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @User ricardo_406737
	 * @Method setAddress
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @User ricardo_406737
	 * @Method getPhone
	 * @return phone 
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @User ricardo_406737
	 * @Method setPhone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @User ricardo_406737
	 * @Method getApplicationId
	 * @return applicationId 
	 */
	public int getApplicationId() {
		return applicationId;
	}
	/**
	 * @User ricardo_406737
	 * @Method setApplicationId
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @User ricardo_406737
	 * @Method getRandomCharacter
	 * @return randomCharacter 
	 */
	public String getRandomCharacter() {
		return randomCharacter;
	}
	/**
	 * @User ricardo_406737
	 * @Method setRandomCharacter
	 * @param randomCharacter the randomCharacter to set
	 */
	public void setRandomCharacter(String randomCharacter) {
		this.randomCharacter = randomCharacter;
	}
	/**
	 * @User ricardo_406737
	 * @Method getEnteredCharacter
	 * @return enteredCharacter 
	 */
	public String getEnteredCharacter() {
		return enteredCharacter;
	}
	/**
	 * @User ricardo_406737
	 * @Method setEnteredCharacter
	 * @param enteredCharacter the enteredCharacter to set
	 */
	public void setEnteredCharacter(String enteredCharacter) {
		this.enteredCharacter = enteredCharacter;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardTOList
	 * @param cardTOList the cardTOList to set
	 */
	public void setCardTOList(List<CardApplicationTO> cardTOList) {
		this.cardTOList = cardTOList;
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
	/**
	* @Method Name: applayCard
	* @Description: Action listener triggered when apply button is pressed in 
	* 				ApplyCard.jsp to send the information of the form
	* @User: Alejandro_406765
	* @Return Type: String
	* 		on Success - "success"
	* 		on Fail    - "fail"
	* @param void
	*/
	public String applayCard(){
		//Create instance of CardApplicationTO and populated with the local values
		CardApplicationTO cardApplTo = new CardApplicationTO();
		cardApplTo.setAddress(this.getAddress());
		cardApplTo.setEmail(this.getEmail());
		cardApplTo.setName(this.getName());
		cardApplTo.setPhone(this.getPhone());
		
		// Create instance of wrapperService
		InfyCreditCardWrapper wrapperService = new InfyCreditCardWrapper(); 
		try {
			if(rightCharacter()){
				// Create object to return to jsf the application Id
				CardApplicationTO cardAppTO_returned = wrapperService.applyCard(cardApplTo);
				String appId = String.valueOf(cardAppTO_returned.getApplicationId());
				// Set the message to return to jsf
				this.setMessage("The request is logged successfully with Id " + appId);
				return "success";
			}
		} catch (Exception e) {
			//Set message in case of error in the service
			this.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return "fail";
	}
	/**
	* @Method Name: approveCard
	* @Description: Action listener triggered when approve button is pressed in 
	* 				ApproveCard.jsp to approve the cards selected n checkboxes
	* @User: Alejandro_406765
	* @Return Type: String
	* 		on Success - "success"
	* 		on Fail    - "fail"
	* @param void
	*/
	public String approveCard(){
		if(cardTOList.size()==0){
			this.setMessage("No data to approve");
			return "fail";
		}
		//verify if cardTOList has any value or not
		if(cardTOList.size()!=0){
			//Create an iterator to get the rows into the new list
			Iterator<CardApplicationTO> iterator = cardTOList.iterator();
			while(iterator.hasNext()){
				// Create a new value to store the current object of the list
				CardApplicationTO currentObject = iterator.next();
				//Verify if the object has its attribute applicationSelected equals true
				if(currentObject.isApplicationSelected()){
					//This object is send to the wrapperService throw approveCard
					// Create instance of wrapperService
					InfyCreditCardWrapper wrapperService = new InfyCreditCardWrapper();
					try{
						wrapperService.approveCard((int)currentObject.getApplicationId());
						this.setMessage("All the selected Card have been approved");
						return "success";
					}catch(NoDataFoundException e){
						this.setMessage(e.getMessage());
						return "fail";
					}
				}
			}
		}
		this.setMessage("Please select the application(s) you want to Approve.");
		return "fail";
	}
	/**
	* @Method Name: rightCharacter
	* @Description: Method called to determinate if the Characters entered 
	* 				are equals to the characters generated. 
	* @User: Alejandro_406765
	* @Return Type: boolean
	* 		same - true
	* 		different - false
	* @param void
	*/
	public boolean rightCharacter(){
		//Store the random value generated
		String C_generated = this.getRandomCharacter();
		//Store locally the entered value
		String C_entered = this.getEnteredCharacter();
		//Compare the values, if different return the appropiate message
		if(C_generated.equals(C_entered))
			return true;
		else{
			this.setMessage("Enter the right characters given");
			return false;
		}
	}
}
