<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login error page</title>
</head>
<body>
<f:view>
	<h:form>
		<center>
		<h:outputLabel styleClass="static" value="You Have to Login First. 
		Click in login link:"></h:outputLabel><br><br>
		<h:outputLink value="Login.jsp" >Login Page</h:outputLink>
		</center>
	</h:form>
</f:view>
</body>
</html>