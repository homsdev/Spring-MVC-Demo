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
	<main class="view">
		<div class="form-container">
			<div class="form-header">
				<h1>Log In</h1>
			</div>
			<div class="form-body">
				<form action="${loginURL}" method="POST">
					<div class="form-group">
						<input name="username" type="text" placeholder="Username"
							autocomplete="off" />
					</div>

					<div class="form-group">
						<input name="password" type="password" placeholder="Password" />
					</div>

					<input type="submit" value="Log In" class="btn" />
				</form>
			</div>
			<div class="form-footer">
				<a href="">Sign Up</a> <a href="">Forgot Password?</a>
			</div>
		</div>
	</main>

</body>
</html>