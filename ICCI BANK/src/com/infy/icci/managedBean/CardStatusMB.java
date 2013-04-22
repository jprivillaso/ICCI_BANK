/*
 * CardStatusMB.java
 */
package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.transferObjects.ApprovedCardTO;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

/**
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 11, 2012
*/
public class CardStatusMB {

	private Calendar fromDate;
	private Calendar toDate;
	private Long cardNo;
	private List cardNoList;
	private Double rejectedPercent;
	private Double approvedPercent;
	private List<CardApplicationTO> cardRejectedList;
	private List<ApprovedCardTO> cardApprovedList;
	private String message;
	private boolean renderPercent;
	private boolean renderTable;
	
	/**
	* Constructor
	*/
	public CardStatusMB(){
		cardRejectedList = new ArrayList<CardApplicationTO>();
		cardApprovedList = new ArrayList<ApprovedCardTO>();
		renderPercent=false;
		renderTable= false;
	}
	
	/** 
	* @Method Name: getCardNumbers
	* @Description: 
	* @User: andres_406763
	* @Return Type: String
	* @return
	*/
	public String getCardNumbers(){
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		try {
			//Empty message
			message="";
			//Set the cardNoList
			cardNoList= wrapper.getCardNumbers(fromDate, toDate);
			//Render data table
			renderTable= true;
			
		} catch (NoDataFoundException e) {
			//Set message
			message= e.getMessage();
			//Hide data table
			renderTable=false;
		}
		return "success";
	}
	
	/**
	* @Method Name: rejectedReport
	* @Description: 
	* @User: andres_406763
	* @Return Type: String
	* @return
	*/
	public String rejectedReport(){
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		try {
			cardRejectedList= wrapper.cardRejectedDetails(fromDate, toDate);
			return "successRejected";
		} catch (NoDataFoundException e) {
			//Hide data table
			renderTable=false;
			//Hide percent
			renderPercent=false;
			//Display Message
			message= e.getMessage();
			return "fail";
		}
	}
	
	/**
	* @Method Name: acceptedReport
	* @Description: 
	* @User: andres_406763
	* @Return Type: String
	* @return
	*/
	public String acceptedReport(){
		InfyCreditCardWrapper wrapper= new InfyCreditCardWrapper();
		cardApprovedList= wrapper.cardApprovedDetails(fromDate, toDate);
		return "successApproved";
	}
	
	/**
	* @Method Name: statusDetails
	* @Description: 
	* @User: andres_406763
	* @Return Type: String
	* @return
	*/
	public String statusDetails(){
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		try{
			//Empty message (since scope is session)
			message="";
			Map<String,Integer> resultMap= wrapper.statusDetails(fromDate, toDate);
			//Set approved and rejected percent with the result Map
			approvedPercent = Double.parseDouble(resultMap.get("approved").toString());
			rejectedPercent = Double.parseDouble(resultMap.get("rejected").toString());
			//Show the percent links
			renderPercent=true;
		}
		catch(NoDataFoundException e){
			//if no data found hide the percent links and data table
			renderPercent=false;
			renderTable=false;
			//Set appropriate message
			message= e.getMessage();
			//set approved and rejected to 0;
			approvedPercent=0.0;
			rejectedPercent=0.0;
		}
		catch(Exception e){
			//if fromDate is greater than toDate hide the percent links and data table
			renderPercent=false;
			renderTable=false;
			message=e.getMessage();
		}
		//Self navigation
		return "success";
	}
	
	/**
	 * @User andres_406763
	 * @Method getFromDate
	 * @return fromDate 
	 */
	public Calendar getFromDate() {
		return fromDate;
	}
	/**
	 * @User andres_406763
	 * @Method setFromDate
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @User andres_406763
	 * @Method getToDate
	 * @return toDate 
	 */
	public Calendar getToDate() {
		return toDate;
	}
	/**
	 * @User andres_406763
	 * @Method setToDate
	 * @param toDate the toDate to set
	 */
	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}
	/**
	 * @User andres_406763
	 * @Method getCardNo
	 * @return cardNo 
	 */
	public Long getCardNo() {
		return cardNo;
	}
	/**
	 * @User andres_406763
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @User andres_406763
	 * @Method getCardNoList
	 * @return cardNoList 
	 */
	public List getCardNoList() {
		return cardNoList;
	}
	/**
	 * @User andres_406763
	 * @Method setCardNoList
	 * @param cardNoList the cardNoList to set
	 */
	public void setCardNoList(List cardNoList) {
		this.cardNoList = cardNoList;
	}
	/**
	 * @User andres_406763
	 * @Method getRejectedPercent
	 * @return rejectedPercent 
	 */
	public Double getRejectedPercent() {
		return rejectedPercent;
	}
	/**
	 * @User andres_406763
	 * @Method setRejectedPercent
	 * @param rejectedPercent the rejectedPercent to set
	 */
	public void setRejectedPercent(Double rejectedPercent) {
		this.rejectedPercent = rejectedPercent;
	}
	/**
	 * @User andres_406763
	 * @Method getApprovedPercent
	 * @return approvedPercent 
	 */
	public Double getApprovedPercent() {
		return approvedPercent;
	}
	/**
	 * @User andres_406763
	 * @Method setApprovedPercent
	 * @param approvedPercent the approvedPercent to set
	 */
	public void setApprovedPercent(Double approvedPercent) {
		this.approvedPercent = approvedPercent;
	}
	/**
	 * @User andres_406763
	 * @Method getCardRejectedList
	 * @return cardRejectedList 
	 */
	public List<CardApplicationTO> getCardRejectedList() {
		return cardRejectedList;
	}
	/**
	 * @User andres_406763
	 * @Method setCardRejectedList
	 * @param cardRejectedList the cardRejectedList to set
	 */
	public void setCardRejectedList(List<CardApplicationTO> cardRejectedList) {
		this.cardRejectedList = cardRejectedList;
	}
	/**
	 * @User andres_406763
	 * @Method getCardApprovedList
	 * @return cardApprovedList 
	 */
	public List<ApprovedCardTO> getCardApprovedList() {
		return cardApprovedList;
	}
	/**
	 * @User andres_406763
	 * @Method setCardApprovedList
	 * @param cardApprovedList the cardApprovedList to set
	 */
	public void setCardApprovedList(List<ApprovedCardTO> cardApprovedList) {
		this.cardApprovedList = cardApprovedList;
	}

	/**
	 * @User andres_406763
	 * @Method getMessage
	 * @return message 
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @User andres_406763
	 * @Method setMessage
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @User andres_406763
	 * @Method isRenderPercent
	 * @return renderPercent 
	 */
	public boolean isRenderPercent() {
		return renderPercent;
	}

	/**
	 * @User andres_406763
	 * @Method setRenderPercent
	 * @param renderPercent the renderPercent to set
	 */
	public void setRenderPercent(boolean renderPercent) {
		this.renderPercent = renderPercent;
	}

	/**
	 * @User andres_406763
	 * @Method isRenderTable
	 * @return renderTable 
	 */
	public boolean isRenderTable() {
		return renderTable;
	}

	/**
	 * @User andres_406763
	 * @Method setRenderTable
	 * @param renderTable the renderTable to set
	 */
	public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}
}
