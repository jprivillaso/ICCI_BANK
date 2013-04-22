package com.infy.icci.manager;

import com.infy.icci.exceptions.InvalidLoginException;
import com.infy.icci.service.LoginService;
import com.infy.icci.transferObjects.LoginTO;

/**
 * 
* @author juan_406753
*
 */
public class LoginManager {
	
	/**
	* @Method Name: validateLogin
	* @Description:  Method implemented to call validateLogin method from service
	* @User: Juan_406762
	* @Return Type: LoginTO
	* @param to
	* @return LoginTO instance
	* @throws InvalidLoginException
	 */
	public LoginTO validateLogin(LoginTO to) throws InvalidLoginException{
		return new LoginService().validateLogin(to);
	}
}
