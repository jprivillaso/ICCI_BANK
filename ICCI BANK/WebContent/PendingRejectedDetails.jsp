<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<title>Pending/Rejected Application Details</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<h2>Pending/Rejected Application Details</h2>
<f:view>
	<h:dataTable border="1" styleClass="bigTable" value="#{cardStatusMB.cardRejectedList}" var="row">
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText value="Application Id"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.applicationId}"></h:outputText>
		</h:column>
		<h:column id="column2">
			<f:facet name="header">
				<h:outputText value="Applicant Name"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.name}"></h:outputText>
		</h:column>
		<h:column id="column3">
			<f:facet name="header">
				<h:outputText value="Email"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.email}"></h:outputText>
		</h:column>
		<h:column id="column4">
			<f:facet name="header">
				<h:outputText value="Phone No"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.phone}"></h:outputText>
		</h:column>
	</h:dataTable><br>
	<h:outputLink value = "ApprovedPendingRejectedReport.jsp">Back</h:outputLink>
</f:view>
</center>
</body>
</html>