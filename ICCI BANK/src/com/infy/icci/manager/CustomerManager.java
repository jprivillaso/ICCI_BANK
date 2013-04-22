/**
* Project Name: ICCI BANK PRUEBA
* User: leslie_406760
* Date: Oct 10, 2012
*/

package com.infy.icci.manager;

import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.exceptions.InvalidCustomerIdException;
import com.infy.icci.service.CustomerService;
import com.infy.icci.transferObjects.CustomerTO;

/**
 * @author leslie_406760
 *
 */

public class CustomerManager {

	/**
	 * 
	* @Method Name: getCustomerDetails
	* @User: juan_406753
	* @Return Type: CustomerTO
	* @param to
	* @return customerTO instance
	* @throws InvalidCustomerIdException
	 */
	public CustomerTO getCustomerDetails(CustomerTO to) throws InvalidCustomerIdException {
		return new CustomerService().getCustomerDetails(to);
	}
}
