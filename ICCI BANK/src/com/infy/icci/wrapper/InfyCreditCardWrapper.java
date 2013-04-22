package com.infy.icci.wrapper;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.infy.icci.exceptions.CannotUnblockCardException;
import com.infy.icci.exceptions.CardAlreadyBlockedException;
import com.infy.icci.exceptions.CardNotBlockedException;
import com.infy.icci.exceptions.InvalidCardNoException;
import com.infy.icci.exceptions.InvalidCauseException;
import com.infy.icci.exceptions.InvalidCustomerIdException;
import com.infy.icci.exceptions.InvalidLoginException;
import com.infy.icci.exceptions.LowBalanceException;
import com.infy.icci.exceptions.NoDataFoundException;
import com.infy.icci.exceptions.NoRevenueFoundException;
import com.infy.icci.exceptions.SchemeAlreadyChosenException;
import com.infy.icci.manager.BlockedCardManager;
import com.infy.icci.manager.CardManager;
import com.infy.icci.manager.CustomerManager;
import com.infy.icci.manager.LoginManager;
import com.infy.icci.manager.PaymentManager;
import com.infy.icci.manager.ReportManager;
import com.infy.icci.manager.SchemeManager;
import com.infy.icci.manager.TransactionManager;
import com.infy.icci.transferObjects.ApprovedCardTO;
import com.infy.icci.transferObjects.BlockedCardTO;
import com.infy.icci.transferObjects.CardApplicationTO;
import com.infy.icci.transferObjects.CardTO;
import com.infy.icci.transferObjects.CustomerTO;
import com.infy.icci.transferObjects.LoginTO;
import com.infy.icci.transferObjects.PaymentTO;
import com.infy.icci.transferObjects.SchemeTO;
import com.infy.icci.transferObjects.TransactionTO;

public class InfyCreditCardWrapper {

	/**
	 * 
	 * @Method Name: getCardDetails
	 * @Description:
	 * @User: leslie_406760
	 * @Return Type: CardTO
	 * @param to
	 * @return
	 * @throws InvalidCardNoException
	 */
	public CardTO getCardDetails(CardTO to) throws InvalidCardNoException {
		return new CardManager().getCardDetails(to);
	}

	/**
	 * 
	 * @Method Name: getSchemeDetails
	 * @Description: This method calls the manager to retrieve an schemeTo
	 *               object with all the details
	 * @User: juan_406752, leslie_406760
	 * @Return Type: SchemeTO
	 * @param schemeTO
	 * @return SchemeTO instance
	 * @throws Exception
	 */
	public SchemeTO getSchemeDetails(SchemeTO schemeTO) throws Exception {
		return new SchemeManager().getSchemeDetails(schemeTO);
	}

	/**
	 * @Method Name: schemeChosen
	 * @Description: This method return true if the customer has a scheme
	 *               chosen, otherwise it returns false
	 * @User: juan_406752
	 * @Return Type: boolean
	 * @param customerTO
	 * @return schemeTO instance
	 * @throws InvalidCustomerIdException
	 * @throws SchemeAlreadyChosenException
	 * @throws SQLException
	 */
	public boolean schemeChosen(CustomerTO customerTO)
			throws SchemeAlreadyChosenException, InvalidCustomerIdException,
			SQLException {
		return new SchemeManager().schemeChosen(customerTO);
	}

	/**
	 * @Method Name: updateSchemeDetails
	 * @Description: Update the scheme details by calling the updateDetails in
	 *               SchemeManager class
	 * @User: juan_406752
	 * @Return Type: void
	 * @param cardTO
	 * @throws Exception
	 */
	public void updateSchemeDetails(CardTO cardTO) throws Exception {
		new SchemeManager().updateSchemeDetails(cardTO);
	}

	/**
	 * @Method Name: cardUsage
	 * @Description: This method calls the manager to retrieve a list with all
	 *               the transactions in the period selected by the user in the
	 *               JSP page
	 * @User: juan_406752
	 * @Return Type: List<TransactionTO>
	 * @param month
	 * @param option
	 * @return The list of transactions to be displayed in the jsp page
	 * @throws Exception
	 */
	public List<TransactionTO> cardUsage(String month, String option)
			throws Exception {

		return new TransactionManager().cardUsage(month, option);
	}

