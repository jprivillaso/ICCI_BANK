<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Home Page</title>
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
		<h:outputLink value="ViewCardDetails.jsp" >View Card Details</h:outputLink>
		<h:outputLink value="CardUsage.jsp">View Card Usage</h:outputLink>
		<h:outputLink value="OnlineTransaction.jsp">Online transaction</h:outputLink>
	</h:panelGrid>
</f:view>
</center></body>
</html>
