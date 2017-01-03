<%@page import="fr.pizzeria.model.Pizza" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Gestionnaire de Clients</title>

    <link type="text/css" href="<c:url value="/dist/css/bootstrap.css" />" rel="stylesheet">
   	<link type="text/css" href="<c:url value="/dist/css/style.css" />" rel="stylesheet">
   	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
   	<script type="text/javascript">
	   	function getListeClient(){
			var clients = [];
			$.ajax({
				url: "http://localhost:8080/boris-pizzeria-admin-web/api/rest/clients",
				type: "GET",
				dataType: "json",
				contentType: "application/json",
				success:function (data) {
	            $.each(data,function(i,v){
					clients.push(v);
	            });
	        }
			});
			var table = document.createElement("table");
			var tHead = table.createTHead();
			var row = tHead.insertRow(-1);
			for(var properties in clients[0]){
				var cell = row.insertCell();
				cell.insertHTML = Object.keys(properties);
			}
			var tBody = document.createElement("tBody");
			table.appendChild(tBody);
			$.each(clients, function(i, client){
				var row = tBody.insertRow(-1);
				for(var properties in client) {
					var cell = row.insertCell();
					cell.insertHTML = properties;
				};
			});
		};
   		$("#submit").click(function(){
				var Client = {
	   					prenom: $("#prenomClient"),
	   					nom: $("nomClient"),
	   					mail: $("mailClient"),
	   					password: $("passwordClient"),
	   			}
	   			
	   			$.ajax({
	   				url: "http://localhost:8080/boris-pizzeria-admin-web/api/rest/clients",
	   				type: "POST",
	   				data: JSON.stringify(data),
	   				contentType: "application/json",
	   				complete: callback
	   			});
			});
   	</script>
</head>
<body>
	<c:import url="/WEB-INF/inc/navbar.jsp" />
		  
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9 main topMerge">
			<div class="row">
				<div class="page-header">
					<h2>Créer un nouveau Client</h2>
				</div>
				<div class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 main topMerge">		
         			<h2>Liste de clients</h2>
         		</div>
		        <div onload="getListeClient()" class="table-responsive">
		        	
		      	</div>
			    <div class="col-xs-6 col-sm-6 placeholder">
			      	<form>
						<div class="form-group">
							<label for="prenomClient">Prenom :</label> 
							<input type="text" name="prenomClient" class="form-control" id="prenomClient" placeholder="prenom" />
						</div>
						<div class="form-group">
							<label for="nomClient">Nom :</label> 
							<input type="text" name="nomClient" class="form-control" id="nomClient" placeholder="nom" />
						</div>
						<div>
							<label for="mailClient">E-mail :</label>
							<input type="text" name="mailClient" class="form-control" id="mailClient" placeholder="mail" />
						</div>
						<div>
							<label for="passwordClient">E-mail :</label>
							<input type="password" name="passwordClient" class="form-control" id="passwordClient" placeholder="mot de passe" />
						</div>
	  					<button type="submit" class="btn btn-primary" id="submit" value="Envoyer">Créer</button>
						</form>
			           </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>