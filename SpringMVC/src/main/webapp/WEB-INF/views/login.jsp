<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<c:url var="loginURL" value="/login"></c:url>
<c:url var="style" value="/css/main.css"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="${style}" />
</head>
<body>

	<main class="fullscreen center">
		<div class="container-md">
			<div class="card">
				<div class="card__header">
					<h1>Log In</h1>
				</div>
				<div class="card__body">
					<form action="${loginURL}" method="POST" class="form">
						<div class="form-group">
							<input type="text" name="username" placeholder="Username" />
						</div>
						<div class="form-group">
							<input type="password" name="password" placeholder="Password" />
						</div>
						<input type="submit" value="Log In" class="btn-dark block" />
					</form>
				</div>

				<div class="card__footer">
					<a href="#">Forgot password?</a> 
					<a href="#">Don´t have an account?</a>
				</div>
			</div>
		</div>
	</main>
	
</body>
</html>