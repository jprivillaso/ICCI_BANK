<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="JS/CardNextField.js" type="text/javascript"></script>
<title>Block Card</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:form id="blockCard">
		<h:panelGrid border="0" columns="5">
			<f:facet name="header">
				<h:outputText value="Enter Card No:" styleClass="header"></h:outputText>
			</f:facet>
			<h:outputText value="Card No:" styleClass="static"></h:outputText>
			<h:inputText value="#{blockCardMB.cardNo[0]}" styleClass="cellsSize"
			id="IdField1" onkeyup="nextInput(this);" onfocus="whenSelected(this)" maxlength="4"></h:inputText>
			<h:inputText value="#{blockCardMB.cardNo[1]}" styleClass="cellsSize"
			id="IdField2" onkeyup="nextInput(this);" onfocus="whenSelected(this)" maxlength="4"></h:inputText>
			<h:inputText value="#{blockCardMB.cardNo[2]}" styleClass="cellsSize"
			id="IdField3" onkeyup="nextInput(this);" onfocus="whenSelected(this)" maxlength="4"></h:inputText>
			<h:inputText value="#{blockCardMB.cardNo[3]}" styleClass="cellsSize"
			id="IdField4" onkeyup="nextInput(this);" onfocus="whenSelected(this)" maxlength="4"
			required="true" requiredMessage="Mandatory field: Please enter the value for Card No."></h:inputText>
		</h:panelGrid>
		<h:message for="IdField4"></h:message>
		<h:panelGrid border="1" columns="3" styleClass="bigTable">
			<f:facet name="header">
				<h:outputText value="Block Card" styleClass="header"></h:outputText>
			</f:facet>
			<h:outputText value="Cause for Blocking Card"></h:outputText>
			<h:selectOneMenu id="selCard" valueChangeListener="#{blockCardMB.checkCause}"
				onchange="submit()" required="true" requiredMessage="Mandatory field: Please select one option">
				<f:selectItem itemLabel="-SELECT-"/>
				<f:selectItems value="#{blockCardMB.causes}"/>
			</h:selectOneMenu>
			<h:message for="selCard"></h:message>
			<h:outputText value="Reason"></h:outputText>
			<h:inputTextarea id="txtDesc" value="#{blockCardMB.cause}" disabled="#{blockCardMB.disable}"
				required="true" requiredMessage="Mandatory field: Please enter the value for description">
				</h:inputTextarea>
			<h:message for="txtDesc"></h:message>
		</h:panelGrid>
		<h:commandButton value="Block Card" type="submit" action="#{blockCardMB.blockCard}"></h:commandButton>
	</h:form>
	<h:outputText styleClass="static" value="#{blockCardMB.message}"></h:outputText>
	<br>
	<h:outputLink value = "CRHomePage.jsp">Home</h:outputLink>
</f:view>
</center></body>
</html>
