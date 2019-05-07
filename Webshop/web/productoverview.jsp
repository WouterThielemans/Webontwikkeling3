<%@page import="domain.model.Product" %>
<%@page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Product Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="nav.jspf"/>
        <h2>
            Product Overview
        </h2>

    </header>
    <main>
        <c:if test="${productenLijst != null}">
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Delete</th>
                    <th></th>
                </tr>
                <c:forEach var="product" items="${productenLijst}">
                    <tr>
                        <td>${product.productid}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td><a href="Controller?command=ConfirmDeleteProduct&productid=${product.productid}&name=${product.name}">Delete</a></td>
                        <td><a href="Controller?command=AddToCart&productid=${product.productid}&name=${product.name}&price=${product.price}">Add to cart</a></td>
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