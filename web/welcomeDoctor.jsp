<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Doctor</title>
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
        <h2>Welcome Doctor</h2>
        <p>Logged in as: <%= session.getAttribute("loggedinUser") %></p>
        <form action="logoutAction" method="post">
            <button type="submit">Logout</button>
        </form>

        <!-- Show MESSAGE/ERROR -->
        <s:actionerror />
    </div>
    <div class="right-column">
        <table>
            <thead>
            <tr>
                <th>Image ID</th>
                <th>Upload Date</th>
                <th>Label</th>
                <th>Operator Description</th>
                <th>Doctor Description</th>
            </tr>
            </thead>
            <tbody id="imageRecords">
            <s:iterator value="imageList" var="image">
                <tr>
                    <td><s:property value="#image.idImage"/></td>
                    <td><s:property value="#image.uploadDate"/></td>
                    <td><s:property value="#image.labeled"/></td>
                    <td><s:property value="#image.operatorDescription"/></td>
                    <td><s:property value="#image.doctorDescription"/></td>
                    <s:form action="displayImageAction">
                        <s:hidden value="%{idImage}" name="ID"/>
                        <s:submit value="Edit"/>
                    </s:form>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
