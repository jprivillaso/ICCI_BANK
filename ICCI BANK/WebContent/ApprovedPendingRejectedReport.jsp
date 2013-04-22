<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="CSS/style.css" rel="stylesheet">
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
<title>Approved &amp; Pending/Rejected Card Report</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<h2>Approved &amp; Pending/Rejected Card Report</h2>
<f:view>
<h:form>
	<h:panelGrid styleClass="bigTable" border="1" columns="3">
		<h:outputText value="From Date"></h:outputText>
		<h:inputText id="txtFromDate" value="#{cardStatusMB.fromDate}" styleClass="fromDate"
			required="true" requiredMessage="Mandatory Field: Please enter the value for From Date"
			converterMessage="Invalid Date (15-Oct-2010)">
			<f:converter converterId="ConverterDates"/>
		</h:inputText>
		<h:message for="txtFromDate"></h:message>
		<h:outputText value="To Date"></h:outputText>
		<h:inputText id="txtToDate" value="#{cardStatusMB.toDate}" styleClass="toDate"
			required="true" requiredMessage="Mandatory Field: Please enter the value for From Date"
			converterMessage="Invalid Date (15-Oct-2010)">
			<f:converter converterId="ConverterDates"/>
		</h:inputText>
		<h:message for="txtToDate"></h:message>
		<h:commandButton type="reset" value="Reset"></h:commandButton>
		<h:commandButton type="submit" value="Get Details" action="#{cardStatusMB.statusDetails}"></h:commandButton>
	</h:panelGrid>
	<h:outputText styleClass="static" value="#{cardStatusMB.message}"></h:outputText>
	<br>
	<h:outputText styleClass="static" value="Percentage of application approved :" rendered="#{cardStatusMB.renderPercent}"></h:outputText>
	<h:commandLink value="#{cardStatusMB.approvedPercent}%" action="#{cardStatusMB.getCardNumbers}" rendered="#{cardStatusMB.renderPercent}"></h:commandLink>
	<br>
	<h:dataTable value="#{cardStatusMB.cardNoList}" var="row" rendered="#{cardStatusMB.renderTable}">
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText styleClass="header" value="Card No"></h:outputText>
			</f:facet>
			<h:commandLink value="#{row}" action="#{cardStatusMB.acceptedReport}">
				<f:param name="cardNo" value="#{row}"></f:param>
			</h:commandLink>
		</h:column>
	</h:dataTable>
	<br>
	<h:outputText styleClass="static" value="Percentage of application rejected : " rendered="#{cardStatusMB.renderPercent}"></h:outputText>
	<h:commandLink value="#{cardStatusMB.rejectedPercent}%" action="#{cardStatusMB.rejectedReport}" rendered="#{cardStatusMB.renderPercent}"></h:commandLink>
	<br>
	<h:outputLink value = "CRHomePage.jsp">Home</h:outputLink>
</h:form>
</f:view>
</center>
</body>
</html>
