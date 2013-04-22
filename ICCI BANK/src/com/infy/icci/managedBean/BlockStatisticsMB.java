package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;

import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class BlockStatisticsMB {
	
	
	private String[] reasons;
	private List<BlockedCardTO> blockedCards;
	private long cardNo;
	private CardTO cardTo;
	private String message;
	private boolean showCreditCards;
	private boolean showCreditCardDetails;
	private HtmlDataTable cards;
	
	
	
	public BlockStatisticsMB() {
		this.showCreditCards = false;
		this.showCreditCardDetails = false;
	}
	/**
	 * @User ricardo_406737
	 * @Method getReasons
	 * @return reasons 
	 */
	
	public String[] getReasons() {
		return reasons;
	}
	/**
	 * @User ricardo_406737
	 * @Method setReasons
	 * @param reasons the reasons to set
	 */
	
	public void setReasons(String[] reasons) {
		this.reasons = reasons;
	}
	/**
	 * @User ricardo_406737
	 * @Method getBlockedCards
	 * @return blockedCards 
	 */
	
	public List<BlockedCardTO> getBlockedCards() {
		return blockedCards;
	}
	/**
	 * @User ricardo_406737
	 * @Method setBlockedCards
	 * @param blockedCards the blockedCards to set
	 */
	
	public void setBlockedCards(List<BlockedCardTO> blockedCards) {
		this.blockedCards = blockedCards;
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
	 * @Method getCardTo
	 * @return cardTo 
	 */
	
	public CardTO getCardTo() {
		return cardTo;
	}
	/**
	 * @User ricardo_406737
	 * @Method setCardTo
	 * @param cardTo the cardTo to set
	 */
	
	public void setCardTo(CardTO cardTo) {
		this.cardTo = cardTo;
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
	 * @User juan_406753
	 * @Method isShowCreditCards
	 * @return showCreditCards 
	 */
	
	public boolean isShowCreditCards() {
		return showCreditCards;
	}
	/**
	 * @User juan_406753
	 * @Method setShowCreditCards
	 * @param showCreditCards the showCreditCards to set
	 */
	
	public void setShowCreditCards(boolean showCreditCards) {
		this.showCreditCards = showCreditCards;
	}
	/**
	 * @User juan_406753
	 * @Method isShowCreditCardDetails
	 * @return showCreditCardDetails 
	 */
	
	public boolean isShowCreditCardDetails() {
		return showCreditCardDetails;
	}
	/**
	 * @User juan_406753
	 * @Method setShowCreditCardDetails
	 * @param showCreditCardDetails the showCreditCardDetails to set
	 */
	
	public void setShowCreditCardDetails(boolean showCreditCardDetails) {
		this.showCreditCardDetails = showCreditCardDetails;
	}
	
	/**
	 * @User juan_406753
	 * @Method getCards
	 * @return cards 
	 */
	
	public HtmlDataTable getCards() {
		return cards;
	}
	/**
	 * @User juan_406753
	 * @Method setCards
	 * @param cards the cards to set
	 */
	
	public void setCards(HtmlDataTable cards) {
		this.cards = cards;
	}
	/**
	 * 
	* @Method Name: getBlockedDetails
	*  @Description: Get the list of cards blocked for the selected reasons.
	* @User: juan_406753
	* @Return Type: void
	* @param e
	 */
	public void getBlockedDetails(ValueChangeEvent e){
		this.blockedCards = new ArrayList<BlockedCardTO>();
		this.showCreditCards = true;
		this.showCreditCardDetails = false;
		this.reasons = (String[])e.getNewValue();
		InfyCreditCardWrapper iccw = new InfyCreditCardWrapper();
		this.blockedCards = iccw.getBlockedDetails(this.reasons);
	}
	
	/**
	 * 
	* @Method Name: getCardDetails
	*  @Description: Get the details for the selected credit card number.
	* @User: juan_406753
	* @Return Type: String
	* @return
	 */
	public String getCardDetails() {
		int i = cards.getRowIndex();
		CardTO card = new CardTO();
		card.setCardNo(blockedCards.get(i).getCardNo());
		InfyCreditCardWrapper wr = new InfyCreditCardWrapper();
		try {
			this.showCreditCardDetails = true;
			this.showCreditCards = true;
			this.cardTo = wr.getCardDetails(card);
			return "success";

		} catch (Exception e) {
			this.showCreditCards = false;
			this.showCreditCardDetails = false;
			this.setMessage(e.getMessage());
			e.printStackTrace();
			return "fail";
		}
	}

	
	
}
