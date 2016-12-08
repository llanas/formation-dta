package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoFactoryBDD;

public class DaoFactoryBDD extends DAOFactory {

	
	public DaoFactoryBDD() {
		super(new PizzaDaoFactoryBDD());
	}
}
