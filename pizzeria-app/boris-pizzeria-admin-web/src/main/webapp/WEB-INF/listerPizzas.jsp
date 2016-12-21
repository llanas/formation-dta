<%@page import="fr.pizzeria.model.Pizza" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Liste Pizzas</title>

    <link type="text/css" href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
   	<link type="text/css" href="<c:url value="/css/style.css" />" rel="stylesheet">

  </head>
  <body>
  

	<c:import url="/WEB-INF/inc/navbar.jsp" />
	  
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 main topMerge">		
         <h2>Pizzas</h2>
         
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>CODE</th>
                  <th>NOM</th>
                  <th>PRIX</th>
                  <th>TYPE</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach var="pizza" items="${ requestScope.pizzas }">
		                <tr>
			                <td></td>
			                <td>${ pizza.code }"</td>
			                <td><a href="<c:url value="/afficherPizza?code=${pizza.code}"/>">${ pizza.nom }"</a></td>
			                <td>${ pizza.code }</td>
			                <td>${ pizza.type }</td>
			                <td><a href="<c:url value="/editer?code=${ pizza.code }"/>"><button type="button" class="btn btn-primary">Modifier</button></a>
			                <td><a href="<c:url value="/supprimerPizza?code=${ pizza.code }" />"><button type="button" class="btn btn-danger">Supprimer</button></a>
		                </tr>
              	</c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
</body>
</html>