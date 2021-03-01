<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
</head>
<body>
	<section>
		<div class="jumbotron">
			<h1 class="display-3">Products</h1>
			<p class="lead">Add new products</p>
		</div>
	</section>

	<section class="container">
		<form:form method="POST" modelAttribute="newProduct">
			<div class="form-input">
				<label for="productID">Product ID</label>
				<form:input path="productID" id="productID" />
			</div>

			<div class="form-input">
				<label for="name">Product Name</label>
				<form:input path="name" id="name" />
			</div>

			<div class="form-input">
				<label for="unitPrice">Price</label>
				<form:input type="number" path="unitPrice" id="unitPrice" />
			</div>

			<div class="form-input">
				<label for="description">Description</label>
				<form:textarea path="description" id="description" />
			</div>

			<div class="form-input">
				<label for="manufacturer">Manufacturer</label>
				<form:input path="manufacturer" id="manufacturer" />
			</div>

			<div class="form-input">
				<label for="category">Category</label>
				<form:input path="category" id="category" />
			</div>

			<div class="form-input">
				<label for="unitsInStock">Units in Stock</label>
				<form:input path="unitsInStock" type="number" id="unitsInStock" />
			</div>

			<div class="form-input">
				<label for="unitsInOrder">Units in Order</label>
				<form:input path="unitsInOrder" type="number" id="unitsInOrder" />
			</div>

			<div class="form-input">
				<label for="discontinued">Discontinued</label>
				<form:checkbox path="discontinued" id="discontinued" />
			</div>

			<div class="form-input">
				<label for="condition">Condition</label>
				<form:radiobutton path="condition" value="new" />
				New
				<form:radiobutton path="condition" value="refurbished" />
				Refurbished
				<form:radiobutton path="condition" value="Old" />
				Old
			</div>
			<input type="submit" value="ADD">
		</form:form>
	</section>


</body>
</html>