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
</head>
<body>

<c:url var="loginURL" value="/login"></c:url>
	<main>
		<div class="container md">
			<form action="${loginURL}" method="post">
				<div class="form-input">
					<label for="username">Username</label>
					<input id="username" name="username" type="text">
				</div>
				<div class="form-input">
					<label for="password">Password</label>
					<input id="password" name="password" type="password">
				</div>
				
				<input type="submit" value="Log In" class="btn btn-submit" />
				
				<div class="form-footer">
				<a href="#">Forgot password?</a>
				</div>
			</form>
		</div>
	</main>

</body>
</html>