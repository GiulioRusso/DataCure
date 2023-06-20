<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operator Interface</title>
    <link rel="stylesheet" type="text/css" href="css/operator.css">
</head>
<body>
<%
    if(session.getAttribute("loggedinUser") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<div class="mega-container">
    <div>
        <table>
            <tr>
                <th><img src="resources/app-images/logo.png" alt="DataCure Logo" width = "50" height = "50"></th>
                <th><h2>Operator Interface</h2></th>
                <th><p>| Logged in as: <s:property value="#session.loggedinUser"/></p></th>
                <th><s:form action="logoutAction" method="post">
                    <s:submit value="Logout" class="button"/>
                </s:form>
                </th>
            </tr>
        </table>
    </div>
    <div class="container">
        <h2> Uploading Form </h2>
        <s:form id="uploadForm" cssClass="form-container" action="uploadAction" method="post" enctype="multipart/form-data">
            <s:file cssClass="fileselect" label="Upload Image" name="imageFile" id="imageFile" onchange="displayFileName()"/>
            <s:property value="fileName"/><br/>

            <s:textfield value="" cssClass="textfield" label="Operator Description" name="operatorDescription" id="operatorDescription"/><br/>

            <s:submit value="Save Data"/>
        </s:form>

        <div>
            <button type="button" class="button" onclick="clearFileInput()">Clear File</button>
        </div>
        <!-- Show MESSAGE/ERROR -->
        <s:actionerror />

    </div>
</div>
<script>
    function displayFileName() {
        var fileInput = document.getElementById("imageFile");
        var fileName = document.getElementById("fileName");

        fileName.textContent = fileInput.files[0].name;
    }

    function clearFileInput() {
        document.getElementById("uploadForm").reset();
    }
</script>
</body>
</html>
