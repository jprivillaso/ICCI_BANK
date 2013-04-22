/*
 * CardApplicationEntity.java
 */
package com.infy.icci.entity;

import java.util.Calendar;
import javax.persistence.NamedQuery;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
* Entity to map the ICCI_Card_Application_details table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_CARD_APPLICATION_DETAILS")
@NamedQuery(name="getCompleteList", query=
					"SELECT C FROM CardApplicationEntity C")
public class CardApplicationEntity {
	/**
	 * The applicationId is generated using a sequence
	 */
	@Id
	@SequenceGenerator(name="sqc_CardApplication", 
			sequenceName="sqc_CardApplication_DB",
			initialValue=40001, allocationSize=40050)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
			generator="sqc_CardApplication")
	private int aplicationId;
	private String name;
	private String email;
	private String phone;
	private String address;
	@Temporal(TemporalType.DATE)
	private Calendar dateOfApplication;
	/**
	 * @User andres_406763
	 * @Method getAplicationId
	 * @return aplicationId 
	 */
	public int getAplicationId() {
		return aplicationId;
	}
	/**
	 * @User andres_406763
	 * @Method setAplicationId
	 * @param aplicationId the aplicationId to set
	 */
	public void setAplicationId(int aplicationId) {
		this.aplicationId = aplicationId;
	}
	/**
	 * @User andres_406763
	 * @Method getName
	 * @return name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * @User andres_406763
	 * @Method setName
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @User andres_406763
	 * @Method getEmail
	 * @return email 
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @User andres_406763
	 * @Method setEmail
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @User andres_406763
	 * @Method getPhone
	 * @return phone 
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @User andres_406763
	 * @Method setPhone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @User andres_406763
	 * @Method getAddress
	 * @return address 
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @User andres_406763
	 * @Method setAddress
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @User andres_406763
	 * @Method getDateOfApplication
	 * @return dateOfApplication 
	 */
	public Calendar getDateOfApplication() {
		return dateOfApplication;
	}
	/**
	 * @User andres_406763
	 * @Method setDateOfApplication
	 * @param dateOfApplication the dateOfApplication to set
	 */
	public void setDateOfApplication(Calendar dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}
}
