<%@page import="fr.pizzeria.model.Pizza" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>HOME</title>

    <link type="text/css" href="<c:url value="/dist/css/bootstrap.css" />" rel="stylesheet">
   	<link type="text/css" href="<c:url value="/dist/css/style.css" />" rel="stylesheet">

  </head>
  <body>
  
	<c:import url="/WEB-INF/inc/navbar.jsp" />
	
    <div class="container">
      <div class="row">
			<div id="carousel-accueil" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img src="<c:url value="dist/img/Carousel/Carousel1.jpg" />"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img src="<c:url value="dist/img/Carousel/Carousel2.jpg" />"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img src="<c:url value="dist/img/Carousel/Carousel3.jpg" />"
							alt="Third slide">
					</div>
				</div>
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span class="icon-prev"
					aria-hidden="true"></span> <span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span class="icon-next"
					aria-hidden="true"></span> <span class="sr-only">Next</span>
				</a>
			</div>
			<div class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 main topMerge">		
         	<h2>BIENVENUE SUR LE SITE DE LA PIZZERIA</h2>
        </div>
      </div>
    </div>
</body>
</html>