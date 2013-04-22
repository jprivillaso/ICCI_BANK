/**
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 11, 2012
*/

package com.infy.icci.manager;

import com.infy.icci.exceptions.CannotUnblockCardException;
import com.infy.icci.exceptions.CardAlreadyBlockedException;
import com.infy.icci.exceptions.CardNotBlockedException;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.exceptions.InvalidCauseException;
import com.infy.icci.service.BlockedCardService;
import com.infy.icci.transferObjects.BlockedCardTO;

/**
 * Project Name: ICCI BANK
 * User: andres_406763
 * Date: Oct 11, 2012
 */
public class BlockedCardManager {
	
	/**
	 * 
	 * @Method Name: checkCardBlocked
	 * @Description: Method implemented to call checkCardBlocked method 
	 * 				 and blockCard method from service
	 * @User: Juan_406762
	 * @Return Type: BlockedCardTO
	 * @param to
	 * @return BlockedCardTO instance 
	 * @throws InvalidCardNoException 
	 * @throws CardAlreadyBlockedException
	 * @throws InvalidCauseException
	 * @throws Exception
	 */
	public BlockedCardTO checkCardBlocked(BlockedCardTO to) throws InvalidCardNoException, 
			CardAlreadyBlockedException, InvalidCauseException, Exception {
		BlockedCardService blockCardService = new BlockedCardService();
		BlockedCardTO toTemp = blockCardService.checkCardBlocked(to.getCardNo());
		/* If the card is not blocked, then call the blockCard method to block it,
		 * else throw an exception since the card is already blocked*/
		if(toTemp==null){
			return blockCardService.blockCard(to);
		}
		else{
			throw new CardAlreadyBlockedException();
		}
	}

	/**
	 * 
	* @Method Name: unblockCard
	* @Description: 
	* @User: andres_406763
	* @Return Type: BlockedCardTO
	* @param to
	* @return
	 */
	public BlockedCardTO unblockCard(BlockedCardTO to)
			throws InvalidCardNoException, 
			CardNotBlockedException, CannotUnblockCardException, Exception {
		BlockedCardService blockedCardService = new BlockedCardService();
		to = blockedCardService.checkCardBlocked(to.getCardNo());
		//if the card is blocked the method checkCardBlocked returns a not null
		if(to!=null){
			return blockedCardService.unblockCard(to);
		}
		else{
			throw new CardNotBlockedException();
		}
	}
}