	/**
	 * @Method Name: getPaymentDetails
	 * @Description: Method implemented to call getPaymentDetails from
	 *               PaymentManager.
	 * @User: Carolina_406764
	 * @Return Type: PaymentTO
	 * @param to
	 * @return paymentTO
	 * @throws Exception
	 * @throws InvalidCardNoException
	 * @throws Exception
	 */
	public PaymentTO getPaymentDetails(CardTO to)
			throws InvalidCardNoException, Exception {
		return new PaymentManager().getPaymentDetails(to);
	}

	/**
	 * @Method Name: unblockCard
	 * @Description:
	 * @User: andres_406763
	 * @Return Type: BlockedCardTO
	 * @param to
	 * @return
	 * @throws Exception
	 * @throws CannotUnblockCardException
	 * @throws CardNotBlockedException
	 * @throws InvalidCardNoException
	 */
	public BlockedCardTO unblockCard(BlockedCardTO to)
			throws InvalidCardNoException, CardNotBlockedException,
			CannotUnblockCardException, Exception {
		return new BlockedCardManager().unblockCard(to);
	}

	/**
	 * @Method Name: getCardNumbers
	 * @Description:
	 * @User: andres_406763
	 * @Return Type: List
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws NoDataFoundException
	 */
	public List getCardNumbers(Calendar fromDate, Calendar toDate)
			throws NoDataFoundException {
		return new ReportManager().getCardNumbers(fromDate, toDate);
	}

	/**
	 * 
	 * @Method Name: cardRejectedDetails
	 * @Description:
	 * @User: andres_406763
	 * @Return Type: List<CardApplicationTO>
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws NoDataFoundException
	 */
	public List<CardApplicationTO> cardRejectedDetails(Calendar fromDate,
			Calendar toDate) throws NoDataFoundException {
		return new ReportManager().cardRejectedDetails(fromDate, toDate);
	}

	/**
	 * 
	 * @Method Name: cardApprovedDetails
	 * @Description:
	 * @User: andres_406763
	 * @Return Type: List<ApprovedCardTO>
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<ApprovedCardTO> cardApprovedDetails(Calendar fromDate,
			Calendar toDate) {
		return new ReportManager().cardApprovedDetails(fromDate, toDate);
	}

	/**
	 * @Method Name: validateLogin
	 * @Description: Method implemented to call validateLogin method from
	 *               manager
	 * @User: Juan_406762
	 * @Return Type: LoginTO
	 * @param to
	 * @return LoginTO instance
	 * @throws InvalidLoginException
	 */
	public LoginTO validateLogin(LoginTO to) throws InvalidLoginException {
		return new LoginManager().validateLogin(to);
	}

	/**
	 * @Method Name: applyCard
	 * @Description: Method implemented to call apply card from manager
	 * @User: Alejandro_406765
	 * @Return Type: CardApplicationTO
	 * @param CardApplicationTO
	 *            cardApplicationTO
	 * @return CardApplicationTO
	 * @throws Exception
	 */
	public CardApplicationTO applyCard(CardApplicationTO to) throws Exception {
		// Instance of manager
		CardManager manager = new CardManager();
		// Return the new created CardApplicationTO with the new applicationId
		// persisted
		return manager.applyCard(to);
	}

	/**
	 * @Method Name: approveCard
	 * @Description: Method implemented to call apply card from manager
	 * @User: Alejandro_406765
	 * @Return Type: CardApplicationTO
	 * @param CardApplicationTO
	 *            cardApplicationTO
	 * @return CardApplicationTO
	 * @throws NoDataFoundException
	 * @throws Exception
	 */
	public void approveCard(int applicantId) throws NoDataFoundException {
		// Instance of manager
		CardManager manager = new CardManager();
		// Set approveCard method with the current Id
		manager.approveCard(applicantId);
	}

	/**
	 * @Method Name: retrieveAppliedCardDetails
	 * @Description: Method implemented to call retrieveAppliedCardDetails from
	 *               manager
	 * @User: Alejandro_406765
	 * @Return Type: List<CardApplicationTO>
	 * @throws Exception
	 */
	public List<CardApplicationTO> retrieveAppliedCardDetails()
			throws Exception {
		// Instance of manager
		CardManager manager = new CardManager();
		// Return the List<CardApplicationTO> list found in the manager
		return manager.retrieveAppliedCardDetails();
	}

