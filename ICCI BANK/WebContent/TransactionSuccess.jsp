<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Success</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<div class="image"></div>
	<h:form>
		<h:panelGrid border="1" columns="2" styleClass="bigTable">
		<f:facet name="header">
			<h:outputLabel value="Successful Transaction" styleClass="header"></h:outputLabel>
		</f:facet>
			<h:outputLabel value="TransactionId"></h:outputLabel>
			<h:outputLabel value="#{transactionMB.transactionId}"></h:outputLabel>
			<h:outputLabel value="Description"></h:outputLabel>			
			<h:outputLabel value="#{transactionMB.description}"></h:outputLabel>
			<h:outputLabel value="Expenditure"></h:outputLabel>
			<h:outputLabel value="#{transactionMB.amount}"></h:outputLabel>
			<h:outputLabel value="Balance Amount"></h:outputLabel>
			<h:outputLabel value="#{transactionMB.balanceAmount}"></h:outputLabel>
			<h:outputLabel value="Date of Transaction"></h:outputLabel>
			<h:outputLabel value="#{transactionMB.dateOfTransaction}"><f:converter converterId="ConverterDates"/></h:outputLabel>
		</h:panelGrid><br><br>
		<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
	</h:form>
	</center>
</f:view>
</body>
</html>