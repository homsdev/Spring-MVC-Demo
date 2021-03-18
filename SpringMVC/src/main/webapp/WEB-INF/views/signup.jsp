<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="style" value="/css/main.css"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="${style}" />
</head>
<body>
	<main class="fullscreen center">
		<div class="container-md">
			<div class="card">
				<div class="card__header">
					<h1>Sign Up</h1>
				</div>

				<div class="card__body">
				<form:form class="form" method="POST" modelAttribute="newUser">
				
					<div class="form-group">
					<form:input path="username" placeholder="username"/>
					</div>
					
					<div class="form-group">
					<form:input path="password" placeholder="username"/>
					</div>
					
					<input type="submit" class="btn-dark block" value="Sign up" />
				</form:form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>