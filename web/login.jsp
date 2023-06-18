<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login to DataCure</title>
	<style>
		html, body {
			height: 100%;
			margin: 0;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.container {
			display: flex;
			height: 100%;
			width: 100%;
		}

		.left-column {
			flex: 1;
			display: flex;
			align-items: center;
			justify-content: center;
			flex-direction: column;
			text-align: center;
		}

		.right-column {
			flex: 1;
			display: flex;
			align-items: center;
			justify-content: center;
			text-align: center;
		}

		.image {
			max-width: 300px;
			max-height: 300px;
			width: 100%;
			height: 100%;
			object-fit: contain;
			margin-bottom: 20px;
			display: flex;
			flex-direction: column;
			align-items: center;
			text-align: center;
		}

		.description {
			margin-bottom: 20px;
		}

		.form-container {
			width: 300px;
			text-align: center;
		}

		.form-container input[type="text"],
		.form-container input[type="password"] {
			width: 100%;
			margin-bottom: 10px;
		}
	</style>


</head>
<body>
<div class="container">

	<div class="left-column">
		<div class="image">
			<img src="resources/app-images/logo.png" alt="DataCure Logo" width = "300" height = "300">
			<div class="description">
				<h3>Welcome to DataCure!</h3>
				<p>bla bla bla description of datacure</p>
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
			<s:actionerror />

		</div>
	</div>

</div>

</body>
</html>

