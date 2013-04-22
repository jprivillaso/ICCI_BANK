<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheme Report</title>

<script type="text/javascript">
function submitScheme(){
	var schemes = document.getElementById("form1:schemeId");
	if (schemes.selectedIndex != 0) {
		window.open('SchemeReportDetails.jsp', '_blank', 'dependent=yes, menubar=no, toolbar=no'); 
		return false;
	}
	return false;
}
</script>

</head>
<body>	
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<f:view>
	<center>
	<h:form id="form1">
		<div class="image"></div>
		<h2>Scheme report</h2>
		<h:panelGrid border="1" columns="3" styleClass="bigTable">
			<h:outputLabel value="SchemeId" ></h:outputLabel>
			<h:selectOneMenu id="schemeId" valueChangeListener="#{schemeReportMB.getCustomersForScheme}" onchange="submit()" required="true" requiredMessage="Mandatory Field: Please enter the  value for scheme Id">
				<f:selectItem itemLabel="-SELECT-"/>
				<f:selectItem itemLabel="A" itemValue="A"/>
				<f:selectItem itemLabel="B" itemValue="B"/>
				<f:selectItem itemLabel="C" itemValue="C"/>
				<f:selectItem itemLabel="D" itemValue="D"/>
				<f:selectItem itemLabel="E" itemValue="E"/>
			</h:selectOneMenu>
			<h:message for="schemeId"></h:message>
		</h:panelGrid>
		<br>
		<h:outputText styleClass="static" value="#{schemeReportMB.message}"></h:outputText>
		<br>
		
		<h:commandButton type="submit" disabled="#{schemeReportMB.emptyCustList}" value="Submit" onclick="submitScheme();"></h:commandButton>
		<br>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</h:form>
	</center>
</f:view>
</body>
</html>

