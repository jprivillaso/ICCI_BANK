<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CR Home Page</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:panelGrid border="1" columns="1" styleClass="bigTable">
		<f:facet name="header"><h:outputText value="Welcome #{customerMB.userName}" styleClass="header"/></f:facet>
		<h:outputLink value="ApproveCard.jsp">Register Card</h:outputLink>
		<h:outputLink value="Payment.jsp">Make Payment</h:outputLink>
		<h:outputLink value="BlockCard.jsp">Block Card</h:outputLink>
		<h:outputLink value="UnblockCard.jsp">Unblock Card</h:outputLink>
		<h:outputLink value="SchemeReport.jsp">Scheme Report</h:outputLink>
		<h:form><h:commandLink value="Transaction Statistics Report" action="#{transactionStatisticsMB.init}"></h:commandLink></h:form>
		<h:outputLink value="BlockedCardStatistics.jsp">Blocked Statistics</h:outputLink>
		<h:outputLink value="ApprovedPendingRejectedReport.jsp">Approved and Rejected - Pending Report</h:outputLink>
		<h:outputLink value="RevenueStatistics.jsp">Revenue Statistics</h:outputLink>
	</h:panelGrid>
</f:view>
</center></body>
</html>