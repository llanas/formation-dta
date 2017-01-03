package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoREST;

public class DaoREST extends DAOFactory {

	public DaoREST() {
		super(new PizzaDaoREST());
	}
}
