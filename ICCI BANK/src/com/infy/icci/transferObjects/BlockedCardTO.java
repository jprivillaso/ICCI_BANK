package com.infy.icci.transferObjects;

import java.util.Calendar;

public class BlockedCardTO {
	
	private int blockId;
	private long cardNo;
	private Calendar dateOfBlock;
	private String description;
	private char status;
	
	/**
	 * 
	* Constructor
	 */
	public BlockedCardTO() {}
	
	/**
	 * 
	* Constructor
	* @param blockId
	* @param cardNo
	* @param dateOfBlock
	* @param description
	* @param status
	 */
	public BlockedCardTO(int blockId, long cardNo, Calendar dateOfBlock,
			String description, char status) {
		this.blockId = blockId;
		this.cardNo = cardNo;
		this.dateOfBlock = dateOfBlock;
		this.description = description;
		this.status = status;
	}


	/**
	 * @User andres_406763
	 * @Method getBlockId
	 * @return blockId 
	 */
	public int getBlockId() {
		return blockId;
	}


	/**
	 * @User andres_406763
	 * @Method setBlockId
	 * @param blockId the blockId to set
	 */
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}


	/**
	 * @User andres_406763
	 * @Method getCardNo
	 * @return cardNo 
	 */
	public long getCardNo() {
		return cardNo;
	}


	/**
	 * @User andres_406763
	 * @Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}


	/**
	 * @User andres_406763
	 * @Method getDateOfBlock
	 * @return dateOfBlock 
	 */
	public Calendar getDateOfBlock() {
		return dateOfBlock;
	}


	/**
	 * @User andres_406763
	 * @Method setDateOfBlock
	 * @param dateOfBlock the dateOfBlock to set
	 */
	public void setDateOfBlock(Calendar dateOfBlock) {
		this.dateOfBlock = dateOfBlock;
	}


	/**
	 * @User andres_406763
	 * @Method getDescription
	 * @return description 
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @User andres_406763
	 * @Method setDescription
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @User andres_406763
	 * @Method getStatus
	 * @return status 
	 */
	public char getStatus() {
		return status;
	}


	/**
	 * @User andres_406763
	 * @Method setStatus
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}
	
}
