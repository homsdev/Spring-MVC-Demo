<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<c:url value="/css/main.css" var="styles"></c:url>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Products</title>
<link rel="stylesheet" href="${styles}" />
</head>
<body>
	<nav>
		<ul>
			<li><a href="">Home</a></li>
			<li><a href="">Products</a></li>
			<li><a href="">Login</a></li>
			<li><a href="">About</a></li>
		</ul>
	</nav>
	<main class="products-view">
		<div class="grid-products">

			<c:forEach var="product" items="${products}">
				<div class="product">
					<div class="product-img">
						<img alt="product image"
							src="<c:url value="/img/${product.productID}.jpg"></c:url>" />
					</div>
					<div class="title">
						<h3>${product.name}</h3>
					</div>
					<div class="description">
						<p>${product.description}
							<span>by: ${product.manufacturer }</span>
						</p>
						<p>$ ${product.unitPrice }</p>
					</div>
					<div class="order">
						<a
							href="<spring:url value="/market/product?id=${product.productID}"></spring:url>">Order
							Now</a>
					</div>
				</div>

			</c:forEach>


		</div>
	</main>
</body>
</html>
