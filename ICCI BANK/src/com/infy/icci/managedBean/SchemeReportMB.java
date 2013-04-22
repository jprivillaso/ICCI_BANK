package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class SchemeReportMB {

	private List<SelectItem> customerIdList;
	private List<SelectItem> cardNoList;
	private CustomerTO customerTo;
	private CardTO cardTo;
	private String message;
	private boolean showCardDetails;
	private boolean showCustomerDetails;
	private boolean emptyCustList;
	
	public SchemeReportMB(){
		this.showCardDetails = false;
		this.showCustomerDetails = false;
		this.emptyCustList = false;
	}
	
	/**
	 * @User juan_406753
	 * @Method getCustomerIdList
	 * @return customerIdList 
	 */
	
	public List<SelectItem> getCustomerIdList() {
		return customerIdList;
	}
	/**
	 * @User juan_406753
	 * @Method setCustomerIdList
	 * @param customerIdList the customerIdList to set	
	 */
	
	public void setCustomerIdList(List<SelectItem> customerIdList) {
		this.customerIdList = customerIdList;
	}
	/**
	 * @User juan_406753
	 * @Method getCardNoList
	 * @return cardNoList 
	 */
	
	public List<SelectItem> getCardNoList() {
		return cardNoList;
	}
	/**
	 * @User juan_406753
	 * @Method setCardNoList
	 * @param cardNoList the cardNoList to set
	 */
	
	public void setCardNoList(List<SelectItem> cardNoList) {
		this.cardNoList = cardNoList;
	}
	/**
	 * @User juan_406753
	 * @Method getCustomerTo
	 * @return customerTo 
	 */
	
	public CustomerTO getCustomerTo() {
		return customerTo;
	}
	/**
	 * @User juan_406753
	 * @Method setCustomerTo
	 * @param customerTo the customerTo to set
	 */
	
	public void setCustomerTo(CustomerTO customerTo) {
		this.customerTo = customerTo;
	}
	/**
	 * @User juan_406753
	 * @Method getCardTo
	 * @return cardTo 
	 */
	
	public CardTO getCardTo() {
		return cardTo;
	}
	/**
	 * @User juan_406753
	 * @Method setCardTo
	 * @param cardTo the cardTo to set
	 */
	
	public void setCardTo(CardTO cardTo) {
		this.cardTo = cardTo;
	}
	/**
	 * @User juan_406753
	 * @Method getMessage
	 * @return message 
	 */
	
	public String getMessage() {
		return message;
	}
	/**
	 * @User juan_406753
	 * @Method setMessage
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @User juan_406753
	 * @Method isShowCardDetails
	 * @return showCardDetails 
	 */
	
	public boolean isShowCardDetails() {
		return showCardDetails;
	}

	/**
	 * @User juan_406753
	 * @Method setShowCardDetails
	 * @param showCardDetails the showCardDetails to set
	 */
	
	public void setShowCardDetails(boolean showCardDetails) {
		this.showCardDetails = showCardDetails;
	}

	/**
	 * @User juan_406753
	 * @Method isShowCustomerDetails
	 * @return showCustomerDetails 
	 */
	
	public boolean isShowCustomerDetails() {
		return showCustomerDetails;
	}

	/**
	 * @User juan_406753
	 * @Method setShowCustomerDetails
	 * @param showCustomerDetails the showCustomerDetails to set
	 */
	
	public void setShowCustomerDetails(boolean showCustomerDetails) {
		this.showCustomerDetails = showCustomerDetails;
	}

	/**
	 * @User juan_406753
	 * @Method isEmptyCustList
	 * @return emptyCustList 
	 */
	
	public boolean isEmptyCustList() {
		return emptyCustList;
	}

	/**
	 * @User juan_406753
	 * @Method setEmptyCustList
	 * @param emptyCustList the emptyCustList to set
	 */
	
	public void setEmptyCustList(boolean emptyCustList) {
		this.emptyCustList = emptyCustList;
	}

	
	/**
	 * 
	* @Method Name: getCustomersForScheme
	*  @Description: Get the customers for the selected scheme.
	* @User: juan_406753
	* @Return Type: void
	* @param e
	 */
	public void getCustomersForScheme(ValueChangeEvent e){
		System.out.println("Getting customers and cards");
		InfyCreditCardWrapper ccw = new InfyCreditCardWrapper();
		this.cardNoList = new ArrayList<SelectItem>();
		this.customerIdList = new ArrayList<SelectItem>();
		
		this.customerTo = null;
		this.cardTo = null;
		this.showCustomerDetails = false;
		this.showCardDetails = false;
		this.emptyCustList = true;
		
		try {
			this.message = "";
			SchemeTO schemeTo = new SchemeTO();
			String schemeId =(String)e.getNewValue();
			schemeTo.setSchemeId(schemeId.charAt(0));
			List<CardTO> cards = ccw.getCustomersForScheme(schemeTo);
			if (!cards.isEmpty()) {
				this.emptyCustList = false;
				for (CardTO cardTo : cards) {
					SelectItem itemCard = new SelectItem();
					itemCard.setLabel(String.valueOf(cardTo.getCardNo()));
					itemCard.setValue(cardTo.getCardNo());
					this.cardNoList.add(itemCard);
					
					SelectItem item = new SelectItem();
					item.setLabel(String.valueOf(cardTo.getCustomerId()));
					item.setValue(cardTo.getCustomerId());
					this.customerIdList.add(item);
				}
			} else {
				this.message = "There is no customers for this scheme";
				this.cardNoList = null;
				this.customerIdList = null;
			}
		} catch (Exception e1) {
			this.message = e1.getMessage();
		}
	}
	
	/**
	 * 
	* @Method Name: getCustomerAndCardDetails
	*  @Description: Get details of the customer and credit card.
	* @User: juan_406753
	* @Return Type: void
	* @param e
	 */
	public void getCustomerAndCardDetails(ValueChangeEvent e){
		InfyCreditCardWrapper ccw = new InfyCreditCardWrapper();
		try {
			String value = (String)e.getNewValue();
			
			if (value.length() == 16) { 		// Card details
				this.showCardDetails = true;
				this.showCustomerDetails = false;
				CardTO to = new CardTO();
				to.setCardNo(Long.parseLong(value));
				this.cardTo = ccw.getCardDetails(to);
			} else { 							// Customer details
				this.showCustomerDetails = true;
				this.showCardDetails = false;
				CustomerTO to = new CustomerTO();
				to.setCustomerId(Integer.parseInt(value));
				this.customerTo = ccw.getCustomerDetails(to);
			}
		} catch (Exception e1) {
			this.message = e1.getMessage();
		}
	}
}
