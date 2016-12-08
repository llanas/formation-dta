package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoFactoryFichier;

public class DaoFactoryFichier extends DAOFactory {
	
	public DaoFactoryFichier() {
		
		super(new PizzaDaoFactoryFichier());
	}

}
