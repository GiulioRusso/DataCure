<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Doctor</title>
    <link rel="stylesheet" href="css/doctor.css">
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
            margin-right: 400px;
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
    </div>
    <div class="right-column">
        <table>
            <thead>
            <tr>
                <th>Image</th>
                <th>Image ID</th>
                <th>Upload Date</th>
                <th>Labeled</th>
                <th>Operator Description</th>
                <th>Doctor Description</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="imageRecords">
            <s:iterator value="imageList">
                <tr>
                    <td><img src="resources/database-images/<s:property value="idImage"/>" width="100" height="100"/></td>
                    <td><s:property value="idImage"/></td>
                    <td><s:property value="uploadDate"/></td>
                    <td><s:property value="labeled"/></td>
                    <td><s:property value="operatorDescription"/></td>
                    <td><s:property value="doctorDescription"/></td>
                    <s:form action="displayImageAction">
                        <s:hidden value="%{idImage}" name="ID"/>
                        <s:hidden value="%{uploadDate}" name="date"/>
                        <s:hidden value="%{labeled}" name="label"/>
                        <s:hidden value="%{operatorDescription}" name="oDesc"/>
                        <s:hidden value="%{doctorDescription}" name="dDesc"/>
                        <td><s:submit value="Edit"/></td>
                    </s:form>

                    <!-- Show MESSAGE/ERROR -->
                    <s:actionerror />
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
