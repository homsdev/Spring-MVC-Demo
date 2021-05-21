<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<c:url value="/css" var="CSS"></c:url>
<c:url value="/img" var="imgURL"></c:url>
<c:url value="/js/app.js" var="JScript"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customers</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${CSS}/main.css">
<link rel="stylesheet" href="${CSS}/customer.css">
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

	<div class="datatable-container">
		<div class="datatable-header">
			<div class="tools">
					<ul>
						<li>
						<span> <input type="checkbox" name="" id="" /></span>
						<li>
							<button>
								<i class="material-icons">add_circle</i>
							</button>
						</li>
						<li>
							<button>
								<i class="material-icons">edit</i>
							</button>
						</li>
						<li>
							<button>
								<i class="material-icons">delete</i>
							</button>
						</li>
						<li>
							<button>
								<i class="material-icons">share</i>
							</button>
						</li>
					</ul>
				</div>
				<div class="search">
            		<input type="text" name="" id="" class="search-input">
        		</div>
			</div>
			
			 <table class="datatable-body">
			  <thead>
	              <tr>
	                  <th></th>
	                  <th>Status</th>
	                  <th>Name</th>
	                  <th>Location</th>
	                  <th>Office</th>
	                  <th>Orders</th>
	                  <th>Date</th>
	              </tr>
         		</thead>
         		
         		<tbody>
         			<c:forEach var="customer" items="${customers}"> 
         				<tr>
         					<td class="table-checkbox">
                      			<input type="checkbox" name="" id="${customer.customerID}">
                  			</td>
                  			<td><span class="away"></span></td>
                  			<td>${customer.name}</td>
                  			<td>${customer.address}</td>
                  			<td>Main Street</td>
                  			<td>${customer.noOfOrders}</td>
                  			<td>06/08/2020</td>
         				</tr>
         			</c:forEach>
         		</tbody>
			 </table>
			
			 <div class="datatable-footer">
	          <div class="list-items">
	              show <select name="" id="">
	                  <option value="">5</option>
	                  <option value="">10</option>
	                  <option value="">15</option>
	              </select>
	              entries
	          </div>
	          <div class="pages">
	              <ul>
	                <li><span class="active">1</span></li>
	                <li><button>2</button></li>
	                <li><button>3</button></li>
	                <li><button>4</button></li>
	                <li><button>...</button></li>
	                <li><button>9</button></li>
	                <li><button>10</button></li>
	              </ul>           
	          </div>
      		</div>
			
		</div>
		<script type="text/javascript" src="${JScript}"></script>
</body>
</html>