	/**
	 * @Method Name: saveTransaction
	 * @Description:
	 * @User: ricardo_406737
	 * @Return Type: TransactionTO
	 * @param TransactionTO
	 * @return
	 * @throws LowBalanceException
	 */
	public TransactionTO saveTransaction(TransactionTO to) throws Exception {
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.saveTransaction(to);
	}

	/**
	 * @Method Name: statusDetails
	 * @Description:
	 * @User: andres_406763
	 * @Return Type: Map<String,Integer>
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws NoDataFoundException
	 */
	public Map<String, Integer> statusDetails(Calendar fromDate, Calendar toDate)
			throws NoDataFoundException, Exception {
		return new ReportManager().statusDetails(fromDate, toDate);
	}

	/**
	 * 
	 * @Method Name: getCustomersForScheme
	 * @Description: Get the customers for the selected scheme.
	 * @User: juan_406753
	 * @Return Type: List<CardTO>
	 * @param schemeTo
	 * @return
	 * @throws Exception
	 */
	public List<CardTO> getCustomersForScheme(SchemeTO schemeTo)
			throws Exception {
		return new ReportManager().getCustomersForScheme(schemeTo);
	}

	/**
	 * 
	 * @Method Name: getCustomerDetails
	 * @Description: Get details for the customer with the given id.
	 * @User: juan_406753
	 * @Return Type: CustomerTO
	 * @param to
	 * @return
	 * @throws InvalidCustomerIdException
	 */
	public CustomerTO getCustomerDetails(CustomerTO to)
			throws InvalidCustomerIdException {
		return new CustomerManager().getCustomerDetails(to);
	}

	/**
	 * @Method Name: getRevenueDetails
	 * @Description:
	 * @User:
	 * @Return Type: List<Double>
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<Double> getRevenueDetails(Calendar fromDate, Calendar toDate)
			throws NoRevenueFoundException,Exception {
		return new ReportManager().getRevenueDetails(fromDate, toDate);
	}

	/**
	 * @Method Name: makePayment
	 * @Description: Method implemented to call makePayment
	 * @User: Carolina_406764
	 * @Return Type: PaymentTO
	 * @param to
	 * @return paymentTO
	 * @throws Exception
	 */
	public PaymentTO makePayment(PaymentTO to) throws Exception {
		return new PaymentManager().makePayment(to);
	}

	/**
	 * @Method Name: checkCardBlocked
	 * @Description: Method implemented to call checkCardBlocked method from
	 *               manager
	 * @User: Juan_406762
	 * @Return Type: BlockedCardTO
	 * @param to
	 * @return BlockedCardTO instance
	 * @throws InvalidCardNoException
	 * @throws CardAlreadyBlockedException
	 * @throws InvalidCauseException
	 * @throws Exception
	 */
	public BlockedCardTO checkCardBlocked(BlockedCardTO to)
			throws InvalidCardNoException, CardAlreadyBlockedException,
			InvalidCauseException, Exception {
		return new BlockedCardManager().checkCardBlocked(to);
	}

	/**
	 * @Method Name: TransactionDetails
	 * @Description:
	 * @User: ricardo_406737
	 * @Return Type: Map
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public Map<Long, Long> TransactionDetails(Calendar fromDate, Calendar toDate) {
		return new ReportManager().transactionDetails(fromDate, toDate);
	}

	/**
	 * @Method Name: getBlockedDetails
	 * @Description:
	 * @User: juan_406753
	 * @Return Type: List<BlockedCardTO>
	 * @param reasons
	 * @return
	 */
	public List<BlockedCardTO> getBlockedDetails(String[] reasons) {
		return new ReportManager().getBlockedDetails(reasons);
	}

	/**
	 * @Method Name: getMonthList
	 * @Description: It call the service to retrieve the months to the managed
	 *               bean to be displayed
	 * @User: juan_406752
	 * @Return Type: List
	 * @return the list of months to be displayed in the jsp page
	 */
	public List getMonthList() {
		return new TransactionManager().getMonthList();
	}

	/**
	 * @Method Name: getTransactions
	 * @Description:
	 * @User:
	 * @Return Type: List<TransactionTO>
	 * @param cardNo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<TransactionTO> getTransactions(long cardNo, Calendar fromDate,
			Calendar toDate) {
		return new ReportManager().getTransactions(cardNo, fromDate, toDate);
	}

}
