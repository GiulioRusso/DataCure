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

            fileInput.value = "";
            fileName.textContent = "";
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
        <p>Logged in as: <%= session.getAttribute("loggedinUser") %></p>
        <form action="logoutAction" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
    <div class="right-column">
        <h2>Upload Image</h2>
        <form action="uploadAction" method="post" enctype="multipart/form-data">
            <label for="imageFile">Upload Image:</label>
            <input type="file" name="imageFile" id="imageFile" onchange="displayFileName()"><br>
            <span id="fileName"></span><br>

            <label for="operatorDescription">Operator Description:</label>
            <input type="text" name="operatorDescription" id="operatorDescription"><br>

            <button type="submit">Save Data</button>
            <button type="button" onclick="clearFileInput()">Clear File</button> <!-- PULIRE ANCHE LA DESCRP -->
        </form>

        <s:actionerror />

    </div>
</div>
</body>
</html>
