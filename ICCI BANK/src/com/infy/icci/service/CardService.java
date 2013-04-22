/**
* Project Name: ICCI BANK
* User: leslie_406760
* Date: Oct 10, 2012
*/

package com.infy.icci.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.entity.CardApplicationEntity;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.CustomerEntity;
import com.infy.icci.entity.LoginEntity;
import com.infy.icci.entity.SchemeEntity;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.transferObjects.CardTO;

/**
 * @author leslie_406760
 *
 */

public class CardService {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	/**
	* Constructor
	* @author andres_406763
	*/
	public CardService(){
		emf=Persistence.createEntityManagerFactory("ICCI BANK");
	}
	
	/**
	 * 
	* @Method Name: getCardDetails
	* @Description: Retrieve and return the details of the card whose CardNo is specified as part of CardTO.
	* @User: leslie_406760
	* @Return Type: CardTO
	* @param to
	* @return
	 * @throws InvalidCardNoException 
	* @throws Exception
	 */
	public CardTO getCardDetails(CardTO to) throws InvalidCardNoException {
		em=null;
		CardTO cardToResult = null;
		try{			
			em=emf.createEntityManager();
			em.getTransaction().begin();
			CardEntity entity = em.find(CardEntity.class, to.getCardNo());
			if(entity != null){
				cardToResult = new CardTO();
				cardToResult.setCardNo(entity.getCardNo());
				cardToResult.setBalanceAmount(entity.getBalanceAmount());
				cardToResult.setCardAmount(entity.getCardAmount());
				cardToResult.setCustomerId(entity.getCustomer().getCustomerId());
				cardToResult.setDateOfRegistration(entity.getDateOfRegistration());
				cardToResult.setPin(entity.getPin());
				cardToResult.setSchemeId(entity.getScheme().getSchemeId());
			}else{
				throw new InvalidCardNoException();
			}
			return cardToResult;
		}finally{
			if(em != null){
				em.close();
			}
		}
	}
	/**
	* @Method Name: applyCard
	* @Description: Method implemented to persist applicationEntity when 
	* 				apply for a new Card
	* @User: Alejandro_406765
	* @Return Type: CardApplicationTO
	* @param CardApplicationTO cardApplicationT
	* @return CardApplicationTO
	* @throws Exception
	*/
	public CardApplicationTO applyCard(CardApplicationTO cardApplicationTO) throws Exception{
		try{
			//Create entity manager factory
			em=emf.createEntityManager();
			// Create new application Entity to set the CardApplicationTO values and persist it
			CardApplicationEntity applicationEntity = new CardApplicationEntity();
			
			// Set the values of the new created entity
			applicationEntity.setAddress(cardApplicationTO.getAddress());
			applicationEntity.setDateOfApplication(new GregorianCalendar());
			applicationEntity.setEmail(cardApplicationTO.getEmail());
			applicationEntity.setName(cardApplicationTO.getName());
			applicationEntity.setPhone(cardApplicationTO.getPhone());
			
			// Persist the entity
			em.getTransaction().begin();
			em.persist(applicationEntity);
			em.getTransaction().commit();
			
			// Set the new getApplicationId generated and return cardApplicationTO object
			cardApplicationTO.setApplicationId(applicationEntity.getAplicationId());
			return cardApplicationTO;
			
		}catch(Exception e){
			// Get the exception in case of error persisting 
			throw new Exception(e.getMessage());
		}
		finally{
			//Close db connection
			if(em != null){
				em.close();
			}
		}
	}
	/**
	* @Method Name: approveCard
	* @Description: 
	* @User: Alejandro_406765
	* @Return void
	* @param long applicationId
	* @throws NoDataFoundException 
	* @throws Exception
	*/
	public void approveCard(int applicationId) throws NoDataFoundException{
		/* With the obtained details of applicationId generate 
		 * userId and Password to populate LoginEntity
		 * */
		//Find the object type CardApplicationEntity with this applicationId
		em = emf.createEntityManager();
		CardApplicationEntity objectSelected = em.find(CardApplicationEntity.class, applicationId);
		
		// throw new NoDataFoundExceptionException in case no data found in objectSelected  
		if(objectSelected==null)
			throw new NoDataFoundException(); 
		
		LoginEntity loginEntity = new LoginEntity();
		
		//Create a Random instance
		Random randomGenerator = new Random();
		//String where the 3 chars are going to be stored
		String Chars ="";
		for(int counter = 0; counter < 3;counter++){
			Chars = Chars + String.valueOf(randomGenerator.nextInt(9));
		}
		//Set the value generated in UserId_randomPart
		String UserId_randomPart = Chars;
		
		/* Generate the userId with 6 characters. The 3 first characters are the 
		 * first 3 letters of the name
		 * */ 
		String newUserId="";
		/*Check if the length of name is greater or equal than 4. 
		 * If it is true trim the name with the first 3 characters*/
		if(objectSelected.getName().length()>=4){
			newUserId = String.valueOf(objectSelected.getName().charAt(0)) + 
			   String.valueOf(objectSelected.getName().charAt(1)) +
			   String.valueOf(objectSelected.getName().charAt(2)) +
			   UserId_randomPart;
		}else{
			//If length is 3 or less, take the complete name and add the random part  
			newUserId = objectSelected.getName() + UserId_randomPart;
		} 
		
		
		//Generate password with 8 random characters
		SecureRandom sRandom = new SecureRandom();
		String alphanumericSequence = new BigInteger(130,sRandom).toString(32);
		String newPassword = alphanumericSequence.substring(0,10);
		
		//Set the new generated values to loginEntity
		loginEntity.setUserId(newUserId);
		loginEntity.setPassword(newPassword);
		
		String newCardNo_String = "";
		for(int counter = 0; counter < 16;counter++){
			newCardNo_String = newCardNo_String + 
							   String.valueOf(randomGenerator.nextInt(9));
		}
		//Create newCardNo and set it to random number of 16 digits
		long newCardNo = Long.valueOf(newCardNo_String);
		
		String newPin_String = "";
		for(int counter = 0; counter < 4;counter++){
			newPin_String = newPin_String + 
							   String.valueOf(randomGenerator.nextInt(9));
		}
		// create newPin and set it to random number of 4 digits
		int newPin = Integer.valueOf(newPin_String);
		
		//Populate CustomerEntity and CardEntity
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setAddress(objectSelected.getAddress());
		customerEntity.setEmail(objectSelected.getEmail());
		customerEntity.setName(objectSelected.getName());
		customerEntity.setPhone(objectSelected.getPhone());
		customerEntity.setUserName(newUserId);
		
		SchemeEntity schemeEntity = new SchemeEntity();
		schemeEntity.setSchemeId('N');
		
		CardEntity cardEntity = new CardEntity();
		cardEntity.setCardNo(newCardNo);
		cardEntity.setPin(newPin);
		cardEntity.setDateOfRegistration(GregorianCalendar.getInstance());
		cardEntity.setCustomer(customerEntity);
		cardEntity.setScheme(schemeEntity);
		cardEntity.setBalanceAmount(0);
		cardEntity.setCardAmount(0);
		
		try{
			//LoginEntity and cardEntity is persisted
			em.getTransaction().begin();
			em.persist(loginEntity);
			em.getTransaction().commit();
			
			em.getTransaction().begin();
			em.persist(cardEntity);
			//Remove the current application Id from CardApplicationEntity
			em.remove(objectSelected);
			em.getTransaction().commit();
		}
		finally{
			if(em!=null)
				em.close();
		}
	}
	
