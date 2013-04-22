/*
 * BlockedCardService.java
 * As specified in the ICCI_DLD this is a separate class
 * to manage block/unlbock operations
 */
package com.infy.icci.service;
 
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.BlockedCardEntity;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.PaymentEntity;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.exceptions.InvalidCauseException;
import com.infy.icci.transferObjects.BlockedCardTO;

/**
* 
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 11, 2012
*/ 
public class BlockedCardService {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public BlockedCardService(){
		emf=Persistence.createEntityManagerFactory("ICCI BANK");
		em=null;
	}
	
	/**
	* @Method Name: checkCardBlocked
	* @Description: Checks whether the card with the specified
	* 				card no. is blocked or not
	* @User: Juan_406762
	* @Return Type: BlockedCardTO
	* @param cardNo
	* @return resulTO
	 */
	public BlockedCardTO checkCardBlocked(long cardNo) throws InvalidCardNoException, Exception{
		BlockedCardTO resultTO = null;
		try{
			em= emf.createEntityManager();
			/* Call the stored function */
			Query query = em.createNativeQuery("select sf_checkCardStatus(?1) from dual");
			/* Set the parameter */
			query.setParameter(1, cardNo);
			List blockList = (List) query.getSingleResult();
			int blocked = Integer.parseInt(String.valueOf(blockList.get(0)));
			/* check if the store function returns an exception */
			if(blocked==0) {
				throw new Exception("Exception raised in the stored function");
			}
			/* check if there is no cards blocked with the specified cardNo */
			if(blocked==1){
				throw new InvalidCardNoException();
			}
			/* check if the card is not blocked */
			if(blocked==2) {
				return null;
			}
			/* if the card is blocked return the BlokedCardTo instance based on the blockid */
			else {
				/* Get the BlockedCardEntity object from the database which match 
				 * with the blockId returned from the stored function 
				 */
				BlockedCardEntity entity = em.find(BlockedCardEntity.class, blocked); 
				/* Set the values of a new BlockCardTO instance with the values
				 * of the BlockedCardEntity found
				 */
				resultTO = new BlockedCardTO();
				resultTO.setBlockId(entity.getBlockId());
				resultTO.setCardNo(entity.getCard().getCardNo());
				resultTO.setDateOfBlock(entity.getDateOfBlock());
				resultTO.setDescription(entity.getDescription());
				resultTO.setStatus(entity.getStatus());
				return resultTO;
			}
		}
		finally{
			//Close data base connection
			if(em != null){
				em.close();
			}
		}
	}
	
	/**
	* @Method Name: blockCard
	* @Description: Block card whose card number is set
	* 				in the BlockedCardTo object received as parameter
	* @User: Juan_406762
	* @Return Type: BlockedCardTO
	* @param to
	* @throws InvalidCauseException
	* @return to
	 */
	public BlockedCardTO blockCard(BlockedCardTO to) throws InvalidCauseException {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			BlockedCardEntity blockedCardEntity = new BlockedCardEntity();
			/* Get the CardEntity object from the database which match 
			 * with the card No. of the BlockedCardTO object received as 
			 * parameter of the function
			 */
			CardEntity cardEntity = em.find(CardEntity.class, to.getCardNo());

			double schemeAmount = cardEntity.getScheme().getSchemeAmount();
			/* Validate whether the card should be blocked for Payment not Done or not */
			if(cardEntity.getBalanceAmount() > (2*schemeAmount)) {
				to.setDescription("Payment not Done");
			}
			else if(to.getDescription().equalsIgnoreCase("Payment not Done")) {
				throw new InvalidCauseException();
			}
			/* Set the values of a new BlockedCardEntity instance with the values
			 * of the BlockedCardTO object received as parameter of the function
			 */
			blockedCardEntity.setCard(cardEntity);
			blockedCardEntity.setDateOfBlock(to.getDateOfBlock());
			blockedCardEntity.setDescription(to.getDescription());
			blockedCardEntity.setStatus(to.getStatus());
			/* Persist the new BlockedCardEntity instance in the database*/
			em.persist(blockedCardEntity);
			em.getTransaction().commit();
			/* Set the blockId property of the BlockedCardTO object received as 
			 * parameter of the function with blockId of the BlockedCardEntity 
			 * instance recently persisted*/
			to.setBlockId(blockedCardEntity.getBlockId());
			return to;
		}
		finally {
			if(em != null)
				em.close();
		}
	}
	/**
	* @Method Name: unblockCard
	* @Description: Unblocks the card with the specified cardNo
	* 				if the description is payment not done only on
	* 				full payment the card can be unblock.
	* @User: andres_406763
	* @Return Type: BlockedCardTO
	* @param blockCardTo
	* @return
	*/
	public BlockedCardTO unblockCard(BlockedCardTO to)
		throws Exception{
		BlockedCardTO resultTO= null;
		try{
			em= emf.createEntityManager();
			em.getTransaction().begin();
			//Check if reason is payment Not done 
			if(to.getDescription().equalsIgnoreCase("Payment Not done")){
				//The payment must be done today from the PaymentService 
				Date date= new Date();
				//Check if the full payment has been done or not
				Query paymentQuery = em.createQuery("select p from " +
						"PaymentEntity p where p.card.cardNo=:cardNo and " +
						"p.dateOfpayment=:dateToday and p.paymentType='F'");
				//Set the date and cardNo parameters
				paymentQuery.setParameter("dateToday", date);
				paymentQuery.setParameter("cardNo", to.getCardNo());
				List rs= paymentQuery.getResultList();
				//if there is not a full payment today
				if(rs.size()==0){
					return null;
				}
			}
			//Find the blocked card
			BlockedCardEntity entity = em.find(BlockedCardEntity.class, to.getBlockId());
			//If it did not find it throw exception
			if(entity==null){
				throw new Exception("The blockId can't be found");
			}
			//Set status to Not blocked
			entity.setStatus('N');
			//Set resultTO data members
			resultTO= new BlockedCardTO();
			resultTO.setBlockId(entity.getBlockId());
			resultTO.setCardNo(entity.getCard().getCardNo());
			resultTO.setDateOfBlock(entity.getDateOfBlock());
			resultTO.setStatus(entity.getStatus());
			//Commit changes
			em.getTransaction().commit();
			//return resultTO
			return resultTO;
		}
		finally{
			//Close data base connection
			if(em != null){
				em.close();
			}
		}
	}
}
