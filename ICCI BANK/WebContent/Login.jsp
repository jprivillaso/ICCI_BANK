<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% 
	String User = session.getId();
	session.setAttribute("Idsesion",User);
%>
<center>
<div class="image"></div>
<f:view>
	<h:form>
		<h:panelGrid border="1" columns="3" styleClass="bigTable">
			<f:facet name="header"><h:outputText value="User Login" styleClass="header"/></f:facet>
			<h:outputText value="Enter the user name"></h:outputText>
			<h:inputText id="txtUserName" value="#{loginMB.userName}" required="true"
				requiredMessage="Please enter the user name"></h:inputText>
			<h:message for="txtUserName"></h:message>
			<h:outputText value="Enter the password"></h:outputText>
			<h:inputSecret id="txtPassword" value="#{loginMB.password}" required="true"
				requiredMessage="Please enter the password"></h:inputSecret>
			<h:message for="txtPassword"></h:message>
			<h:commandButton value="Login" type="submit" action="#{loginMB.validateLogin}"></h:commandButton>
		</h:panelGrid>
	</h:form>
	<h:outputText styleClass="static" value="#{loginMB.message}"></h:outputText>
	<br><h:outputText styleClass="static" value="Want a credit card?. "></h:outputText>
	<h:outputLink value="ApplyCard.jsp">Apply for a Card</h:outputLink>
</f:view>
</center></body>
</html>