<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="JS/CardNextField.js" type="text/javascript"></script>
<title>Customer Card Details</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:form id="cardDetails">
		<h:panelGrid border="1" columns="3" styleClass="bigTable">
			<f:facet name="header"><h:outputText value="Card Details" styleClass="header"/></f:facet>
			<h:outputText value="Enter your card No"></h:outputText>
			<h:panelGrid border="1" columns="4">
				<h:inputText value="#{cardMB.cardNo1}" id="IdField1" maxlength="4"
				onkeyup="nextInput(this);" onfocus="whenSelected(this)" styleClass="cellsSize"></h:inputText>
				<h:inputText value="#{cardMB.cardNo2}" id="IdField2" maxlength="4"
				onkeyup="nextInput(this);" onfocus="whenSelected(this)" styleClass="cellsSize"></h:inputText>
				<h:inputText value="#{cardMB.cardNo3}" id="IdField3" maxlength="4"
				onkeyup="nextInput(this);" onfocus="whenSelected(this)" styleClass="cellsSize"></h:inputText>
				<h:inputText value="#{cardMB.cardNo4}" id="IdField4" maxlength="4"
				onkeyup="nextInput(this);" onfocus="whenSelected(this)" styleClass="cellsSize"
				required="true" requiredMessage="Mandatory field: Please enter the value for Card No."
				validatorMessage="The Card No. field must have 16 digits.">
					<f:validateLength minimum="4" maximum="4"></f:validateLength>
				</h:inputText>
			</h:panelGrid>
			<h:message for="IdField4"></h:message>
			<h:outputText value="Enter your pin"></h:outputText>
			<h:inputText id="txtPin" value="#{cardMB.pin}" required="true" 
			requiredMessage="Mandatory field: Please enter the value for Pin." 
			validatorMessage="Pin can only be numbers">
				<f:validator validatorId="validateNumbers"/>
			</h:inputText>
			<h:message for="txtPin"></h:message>
			<h:commandButton value="Submit" type="submit" action="#{cardMB.checkCardDetails}"></h:commandButton>
		</h:panelGrid>
	</h:form>
	<h:outputText styleClass="static" value="#{cardMB.message}"></h:outputText>
</f:view>
</center></body>
</html>
