package com.infy.icci.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.TransactionEntity;
import com.infy.icci.transferObjects.TransactionTO;


/**
 * 
 * @author juan_406753
 *
 */
public class TransactionService {

	/**
	 * 
	 * @Method Name: getBalance
	 * @Description: This method returns the current balance for the 
	 * credit card identified with cardNo
	 * @User: juan_406753, juan_406752
	 * @Return Type: double
	 * @param cardNo
	 * @return the balance of the credit card identified with cardNo
	 */
	public double getBalance(long cardNo){
		EntityManager em = null;
		try{
			EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("ICCI BANK");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CardEntity cardEntity = em.find(CardEntity.class, cardNo);
			if(cardEntity == null){
				return -1;
			}else{
				return cardEntity.getBalanceAmount();
			}			
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	* @Method Name: getCardAmount
	* @Description: Returns the card amount of the given card number
	* @User: ricardo_406737
	* @Return Type: double
	* @param cardNo
	* @return
	 */
	public double getCardAmount(long cardNo){
		EntityManager em = null;
		try{
			EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("ICCI BANK");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CardEntity cardEntity = em.find(CardEntity.class, cardNo);
			if(cardEntity == null){
				return -1;
			}else{
				return cardEntity.getCardAmount();
			}			
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}

	/**
	 * 
	 * @Method Name: saveTransaction
	 * @Description: Persists the transaction information into a Transaction 
	 * Entity
	 * @User: juan_406753
	 * @Return Type: TransactionTO
	 * @param transactionTo
	 * @return return the TransactionTO with the data of the transaction 
	 * persisted
	 */
	public TransactionTO saveTransaction(TransactionTO transactionTo){
		EntityManager em = null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI BANK");
			//Creating an instance of EntityManager to manage entity
			em =  emf.createEntityManager();
			em.getTransaction().begin();
			//Creating an instance of the TransactionEntity to persist the transaction information
			TransactionEntity transactionEntity = new TransactionEntity();
			transactionEntity.setAmount(transactionTo.getAmount());			
			transactionEntity.setCardNo(transactionTo.getCardNo());
			transactionEntity.setDescription(transactionTo.getDescription());
			transactionEntity.setDateOfTransaction(transactionTo.getDateOfTransaction());
			//Persisting the TransactionEntity
			em.persist(transactionEntity);
			em.getTransaction().commit();
			//Saving the transaction ID in the TransactionTO that will be returned
			transactionTo.setTransactionId(transactionEntity.getTransactionId());
			return transactionTo;
		}finally{
			//Closing the EntityManager
			if(em!=null){
				em.close();
			}
		}
	}

	/**
	 * @Method Name: updateCardAmount
	 * @Description: updates the card amount identified by carNo, with the
	 * amount given as parameter
	 * @User: juan_406753
	 * @Return Type: 
	 * @param cardNo
	 * @param amount
	 * @return -1 if the card identified with cardNo isn't found, 1 if the
	 * update was successful
	 */
	public void updateCardAmount(long cardNo, double amount){
		EntityManager em = null;
		try{
			EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("ICCI BANK");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CardEntity cardEntity = em.find(CardEntity.class, cardNo);
			cardEntity.setCardAmount(cardEntity.getCardAmount() - amount);
			cardEntity.setBalanceAmount(cardEntity.getBalanceAmount() + amount);
			em.merge(cardEntity);
			em.getTransaction().commit();			
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}

	/**
	* @Method Name: cardUsage
	* @Description: Generates a report with the usage information of the credit
	* card identified with carNo in the month and year specified
	* @User: juan_406752
	* @Return Type: List<TransactionTO>
	* @param month
	* @param year
	* @param cardNo
	* @return A list with the transactions matched with customer
	* data
	* @throws Exception
	*/
	@SuppressWarnings("deprecation")
	public List<TransactionTO> cardUsage(String month, String year, 
			long cardNo) throws Exception{
		
		EntityManager em = null;
		try{
			//Creating entity manager factory and entity manager
			EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("ICCI BANK");
			em = emf.createEntityManager();

			/*
			 * Create an array to store the date
			 */
			Date date = new Date();
			String [] completeDate = date.toGMTString().split(" ");
			int actualYear = Integer.parseInt(completeDate[2]);
			int monthDays = 31;
			
			/*
			 * The strings to be passed, by default will be of the current year
			 */
			String date1 = "01-" + month.toUpperCase() + "-" + actualYear;
						
			if((month.equalsIgnoreCase("April")|| month.equalsIgnoreCase("November")||
					month.equalsIgnoreCase("September")||month.equalsIgnoreCase("June"))){
				/*
				 * If the month is April, November, September or June then
				 * it will have 30 days
				 */
				monthDays = 30;
			
			}else if(((actualYear%400) == 0||(actualYear%4) == 0)&& 
					month.equals("February")) {
				/*
				 * If month is February and the year is lead then
				 * the month will have 29 days
				 */
				monthDays = 29;
				
			}else if (!((actualYear%400) == 0 || (actualYear%4) == 0)&& 
					month.equals("February")){
				/*
				 * If month is February and the year is not lead then
				 * the month will have 28 days
				 */
				monthDays = 28;
			}
			
			/*
			 * Set the max boundary of the query
			 */
			String date2 = monthDays + "-" + month.toUpperCase() + "-" + actualYear;
			
			/*
			 * If the year selected is previous, the year must be modified
			 */
			if(year.equalsIgnoreCase("previous")){
				date1 = "01-" + month.toUpperCase() + "-" + (actualYear-1);
				date2 = monthDays + "-" + month.toUpperCase() + "-" + (actualYear-1);
			}		
			
			/*
			 * The boundaries of the query to be searched
			 */			
			
			Date first = new Date(date1);
			Date Second = new Date(date2);			
			
			/*
			 * A query to search transactions of the customer with the  
			 * card number passed as a parameter and in the month and year
			 * selected by the user in the jsp page
			 */
			
			Query query = em.createQuery("select e from TransactionEntity " +
					"e where e.dateOfTransaction >=?1 " +
			"AND e.dateOfTransaction <=?2 AND e.cardNo =?3");
			
			query.setParameter(1, first);
			query.setParameter(2, Second);
			query.setParameter(3, cardNo);

			/*
			 * The list with the transactions		
			 */
			List<TransactionEntity> list = query.getResultList();

			/*
			 * The list to be returned must be of TransactionTo, 
			 * so a new List<TransactionTo> must be created and mapped
			 * with the other list values
			 */
			List<TransactionTO> finallist = new ArrayList<TransactionTO>();

			/*
			 * Passing each value of the list, to the returned List
			 */
			for (int i = 0; i < list.size(); i++) {
				TransactionTO transactionTO = new TransactionTO();
				transactionTO.setAmount(list.get(i).getAmount());
				transactionTO.setCardNo(list.get(i).getCardNo());
				transactionTO.setDateOfTransaction(list.get(i).getDateOfTransaction());
				transactionTO.setDescription(list.get(i).getDescription());
				transactionTO.setTransactionId(list.get(i).getTransactionId());
				finallist.add(transactionTO);				
			}			

			return finallist;			
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
}
