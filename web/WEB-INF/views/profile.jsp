<%@ page import="spittr.Spitter" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2021/1/28
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
    <h1>Your Profile</h1>
    <c:out value="${spitter.username}" /><br/>
    <c:out value="${spitter.firstName}" /> <c:out value="${spitter.lastName}" /><br/>
    <c:out value="${spitter.email}" />
    <%
        Spitter spitter = (Spitter) request.getAttribute("spitter");
    %>
    <%= spitter.getUsername()%>
</body>
</html>
