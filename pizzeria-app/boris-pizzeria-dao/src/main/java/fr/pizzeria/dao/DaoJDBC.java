package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoJDBC;

public class DaoJDBC extends DAOFactory {

	
	public DaoJDBC() {
		super(new PizzaDaoJDBC());
	}
}
