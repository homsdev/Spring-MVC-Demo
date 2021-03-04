<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;
      charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-5">
				<img alt="product image"
					src="<c:url value="/img/${product.productID}.jpg"></c:url>" />
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>Item Code : </strong><span class="label label warning">${product.productID}
					</span>
				</p>
				<p>
					<strong>manufacturer</strong> : ${product.manufacturer}
				</p>
				<p>
					<strong>category</strong> : ${product.category}
				</p>
				<p>

					<strong>Available units in stock </strong> :
					${product.unitsInStock}
				</p>
				<h4>${product.unitPrice}USD</h4>
				<p>
					<a href='<spring:url value="/market/products"></spring:url>'
						class="btn btn-warning btn-large"> <span
						class="glyphicon-shopping-cart glyphicon"> </span> Order Now
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>