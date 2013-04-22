package com.infy.icci.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.infy.icci.entity.LoginEntity;
import com.infy.icci.exceptions.InvalidLoginException;
import com.infy.icci.transferObjects.LoginTO;

/**
 * 
* @author juan_406753
*
 */
public class LoginService {

	/**
	 * 
	* @Method Name: validateLogin
	* @User: Juan_406762
	* @Return Type: LoginTO
	* @param loginTo
	* @return loginToTemp
	* @throws InvalidLoginException
	 */
	public LoginTO validateLogin(LoginTO loginTo) throws InvalidLoginException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI BANK");
		EntityManager em = null;
		try {
			String userName = loginTo.getUserId();
			String password = loginTo.getPassword();
			em = emf.createEntityManager();
			/* Get the LoginEntity object from the database which match 
			 * with the LoginTO object received as parameter of the function	 
			 */
			LoginEntity loginEntity = em.find(LoginEntity.class, userName);
			/* Validate the userName and password */
			if(loginEntity != null && password.equals(loginEntity.getPassword())) {
				LoginTO loginToTemp = new LoginTO();
				/* Set the properties of a new LoginTO instance with the values
				 * of the LoginEntity object found 
				 */
				loginToTemp.setUserId(loginEntity.getUserId());
				loginToTemp.setPassword(loginEntity.getPassword());
				return loginToTemp;
			}
			else {
				throw new InvalidLoginException();
			}
		}
		finally {
			if(em != null)
				em.close();
		}
	}
}
