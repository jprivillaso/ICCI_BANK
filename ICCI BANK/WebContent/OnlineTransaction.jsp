<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Transaction</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<div class="image"></div>
	<h:form>
		
		<h:panelGrid border="1" columns="2" styleClass="bigTable">
			<f:facet name="header"><h:outputLabel value="Make an Online Transaction" styleClass="header"/></f:facet>
			
			<h:outputLabel value="Description" ></h:outputLabel>
			<h:inputText id="description" value="#{transactionMB.description}" required="true" requiredMessage="Mandatory Field: Please enter the  value for description"  validatorMessage="String Field: enter a valid string in the description">
				<f:validator validatorId="StringValidator"/>
			</h:inputText>
			<h:outputLabel value="Amount" ></h:outputLabel>
			<h:inputText id="amount" value="#{transactionMB.amount}" 
			required="true" requiredMessage="Mandatory Field: Please enter the  value for amount" 
			converter="javax.faces.Double" converterMessage="Numeric Field : Please enter only numbers for amount">
			<f:validateLongRange minimum="1"></f:validateLongRange>
			</h:inputText>
			<h:commandButton type="submit" value="Pay Online" action="#{transactionMB.saveTransaction}"></h:commandButton>

		</h:panelGrid>
		<h:message for="description"></h:message><br>
		<h:message for="amount"></h:message><br>
		<h:outputLabel styleClass="static" value="#{transactionMB.errorMessage}"></h:outputLabel><br>
		<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
	

	</h:form>
	</center>
</f:view>
</body>
</html>
