<%@page import="domain.model.Person" %>
<%@page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Person Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="nav.jspf"/>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
        <c:if test="${personenLijst != null}">
            <table>
                <tr>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Delete</th>
                    <th>Check Password</th>
                </tr>
                <c:forEach var="person" items="${personenLijst}">
                    <tr>
                        <td>${person.email}</td>
                        <td>${person.firstName}</td>
                        <td>${person.lastName}</td>
                        <td><a href="Controller?command=ConfirmDeletePerson&personId=${person.userid}&firstName=${person.firstName}&lastName=${person.lastName}">Delete</a></td>
                        <td><a href="/checkPassword.jsp?id=${person.userid}">Check</a></td>
                    </tr>
                </c:forEach>

                <caption>Users Overview</caption>
            </table>
        </c:if>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>