<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
				<label for="productID"><spring:message code="formProduct.label.productID"></spring:message></label>
				<form:input path="productID" id="productID" />
			</div>

			<div class="form-input">
				<label for="name"><spring:message code="formProduct.label.name"></spring:message></label>
				<form:input path="name" id="name" />
			</div>

			<div class="form-input">
				<label for="unitPrice"><spring:message code="formProduct.label.price"></spring:message></label>
				<form:input type="number" path="unitPrice" id="unitPrice" />
			</div>

			<div class="form-input">
				<label for="description"><spring:message code="formProduct.label.description"></spring:message></label>
				<form:textarea path="description" id="description" />
			</div>

			<div class="form-input">
				<label for="manufacturer"><spring:message code="formProduct.label.manufacturer"></spring:message></label>
				<form:input path="manufacturer" id="manufacturer" />
			</div>

			<div class="form-input">
				<label for="category"><spring:message code="formProduct.label.category"></spring:message></label>
				<form:input path="category" id="category" />
			</div>

			<div class="form-input">
				<label for="unitsInStock"><spring:message code="formProduct.label.stock"></spring:message></label>
				<form:input path="unitsInStock" type="number" id="unitsInStock" />
			</div>

			<div class="form-input">
				<label for="condition"><spring:message code="formProduct.label.condition"></spring:message></label>
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