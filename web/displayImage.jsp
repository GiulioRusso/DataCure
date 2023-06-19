<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gianf
  Date: 17/06/2023
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <img src= "<s:property value="imgPath"/>">
    </div>
    <div class="right-column">
        <s:form action="updateImageAction" onsubmit="return validateForm()">
            <s:property value="ID"/>
            <s:hidden value="%{ID}" name="ID"/>
            <br>
            <s:textfield disabled="true" name="date"/>
            <br>
            <s:checkbox label="Label" name="label"/>
            <br>
            <s:textfield disabled="true" label="Operator Description" name="oDesc"/>
            <br>
            <s:textfield label="Doctor Description" name="dDesc" id="doctorDescription"/>
            <s:submit value="Submit"/>
        </s:form>
        <s:form action="welcomeDoctorAction">
            <s:submit value="Cancel"/>
        </s:form>
    </div>

</div>
<script type="text/javascript">
    function validateForm() {
        var doctorDescription = document.getElementById('doctorDescription').value;
        if (doctorDescription.trim() === '') {
            alert('ERROR: Doctor Description cannot be empty.');
            return false;
        }
        return true;
    }
</script>
</body>
</html>