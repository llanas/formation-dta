package fr.pizzeria.dao;

import fr.pizzeria.dao.personne.client.ClientDaoJPA;
import fr.pizzeria.dao.pizza.PizzaDaoJPA;

public class DaoJPA extends DAOFactory {

	public DaoJPA() {
		super(new PizzaDaoJPA(), new ClientDaoJPA());
	}

}
