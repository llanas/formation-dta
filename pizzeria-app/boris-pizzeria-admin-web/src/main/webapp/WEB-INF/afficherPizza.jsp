<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="fr.pizzeria.model.Pizza" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title><c:out value="${ pizza.nom }" /></title>

    <link type="text/css" href="<c:url value="/dist/css/bootstrap.css" />" rel="stylesheet">
   	<link type="text/css" href="<c:url value="/dist/css/style.css" />" rel="stylesheet">

  </head>
<body>
	<c:import url="/WEB-INF/inc/navbar.jsp" />
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9 main topMerge">
				<div class="row">
					<div class="page-header">
						<h2>Pizza : <c:out value="${ pizza.nom }" /></h2>
					</div>
					<div class="col-xs-6 col-sm-6 placeholder">
						<img src="img/reine.jpg" width="400" height="400" class="img-fluid" alt="Generic placeholder thumbnail">
			            <h4><c:out value="${ pizza.nom }" /></h4>
					</div>
					 <div class="col-xs-6 col-sm-6 placeholder">
					 	<ul class="list-group">
					 		<li class="list-group-item list-group-item-warning">Code : <c:out value="${ pizza.code }" /></li>
					 		<li class="list-group-item list-group-item-warning">Nom : <c:out value="${ pizza.nom }" /></li>
					 		<li class="list-group-item list-group-item-warning">Prix : <c:out value="${ pizza.prix }" /></li>
					 		<li class="list-group-item list-group-item-warning">Type : <c:out value="${ pizza.type }" /></li>
					 	</ul>
					 </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>