<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Doctor</title>
    <link rel="stylesheet" href="css/doctor.css">
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
                <th><h2>Doctor Interface</h2></th>
                <th><p>| Logged in as: <s:property value="#session.loggedinUser"/></p></th>
                <th><s:form action="logoutAction" method="post">
                    <s:submit value="Logout" class="button"/>
                </s:form>
                </th>
            </tr>
        </table>
    </div>
    <div class="container">
        <table>
            <thead>
            <tr>
                <th class="cell-head">Image</th>
                <th class="cell-head">Image ID</th>
                <th class="cell-head">Upload Date</th>
                <th class="cell-head">Labeled</th>
                <th class="cell-head">Operator Description</th>
                <th class="cell-head">Doctor Description</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="imageRecords">
            <s:iterator value="imageList">
                <s:if test="labeled == false">
                <tr>
                    <td class="cell"><img src="resources/database-images/<s:property value="idImage"/>" width="100" height="100"/></td>
                    <td class="cell"><s:property value="idImage"/></td>
                    <td class="cell"><s:property value="uploadDate"/></td>
                    <td class="cell"><s:property value="labeled"/></td>
                    <td class="cell"><s:property value="operatorDescription"/></td>
                    <td class="cell"><s:property value="doctorDescription"/></td>
                    <td><s:form action="displayImageAction">
                            <s:hidden value="%{idImage}" name="ID"/>
                            <s:hidden value="%{uploadDate}" name="date"/>
                            <s:hidden value="%{labeled}" name="label"/>
                            <s:hidden value="%{operatorDescription}" name="oDesc"/>
                            <s:hidden value="%{doctorDescription}" name="dDesc"/>
                            <s:submit value="Edit"/>
                        </s:form></td>

                    <!-- Show MESSAGE/ERROR -->
                    <s:actionerror />
                </tr>
                </s:if>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
