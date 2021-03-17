
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<c:url value="/css/main.css" var="styles"></c:url>
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
	<nav class="nav-fixed-top">
      <ul class="nav-items">
        <li class="nav-items__item"><a href="">Home</a></li>
        <li class="nav-items__item"><a href="">Products</a></li>
        <li class="nav-items__item"><a href="">Login</a></li>
        <li class="nav-items__item"><a href="">About</a></li>
      </ul>
    </nav>

	<main class="fullscreen-main">
      <div class="jumbotron-dark">
        <h1>${greeting }</h1>
        <p>${tagline}</p>
      </div>
    </main>
</body>
</html>
