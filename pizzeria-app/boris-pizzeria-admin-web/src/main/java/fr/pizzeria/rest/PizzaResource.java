package fr.pizzeria.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pizzeria.metier.ejb.PizzaService;
import fr.pizzeria.model.Pizza;

@Path("/pizzas")
public class PizzaResource {

	@EJB private PizzaService sPizza;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> listerPizza() {
		return sPizza.getListePizza();
	}
	
	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pizza recupererPizza( @PathParam("code") String code ) {
		return sPizza.recupererPizza(code);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void ajouter(Pizza pizza){
		sPizza.ajouterPizza(pizza);
	}
	
	@PUT
	@Path("/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifier(Pizza pizza, @PathParam("code") String oldCode) {
		sPizza.modifierPizza(pizza, oldCode);
	}
	
	@DELETE
	@Path("/{code}")
	public void supprimer(@PathParam("code") String code ) {
		sPizza.supprimerPizza(code);
	}
}
