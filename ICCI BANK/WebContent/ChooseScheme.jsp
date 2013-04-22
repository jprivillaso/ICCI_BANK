<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="CSS/style.css">
<title>Choose Scheme</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<div class="image"></div>
	<h:form>
		<br>
		<h:panelGrid border="1" columns="2" styleClass="bigTable">
			<f:facet name="header">
			<h:outputLabel value="Choose a scheme" styleClass="header" /></f:facet>
			<h:outputText value="SchemeId"></h:outputText>
			<h:selectOneMenu value = "#{schemeMB.schemeId}" valueChangeListener="#{schemeMB.display}" onchange="submit()">
			<f:selectItem itemLabel="--SELECT--" itemDisabled="true"/>
			<f:selectItem itemLabel="A" itemValue="A"/>
			<f:selectItem itemLabel="B" itemValue="B"/>
			<f:selectItem itemLabel="C" itemValue="C"/>
			<f:selectItem itemLabel="D" itemValue="D"/>
			<f:selectItem itemLabel="E" itemValue="E"/>
			</h:selectOneMenu>
		</h:panelGrid><br>
		<h:panelGrid styleClass="bigTable" border="1" columns="2">
			<h:outputText value="SchemeId"></h:outputText>
			<h:outputText value="#{schemeMB.schemeId}"></h:outputText>
			<h:outputText value="SchemeAmount"></h:outputText>
			<h:outputText value="#{schemeMB.schemeAmount}"></h:outputText>
			<h:outputText value="MinimumAmount"></h:outputText>
			<h:outputText value="#{schemeMB.minimumAmount}"></h:outputText>
			<h:outputText value="InterestRate"></h:outputText>
			<h:outputText value="#{schemeMB.interestRate}"></h:outputText>
		</h:panelGrid>
		<h:commandButton value="choose" type="submit" action="success"></h:commandButton>
		<br>
		<h:outputLabel styleClass="static" value="#{schemeMB.message}"></h:outputLabel>
	</h:form>
	</center>
</f:view>
</body>
</html>