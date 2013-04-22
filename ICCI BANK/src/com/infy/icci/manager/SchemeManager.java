/**
* Project Name: ICCI BANK PRUEBA
* User: leslie_406760
* Date: Oct 10, 2012
*/

package com.infy.icci.manager;

import java.sql.SQLException;

import com.infy.icci.exceptions.InvalidCustomerIdException;
import com.infy.icci.exceptions.InvalidSchemeException;
import com.infy.icci.exceptions.SchemeAlreadyChosenException;
import com.infy.icci.service.SchemeService;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.SchemeTO;

/**
* Project Name: ICCI BANK
* User: juan_406752, leslie_406760
* Date: Oct 11, 2012
*/
public class SchemeManager {
	
	/**
	* 
	* @Method Name: getSchemeDetails
	* @Description: This method retrieve an scheme with the details
	* of the scheme selected by the user in the JSP page
	* @User: leslie_406760
	* @Return Type: SchemeTO
	* @param schemeTO
	* @return SchemeTo object
	* @throws Exception
	*/
	public SchemeTO getSchemeDetails(SchemeTO schemeTO) throws Exception {		
		return new SchemeService().getSchemeDetails(schemeTO);
	}
	
	/**
	* @Method Name: schemeChosen
	* @Description: This method call the service, to know if the customer
	* has an scheme or not
	* @User: juan_406752
	* @Return Type: boolean
	* @param customerTO
	* @return a boolean indicating if the customer has an scheme or not
	* @throws InvalidCustomerIdException 
	* @throws SchemeAlreadyChosenException 
	* @throws SQLException 
	*/
	public boolean schemeChosen(CustomerTO customerTO) 
		throws SchemeAlreadyChosenException, InvalidCustomerIdException, SQLException{
		
		return new SchemeService().schemeChosen(customerTO);
	}
	
	/**
	* @Method Name: updateSchemeDetails
	* @Description: This method updates the scheme details by calling the 
	* Appropriate service
	* @User: juan_406752
	* @Return Type: void
	* @param cardTO
	*/
	public void updateSchemeDetails(CardTO cardTO) throws Exception{
		new SchemeService().updateSchemeDetails(cardTO);
	}
	
	
}
