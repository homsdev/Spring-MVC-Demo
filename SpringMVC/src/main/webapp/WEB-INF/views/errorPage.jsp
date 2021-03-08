<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<section>
		<div class="jumbotron">
			<h1 class="danger">
				Oops! Something went wrong...<sub>: ${alert}</sub>
			</h1>
			<a href="<spring-ulr value="/market/products"></spring-ulr>">
				Products</a>
		</div>
	</section>

</body>
</html>