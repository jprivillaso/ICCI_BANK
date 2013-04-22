/**
* Project Name: ICCI BANK
* User: leslie_406760
* Date: Oct 10, 2012
*/

package com.infy.icci.manager;
import java.util.List;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.service.CardService;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.BlockedCardTO;

/**
 * @author leslie_406760
 *
 */

public class CardManager {
	
	/**
	 * 
	* @Method Name: getCardDetails
	* @Description: 
	* @User: leslie_406760
	* @Return Type: CardTO
	* @param to
	* @return CardTO instance
	 * @throws InvalidCardNoException 
	* @throws Exception
	 */
	public CardTO getCardDetails(CardTO to) throws InvalidCardNoException{
		return new CardService().getCardDetails(to);
	}
	
	/**
	 * 
	* @Method Name: unblockCard
	* @User: 
	* @Return Type: BlockedCardTO
	* @param to
	* @return
	 */
	public BlockedCardTO unblockCard(BlockedCardTO to){
		return null;
	}
	/**
	* @Method Name: applyCard
	* @Description: Method implemented to call apply card service 
	* @User: Alejandro_406765
	* @Return Type: CardApplicationTO
	* @param CardApplicationTO cardApplicationTO
	* @return CardApplicationTO
	* @throws Exception
	*/
	public CardApplicationTO applyCard(CardApplicationTO cardApplicationTO) throws Exception{
		// Create new instance of service
		CardService service = new CardService();
		// return the cardApplicationTO with the new addplicationId persisted
		return service.applyCard(cardApplicationTO);
	}
	/**
	* @Method Name: approveCard
	* @Description: Method implemented to call approveCard card service 
	* @User: Alejandro_406765
	* @Return void
	* @param int applicationId
	* @throws NoDataFoundException
	*/
	public void approveCard(int applicationId) throws NoDataFoundException{
		// Create new instance of service
		CardService service = new CardService();
		//call the approveCard method of service
		service.approveCard(applicationId);
	}
	
	/**
	* @Method Name: retrieveAppliedCardDetails
	* @Description: Method implemented to call retrieveAppliedCardDetails service 
	* @User: Alejandro_406765
	* @Return Type: List<CardApplicationTO>
	* @throws Exception
	*/
	public List<CardApplicationTO> retrieveAppliedCardDetails() throws Exception{
		// Create new instance of service
		CardService service = new CardService();
		// return the List<CardApplicationTO> with the list found
		return service.retrieveAppliedCardDetails();
	}
}
