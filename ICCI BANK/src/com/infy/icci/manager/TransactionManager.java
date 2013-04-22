package com.infy.icci.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.exceptions.LowBalanceException;
import com.infy.icci.service.TransactionService;
import com.infy.icci.transferObjects.TransactionTO;

/**
 * 
 * @author juan_406753
 *
 */
public class TransactionManager {

	/**
	 * 
	 * @Method Name: saveTransaction
	 * @User: juan_406753
	 * @Return Type: TransactionTO
	 * @param transactionTO
	 * @return
	 * @throws Exception 
	 */
	public TransactionTO saveTransaction(TransactionTO transactionTO) throws LowBalanceException{
		//Recovers the session to get the customers information 
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		long cardNo = (Long) session.getAttribute("cardNo");
		TransactionService transactionService = new TransactionService();
		//Gets the balance of the customer's credit card
		double cardAmount = transactionService.getCardAmount(cardNo);
		//Guarantees that the balance in the credit card allows the transaction
		if(cardAmount < transactionTO.getAmount()){
			throw new LowBalanceException();
		}else{
			//Enters the transaction information in a TransactionTO object to persist it
			transactionTO.setCardNo(cardNo);
			GregorianCalendar transactionDateGregorian =  new GregorianCalendar();
			transactionTO.setDateOfTransaction(transactionDateGregorian);
			transactionService.updateCardAmount(cardNo, transactionTO.getAmount());
			//The transaction information is send to the TransactionService to be saved
			transactionTO = transactionService.saveTransaction(transactionTO);
			return transactionTO;
		}
	}

	/**
	 * 
	 * @Method Name: cardUsage
	 * @Description: This method call the transaction service to retrieve a list
	 * with all the transactions in the period selected
	 * @User: juan_406752
	 * @Return Type: List<TransactionTO>
	 * @param month
	 * @param position
	 * @return a list with all the transactions
	 * in the period selected
	 * @throws Exception 
	 */
	public List<TransactionTO> cardUsage(String month, String year) 
	throws Exception{
				
		/*
		 * Setting the session Id in session in order to be used in the 
		 * next JSP Page
		 */
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Long cardNo = (Long) session.getAttribute("cardNo");

		return new TransactionService().cardUsage(month, year, cardNo);
	}

	/**
	 * 
	* @Method Name: getMonthList
	* @Description: This method checks the current month, and based on that
	* return a list of lists with the last 6 months in the current year or
	* in the previous year
	* @User: juan_406752
	* @Return Type: List
	* @return
	 */
	public List getMonthList(){
		
		/*
		 * Create two Array lists and the list returned will
		 * contain the other two array lists
		 */
		List listReturned = new ArrayList();	
		List currentYear = new ArrayList();
		List previousYear = new ArrayList();

		/*
		 * Create an array that contains all the months of the year
		 */
		String [] months = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE",
				"JULY","AUGUST","SEPTEMBRE","OCTOBER","NOVEMBER","DECEMBER"};
		
		
		Date date = new Date();
		int month = date.getMonth();
		int counter = 0;
		
		/*
		 * Check if the month selected by the user is greater or equal to June
		 */
		if(month > 4){
			for (int i = month-5; i <= month; i++) {
				currentYear.add(months[i]);
				counter++;
			}
		}else{
			/*
			 * If the month selected is before June, it first fill the current
			 * Year array, then, it will fill the previous array with the
			 * remaining months
			 */
			for (int i = 0; i <= month; i++) {
				currentYear.add(months[month-i]);
				counter++;
				
			}			
			int max = 6-counter;
			
			for (int i = 0; i < max ; i++) {
				previousYear.add(months[(months.length-1)-i]);
				counter++;
			}
		}
		/*
		 * Add the two arrays to the list that the method will return
		 */
		listReturned.add(currentYear);
		listReturned.add(previousYear);
		
		/*
		 * Return the list of months of current and previous year
		 */
		return listReturned;
	}
}
