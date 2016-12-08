package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDao;

public abstract class DAOFactory {

	PizzaDao pizzaDao;

	public DAOFactory(PizzaDao pizzaDao) {
		super();
		this.pizzaDao = pizzaDao;
	}

	public PizzaDao getPizzaDao() {
		return this.pizzaDao;
	}
}
