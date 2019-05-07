<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <%@include file="nav.jspf" %>
        <h2>
            Sign Up
        </h2>

    </header>
    <main>
        <c:if test="${result!=null}">
            <div class="alert-danger alert-${result!=null?'danger':''}">
                <c:forEach items="${result}" var="message">
                    <ul>
                        <li>${message}</li>
                    </ul>
                </c:forEach>
            </div>
        </c:if>

        <form method="post" action="Controller?command=SignUp" novalidate="novalidate">

            <!-- novalidate in order to be able to run tests correctly -->
            <p class="${useridClass}"><label for="userid">User id</label><input type="text" id="userid" name="userid"
                                                                                required value="<c:out value='${param.userid}'/>">
            </p>
            <p class="${firstNameClass}"><label for="firstName">First Name</label><input type="text" id="firstName"
                                                                                         name="firstName"
                                                                                         required value="<c:out value='${param.firstName}'/>">
            </p>
            <p class="${lastNameClass}"><label for="lastName">Last Name</label><input type="text" id="lastName"
                                                                                      name="lastName"
                                                                                      required value="<c:out value='${param.lastName}'/>">
            </p>
            <p class="${emailClass}"><label for="email">Email</label><input type="email" id="email" name="email"
                                                                            required value="<c:out value='${param.email}'/>">
            </p>
            <p class="${passwordClass}"><label for="password">Password</label><input type="password" id="password"
                                                                                     name="password"
                                                                                     required value="<c:out value='${param.password}'/>">
            </p>
            <p><input type="submit" id="SignUp" value="SignUp"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
