package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIData;

import com.infy.icci.exceptions.WrongDateException;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.TransactionTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class TransactionStatisticsMB {

	private Integer custId;
	private Long cardNo=0L;
	private Calendar fromDate;
	private Calendar toDate;
	private CustomerTO customerDetails;
	private List<CustomerTO> customers;
	private List<Integer> count;
	private List<TransactionTO> transaction;
	private String message;
	private UIData customerTable;
	private UIData transactionTable;
	private boolean hyperlinkUI = false;
	private boolean customerUI = false;
	private boolean transactionUI = false;
	private List<Long> transactionCard;
	
	
	/**
	* @Method Name: init
	* @Description: Allows all the components to be refreshed
	* @User: ricardo_406737
	* @Return Type: String
	* @return
	*/
	public String init(){		
		this.setHyperlinkUI(false);
		this.setCustomerUI(false);
		this.setTransactionUI(false);
		this.setMessage("");
		this.setFromDate(null);
		this.setToDate(null);
		return "init";
	}
	
	/**
	* @Method Name: reset
	* @Description: 
	* @User: ricardo_406737
	* @Return Type: String
	* @return
	 */
	public String reset(){
		
		this.setHyperlinkUI(false);
		this.setCustomerUI(false);
		this.setTransactionUI(false);
		this.setMessage("");
		this.setFromDate(null);
		this.setToDate(null);
		return "reset";
	}
	
	
	/**
	 * 
	* @Method Name: getStatistics
	* @Description: Returns the information of the customer that has made transaction between the given dates
	* @User: ricardo_406737
	* @Return Type: String
	* @return
	 */
	public String getStatistics(){
		try{
			transactionCard = new ArrayList<Long>();
			customers =  new ArrayList<CustomerTO>();
			count = new ArrayList<Integer>();
			//Validates the order of the dates
			if(this.getFromDate().after(this.getToDate())){
				throw new WrongDateException();
			}
			//Calls the TransactionDetails method of InfyCreditCardWrapper to obtain the customer and his transaction's information between the dates
			Map<Long, Long> statistics = new InfyCreditCardWrapper().TransactionDetails(this.getFromDate(), this.getToDate());
			//Obtaining the key set composed of the credit card number of the customer that made transactions between the given dates
			Set keySet = statistics.keySet();
			Iterator<Long> iterator = keySet.iterator();
			while(iterator.hasNext()){
				Long key = iterator.next();
				CardTO to = new CardTO();
				to.setCardNo(key);
				transactionCard.add(key);
				System.out.println(to.getCardNo());
				//Calling the getCardDetails method of the InfyCreditCardWrapper to obtain the credit card information of the transaction made
				to =  new InfyCreditCardWrapper().getCardDetails(to);
				CustomerTO customerTo =  new CustomerTO();
				customerTo.setCustomerId(to.getCustomerId());
				//Caling the getCustomerDetails of the InfyCreditCardWrapper to obtain the customer information of the credit card owner
				customerTo = new InfyCreditCardWrapper().getCustomerDetails(customerTo);
				customers.add(customerTo);
				Integer noTransactions = Integer.parseInt(statistics.get(key).toString());				
				//Storing the number of transactions made by a customer in count
				count.add(noTransactions);
			}			
			//Changing the rendered parameters of the jsp elements
			this.setHyperlinkUI(true);
			this.setCustomerUI(false);
			this.setTransactionUI(false);
			this.setMessage("");
			System.out.println(hyperlinkUI);
			return "success";
		}catch(Exception exception){
			this.setMessage(exception.getMessage());
			exception.printStackTrace();
			return "fail";
		}
	}
	
	/** 
	* @Method Name: getTransactions
	* @Description: get the transaction information of a particular customer
	* @User: ricardo_406737
	* @Return Type: String
	* @return
	 */
	public String getTransactions(){
		//Obtains the index of the selected transaction
		int transactionIndex = transactionTable.getRowIndex();
		//Updates the credit card number of the bean
		this.setCardNo(transactionCard.get(transactionIndex));
		//Updates the rendered parameters of the jsp elements
		this.setCustomerUI(false);	
		this.setTransactionUI(true);
		//Invokes the getTransaction methods of the InfyCreditCardWrapper to obtain the transaction information between the given dates
		transaction = new InfyCreditCardWrapper().getTransactions(this.getCardNo(), this.getFromDate(), this.getToDate());
		return "success";
	}
	
	/** 
	* @Method Name: getCustomer
	* @Description: OBtains the information of the customer selected in the table
	* @User: ricardo_406737
	* @Return Type: String
	* @return
	 */
	public String getCustomer(){
		//Retrieves the customer selected
		customerDetails = (CustomerTO)customerTable.getRowData();
		//Updates the rendered values of the jsp elements
		this.setCustomerUI(true);	
		this.setTransactionUI(false);
		return "success";
	}
	
	
	/**
	 * @User ricardo_406737
	 * @Method getCustomerTable
	 * @return customerTable 
	 */
	
	public UIData getCustomerTable() {
		return customerTable;
	}


	/**
	 * @User ricardo_406737
	 * @Method setCustomerTable
	 * @param customerTable the customerTable to set
	 */
	
	public void setCustomerTable(UIData customerTable) {
		this.customerTable = customerTable;
	}


	/**
	 * @User ricardo_406737
	 * @Method getTransactionTable
	 * @return transactionTable 
	 */
	
	public UIData getTransactionTable() {
		return transactionTable;
	}


	/**
	 * @User ricardo_406737
	 * @Method setTransactionTable
	 * @param transactionTable the transactionTable to set
	 */
	
	public void setTransactionTable(UIData transactionTable) {
		this.transactionTable = transactionTable;
	}


	/**
	 * @User ricardo_406737
	 * @Method getCustId
	 * @return custId 
	 */
	
	public int getCustId() {
		return custId;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCustId
	 * @param custId the custId to set
	 */
	
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCardNo
	 * @return cardNo 
	 */
	
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User ricardo_406737
	 * @Method getFromDate
	 * @return fromDate 
	 */
	
	public Calendar getFromDate() {
		return fromDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method setFromDate
	 * @param fromDate the fromDate to set
	 */
	
	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method getToDate
	 * @return toDate 
	 */
	
	public Calendar getToDate() {
		return toDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method setToDate
	 * @param toDate the toDate to set
	 */
	
	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCustoemrDetails
	 * @return custoemrDetails 
	 */
	
	public CustomerTO getCustomerDetails() {
		return customerDetails;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCustoemrDetails
	 * @param custoemrDetails the custoemrDetails to set
	 */
	
	public void setCustomerDetails(CustomerTO custoemrDetails) {
		this.customerDetails = custoemrDetails;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCustomers
	 * @return customers 
	 */
	
	public List<CustomerTO> getCustomers() {
		return customers;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCustomers
	 * @param customers the customers to set
	 */
	
	public void setCustomers(List<CustomerTO> customers) {
		this.customers = customers;
	}
	/**
	 * @User ricardo_406737
	 * @Method getCount
	 * @return count 
	 */
	
	public List<Integer> getCount() {
		return count;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCount
	 * @param count the count to set
	 */
	
	public void setCount(List<Integer> count) {
		this.count = count;
	}
	/**
	 * @User ricardo_406737
	 * @Method getTransaction
	 * @return transaction 
	 */
	
	public List<TransactionTO> getTransaction() {
		return transaction;
	}
	/**
	 * @User ricardo_406737
	 * @Method setTransaction
	 * @param transaction the transaction to set
	 */
	
	public void setTransaction(List<TransactionTO> transaction) {
		this.transaction = transaction;
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
	 * @User ricardo_406737
	 * @Method setCustId
	 * @param custId the custId to set
	 */
	
	public void setCustId(Integer custId) {
		this.custId = custId;
	}


	/**
	 * @User ricardo_406737
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}


	/**
	 * @User ricardo_406737
	 * @Method isHyperlinkUI
	 * @return hyperlinkUI 
	 */
	
	public boolean isHyperlinkUI() {
		return hyperlinkUI;
	}


	/**
	 * @User ricardo_406737
	 * @Method setHyperlinkUI
	 * @param hyperlinkUI the hyperlinkUI to set
	 */
	
	public void setHyperlinkUI(boolean hyperlinkUI) {
		this.hyperlinkUI = hyperlinkUI;
	}


	/**
	 * @User ricardo_406737
	 * @Method isCustomerUI
	 * @return customerUI 
	 */
	
	public boolean isCustomerUI() {
		return customerUI;
	}


	/**
	 * @User ricardo_406737
	 * @Method setCustomerUI
	 * @param customerUI the customerUI to set
	 */
	
	public void setCustomerUI(boolean customerUI) {
		this.customerUI = customerUI;
	}


	/**
	 * @User ricardo_406737
	 * @Method isTransactionUI
	 * @return transactionUI 
	 */
	
	public boolean isTransactionUI() {
		return transactionUI;
	}


	/**
	 * @User ricardo_406737
	 * @Method setTransactionUI
	 * @param transactionUI the transactionUI to set
	 */
	
	public void setTransactionUI(boolean transactionUI) {
		this.transactionUI = transactionUI;
	}
	
	
	
}
