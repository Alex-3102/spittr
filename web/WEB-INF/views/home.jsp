<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Welcome to Spitter</h1>

<a href="<c:url value="/spittle" />">Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register</a>
<input id="username" type="text">
<button id="find" onclick="find()">find</button>
<button id="find" onclick="deleteFun()">delete</button>
<script>
    function find(){
        var username = document.getElementById("username").value;
        console.log(document.getElementById("username"));
        url = "<c:url value="/spitter/" />" + username;
        window.location.href=url;
    }
    function deleteFun(){
        var username = document.getElementById("username").value;
        console.log(document.getElementById("username"));
        url = "<c:url value="/spitter/delete/" />" + username;
        window.location.href=url;
    }
</script>
</body>
</html>
