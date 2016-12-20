<%@page import="fr.pizzeria.model.Pizza" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Modifier Pizza</title>
	
	<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
	<link type="text/css" href="css/style.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-fixed-top navbar-dark bg-inverse">
		<a class="navbar-brand" href="#">Project name</a>
		<ul class="nav navbar-nav">
			<li class="nav-item active">
				<a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">ListePizza </a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Contact </a>
			</li>
		</ul>
	</nav>
		  
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 main topMerge">
		        <div class="col-xs-6 col-sm-2 placeholder">
		        	<% 
		        		Pizza p = (Pizza) request.getAttribute("pizza");
		        	%>
		            <img src="img/reine.jpg" width="200" height="200" class="img-fluid" alt="Generic placeholder thumbnail">
		            <h4><%=p.getNom() %></h4>
		            <span class="text-muted"><%=p.getType() %></span>
		            </div>
				</div>
			</div>
		</div>
		        
</body>
</html>