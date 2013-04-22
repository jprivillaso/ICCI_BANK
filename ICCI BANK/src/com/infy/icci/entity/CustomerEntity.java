/*
 * CustomerEntity.java
 */
package com.infy.icci.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
* Entity to map the ICCI_Customer table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_CUSTOMER")
public class CustomerEntity {
	/**
	 * CustomerId is auto-generated using a sequence
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String userName;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
