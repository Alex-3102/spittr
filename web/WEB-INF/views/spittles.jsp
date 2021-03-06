<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
<div class="spittleForm">
    <h1>Spit it out...</h1>


<%--    <form method="POST" name="spittle" action="/spittles">--%>
        <input type="hidden" name="latitude">
        <input type="hidden" name="longitude">
        <textarea id="message" name="message" cols="80" rows="5"></textarea><br/>
<%--        <input type="submit" value="Add" />--%>
<%--    </form>--%>
    <button value="Add" onclick="sendMes()">Add</button>

<%--    &lt;%&ndash;@elvariable id="spittle" type="spittr.Spittle"&ndash;%&gt;--%>
<%--    <sf:form method="POST" commandName="spittle" action="/spittles" enctype="multipart/form-data">--%>
<%--        <sf:input path="latitude"/><br/>--%>
<%--        <sf:input path="longitude"/><br/>--%>
<%--        <sf:textarea path="message"></sf:textarea>--%>
<%--        <input type="submit" value="submit" />--%>
<%--    </sf:form>--%>
    
</div>
<div class="listTitle">
    <h1>Recent Spittles</h1>
    <ul class="spittleList">
        <c:forEach items="${spittleList}" var="spittle" >
            <li id="spittle_<c:out value="spittle.id"/>">
                <div class="spittleMessage"><c:out value="${spittle.message}" /></div>
                <div>
                    <span class="spittleTime"><c:out value="${spittle.time}" /></span>
                    <span class="spittleLocation">(<c:out value="${spittle.latitude}" />, <c:out value="${spittle.longitude}" />)</span>
                </div>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${fn:length(spittleList) gt 20}">
        <hr />
        <s:url value="/spittles?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
    </c:if>
</div>
</body>
<script>
    function sendMes(){
        var xmlhttp;
        if (window.XMLHttpRequest) {
            // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp=new XMLHttpRequest();
        }
        xmlhttp.onreadystatechange=function() {
            if (xmlhttp.readyState==4 && xmlhttp.status==201) {
                console.log(xmlhttp.responseText);
                // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("POST","/spittles", true);
        xmlhttp.setRequestHeader("Content-Type","application/json");
        console.log(JSON.stringify({latitude: 23, longitude:23, message: document.getElementById("message").value}));
        xmlhttp.send(JSON.stringify({latitude: 23, longitude:23, message: document.getElementById("message").value}));
    }
</script>
</html>