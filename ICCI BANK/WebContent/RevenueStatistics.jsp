<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue Statistics</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
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
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:form id="form1">
		<h:panelGrid border="1" columns="3" styleClass="bigTable">
			<f:facet name="header">
				<h:outputText value="Revenue Statistics" styleClass="header" />
			</f:facet>
			<h:outputText value="From Date"></h:outputText>
			<h:inputText value="#{revenueStatisticsMB.fromDate}" required="true" requiredMessage="Mandatory Field: Please enter the value for From Date"
				converterMessage="Invalid Date format: Please enter From Date in 'DD-MON-YYYY' format"	id="txtFromDate" styleClass="fromDate">
				<f:converter converterId="ConverterDates"/>
			</h:inputText>			
			<h:message for="txtFromDate"></h:message>
			<h:outputText value="To Date"></h:outputText>
			<h:inputText value="#{revenueStatisticsMB.toDate}" required="true" requiredMessage="Mandatory Field: Please enter the value for To Date" 
			converterMessage="Invalid Date format: Please enter To Date in 'DD-MON-YYYY' format" id="txtToDate" styleClass="toDate">
				<f:converter converterId="ConverterDates"/>				
			</h:inputText>
			<h:message for="txtToDate"></h:message>
			<h:commandButton value="Get Details!"
				action="#{revenueStatisticsMB.getDetails}">
				</h:commandButton>
		</h:panelGrid>
		<br>
		<br>
		<h:outputText styleClass="static" value="#{revenueStatisticsMB.message}"></h:outputText>
		<br>
		<br>
		<h:panelGrid styleClass="bigTable" columns="2" border="1">
			<h:outputText value="Profit"></h:outputText>
			<h:outputText value="#{revenueStatisticsMB.profit}"></h:outputText>
			<h:outputText value="Revenue"></h:outputText>
			<h:outputText value="#{revenueStatisticsMB.revenue}"></h:outputText>
			<h:outputText value="Expenditure"></h:outputText>
			<h:outputText value="#{revenueStatisticsMB.expenditure}"></h:outputText>
		</h:panelGrid>
		<br>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</h:form>
</f:view></center>
</body>
</html>
