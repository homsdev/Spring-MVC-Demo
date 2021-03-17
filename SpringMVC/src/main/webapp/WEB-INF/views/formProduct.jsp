<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<c:url var="style" value="/css/main.css"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${style}" />
</head>
<body>


	<section class="center">

		<div class="container-lg">

			<div class="card">
				<div class="card__header">
					<h1>Add Product</h1>
				</div>

				<div class="card__body">
					<form:form method="POST" modelAttribute="newProduct" class="form">
						<div class="form-group">
							<form:input path="productID" id="productID" placeholder="Product ID" />
						</div>

						<div class="form-group">
							<form:input path="name" id="name" placeholder="Product Name"/>
						</div>

						<div class="form-group">
							<form:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price" />
						</div>

						<div class="form-group">
							<form:textarea path="description" id="description" placeholder="Description" />
						</div>

						<div class="form-group">
							<form:input path="manufacturer" id="manufacturer" placeholder="Manufacturer"/>
						</div>

						<div class="form-group">
							<form:input path="category" id="category" placeholder="Category"/>
						</div>

						<div class="form-group">
							<form:input path="unitsInStock" type="number" id="unitsInStock" placeholder="Units in stock" />
						</div>

						<div class="form-group">
							<form:radiobutton path="condition" value="new" />
							New
							<form:radiobutton path="condition" value="refurbished" />
							Refurbished
							<form:radiobutton path="condition" value="Old" />
							Old
						</div>
						<input type="submit" class="btn-dark block" value="add" />
					</form:form>
				</div>

			</div>
		</div>

	</section>


</body>
</html>