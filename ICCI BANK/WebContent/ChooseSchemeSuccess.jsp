<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="CSS/style.css">
<title>Choose Scheme Success</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<div class="image"></div>
	<h:panelGrid border="1" columns="1" styleClass="bigTable">
		<f:facet name="header">
			<h:outputText value="Success Page" styleClass="header"></h:outputText>
		</f:facet>
		<h:outputText value="Scheme Successfully chosen" styleClass="static"></h:outputText>
	</h:panelGrid><br>
	<h:outputLink value = "CRHomePage.jsp">Customer Home Page</h:outputLink>
	</center>
</f:view>
</body>
</html>