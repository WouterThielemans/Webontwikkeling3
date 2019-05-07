<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Something Wrong</title>
    <link href="css/sample.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="nav.jspf"/>
<main id="container">
    <jsp:include page="title.jsp">
        <jsp:param name="title" value="Something is Wrong"/>
    </jsp:include>
    <p>You caused a ${pageContext.exception} on the server.</p>
    <p>Go <a href="index.jsp">home</a>.</p>
</main>
</body>
</html>