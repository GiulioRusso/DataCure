<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Admin</title>
    <link rel="stylesheet" href="css/admin.css">
    <style>
        .container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }

        .left-column {
            flex: 1;
            margin-right: 20px;
        }

        .right-column {
            flex: 1;
        }
    </style>
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
                <th><s:form action="logoutAction" method="post">
                        <s:submit value="Logout" class="button"/>
                    </s:form>
                </th>
            </tr>
        </table>
    </div>
    <div class="container">
        <div class="left-column">
            <s:form id="Form" style="form-container" method="POST">
                <s:textfield id="userID" name="userID" label="ID"/>
                <s:textfield id="userPassword" name="userPassword" label="Password"/>
                <div class="buttons-layout">
                    <s:submit id="addButton" value="Add" class="button" method="addAction"/>
                    <s:submit id="updateButton" value="Update" class="button" method="updateAction"/>
                    <s:submit id="deleteButton" value="Delete" class="button" method="deleteAction"/>
                </div>
            </s:form>

            <!-- Show MESSAGE/ERROR -->
            <s:actionerror />

        </div>

        <div class="right-column">
            <h2>Login Records</h2>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Password</th>
                </tr>
                </thead>
                <tbody id="userRecords">
                <s:iterator value="userList" var="user">
                    <tr>
                        <td><s:property value="ID"/></td>
                        <td><s:property value="password"/></td>
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
