<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Customer</title>
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
		<form:form method="POST" modelAttribute="newCustomer">
			<div class="form-input">
				<label for="customerID">Customer ID</label>
				<form:input path="customerID" id="customerID" />
			</div>

			<div class="form-input">
				<label for="name">Customer Name</label>
				<form:input path="name" id="name" />
			</div>
			
			<div class="form-input">
				<label for="address">Customer Address</label>
				<form:input path="address" id="address" />
			</div>

			<div class="form-input">
				<label for="NoOfOrders">Number of Orders</label>
				<form:input type="number" path="NoOfOrders" id="NoOfOrders" />
			</div>
			
			<input type="submit" value="ADD">
		</form:form>
	</section>


</body>
</html>