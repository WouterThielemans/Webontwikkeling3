<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Check Password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <%@include file="nav.jspf" %>
        <h2>Home</h2>

    </header>
    <main>
        <p>Fill out your password</p>
        <form method="post" action="Controller?command=IsCorrectPassword&id=${param.id}" novalidate="novalidate">
            <label for="checkPassword">Password</label><input type="password" id="checkPassword"  name="checkPassword"/> </p>
            <p><input type="submit" id="signUp" value="check"></p>
        </form>

    </main>

    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>