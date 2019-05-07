<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
		<main> Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt.
		</main>
			<c:if test="${authenticated!=null }">
				<h3 class = error >${authenticated }</h3>
			</c:if>

			<c:choose>
				<c:when test="${sessionScope.person != null}">
					<h3>Welcome, ${sessionScope.person.firstName}.</h3>
					<a href="Controller?action=LogOut" id="logOut">Log out</a>
				</c:when>
				<c:otherwise>
					<form method="post" action="Controller?action=Login" novalidate="novalidate">
						<p>
							<label for="id">User ID</label>
							<input type="text" id="id" name="id" required value="<c:out value="${person.userid}"/>">
						</p>
						<p>
							<label for="password">Password</label>
							<input type="password" id="password" name="password" required>
						</p>
						<p>
							<input type="submit" id="logIn" value="Log in">
						</p>
					</form>
				</c:otherwise>
			</c:choose>
		</article>

		<article>
			<h4>Do you want to see a quote?</h4>
			<form method="post" action="Controller?command=SetQuote">
				<p><input type="radio" name="quote" value="yes"> Yes</p>
				<p><input type="radio" name="quote" value="no"> No</p>
				<p><input type="submit" id="submit" value="submit"></p>
			</form>
		</article>
		<c:if test="${quoteTekst != null}">
			<p>${quoteTekst}</p>
		</c:if>
		<article>

		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>