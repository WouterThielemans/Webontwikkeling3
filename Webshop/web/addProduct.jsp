<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="nav.jspf"/>
        <h2>
            Add Product
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

        <form method="post" action="Controller?command=AddProduct" novalidate="novalidate">

            <!-- novalidate in order to be able to run tests correctly -->
            <p class="${nameClass}"><label for="name">Name</label><input type="text"
                                                                         id="name"
                                                                         name="name"
                                                                         required value="${useridPreviousValue}">
            </p>
            <p class="${descriptionClass}"><label for="description">Description</label><input type="text"
                                                                                              id="description"
                                                                                              name="description"
                                                                                              required
                                                                                              value="${firstNamePreviousValue}">
            </p>
            <p class="${priceClass}"><label for="price">Price</label><input type="text"
                                                                            id="price"
                                                                            name="price"
                                                                            required value="${lastNamePreviousValue}">
            </p>
            <p><input type="submit" id="AddProduct" value="AddProduct"></p>
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
