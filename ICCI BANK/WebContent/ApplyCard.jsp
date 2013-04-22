<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apply Card</title>
</head>
<body>
<% if(!session.getAttribute("Idsesion").equals(session.getId()))
		session.invalidate();%>
<center><div class="image"></div></center>
<f:view>
	<h:form>
		<center>
			<h:panelGrid border="1" columns="3" styleClass="bigTable">
				<f:facet name="header">
					<h:outputText value="Apply For Card" styleClass="header"></h:outputText>
				</f:facet>
				<h:outputText value="Name" styleClass="static"/>
				<h:inputText id="IdName" value="#{cardApplicationMB.name}" required="true" requiredMessage="Mandatory Field: Please enter the value for Name">
				<f:validator validatorId="validateNoSymbolsNoNumbers"></f:validator>
				</h:inputText>
				<h:message for="IdName"></h:message>
				<h:outputText value="Address" styleClass="static"/>
				<h:inputText id="IdAddress" value="#{cardApplicationMB.address}" required="true" requiredMessage="Mandatory Field: Please enter the value for Address"></h:inputText>
				<h:message for="IdAddress"></h:message>
				<h:outputText value="Email" styleClass="static"/>
				<h:inputText id="IdEmail" value="#{cardApplicationMB.email}" required="true" requiredMessage="Mandatory Field: Please enter the value for Email">
				<f:validator validatorId="validateEmail"></f:validator>
				</h:inputText>
				<h:message for="IdEmail"></h:message>
				<h:outputText value="Phone" styleClass="static"/>
				<h:inputText id="IdPhone" value="#{cardApplicationMB.phone}" required="true" requiredMessage="Mandatory Field: Please enter the value for Phone">
					<f:validator validatorId="validateNumbers"></f:validator>
				</h:inputText>
				<h:message for="IdPhone"></h:message>
				<h:outputText value="Please enter the characters given" styleClass="static"/>
				<h:inputText id="IdCharacters" value="#{cardApplicationMB.enteredCharacter}" required="true" requiredMessage="Mandatory Field: Please enter the characters given"></h:inputText>
				<h:message for="IdCharacters"></h:message>
				<h:outputText value="#{cardApplicationMB.randomCharacter}" styleClass="random" id="IdCharac"></h:outputText>
				<h:inputHidden value="#{cardApplicationMB.randomCharacter}" id="HidenChardField"></h:inputHidden>
				<h:outputLabel></h:outputLabel>
				<h:commandButton value="Apply" action="#{cardApplicationMB.applayCard}"></h:commandButton>
				<h:inputHidden></h:inputHidden>
			</h:panelGrid>
			<h:outputText value="#{cardApplicationMB.message}" styleClass="static"/>
		</center>
	</h:form>
</f:view>
</body>
</html>
