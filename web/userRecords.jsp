<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table class="loginTable">
  <thead>
  <tr>
    <th>ID</th>
    <th>Password</th>
  </tr>
  </thead>
  <tbody>
  <s:iterator value="userList" var="user">
    <tr>
      <td><s:property value="#user.ID" /></td>
      <td><s:property value="#user.password" /></td>
    </tr>
  </s:iterator>
  </tbody>
</table>
