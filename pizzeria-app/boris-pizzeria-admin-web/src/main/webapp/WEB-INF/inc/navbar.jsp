
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-fixed-top navbar-dark bg-inverse">
		<div class="container">
			<div class="navbar-header">
				
         		<a class="navbar-brand" href="#">Pizzeria</a>
			</div>
			
			<ul class="nav navbar-nav">
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value="/accueil" /> "> Accueil <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/listerPizza" />">ListePizza </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contact </a>
				</li>
			</ul>
				<c:choose>
					<c:when test="${ empty sessionScope.sessionClient }">
						<form class="form-inline float-xs-right" method="post" action="authentification">
						    <div class="form-group">
						    	<input type="text" name="mailClient" id="mailClient" placeholder="Email" class="form-control">
						    </div>
						    <div class="form-group">
							    <input type="password" name="passwordClient" id="passwordClient" placeholder="Password" class="form-control">
						    </div>
						    <button type="submit" class="btn btn-success">Connexion</button>
					    </form>
					</c:when>
					<c:otherwise>
						<form class="form-inline float-xs-right" method="post" action="deconnexion">
						    <div class="form-group" style="color:white;">
						    	<label>Bonjour <c:out value="${ sessionScope.sessionClient.prenom }" />
							<c:out value="${ sessionScope.sessionClient.nom }" /></label>
						    </div>
						    <button type="submit" class="btn btn-success">Deconnexion</button>
					    </form>
					</c:otherwise>
				</c:choose>
		</div>
	</nav>