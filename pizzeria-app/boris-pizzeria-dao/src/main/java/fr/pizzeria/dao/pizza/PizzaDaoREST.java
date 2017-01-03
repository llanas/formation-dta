package fr.pizzeria.dao.pizza;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoREST implements PizzaDao {

	private static final String TARGET_URI = "http://localhost:8080/boris-pizzeria-admin-web/api/rest/pizzas";

	Client client = ClientBuilder.newClient( new ClientConfig() );
	
	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		return client.target(TARGET_URI).path(code).request().get(new GenericType<Pizza>(){});
	}

	@Override
	public List<Pizza> getListePizza() {
		return client.target(TARGET_URI).request().get(new GenericType<List<Pizza>>(){
		});
		
	}

	@Override
	public Pizza ajouter(Pizza pizza) throws PizzaException {
		return client.target(TARGET_URI).request().post(Entity.entity(pizza, MediaType.APPLICATION_JSON), Pizza.class);
	}

	@Override
	public Pizza modifier(Pizza pizza, String oldCode) throws PizzaException {
		return client.target(TARGET_URI).path(oldCode).request().put(Entity.entity(pizza, MediaType.APPLICATION_JSON), Pizza.class);
	}

	@Override
	public void supprimer(Pizza pizza) throws PizzaException {
		client.target(TARGET_URI).path(pizza.getCode()).request().delete();
	}

}
