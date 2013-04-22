<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="CSS/jquery-ui-1.9.0.custom.css">
<script type="text/javascript" src="JS/jquery-1.8.2.js"></script>
<script type="text/javascript" src="JS/jquery.ui.core.js"></script>
<script type="text/javascript" src="JS/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JS/jquery.ui.datepicker.js"></script>
<script>
$(document).ready(function(){
	$(".fromDate").datepicker({dateFormat:"dd-M-yy"});
	$(".toDate").datepicker({dateFormat:"dd-M-yy"});
});
</script>

<title>Transaction Statistics</title>
</head>
<body>
<f:view>
	<center>
	<div class="image"></div>
	<h:form>
		<h:outputLabel value="Transaction Statistics" styleClass="header"></h:outputLabel>
		<h:panelGrid border="1" columns="2" styleClass="bigTable">
			<h:outputLabel value="From Date"></h:outputLabel>
			<h:inputText id="fromDate" value="#{transactionStatisticsMB.fromDate}"
				required="true"
				requiredMessage="Mandatory Field: Please enter the  value for From Date"
				converterMessage="Invalid Date format for From Date: Please enter Date in DD-MON-YYYY format" styleClass="fromDate">
				<f:converter converterId="ConverterDates" />
			</h:inputText> 
			<h:outputLabel value="To Date"></h:outputLabel>
			<h:inputText id="toDate" value="#{transactionStatisticsMB.toDate}"
				required="true"
				requiredMessage="Mandatory Field: Please enter the  value for To Date"
				converterMessage="Invalid Date format for To Date: Please enter Date in DD-MON-YYYY format" styleClass="toDate">
				<f:converter converterId="ConverterDates" />
			</h:inputText>
			<h:commandButton type="submit" value="Submit" action="#{transactionStatisticsMB.getStatistics}"></h:commandButton>
			<h:commandButton type="submit" value="Reset" action="#{transactionStatisticsMB.reset}"></h:commandButton>
		</h:panelGrid>
		<br>
		<h:outputLabel value="#{transactionStatisticsMB.message}"></h:outputLabel>
		<h:message for="fromDate"></h:message><br>
		<h:message for="toDate"></h:message>
		<br>
		<h:panelGrid border="1" columns="2" styleClass="bigTable" rendered="#{transactionStatisticsMB.hyperlinkUI}">
			<h:dataTable border="1" value="#{transactionStatisticsMB.customers}"
				var="customer" binding="#{transactionStatisticsMB.customerTable}">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputLabel value="Customer Name" style="font-weight: bold"></h:outputLabel>
					</f:facet>
					<h:commandLink value="#{customer.name}" action="#{transactionStatisticsMB.getCustomer}" onclick="submit()"></h:commandLink>
				</h:column>
			</h:dataTable>
			<h:dataTable border="1" value="#{transactionStatisticsMB.count}"
				var="count" binding="#{transactionStatisticsMB.transactionTable}">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputLabel value="Number of Transactions"
							style="font-weight: bold"></h:outputLabel>
					</f:facet>
					<h:commandLink value="#{count}" action="#{transactionStatisticsMB.getTransactions}" onclick="submit()"></h:commandLink>
				</h:column>
			</h:dataTable>
		</h:panelGrid>
		<br>

		<h:dataTable border="1"
			value="#{transactionStatisticsMB.customerDetails}" var="details" rendered="#{transactionStatisticsMB.customerUI}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="CustomerName"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{details.name}"></h:outputLabel>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Customer Id"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{details.customerId}"></h:outputLabel>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Address"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{details.address}"></h:outputLabel>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Phone Number"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{details.phone}"></h:outputLabel>
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="E-Mail"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{details.email}"></h:outputLabel>
			</h:column>
			<h:column id="column6">
				<f:facet name="header">
					<h:outputText value="UserName"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{details.userName}"></h:outputLabel>
			</h:column>
		</h:dataTable>
		<br>
		<h:outputLabel value="Card Number: " rendered="#{transactionStatisticsMB.transactionUI}"></h:outputLabel>
		<h:outputLabel value="#{transactionStatisticsMB.cardNo}" rendered="#{transactionStatisticsMB.transactionUI}"></h:outputLabel>
		<h:dataTable border="1"	value="#{transactionStatisticsMB.transaction}" var="transactions" rendered="#{transactionStatisticsMB.transactionUI}">
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText value="Transaction ID"></h:outputText>
			</f:facet>
			<h:outputLabel value="#{transactions.transactionId}"></h:outputLabel>
		</h:column>
		<h:column id="column2">
			<f:facet name="header">
				<h:outputText value="Date of Transaction"></h:outputText>
			</f:facet>
			<h:outputLabel value="#{transactions.dateOfTransaction}">
				<f:converter converterId="ConverterDates"/>
			</h:outputLabel>
		</h:column>
		<h:column id="column3">
			<f:facet name="header">
				<h:outputText value="Description"></h:outputText>
			</f:facet>
			<h:outputLabel value="#{transactions.description}"></h:outputLabel>
		</h:column>
		<h:column id="column4">
			<f:facet name="header">
				<h:outputText value="Amount"></h:outputText>
			</f:facet>
			<h:outputLabel value="#{transactions.amount}"></h:outputLabel>
		</h:column>
		<h:column id="column5">
			<f:facet name="header">
				<h:outputText value="Balance Amount"></h:outputText>
			</f:facet>
			<h:outputLabel value="#{transactions.balanceAmount}"></h:outputLabel>
		</h:column>
	</h:dataTable><br><br>
	<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</h:form>		
	</center>
</f:view>
</body>
</html>
