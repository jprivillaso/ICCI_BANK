<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<title>Unblock Card</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center>
<div class="image"></div>
<f:view>
	<h:panelGrid styleClass="bigTable" border="1" columns="1">
		<h:outputText value="#{unblockCardMB.message}"></h:outputText>
	</h:panelGrid><br>
	<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
</f:view>
</center>
</body>
</html>