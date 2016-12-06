package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoFactoryList;

public class DaoFactoryList extends DaoFactory {
	
	public DaoFactoryList() {
		
		super(new PizzaDaoFactoryList());
	}

}
