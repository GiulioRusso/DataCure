<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Admin</title>
    <link rel="stylesheet" href="css/style.css">
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
<div class="container">
    <div class="left-column">
        <h2>Admin Interface</h2>
        <!-- <a href="logoutAction" style="float:right">Logout</a> -->
        <form action="logoutAction" method="post">
            <button type="submit">Logout</button>
        </form>
        <form id="Form" class="filterPanel" action="addAction" method="POST">
            <label for="userID">ID:</label>
            <input type="text" id="userID" name="userID" class="formTextField">
            <label for="userPassword">Password:</label>
            <input type="text" id="userPassword" name="userPassword" class="formTextField">
            <button type="submit" id="addButton">Add</button>
            <button type="button" id="updateButton">Update</button>
            <button type="button" id="deleteButton">Delete</button>
        </form>

        <s:actionerror />

    </div>
    <div class="right-column">
        <h2>Login Records</h2>
        <table class="loginTable">
            <thead>
            <tr>
                <th>ID</th>
                <th>Password</th>
            </tr>
            </thead>
            <tbody id="userRecords">
            <!-- Display the login records here -->
            <s:iterator value="userList" var="user">
                <tr>
                    <td><s:property value="ID" /></td>
                    <td><s:property value="password" /></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>

<script>
    window.onload = function() {
        // Get references to the form and buttons
        var form = document.getElementById('Form');
        var addButton = document.getElementById('addButton');
        var updateButton = document.getElementById('updateButton');
        var deleteButton = document.getElementById('deleteButton');

        // Add event listeners for the buttons
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
