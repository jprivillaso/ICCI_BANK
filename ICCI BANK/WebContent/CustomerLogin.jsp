<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:form>
		<h:panelGrid border="1" columns="3" styleClass="bigTable">
			<f:facet name="header"><h:outputText value="Customer Login" styleClass="header"/></f:facet>
			<h:outputText value="Customer Id"></h:outputText>
			<h:inputText id="txtCustomerId" value="#{customerMB.customerId}" required="true"
				requiredMessage="Mandatory field: Please enter the value for Customer Id."
				validatorMessage="Customer Id can only be numbers">
				<f:validator validatorId="validateNumbers"/>	
			</h:inputText>
			<h:message for="txtCustomerId"></h:message>
			<h:outputText value="Enter the code shown:"></h:outputText>
			<h:inputText id="txtCode" value="#{customerMB.enteredCode}" required="true"
				requiredMessage="Mandatory field: Please enter the code shown"></h:inputText>
			<h:message for="txtCode"></h:message>
			<h:outputText value="#{customerMB.randomCode}" styleClass="random"></h:outputText>
			<h:inputHidden value="#{customerMB.randomCode}"></h:inputHidden>
			<h:inputHidden></h:inputHidden>
			<h:commandButton value="Enter" type="submit" action="#{customerMB.validateCustomer}"></h:commandButton>
		</h:panelGrid>
	</h:form>
	<h:outputText styleClass="static" value="#{customerMB.message}"></h:outputText>
</f:view>
</center></body>
</html>
