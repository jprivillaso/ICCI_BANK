/*
 * BlockedCardEntity.java
 */
package com.infy.icci.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Entity to map the ICCI_BlockedCard table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_BLOCKEDCARD")
public class BlockedCardEntity {
	
	/**
	 * Id is generated using a sequence
	 */
	@Id
	@SequenceGenerator(name="Seq_blockId", sequenceName="DB_Seq_blockId",
			initialValue=101, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_blockId")
	private int blockId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cardNo")
	private CardEntity card;
	@Temporal(TemporalType.DATE)
	private Calendar dateOfBlock;
	private String description;
	private char status;
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
	 * @Method getCard
	 * @return card 
	 */
	public CardEntity getCard() {
		return card;
	}
	/**
	 * @User andres_406763
	 * @Method setCard
	 * @param card the card to set
	 */
	public void setCard(CardEntity card) {
		this.card = card;
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
