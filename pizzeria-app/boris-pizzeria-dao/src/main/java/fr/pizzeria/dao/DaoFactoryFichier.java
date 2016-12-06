package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoFactoryFichier;

public class DaoFactoryFichier extends DaoFactory {
	
	public DaoFactoryFichier() {
		
		super(new PizzaDaoFactoryFichier());
	}

}
