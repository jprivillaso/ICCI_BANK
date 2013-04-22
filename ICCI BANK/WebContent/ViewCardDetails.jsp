<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Card Details</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:form>
		<h:panelGrid border="1" columns="2" styleClass="bigTable">
			<f:facet name="header"><h:outputText value="View Card Details" styleClass="header"/></f:facet>
			<h:outputText value="Customer Name"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.customerName}"></h:outputText>
			<h:outputText value="Card Number"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.cardNo}"></h:outputText>
			<h:outputText value="Balance Amount"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.balanceAmount}"></h:outputText>
			<h:outputText value="Scheme Name"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.schemeId}"></h:outputText>
			<h:outputText value="Scheme Amount"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.schemeAmount}"></h:outputText>
			<h:outputText value="Card Amount"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.cardAmount}"></h:outputText>
			<h:outputText value="Rate Of Interest"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.rateOfInterest}"></h:outputText>
			<h:outputText value="Last Payment Date"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.lastPaymentDate}" converter="ConverterDates">	
			<f:converter converterId="ConverterDates"/>
			</h:outputText>
			<h:outputText value="Card Status"></h:outputText>
			<h:outputText value="#{viewCardDetailsMB.cardStatus}"></h:outputText>
		</h:panelGrid>
		<h:outputText styleClass="static" value="#{viewCardDetailsMB.message}"></h:outputText><br>
		<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
	</h:form>
</f:view>
</center>
</body>
</html>
