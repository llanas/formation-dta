<%@page import="fr.pizzeria.model.Pizza" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Liste Pizzas</title>

    <!-- Bootstrap core CSS -->
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
              	<%
         			int i = 0;
    				List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");
         			for(Pizza p : pizzas) {
         		%>
		                <tr>
			                <td><%=i %></td>
			                <td><%=p.getCode() %></td>
			                <td><a href="/afficherPizza?code=<%=p.getCode() %>"><%=p.getNom() %></a></td>
			                <td><%=Double.toString(p.getPrix()) %></td>
			                <td><%=p.getType() %></td>
			                <td><a href="<%= request.getContextPath() %>/editer?code=<%=p.getCode() %>"><button type="button" class="btn btn-primary">Modifier</button></a>
			                <td><a href="/supprimer?code=<%=p.getCode() %>"><button type="button" class="btn btn-danger">Supprimer</button></a>
		                </tr>
                <%
	                	i++;
	         		}
                %>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    
  

</body></html>