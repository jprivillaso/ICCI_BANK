<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="JS/CardNextField.js"></script>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<title>Unblock Card</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
<h:form id="form">
<h:panelGrid border="1" columns="6" styleClass="bigTable">
	<h:outputText styleClass="static" value="CardNo:"></h:outputText>
	<h:inputText maxlength="4" value="#{unblockCardMB.cardNo[0]}"
		styleClass="cellsSize" onkeyup="nextInput(this)" onfocus="whenSelected(this)">
	</h:inputText>
	<h:inputText maxlength="4" value="#{unblockCardMB.cardNo[1]}"
		styleClass="cellsSize" onkeyup="nextInput(this)" onfocus="whenSelected(this)">
	</h:inputText>
	<h:inputText maxlength="4" value="#{unblockCardMB.cardNo[2]}"
		styleClass="cellsSize" onkeyup="nextInput(this)" onfocus="whenSelected(this)">
	</h:inputText>
	<h:inputText id="txtCardNo" maxlength="4" value="#{unblockCardMB.cardNo[3]}"
		styleClass="cellsSize" validatorMessage="The Card No field must have 16 digits"
		required="true" requiredMessage="Mandatory Field: Please enter the value for Card No"
		onkeyup="nextInput(this)" onfocus="whenSelected(this)">
	<f:validateLength minimum="4" maximum="4"/>
	</h:inputText>
	<h:message for="txtCardNo"></h:message>
	</h:panelGrid>
	<h:panelGrid columns="1">
		<h:commandButton type="submit" value="Unblock Card" action="#{unblockCardMB.unblockCard}"></h:commandButton>
		<h:outputText styleClass="static" value="#{unblockCardMB.message}"></h:outputText>
	</h:panelGrid>
	<br>
	<h:outputLink value = "CRHomePage.jsp">Home</h:outputLink>
	</h:form>
</f:view>
</center>
</body>
</html>
