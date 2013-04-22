<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blocked card statistics</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<div class="image"></div>
	<h2>Blocked card statistics</h2>
	<h:form>
		<h:panelGrid border="1" columns="2" styleClass="bigTable">
			<h:outputLabel value="Select the blocking reason"></h:outputLabel>
			<h:selectManyCheckbox value="#{blockStatisticsMB.reasons}" valueChangeListener="#{blockStatisticsMB.getBlockedDetails}" onclick="submit();">
				<f:selectItem itemLabel="Payment not done" itemValue="Payment not done"/>
				<f:selectItem itemLabel="Theft" itemValue="Theft"/>
				<f:selectItem itemLabel="Others" itemValue="Others"/>
			</h:selectManyCheckbox>

		</h:panelGrid>
		
		<br>
		<h:dataTable border="1" value="#{blockStatisticsMB.blockedCards}" var="cred" binding="#{blockStatisticsMB.cards}"
		 	styleClass="bigTable" rendered="#{blockStatisticsMB.showCreditCards}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Block Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{cred.blockId}" />
			</h:column>

			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Card Number"></h:outputText>
				</f:facet>
				<h:commandLink value="#{cred.cardNo}" action="#{blockStatisticsMB.getCardDetails}"></h:commandLink>
			</h:column>

			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Date Of Block"></h:outputText>
				</f:facet>
				<h:outputText value="#{cred.dateOfBlock}">
					<f:converter converterId="ConverterDates"/>
				</h:outputText>
			</h:column>
			
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Reason"></h:outputText>
				</f:facet>
				<h:outputText value="#{cred.description}" />
			</h:column>
		</h:dataTable>
		
		<br>
		<h:dataTable border="1" value="#{blockStatisticsMB.cardTo}" var="card" 
			styleClass="bigTable" rendered="#{blockStatisticsMB.showCreditCardDetails}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Card Number"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.cardNo}" />
			</h:column>

			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Scheme chosen"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.cardAmount}" />
			</h:column>

			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Balance Amount"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.customerId}" />
			</h:column>
			
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Date of Registration"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{card.dateOfRegistration}">
					<f:converter converterId="ConverterDates"/>
				</h:outputLabel>
			</h:column>
			
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Customer Id"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{card.dateOfRegistration}">
					<f:converter converterId="ConverterDates"/>
				</h:outputLabel>
			</h:column>
		</h:dataTable>
		
		<br>
		<h:outputLabel styleClass="static" value="#{blockStatisticsMB.message}"></h:outputLabel>
		
		<br>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>


	</h:form>
	</center>
</f:view>
</body>
</html>

