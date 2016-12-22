<%@page import="fr.pizzeria.model.Pizza" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Creation Pizza</title>

    <link type="text/css" href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
   	<link type="text/css" href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>
	<c:import url="/WEB-INF/inc/navbar.jsp" />
		  
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9 main topMerge">
			<div class="row">
				<div class="page-header">
					<h2>Créer une nouvelle Pizza</h2>
				</div>
			        <div class="col-xs-6 col-sm-6 placeholder">
			        	<form method="post" action="ajouterPizza">
							<div class="form-group">
								<label for="code">Code :</label> 
								<input type="text" name="codePizza" class="form-control" id="codePizza" placeholder="code" />
							</div>
							<div class="form-group">
								<label for="nom">Nom :</label> 
								<input type="text" name="nomPizza" class="form-control" id="nomPizza" placeholder="nom" />
							</div>
							<div>
								<label for="prix">Prix :</label>
								<input type="text" name="prixPizza" class="form-control" id="prixPizza" placeholder="prix" />
							</div>
							<div class="form-group">
								<label for="exampleSelect1">Catégorie :</label>
								<select class="form-control" id="typePizza" name="typePizza">
								<c:forEach var="type" items="${ type }">
									<option <c:if test="${ pizza.type == type }">selected</c:if>> 
										<c:out value="${ type }" />
									</option>
								</c:forEach>
								</select>
							</div>
	  						<button type="submit" class="btn btn-primary">Créer</button>
							</form>
							<c:if test="${ requestScope.form }">
								<div class="alert alert-danger">
									<strong>Erreur!</strong> <c:out value="${ requestScope.form.resultat }" />
								</div>
							</c:if>
			            </div>
					</div>
				</div>
			</div>
		</div>
		        
</body>
</html>