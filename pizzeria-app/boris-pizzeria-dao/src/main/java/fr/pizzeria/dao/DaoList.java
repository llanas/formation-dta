package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoList;

public class DaoList extends DAOFactory {
	
	public DaoList() {
		
		super(new PizzaDaoList());
	}

}
