<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login to DataCure</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<div class="container">

	<div class="left-column">
		<div class="image">
			<img src="resources/app-images/logo.png" alt="DataCure Logo" width = "300" height = "300">
			<div class="description">
				<h3>Welcome to DataCure!</h3>
				<p>DataCure aims to create a system for collecting clinical data for scientific studies.</p>
				<p>Please enter your credentials to log in.</p>
			</div>
		</div>
	</div>

	<div class="right-column">
		<div class="form-container">
			<h2>Login to DataCure</h2>
			<s:form action="loginAction">
				<s:textfield name="userID" label="ID" />
				<s:password name="userPassword" label="Password" />
				<s:submit value="Login" />
			</s:form>

			<!-- Show MESSAGE/ERROR -->
			<s:actionerror cssClass="action-error"/>

		</div>
	</div>

</div>

</body>
</html>

