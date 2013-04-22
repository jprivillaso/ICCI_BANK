package com.infy.icci.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.BlockedCardEntity;
import com.infy.icci.entity.CardApplicationEntity;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.CustomerEntity;
import com.infy.icci.entity.SchemeEntity;
import com.infy.icci.entity.TransactionEntity;
import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.exceptions.NoRevenueFoundException;
import com.infy.icci.transferObjects.ApprovedCardTO;
import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.transferObjects.TransactionTO;

public class ReportService {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ReportService(){
		emf = Persistence.createEntityManagerFactory("ICCI BANK");
	}

	/**
	 * 
	* @Method Name: getTransactions
	* @User: juan_406753
	* @Return Type: List<TransactionTO>
	* @param cardNo
	* @param fromDate
	* @param toDate
	* @return
	 */
	public List<TransactionTO> getTransactions(long cardNo, Calendar fromDate, Calendar toDate){
		EntityManager em = null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI BANK");
			//Creates an instance of the EntityMangaer to manage the entity
			em =  emf.createEntityManager();
			em.getTransaction().begin();	
			//Creates a query that will return the transaction information of a credit card between the given dates 
			Query query = em.createQuery("select t from TransactionEntity t where t.cardNo = ?1 and t.dateOfTransaction between ?2 and ?3");
			query.setParameter(1, cardNo);
			query.setParameter(2, fromDate);
			query.setParameter(3, toDate);
			List<TransactionEntity> transactions = query.getResultList();
			Iterator<TransactionEntity> transIter = transactions.iterator();
			List<TransactionTO> transactionTOReturn = new ArrayList<TransactionTO>();
			//Store the transaction information of the credit card in a list 
			while(transIter.hasNext()){
				TransactionEntity te = transIter.next();
				TransactionTO transactionTo = new TransactionTO();
				transactionTo.setAmount(te.getAmount());
				transactionTo.setCardNo(te.getCardNo());
				transactionTo.setDateOfTransaction(te.getDateOfTransaction());
				transactionTo.setDescription(te.getDescription());
				transactionTo.setTransactionId(te.getTransactionId());
				transactionTOReturn.add(transactionTo);
			}
			return transactionTOReturn;
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: getBlockedDetails
	* @User: juan_406753
	* @Return Type: List<BlockedCardTO>
	* @param fromDate
	* @param toDate
	* @return
	 */
	public List<BlockedCardTO> getBlockedDetails(String [] reasons){
		List<BlockedCardTO> blockedCards = new ArrayList<BlockedCardTO>();
		List<BlockedCardEntity> blockedCardsEntities = new ArrayList<BlockedCardEntity>();
		
		EntityManager em =  null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI BANK");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			for (String reason : reasons) {
				Query query = em.createQuery("select bc from BlockedCardEntity bc where bc.status = 'B' and bc.description = ?1");
				query.setParameter(1, reason);

				blockedCardsEntities = (List<BlockedCardEntity>)query.getResultList();
				for (BlockedCardEntity entity : blockedCardsEntities) {
					blockedCards.add(new BlockedCardTO(entity.getBlockId(), entity.getCard().getCardNo(), entity.getDateOfBlock(), entity.getDescription(), entity.getStatus()));
				}
			}
		}finally{
			if(em!=null){
				em.close();
			}
		}
		return blockedCards;
	}
	
	/**
	 * 
	* @Method Name: transactionDetails
	* @User: juan_406753
	* @Return Type: Map
	* @param fromDaCalendar
	* @param toDate
	* @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Long, Long> transactionDetails (Calendar fromDaCalendar, Calendar toDate){
		EntityManager em =  null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI BANK");
			//Creating an  instance of EntityManager to manage the entity
			em = emf.createEntityManager();
			em.getTransaction().begin();
			//Creating a query that will return the credit card number and the number of transactions made by that card between the given dates
			Query query = em.createQuery("select t.cardNo, count(t.transactionId) from TransactionEntity t where t.dateOfTransaction between ?1 and ?2 group by t.cardNo");
			query.setParameter(1, fromDaCalendar);
			query.setParameter(2, toDate);
			List result = query.getResultList();
			Map<Long, Long> details = new HashMap<Long, Long>();
			//Storing the result obtained in the query in a HashMap where the key is the credit card number and the data is the number of transactions made
			for (int i = 0; i < result.size(); i++) {
				Object transactionDetails[] =  (Object[]) result.get(i);
				details.put((Long)transactionDetails[0], (Long)transactionDetails[1]);
			}
			return details;
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: getRevenueDetails
	* @Description: Retrieve the sum of Payments and Cards which were registered between the said dates from Payment table
	* @User: leslie_406760
	* @Return Type: List<Double>
	* @param fromDate
	* @param toDate
	* @return
	 * @throws NoRevenueFoundException 
	 */
	@SuppressWarnings("unchecked")
	public List<Double> getRevenueDetails(Calendar fromDate, Calendar toDate) throws NoRevenueFoundException, Exception {
		em = null;
		List<Double> listResult =null;
		Double revenue = 0.0;
		Double expenditure = 0.0;
		Double profit =  0.0;
		try {
			if(fromDate.after(toDate)){
				throw new Exception("From date can’t be greater than To date");
			}
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query queryPayment = em
					.createQuery("select sum(p.amountPaid) from PaymentEntity p where p.dateOfpayment>=:fromDate and p.dateOfpayment<=:toDate");
			queryPayment.setParameter("fromDate", fromDate);
			queryPayment.setParameter("toDate", toDate);
			revenue = (Double)queryPayment.getSingleResult();
			if (revenue == null) {
				throw new NoRevenueFoundException();
			}
			Query queryCard = em
					.createQuery("select c from CardEntity c where c.dateOfRegistration>=:fromDate and c.dateOfRegistration<=:toDate");
			queryCard.setParameter("fromDate", fromDate);
			queryCard.setParameter("toDate", toDate);
			List<CardEntity> listCards =queryCard.getResultList();
			for (CardEntity cardEntity : listCards) {
				expenditure = expenditure+cardEntity.getScheme().getSchemeAmount();
			}
			profit = revenue-expenditure;
			listResult =  new ArrayList<Double>();
			listResult.add(revenue);
			listResult.add(expenditure);
			listResult.add(profit);			
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return listResult;
	}
	
	/**
	 * 
	* @Method Name: getCardNumbers
	* @User: juan_406753
	* @Return Type: List
	* @param fromDate
	* @param toDate
	* @return
	 */
	public List<Object> getCardNumbers(Calendar fromDate, Calendar toDate)
		throws NoDataFoundException{
		em= emf.createEntityManager();
		try{
			//create query to select card numbers between dates
			Query query = em.createQuery("select c.cardNo from CardEntity c " +
					"where c.dateOfRegistration between :fromDate " +
					"and :toDate");
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			//Get the result list
			List rs = query.getResultList();
			//if the result list size is 0 throw NoDataFoundException
			if(rs.size()==0){
				throw new NoDataFoundException();
			}
			//return list
			return rs;
		}
		finally{
			//close connection
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: cardRejectedDetails
	* @User: juan_406753
	* @Return Type: List<CardApplicationTO>
	* @param fromDate
	* @param toDate
	* @return
	 * @throws NoDataFoundException 
	 */
	public List<CardApplicationTO> cardRejectedDetails(Calendar fromDate, Calendar toDate) 
		throws NoDataFoundException{
		em= emf.createEntityManager();
		try{
			//Get the cardApplicationEntities
			Query rejectedQuery= em.createQuery("select c from " +
					"CardApplicationEntity c where c.dateOfApplication " +
					"between :fromDate and :toDate");
			//Set the fromDate and toDate parameters
			rejectedQuery.setParameter("fromDate", fromDate);
			rejectedQuery.setParameter("toDate", toDate);
			//Get the result list
			List<CardApplicationEntity>cardApplicationList=
												rejectedQuery.getResultList();
			//Check if no data is found
			if(cardApplicationList.size()==0){
				throw new NoDataFoundException();
			}
			//Populate the cardApplicationTO list
			List<CardApplicationTO>toList= new ArrayList<CardApplicationTO>();
			for (CardApplicationEntity cardApplication : cardApplicationList) {
				CardApplicationTO to = new CardApplicationTO();
				to.setApplicationId(cardApplication.getAplicationId());
				to.setName(cardApplication.getName());
				to.setEmail(cardApplication.getEmail());
				to.setPhone(cardApplication.getPhone());
				toList.add(to);
			}
			
			//return list
			return toList;
		}
		finally{
			//close connection
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: cardApprovedDetails
	* @User: juan_406753
	* @Return Type: List<ApprovedCardTO>
	* @param fromDate
	* @param toDate
	* @return
	 */
	public List<ApprovedCardTO> cardApprovedDetails(Calendar fromDate, Calendar toDate){
		//Get cardNo from the requestParameter
		String cardNoTemp= (String)FacesContext.getCurrentInstance().getExternalContext().
											getRequestParameterMap().get("cardNo");

		em= emf.createEntityManager();
		try{
			//Declare fields
			String customerName= null;
			Double schemeAmount= null;
			String paymentType= null;
			Calendar dateOfBlock= null;
			//Parse cardNo
			long cardNo = Long.parseLong(cardNoTemp);
			//Get the cardEntity
			CardEntity cardEntity= em.find(CardEntity.class, cardNo);
			//Get the customer of cardEntity
			CustomerEntity customerEntity= cardEntity.getCustomer();
			//Get the customer name
			if(customerEntity!=null){
				customerName= customerEntity.getName();
			}
			//Get the scheme of cardEntity
			SchemeEntity schemeEntity= cardEntity.getScheme();
			//Get the scheme amount
			if(schemeEntity!=null){
				schemeAmount = schemeEntity.getSchemeAmount();
			}
			//Get the payment type of last payment
			Query paymentQuery = em.createQuery("select p.paymentType from " +
					"PaymentEntity p where p.card.cardNo=:cardNo " +
					"order by p.dateOfpayment DESC");
			paymentQuery.setParameter("cardNo", cardNo);
			List paymentTypeList= paymentQuery.getResultList();
			//Get the first result
			if(paymentTypeList.size()>0){
				paymentType= paymentTypeList.get(0).toString();
			}
			//If no results display NA
			else{
				paymentType= "NA";
			}
			//Get the last block date if applicable
			Query blockedQuery= em.createQuery("select b.dateOfBlock from " +
					"BlockedCardEntity b where b.card.cardNo=:cardNo " +
					"order by b.dateOfBlock DESC");
			blockedQuery.setParameter("cardNo", cardNo);
			List<Calendar> dateOfBlockList= blockedQuery.getResultList();
			//Get the first result
			if(dateOfBlockList.size()>0){
				dateOfBlock= dateOfBlockList.get(0);
			}
			//If no results set a value so the converter display NA
			else{
				dateOfBlock=Calendar.getInstance();
				//Adds 100 years to the current date
				dateOfBlock.add(Calendar.YEAR, 100);
			}
			
			//Set values to a ApprovedCardTO
			ApprovedCardTO approvedCardTO= new ApprovedCardTO();
			approvedCardTO.setCustomerName(customerName);
			approvedCardTO.setSchemeAmt(schemeAmount);
			approvedCardTO.setPaymentType(paymentType);
			approvedCardTO.setDateOfBlock(dateOfBlock);
			
			//Create list and add the TO
			List<ApprovedCardTO> approvedList = new ArrayList<ApprovedCardTO>();
			approvedList.add(approvedCardTO);
			return approvedList;
		}
		finally{
			//close connection
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: statusDetails
	* @User: juan_406753
	* @Return Type: Map<String,Integer>
	* @param fromDate
	* @param toDate
	* @return the percentages of approved and rejected cards
	 */
	public Map<String, Integer> statusDetails(Calendar fromDate, Calendar toDate)
		throws NoDataFoundException, Exception{
		em= emf.createEntityManager();
		try{
			if(fromDate.after(toDate)){
				throw new Exception("From date can’t be greater than To date");
			}
			//Get all the approved cards between dates
			Query approvedCards= em.createQuery("select c from CardEntity c " +
					"where c.dateOfRegistration between :fromDate and :toDate");
			approvedCards.setParameter("fromDate", fromDate);
			approvedCards.setParameter("toDate", toDate);
			List approvedList = approvedCards.getResultList();
			//Number of approved cards
			int numberApproved = approvedList.size();
			
			//Get all the rejected cards between dates
			Query rejectedCards = em.createQuery("select c from " +
					"CardApplicationEntity c where c.dateOfApplication" +
					" between :fromDate and :toDate");
			rejectedCards.setParameter("fromDate", fromDate);
			rejectedCards.setParameter("toDate", toDate);
			List rejectedList = rejectedCards.getResultList();
			//Number or rejected cards
			int numberRejected= rejectedList.size();
			
			//Calculate total number of cards
			int total= numberApproved + numberRejected;
			
			//Check if there's not approved nor rejected cards
			if(total==0)
			{
				throw new NoDataFoundException();
			}
			
			//Calculate percentages
			int approvedPercentage= (numberApproved*100)/total;
			int rejectedPercentage= (numberRejected*100)/total;
			
			//Store data on map and return it
			Map<String,Integer> percentages = new HashMap<String,Integer>();
			percentages.put("approved", approvedPercentage);
			percentages.put("rejected", rejectedPercentage);
			
			return percentages;
		}
		finally{
			//close connection
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: getCustomersForScheme
	* @User: juan_406753
	* @Return Type: List<CardTO>
	* @param schemeTo
	* @return
	 */
	@SuppressWarnings("unchecked")
	public List<CardTO> getCustomersForScheme(SchemeTO schemeTo) throws Exception{
		List<CardEntity> customersForScheme = new ArrayList<CardEntity>();
		List<CardTO> customersForScheme2 = new ArrayList<CardTO>();
		try{		
			emf=Persistence.createEntityManagerFactory("ICCI BANK");
			em=emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			if (schemeTo.getSchemeId() == 'A' || schemeTo.getSchemeId() == 'B' || schemeTo.getSchemeId() == 'C'
				|| schemeTo.getSchemeId() == 'D' || schemeTo.getSchemeId() == 'E') {

//				Query customersForSchemeQuery = em.createQuery("select ca from SchemeEntity s, CardEntity ca where s.schemeId = ca.schemeId and s.schemeId=?1");
				Query customersForSchemeQuery = em.createQuery("select ca from CardEntity ca where ca.scheme.schemeId = ?1");
				customersForSchemeQuery.setParameter(1, schemeTo.getSchemeId());
				customersForScheme = (List<CardEntity>)customersForSchemeQuery.getResultList();
				
				for (CardEntity card : customersForScheme) {
					customersForScheme2.add(new CardTO(card.getCardNo(), card.getPin(),card.getScheme().getSchemeId(),card.getBalanceAmount(),card.getCardAmount(),card.getDateOfRegistration(),card.getCustomer().getCustomerId()));
				}
				
			} else {
				throw new Exception("Scheme not choosen");
			}
			return customersForScheme2;
		}finally{
			if(em != null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	* @Method Name: getcustomerDetails
	* @User: juan_406753
	* @Return Type: CustomerTO
	* @param customerTo
	* @return
	 */
	public CustomerTO getcustomerDetails(CustomerTO customerTo){
		CustomerTO customer = null;
		try{	
			emf=Persistence.createEntityManagerFactory("ICCI BANK");
			em=emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			CustomerEntity entity = em.find(CustomerEntity.class, customerTo.getCustomerId());		
			if(entity != null){
				customer = new CustomerTO();
				customer.setAddress(entity.getAddress());
				customer.setCustomerId(entity.getCustomerId());
				customer.setEmail(entity.getEmail());
				customer.setName(entity.getName());
				customer.setPhone(entity.getPhone());
				customer.setUserName(entity.getUserName());
			}else{
				throw new Exception("Invalid customer");
			}
			return customer;
		}catch (Exception e) {
			e.getMessage();
			return null;
		}finally{
			if(em != null){
				em.close();
			}
		}
	}
	
}
