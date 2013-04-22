/**
* Project Name: ICCI BANK PRUEBA
* User: leslie_406760
* Date: Oct 10, 2012
*/

package com.infy.icci.manager;

import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.service.BlockedCardService;
import com.infy.icci.service.PaymentService;
import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.PaymentTO;

/**
 * @author leslie_406760, Carolina_406764
 *
 */

public class PaymentManager {
	
	/**
	* @Method Name: getPaymentDetails
	* @Description: Checks whether specified card is blocked.
	* The details are populated and returned in PaymentTO.
	* @User: leslie_406760, Carolina_406764
	* @Return Type: PaymentTO
	* @param CardTO to
	* @return paymentTO
	* @throws Exception, InvalidCardNoException 
	 */
	public PaymentTO getPaymentDetails(CardTO to) throws InvalidCardNoException, Exception{
		//Create an instance of PaymentService
		PaymentService service = new PaymentService();
		//call getPaymentDetails method from PaymentTO passing to parameter
		PaymentTO paymentTO = service.getPaymentDetails(to);
		//Create an instance of BlockedCardService and call checkCardBlocked method
		BlockedCardTO blockedCardTO = new BlockedCardService().checkCardBlocked(to.getCardNo());
		//Check if the status of the card is B (blocked) or N (Not blocked)
		if (blockedCardTO == null) {			
			paymentTO.setCardStatus("No Blocked");
		}else{
			paymentTO.setCardStatus("Blocked");		
		}
		return paymentTO;
	}
	
	/**
	 * 
	* @Method Name: makePayment
	* @Description: Checks whether specified card is blocked.
	* 				The details are populated and returned in PaymentTO.
	* @User: Carolina_406764
	* @Return Type: PaymentTO
	* @param to
	* @return paymentTO
	* @throws Exception
	 */

	public PaymentTO makePayment(PaymentTO to) throws Exception {
		//Create an instance of PaymentService
		PaymentService service = new PaymentService();
		//Create an instance of BlockedCardService
		BlockedCardService blockedCardService = new BlockedCardService();
		//Create an instance of BlockedCardTO
		BlockedCardTO blockedCardTO = new BlockedCardTO();
		//Call checkCardBlocked method and save it into blockedCardTO
		blockedCardTO = blockedCardService.checkCardBlocked(to.getCardNo());
		//Call makePayment method from PaymentService and make the payment
		to = service.makePayment(to);
		//Check if the status of the card is B (blocked) or N (Not blocked)
		if (blockedCardTO!=null) {
			// The card will be unblocked only if the description is "Payment not done"
			if(blockedCardTO.getDescription().equalsIgnoreCase("Payment not done")){
				blockedCardService.unblockCard(blockedCardTO);
			}
		}
		return to;
	}
	
}
