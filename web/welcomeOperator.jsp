<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Operator</title>
    <script>
        function displayFileName() {
            var fileInput = document.getElementById("imageFile");
            var fileName = document.getElementById("fileName");

            fileName.textContent = fileInput.files[0].name;
        }

        function clearFileInput() {
            var fileInput = document.getElementById("imageFile");
            var fileName = document.getElementById("fileName");
            var operatorDescription = document.getElementById("operatorDescription");

            fileInput.value = "";
            fileName.textContent = "";
            operatorDescription.value = "";
        }
    </script>
</head>
<body>
<%
    if(session.getAttribute("loggedinUser") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<div class="container">
    <div class="left-column">
        <h2>Welcome Operator</h2>
        <p>Logged in as: <s:property value="#session.loggedinUser"/></p>
        <s:form action="logoutAction" method="post">
            <s:submit value="Logout"/>
        </s:form>
    </div>
    <div class="right-column">
        <h2>Upload Image</h2>
        <s:form action="uploadAction" method="post" enctype="multipart/form-data">
            <s:file label="Upload Image:" name="imageFile" id="imageFile" onchange="displayFileName()"/>
            <s:property value="fileName"/><br/>

            <s:textfield label="Operator Description:" name="operatorDescription" id="operatorDescription"/><br/>

            <s:submit value="Save Data"/>
            <button type="button" onclick="clearFileInput()">Clear File</button>
        </s:form>

        <!-- Show MESSAGE/ERROR -->
        <s:actionerror />

    </div>
</div>
</body>
</html>
