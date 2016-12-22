package fr.pizzeria.dao.pizza;

import java.util.List;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public interface PizzaDao {
	
	
	Pizza recupererPizza( String code ) throws PizzaException;
	
	List<Pizza> getListePizza();
	
	Pizza ajouter( Pizza pizza ) throws PizzaException;
	
	Pizza modifier( Pizza pizza, String oldCode) throws PizzaException;
	
	void supprimer( Pizza pizza ) throws PizzaException;
	
	
}
