<%@page import="domain.model.Product" %>
<%@page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Confirm Delete Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <jsp:include page="nav.jspf"/>
        <h2>Confirm Delete</h2>

    </header>
    <main>
        <p>Are you sure you wan't to delete ${param.name} ?</p>
        <form action="Controller?command=DeleteProduct&productid=${param.productid}" method="POST">
            <input type="submit" value="Yes">
        </form>
            <p><a href="Controller?command=OverviewProduct">No</a> ,if you don't wan't to delete ${param.name} .</p>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>