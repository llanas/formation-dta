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

    <link type="text/css" href="<c:url value="/dist/css/bootstrap.css" />" rel="stylesheet">
   	<link type="text/css" href="<c:url value="/dist/css/style.css" />" rel="stylesheet">

  </head>
  <body>
  

	<c:import url="/WEB-INF/inc/navbar.jsp" />
	  
    <div class="container">
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
			                <td>${ pizza.id }</td>
			                <td>${ pizza.code }</td>
			                <td><a href="<c:url value="/afficherPizza?code=${pizza.code}"/>">${ pizza.nom }</a></td>
			                <td>${ pizza.code }</td>
			                <td>${ pizza.type }</td>
			                <c:if test="${ sessionScope.sessionClient != null }">
				                <td><a href="<c:url value="/admin/editerPizza?code=${ pizza.code }"/>"><button type="button" class="btn btn-primary">Modifier</button></a></td>
				                <td><a href="<c:url value="/admin/supprimerPizza?code=${ pizza.code }" />"><button type="button" class="btn btn-danger">Supprimer</button></a></td>
				            </c:if>
		                </tr>
              	</c:forEach>
              </tbody>
            </table>
            
          </div>
          <c:if test="${ sessionScope.sessionClient != null }">
          	<a href="<c:url value="/admin/ajouterPizza" /> "><button type="button" class="btn btn-primary">Ajouter</button></a>
        	</c:if>
        </div>
      </div>
    </div>
</body>
</html>