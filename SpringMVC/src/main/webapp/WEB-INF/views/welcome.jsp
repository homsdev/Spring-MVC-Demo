
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<c:url value="/css/main.css" var="styles"></c:url>
<c:url value="/market/products" var="productsURL"></c:url>
<c:url value="/login" var="loginURL"></c:url>
<c:url value="/" var="welcomeURL"></c:url>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Online Store</title>
<link rel="stylesheet" href="${styles}" />
</head>
<body>

	<header>
		<nav class="nav">
			<div class="logo">
				<h1>Sprinkled</h1>
			</div>
			<div class="sign">
				<a href="${loginURL}">Login <img
					src="/assets/img/icons/logon.png" alt="" /></a>
			</div>
		</nav>
	</header>

	<div class="pop">
		<p>User: Demo</p>
		<p>Pass: demo$pring123.</p>
	</div>

	<div class="landing">
		<div class="content">
			<h1>${greeting}</h1>
			<h2>${tagline}</h2>
			<a href="https://www.homsdev.com">Click to know <span>More</span></a>
		</div>
	</div>

</body>
</html>
