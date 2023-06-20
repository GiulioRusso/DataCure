<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin Interface</title>
    <link rel="stylesheet" href="css/admin.css">
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
                <th><h2>Admin Interface</h2></th>
                <th><p>| Logged in as: <s:property value="#session.loggedinUser"/></p></th>
                <th><s:form action="logoutAction" method="post">
                        <s:submit value="Logout" class="button"/>
                    </s:form>
                </th>
            </tr>
        </table>
    </div>
    <div class="container">
        <div class="left-column">
            <h2> Users Form </h2>
            <div class="form-container">
            <s:form id="Form" method="POST">
                <s:textfield id="userID" name="userID" label="ID"/>
                <s:textfield id="userPassword" name="userPassword" label="Password"/>
                <table class="buttons-layout">
                    <tr>
                        <th><s:submit id="addButton" value="Add" class="button"/></th>
                        <th><s:submit id="updateButton" value="Update" class="button"/></th>
                        <th><s:submit id="deleteButton" value="Delete" class="button"/></th>
                    </tr>
                </table>
            </s:form>
            </div>

            <!-- Show MESSAGE/ERROR -->
            <s:actionerror />

        </div>

        <div class="right-column">
            <h2>Login Records</h2>
            <table style="">
                <thead>
                <tr>
                    <th class="cell-head">ID</th>
                    <th class="cell-head">Password</th>
                </tr>
                </thead>
                <tbody id="userRecords">
                <s:iterator value="userList" var="user">
                    <tr>
                        <td class="cell"><s:property value="ID"/></td>
                        <td class="cell"><s:property value="password"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    <!-- enhances the functionality of the form by dynamically changing the form's action attribute based on the button clicked -->
    window.onload = function() {
        var form = document.getElementById('Form');
        var addButton = document.getElementById('addButton');
        var updateButton = document.getElementById('updateButton');
        var deleteButton = document.getElementById('deleteButton');

        addButton.addEventListener('click', function() {
            form.action = 'addAction';
            form.submit();
        });

        updateButton.addEventListener('click', function() {
            form.action = 'updateAction';
            form.submit();
        });

        deleteButton.addEventListener('click', function() {
            form.action = 'deleteAction';
            form.submit();
        });
    };
</script>
</body>
</html>
