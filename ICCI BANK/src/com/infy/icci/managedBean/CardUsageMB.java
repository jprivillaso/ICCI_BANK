package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import com.infy.icci.transferObjects.TransactionTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

/**
 * Project Name: ICCI BANK
 * User: juan_406752
 * Date: Oct 10, 2012
 */
public class CardUsageMB {

	private List<TransactionTO> transactionList;
	private List<SelectItem> displayList = new ArrayList<SelectItem>();
	private String monthSelectedValue;
	private String option;
	private String message;

	/**
	 * Constructor
	 */	
	public CardUsageMB() {
		
	}

	/**
	 * @Method Name: populateTable
	 * @Description: This method populate the table of transactions
	 * displayed dynamically in the JSP page
	 * @User: juan_406752
	 * @Return Type: String
	 * @return an string indicating success or failure update
	 */
	public String populateTable(){

		try {
			/*
			 * Set the transaction list up to the data
			 * retrieved from the service method
			 */
			this.setTransactionList(new InfyCreditCardWrapper().
					cardUsage(this.getMonthSelectedValue(), this.getOption()));			
			
			/*
			 * Set an appropriate message if no transactions found
			 */
			if(transactionList.size() == 0 ){
				this.setMessage("No transactions found in this date");
			}
			return "success";

		} catch (Exception e) {
			/*
			 * Set a failure message
			 */
			this.setMessage("Could not populate the table");
			return "failure";
		}
	}


	/**
	 * @Method Name: populateMonths
	 * @Description: Is used to retrieve and display a List from 
	 * the getMonthList() method in Transaction manager
	 * @User: juan_406752
	 * @Return Type: void
	 * @param event
	 */
	public void populateMonths(ValueChangeEvent event){
		
		/*
		 * Initialize the array of months
		 */
		this.setOption(event.getNewValue().toString());			
		this.setTransactionList(new ArrayList<TransactionTO>());		
		/*
		 * Create a new list to store the list of months retrieved
		 * from the wrapper
		 */
		List list = new InfyCreditCardWrapper().getMonthList();
				
		/*
		 * In the first position, the months of the current year will
		 * be stored, and in the second position, the previous position
		 * will be stored 
		 */
		List current = (List) list.get(0);
		List previous = (List) list.get(1);
		
		/*
		 * The list to be displayed will be displayed with the data 
		 * corresponding on the period of year selected in the jsp page
		 */
		this.setDisplayList(new ArrayList<SelectItem>());
		if(this.getOption().equals("current")){
			for (Object object : current) {
				displayList.add(new SelectItem(object, object.toString()));
			}		
		}else{
			for (Object object : previous) {				
				displayList.add(new SelectItem(object, object.toString()));
			}		
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */

	/**
	 * @User juan_406752
	 * @Method getTransactionList
	 * @return transactionList 
	 */

	public List<TransactionTO> getTransactionList() {
		return transactionList;
	}

	/**
	 * @User juan_406752
	 * @Method setTransactionList
	 * @param transactionList the transactionList to set
	 */

	public void setTransactionList(List<TransactionTO> transactionList) {
		this.transactionList = transactionList;
	}

	/**
	 * @User juan_406752
	 * @Method getDisplayList
	 * @return displayList 
	 */

	public List<SelectItem> getDisplayList() {
		return displayList;
	}

	/**
	 * @User juan_406752
	 * @Method setDisplayList
	 * @param displayList the displayList to set
	 */

	public void setDisplayList(List<SelectItem> displayList) {
		this.displayList = displayList;
	}

	/**
	 * @User juan_406752
	 * @Method getMonthSelectedValue
	 * @return monthSelectedValue 
	 */

	public String getMonthSelectedValue() {
		return monthSelectedValue;
	}

	/**
	 * @User juan_406752
	 * @Method setMonthSelectedValue
	 * @param monthSelectedValue the monthSelectedValue to set
	 */

	public void setMonthSelectedValue(String monthSelectedValue) {
		this.monthSelectedValue = monthSelectedValue;
	}

	/**
	 * @User juan_406752
	 * @Method getOption
	 * @return option 
	 */

	public String getOption() {
		return option;
	}

	/**
	 * @User juan_406752
	 * @Method setOption
	 * @param option the option to set
	 */

	public void setOption(String option) {
		this.option = option;
	}
	

	/**
	 * @User juan_406752
	 * @Method getMessage
	 * @return message 
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * @User juan_406752
	 * @Method setMessage
	 * @param the message to set
	 */

	public void setMessage(String message) {
		this.message = message;
	}

}
