<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<c:url value="/css" var="CSS"></c:url>
<c:url value="/img" var="imgURL"></c:url>
<c:url value="/js/app.js" var="JScript"></c:url>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Products</title>
<link rel="stylesheet" href="${CSS}/main.css" />
</head>
<body>
	<header>
		<nav>
			<a href="/" class="logo">Cookies n'Cream</a>
			<div class="toggle">
				<div class="bar"></div>
				<ul>
					<li>
						<a href="<c:url value="/logout"></c:url>"> 
						<img src="${imgURL}/icons/login.png" alt="" /> Logout</a>
					</li>
					<li>
						<a href="<c:url value="/market/products"></c:url>">
						<img src="${imgURL}/icons/shop.png" alt="" />Market</a>
					</li>
					<li>
						<a href="<c:url value="/customer/list"></c:url>">
						<img src="${imgURL}/icons/customer.png" alt="" /> Customer</a>
					</li>
					<li>
						<a href=""> 
						<img src="${imgURL}/icons/about.png" alt="" /> About</a>
					</li>
				</ul>
			</div>
		</nav>
	</header>


	<div class="modal hidden">
		<div class="content">
			<div class="modal__header">
				<h2>Quantity</h2>
				<span id="btnCloseModal">&times</span>
			</div>
			<div class="modal__body">
				<form action="POST" id="modalForm">
					<input id="quantity" type="number" min="1" value="1" />
					<button type="submit">Buy</button>
				</form>
			</div>
		</div>
	</div>

	<div class="cart cart-hide">
		<div class="cart__toggle">
			<button id="btnCloseCart"></button>
		</div>
		<div class="cart__items" id="shopping-list"></div>
	</div>

	<main class="products-view">
		<div class="grid-container">

			<c:forEach var="product" items="${products}">

				<div class="item">
					<div class="item__title">
						<h2>What's new</h2>
					</div>
					<div class="item__body">
						<div class="preview">
							<h3>${product.category}
								<br /> <span>${product.name}</span>
							</h3>
							<p>$ ${product.unitPrice}</p>
							<img src="${imgURL}/${product.category}.png" alt="Product Image" />
						</div>

						<div class="details">
							<p>${product.description}</p>
						</div>
					</div>

					<div class="item__footer">
						<button class="btn-cart" data-id="${product.productID}">Add
							to Cart</button>
					</div>
				</div>

			</c:forEach>


		</div>
	</main>

	<script type="text/javascript" src="${JScript}"></script>
</body>
</html>
