package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoFichier;

public class DaFichier extends DAOFactory {
	
	public DaFichier() {
		
		super(new PizzaDaoFichier());
	}

}
