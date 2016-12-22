package fr.pizzeria.metier.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.DaoJPA;

public class DaoProducer {

	@Produces
	@ApplicationScoped
	public DAOFactory getDaoFactory() {
		return new DaoJPA();
	}
}
