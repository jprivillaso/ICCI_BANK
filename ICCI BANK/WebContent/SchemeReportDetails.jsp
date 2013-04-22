<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheme Report Details</title>
<script type="text/javascript">
function selectCustomerCard(){
	var custId = document.getElementById("form1:customerId");
	var cardId = document.getElementById("form1:cardNo");
	var form = document.getElementById("form1").submit();
	
	if ((custId.selectedIndex != 0) && (cardId.selectedIndex == 0)) {
		cardId.selectedIndex = 1;
	} else if ((cardId.selectedIndex != 0) && (custId.selectedIndex == 0)) {
		custId.selectedIndex = 1;
	} 

	if ((custId.selectedIndex != 0) || (cardId.selectedIndex != 0)) {
		form.submit();
		cardId.selectedIndex = 0;
		custId.selectedIndex = 0;
	}
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
		<h:panelGrid border="1" columns="4" styleClass="bigTable">
			<h:outputLabel value="Customer Id" ></h:outputLabel>
			<h:selectOneMenu id="customerId" valueChangeListener="#{schemeReportMB.getCustomerAndCardDetails}" onchange="selectCustomerCard();" onblur="selectCustomerCard();" requiredMessage="Mandatory Field: Please enter the  value for customer id">
				<f:selectItem itemLabel="-SELECT-"/>
				<f:selectItems value="#{schemeReportMB.customerIdList}"/>
			</h:selectOneMenu>
			
			<h:outputLabel value="Card No" ></h:outputLabel>
			<h:selectOneMenu id="cardNo" valueChangeListener="#{schemeReportMB.getCustomerAndCardDetails}" onchange="selectCustomerCard();" onblur="selectCustomerCard();" requiredMessage="Mandatory Field: Please enter the  value for card No">
				<f:selectItem itemLabel="-SELECT-"/>
				<f:selectItems value="#{schemeReportMB.cardNoList}" />
			</h:selectOneMenu>
		</h:panelGrid>
		<br>

		<h:dataTable styleClass="bigTable" border="1" value="#{schemeReportMB.customerTo}" var="cust" rendered="#{schemeReportMB.showCustomerDetails}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Customer Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{cust.customerId}" />
			</h:column>

			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Name"></h:outputText>
				</f:facet>
				<h:outputText value="#{cust.name}" />
			</h:column>

			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Address"></h:outputText>
				</f:facet>
				<h:outputText value="#{cust.address}" />
			</h:column>
		</h:dataTable>
		
		<h:dataTable styleClass="bigTable" border="1" value="#{schemeReportMB.cardTo}" var="card" rendered="#{schemeReportMB.showCardDetails}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Card No"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.cardNo}" />
			</h:column>

			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Amount"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.cardAmount}" />
			</h:column>

			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Customer Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.customerId}" />
			</h:column>
			
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Date of Registration"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{card.dateOfRegistration}">
					<f:converter converterId="ConverterDates"/>
				</h:outputLabel>
			</h:column>
		</h:dataTable>
		<br>
		<h:outputLabel styleClass="static" value="#{schemeReportMB.message}"></h:outputLabel>

		<br>
		<h:outputLink value="javascript:window.close()">Close</h:outputLink>
	</h:form>
	</center>
</f:view>
</body>
</html>

