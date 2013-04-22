/**
 * Project Name: ICCI BANK PRUEBA
 * User: leslie_406760
 * Date: Oct 10, 2012
 */

package com.infy.icci.service;

import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;

import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.SchemeEntity;
import com.infy.icci.exceptions.InvalidCustomerIdException;
import com.infy.icci.exceptions.InvalidSchemeException;
import com.infy.icci.exceptions.SchemeAlreadyChosenException;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.SchemeTO;

/**
 * 
 * Project Name: ICCI BANK
 * User: juan_406752, leslie_406760
 * Date: Oct 11, 2012
 */

public class SchemeService {

	private EntityManagerFactory emf;
	private EntityManager em;

	/**
	 * 
	 * Constructor
	 */
	public SchemeService() {
		emf = Persistence.createEntityManagerFactory("ICCI BANK");
	}

	/**
	 * @Method Name: getSchemeDetails
	 * @Description: This method retrieve an scheme with all the 
	 * details to be displayed
	 * @User: leslie_406760
	 * @Return Type: SchemeTO
	 * @param schemeTO
	 * @return
	 * @throws Exception 
	 */
	public SchemeTO getSchemeDetails(SchemeTO schemeTO) 
	throws Exception{

		SchemeTO schemeToResult = null;
		try{			
			em=emf.createEntityManager();
			SchemeEntity entity = em.find(SchemeEntity.class, 
					schemeTO.getSchemeId());		

			if(entity != null){
				schemeToResult = new SchemeTO();
				schemeToResult.setInterestRate(entity.getInterestRate());
				schemeToResult.setMinimumAmount(entity.getMinimumAmount());
				schemeToResult.setSchemeAmount(entity.getSchemeAmount());
				schemeToResult.setSchemeId(entity.getSchemeId());
			}else{
				throw new InvalidSchemeException();

			}
			return schemeToResult;
		}catch (Exception e) {
			e.getMessage();
			return null;
		}finally{

			if(em != null){
				em.close();
			}
		}
	}

	/**
	 * @Method Name: schemeChosen
	 * @Description: This method checks whether the customer has already
	 * chosen one scheme
	 * @User: juan_406752
	 * @Return Type: boolean
	 * @param customerTO
	 * @return a boolean indicating if the customer has an scheme chosen 
	 * or not
	 * @throws SchemeAlreadyChosenException 
	 * @throws InvalidCustomerIdException 
	 */
	
	public boolean schemeChosen(CustomerTO customerTO) 
		throws SchemeAlreadyChosenException, InvalidCustomerIdException, SQLException{
		/*
		 * Create the entity manager factory with the name of the 
		 * persistence unit
		 */
		EntityManagerFactory emf2 = 
			Persistence.createEntityManagerFactory("ICCI BANK");
		
		/*
		 * Initializing the entityManager as null
		 */
		EntityManager em2 = null;
		try {
			em2 = emf2.createEntityManager();
			
			/*
			 * Get the card number from the session
			 */
			HttpSession session = (HttpSession)FacesContext.
			 	getCurrentInstance().getExternalContext().getSession(false);
						
			Long cardNo = (Long)session.getAttribute("cardNo");
			
			/*
			 * Create the query to search if the customer have 
			 * an scheme chosen
			 */
			Query query = em2.createQuery("select c.scheme from " +
			"CardEntity c where c.cardNo =?2");
				
			query.setParameter(2, cardNo);
			
			/*
			 * If the object aux is null, so the customer has not
			 * an scheme
			 */
			Object aux = query.getSingleResult();
			
			SchemeEntity scheme = (SchemeEntity)aux;
			
			/*
			 * The scheme N indicates a default value that means the
			 * customer does not have an scheme chosen already
			 */
			if(scheme.getSchemeId() == 'N'){
				return false;
			}
									
			return true;

		} finally {
			/*
			 * Close the entity manager
			 */
			if(em2!= null){
				em2.close();
			}
		}
	}

	/**
	* @Method Name: updateSchemeDetails
	* @Description: This method updates the scheme of the customer
	* @User: juan_406752
	* @Return Type: void
	* @param cardTO
	* @throws Exception
	* @throws SQLException
	*/
	public void updateSchemeDetails(CardTO cardTO) 
		throws Exception, SQLException{
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			/*
			 * Find an entity of card with the object passed as a parameter
			 */
			CardEntity card = em.find(CardEntity.class, cardTO.getCardNo());
			
			if(card == null){
				throw new Exception("Entity card not found");
			}
			/*
			 * Getting the sessionId from the session
			 */
			HttpSession session = (HttpSession)FacesContext.
				getCurrentInstance().getExternalContext().getSession(false);
			
			String scheme = session.getAttribute("schemeId").toString();			
			char schemeId = scheme.charAt(0);
			
			/*
			 * Create the entity of schemeEntity to be persisted in card
			 */
			SchemeEntity schemeEntity = new SchemeEntity();
			
			schemeEntity.setSchemeId(schemeId);
			
			/*
			 * Find a schemeEntity with the details of SchemeAmount, 
			 * balanceAmount, etc. 
			 */
			SchemeEntity schemeEntity2 = em.find(SchemeEntity.class, schemeId);
			
			/*
			 * Persist the entity card with the new scheme
			 */
			card.setScheme(schemeEntity);
			card.setCardAmount(schemeEntity2.getSchemeAmount());
						
			/*
			 * Commit the changes done
			 */
			em.getTransaction().commit();

		} finally {
			/*
			 * If the entity manager is not null, it needs to be closed
			 */
			if(em!= null){
				em.close();
			}
		}
	}
}
