/*
 * LoginEntity.java
 */
package com.infy.icci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entity to map the ICCI_Login table
* Project Name: ICCI BANK
* User: andres_406763
* Date: Oct 18, 2012
 */
@Entity
@Table(name="ICCI_LOGIN")
public class LoginEntity {

	@Id
	@Column(name="userName")
	private String userId;
	private String password;
	
	public LoginEntity(){
		
	}

	/**
	 * @User andres_406763
	 * @Method getUserId
	 * @return userId 
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @User andres_406763
	 * @Method setUserId
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @User andres_406763
	 * @Method getPassword
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @User andres_406763
	 * @Method setPassword
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