	/**
	* @Method Name: retrieveAppliedCardDetails
	* @Description: The method is used to retrieve the applied card details
	* @User: Alejandro_406765
	* @Return Type: List<CardApplicationTO>
	* @throws Exception 
	*/
	public List<CardApplicationTO> retrieveAppliedCardDetails() throws Exception{
		List<CardApplicationTO> result = new ArrayList<CardApplicationTO>();
		try{
			//Create entity manager factory
			em=emf.createEntityManager();
			// get the named query from CardApplicationEntity
			Query query = em.createNamedQuery("getCompleteList");
			List<CardApplicationEntity> resultEtity = query.getResultList();
			//Iterate the list found
			for(int counter=0;counter<resultEtity.size();counter++){
				//Create one object to store the current entity found
				CardApplicationEntity currentEnity = resultEtity.get(counter);
				//Create one object type CardApplicationTO to set the values found in entity
				CardApplicationTO currentTO = new CardApplicationTO();
				//Set the values found in the entity
				currentTO.setAddress(currentEnity.getAddress());
				currentTO.setApplicationId(currentEnity.getAplicationId());
				currentTO.setEmail(currentEnity.getEmail());
				currentTO.setName(currentEnity.getName());
				currentTO.setPhone(currentEnity.getPhone());
				currentTO.setApplicationSelected(false);
				//Add the currenTo object to the list 
				result.add(currentTO);
			}
			if(result!=null){
				return result;
			}
			else{
				throw new Exception("No request to approve");
			}
		}catch(Exception e){
			// Get the exception in case of error persisting 
			throw new Exception(e.getMessage() + ("retrieveAppliedCardDetails() from service"));
		}
		finally{
			//Close db connection
			if(em != null){
				em.close();
			}
		}
	}
}
