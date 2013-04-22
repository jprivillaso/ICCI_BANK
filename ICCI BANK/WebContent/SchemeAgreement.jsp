<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="CSS/style.css">
<title>Scheme Agreement</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<h:form id = "f1">
	<div class="image"></div>
	<h:panelGrid border="1" columns="1"
		style="width: 345px" styleClass="bigTable">
		<f:facet name="header">
			<h:outputLabel value="Scheme Agreement" styleClass="header"/>
		</f:facet>
		<h:outputLabel value="I accept the terms and conditions"></h:outputLabel>
		<h:selectBooleanCheckbox id = "check1" onclick="submit()" value="#{schemeMB.checked}" >
			<f:selectItem/>
		</h:selectBooleanCheckbox>
	</h:panelGrid>		
		<br><h:commandButton value = "Submit" type = "submit" action="#{schemeMB.updateSchemeDetails}" rendered="#{schemeMB.checked==true}" ></h:commandButton>
		<br><br>
		<h:message for="check1"></h:message>
				
	</h:form>
	</center>
</f:view>
</body>
</html>