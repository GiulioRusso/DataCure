<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Doctor</title>
</head>
<body>
<div class="container">
    <div class="left-column">
        <h2>Welcome Doctor</h2>
        <p>Logged in as: <%= session.getAttribute("loggedinUser") %></p>
        <form action="logoutAction" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
    <div class="right-column">

    </div>
</div>
</body>
</html>
