/**
* Project Name: ICCI BANK PRUEBA
* User: leslie_406760
* Date: Oct 10, 2012
*/

package com.infy.icci.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.infy.icci.entity.CustomerEntity;
import com.infy.icci.exceptions.InvalidCustomerIdException;
import com.infy.icci.transferObjects.CustomerTO;

/**
 * @author leslie_406760
 *
 */

public class CustomerService {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	/**
	 * 
	* Constructor
	 */
	public CustomerService() {	
		emf=Persistence.createEntityManagerFactory("ICCI BANK");
	}
	
	/**
	 * 
	* @Method Name: getCustomerDetails
	* @Description: Retrieve and return the details of the customer whose customerid is 
	* 				specified as part of CustomerTO.
	* @User: leslie_406760
	* @Return Type: CustomerTO
	* @param to
	* @return customerToResult
	* @throws InvalidCustomerIdException
	 */
	public CustomerTO getCustomerDetails(CustomerTO to) throws InvalidCustomerIdException {
		em=null;
		CustomerTO customerToResult = null;
		try{			
			em=emf.createEntityManager();
			em.getTransaction().begin();
			/* Get the CustomerEntity object from the database which match 
			 * with the CustomerTO object received as parameter of the function	 
			 */
			CustomerEntity entity = em.find(CustomerEntity.class, to.getCustomerId());
			/* Validate if the CustomerEntity object has been found */
			if(entity != null){
				/* Set the properties of a new CustomerTO instance with the values
				 * of the CustomerEntity object found 
				 */
				customerToResult = new CustomerTO();
				customerToResult.setCustomerId(entity.getCustomerId());
				customerToResult.setAddress(entity.getAddress());
				customerToResult.setEmail(entity.getEmail());
				customerToResult.setName(entity.getName());
				customerToResult.setPhone(entity.getPhone());
				customerToResult.setUserName(entity.getUserName());
			}else{
				throw new InvalidCustomerIdException();
			}
			return customerToResult;
		}finally{
			if(em != null){
				em.close();
			}
		}
	}
	
	
}
