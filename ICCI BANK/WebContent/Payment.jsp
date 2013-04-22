<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="JS/CardNextField.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<title>Payment</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
<center>
<div class="image"></div>
<h:form id="creditCard">
<h:panelGrid border="1" columns="6" styleClass="smallTable">
	<f:facet name="header">
	<h:outputText value="Enter Card No" styleClass="header"></h:outputText>
	</f:facet>
		<h:outputText styleClass="static" value="Card No:"></h:outputText>
		<h:inputText id="IdFild1" maxlength="4" value="#{paymentMB.cardNo[0]}"
		styleClass="cellsSize" onkeyup="nextInput(this);">
		</h:inputText>
		<h:inputText id="IdFild2" maxlength="4" value="#{paymentMB.cardNo[1]}"
		styleClass="cellsSize" onkeyup="nextInput(this);">
		</h:inputText>
		<h:inputText id="IdFild3" maxlength="4" value="#{paymentMB.cardNo[2]}"
		styleClass="cellsSize" onkeyup="nextInput(this);">
		</h:inputText>
		<h:inputText id="IdFild4" maxlength="4" value="#{paymentMB.cardNo[3]}"
		styleClass="cellsSize" onkeyup="nextInput(this);">
		</h:inputText>
	</h:panelGrid>
	<h:commandButton type="submit" value="Ok" action="#{paymentMB.getPaymentInformation}"></h:commandButton><br><br>
	<h:outputText styleClass="static" value="#{paymentMB.message}" rendered="#{paymentMB.renderMessage}"></h:outputText><br><br>
	</h:form>
	<h:form id="makePayment">
<h:panelGrid border="1" columns="2" styleClass="bigTable" rendered="#{paymentMB.renderForm}">
	<f:facet name="header">
		<h:outputText value="Make a Payment:" styleClass="header"></h:outputText>
	</f:facet>
		<h:outputText value="CustomerId"></h:outputText>
		<h:outputText value="#{paymentMB.customerId}"></h:outputText>
		<h:outputText value="CustomerName"></h:outputText>
		<h:outputText value="#{paymentMB.customerName}"></h:outputText>
		<h:outputText value="Balance Amount"></h:outputText>
		<h:outputText value="#{paymentMB.balanceAmount}"></h:outputText>
		<h:outputText value="Last Payment Date"></h:outputText>
		<h:outputText value="#{paymentMB.lastPaymentDate}">
			<f:converter converterId="ConverterDates"/>
		</h:outputText>
		<h:outputText value="Card Status"></h:outputText>
		<h:outputText value="#{paymentMB.cardStatus}"></h:outputText>
		<h:outputText value="Select Payment Type"></h:outputText>
		<h:selectOneRadio value="#{paymentMB.paymentType}">
			<f:selectItem itemLabel="Full" itemValue='F'/>
			<f:selectItem itemLabel="Minimum" itemValue='M'/>
		</h:selectOneRadio>
		<h:inputHidden></h:inputHidden>
		<h:commandButton type="submit" value="Get Amount Details" action="#{paymentMB.calculateBalance}"></h:commandButton>
		<h:outputText value="Amount Paid"></h:outputText>
		<h:outputText value="#{paymentMB.amountPaid}"></h:outputText>
		<h:outputText value="Balance(inclusive of tax)"></h:outputText>
		<h:outputText value="#{paymentMB.balance}"></h:outputText>
	</h:panelGrid>
	<h:commandButton type="submit" value="Make Payment" action="#{paymentMB.makePayment}" rendered="#{paymentMB.renderForm}"
	disabled="#{paymentMB.disable}"></h:commandButton><br>
	<h:messages></h:messages><br><br>
	<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
</h:form>
</center>
</f:view>
</body>
</html>