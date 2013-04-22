package com.infy.icci.manager;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.exceptions.NoRevenueFoundException;
import com.infy.icci.service.ReportService;
import com.infy.icci.transferObjects.ApprovedCardTO;
import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.transferObjects.TransactionTO;

/**
 * 
* @author juan_406753
*
 */
public class ReportManager {

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
		return new ReportService().getTransactions(cardNo, fromDate, toDate);
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
	public List<BlockedCardTO> getBlockedDetails(String[] reasons){
		return new ReportService().getBlockedDetails(reasons);
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
	public Map<Long, Long> transactionDetails (Calendar fromDaCalendar, Calendar toDate){
		return new ReportService().transactionDetails(fromDaCalendar, toDate);
	}
	
	/**
	 * 
	* @Method Name: getRevenueDetails
	* @User: juan_406753,leslie_406760
	* @Return Type: List
	* @param fromDate
	* @param toDate
	* @return
	 * @throws NoRevenueFoundException 
	 */
	public List<Double> getRevenueDetails(Calendar fromDate, Calendar toDate) throws NoRevenueFoundException,Exception{		
		return new ReportService().getRevenueDetails(fromDate, toDate);
	}
	
	/**
	 * 
	* @Method Name: getCardNumbers
	* @User: juan_406753
	* @Return Type: List
	* @param fromDate
	* @param toDate
	* @return
	 * @throws NoDataFoundException 
	 */
	public List getCardNumbers(Calendar fromDate, Calendar toDate) 
		throws NoDataFoundException{
		return new ReportService().getCardNumbers(fromDate, toDate);
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
		return new ReportService().cardRejectedDetails(fromDate, toDate);
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
		return new ReportService().cardApprovedDetails(fromDate, toDate);
	}
	
	/**
	 * 
	* @Method Name: statusDetails
	* @User: juan_406753
	* @Return Type: Map<String,Integer>
	* @param fromDate
	* @param toDate
	* @return
	 */
	public Map<String, Integer> statusDetails(Calendar fromDate, Calendar toDate)
		throws NoDataFoundException, Exception{
		ReportService reportService = new ReportService();
		return reportService.statusDetails(fromDate, toDate);
	}
	
	/**
	 * 
	* @Method Name: getCustomersForScheme
	* @User: juan_406753
	* @Return Type: List<CardTO>
	* @param schemeTo
	* @return
	 */
	public List<CardTO> getCustomersForScheme(SchemeTO schemeTo) throws Exception{
		return new ReportService().getCustomersForScheme(schemeTo);
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
		/*This method gets the details of the particular
		 *  customer by invoking the corresponding service method. 
		 *  Invokes the getCustomerDetails and getCardDetailsForUpdate methods of the wrapper.
		 *  
		 */
		return null;
	}
	
	
}
