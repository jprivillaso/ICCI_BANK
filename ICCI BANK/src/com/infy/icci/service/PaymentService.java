/**
 * Project Name: ICCI BANK
 * User: Carolina_406764
 * Date: Oct 10, 2012
 */

package com.infy.icci.service;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.PaymentEntity;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.LoginTO;
import com.infy.icci.transferObjects.PaymentTO;
import com.infy.icci.transferObjects.SchemeTO;

/**
 * 
* @author Carolina_406764
*
 */

public class PaymentService {
	private EntityManagerFactory emf;
	private EntityManager em;

	/**
	 * Constructor PaymentService
	 */
	public PaymentService() {
		emf = Persistence.createEntityManagerFactory("ICCI BANK");
	}

	/**
	 * @Method Name: getPaymentDetails
	 * @Description: Retrieve and return payment details related to a cardNo
	 *               like lastPaymentDate, type of payment made, amount paid etc
	 * @User: leslie_406760, carolina_406764
	 * @Return Type: PaymentTO
	 * @param cardNo
	 * @return paymentToResult
	 * @throws InvalidCardNoException, Exception 
	 */
	public PaymentTO getPaymentDetails(CardTO cardNo) throws InvalidCardNoException,Exception{
		em = null;
		// Initialize paymentToResult
		PaymentTO paymentToResult = null;
		try {
			// Creates Entity Manager and start the transaction
			em = emf.createEntityManager();
			em.getTransaction().begin();
			// Finds if the cardNo passed exist or not and throws InvalidCardNoException in case of be invalid number
			CardEntity cardEntity = em.find(CardEntity.class, cardNo
					.getCardNo());
			if (cardEntity == null) {
				throw new InvalidCardNoException();
			} else {
				// Query to find payments made before for that cardNo
				Query query = em
						.createQuery("select p from PaymentEntity p where p.dateOfpayment =(select max(l.dateOfpayment)from PaymentEntity l where l.card.cardNo =:cardNo)");
				query.setParameter("cardNo", cardEntity.getCardNo());
				if (paymentToResult == null) {
					try {
						// Save the row founded and save it into entity
						PaymentEntity entity = (PaymentEntity)query.getResultList().get(0);
						// Create an instance of PaymentTO
						paymentToResult = new PaymentTO();
						// Set the values for paymentToResult data members with entity founded
						paymentToResult.setAmountPaid(entity.getAmountPaid());
						paymentToResult.setCardNo(entity.getCard().getCardNo());
						paymentToResult.setDateOfPayment(entity
								.getDateOfpayment());
						paymentToResult.setPaymentId(entity.getPaymentId());
						paymentToResult.setPaymentType(entity.getPaymentType());
					// Catch Exception
					} catch (Exception e) {
						return new PaymentTO();
					}
				}
			}
		} finally {
			// Close the connection
			if (em != null) {
				em.close();
			}
		}
		return paymentToResult;
	}
	
	/**
	 * 
	* @Method Name: getCustomerId
	* @Description: Retrieve and return the details of the customer whose customerid is 
	* 				specified as part of CustomerTO.
	* @User: 
	* @Return Type: CustomerTO
	* @param LoginTO lTo
	* @return CustomerTO
	 */

	public CustomerTO getCustomerId(LoginTO lTo) {
		EntityManager em = emf.createEntityManager();
		try {

		} finally {
			if (em != null) {
				em.close();
			}
		}

		return null;
	}
/**
 * 
* @Method Name: makePayment
* @Description: Retrieve and return payment details after payment is made
* @User: leslie_406760, Carolina_406764
* @Return Type: PaymentTO
* @param PaymentTO to
* @return paymentToResult
* @throws InvalidCardNoException, Exception
 */
	public PaymentTO makePayment(PaymentTO to) throws InvalidCardNoException,Exception {
		em = null;

		try {
			// Creates Entity Manager and start the transaction
			em = emf.createEntityManager();
			em.getTransaction().begin();
			// Finds if the cardNo passed exist or not and throws InvalidCardNoException in case of be invalid number
			CardEntity cardEntity = em.find(CardEntity.class, to.getCardNo());
			if (cardEntity == null) {
				throw new InvalidCardNoException();
			} else {
				// Create an instance of PaymentEntity and populated
				PaymentEntity paymentEntity = new PaymentEntity();
				paymentEntity.setAmountPaid(to.getAmountPaid());
				paymentEntity.setCard(cardEntity);
				paymentEntity.setDateOfpayment(Calendar.getInstance());
				paymentEntity.setPaymentType(to.getPaymentType());
				// Persist PaymentEntity
				em.persist(paymentEntity);
				//Create instance of CardTo
				CardTO cardTO = new CardTO();
				//Set cardNo to CardTO Data member
				cardTO.setCardNo(to.getCardNo());
				//Create an instance of CardService
				CardService cardService = new CardService();
				//Call getCardDetails method and save it into cardTO
				cardTO = cardService.getCardDetails(cardTO);
				//Create an instance of SchemeTO
				SchemeTO schemeTO = new SchemeTO();
				//Set SchemeId to schemeTO Data member
				schemeTO.setSchemeId(cardTO.getSchemeId());
				//Create an instance of SchemeService
				SchemeService schemeService = new SchemeService();
				//Call getSchemeDetails method and save it into schemeTO
				schemeTO = schemeService.getSchemeDetails(schemeTO);
				double interest = (schemeTO.getMinimumAmount()*(schemeTO.getInterestRate()/100));
				//Calculates the balanceAmount depending on the paymentType
				if(to.getPaymentType()=='M'){
					double balance = cardEntity.getBalanceAmount()-to.getAmountPaid();
					cardEntity.setBalanceAmount(balance+interest);
					cardEntity.setCardAmount(cardTO.getCardAmount()+to.getAmountPaid());
				}else{
					cardEntity.setBalanceAmount(0.0);
					cardEntity.setCardAmount(schemeTO.getSchemeAmount());
				}
				em.persist(cardEntity);
				// Commit the transaction
				em.getTransaction().commit();
				// Get the generated paymentId
				to.setPaymentId(paymentEntity.getPaymentId());
				// Set Date of Payment
				to.setDateOfPayment(paymentEntity.getDateOfpayment());
				// Return the transfer object
				return to;
			}
		} finally {
			// Close the connection
			if (em != null) {
				em.close();
			}
		}
	}

}
