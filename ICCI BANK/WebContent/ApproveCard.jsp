<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<title>Approve Card</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
<center>
<div class="image"></div>
<h:form>
		<h:dataTable border="1" id="IdDataTableToApprove" styleClass="bigTable" value="#{cardApplicationMB.cardTOList}" var="row">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Application Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.applicationId}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Customer Name"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.name}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Address"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.address}"></h:outputText>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Email"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.email}"></h:outputText>
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Phone"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.phone}"></h:outputText>
			</h:column>
			<h:column id="column6">
				<f:facet name="header">
					<h:outputText value="Select Applications"></h:outputText>
				</f:facet>
				<h:selectBooleanCheckbox id="IdChedkApplication" value="#{row.applicationSelected}"/>
			</h:column>
		</h:dataTable>
		<h:commandButton value="Approve" action="#{cardApplicationMB.approveCard}"></h:commandButton><br>
		<h:outputText styleClass="static" value="#{cardApplicationMB.message}"></h:outputText>
	</h:form>
</center>
</f:view>
</body>
</html>