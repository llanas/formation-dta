package fr.pizzeria.dao;


import fr.pizzeria.dao.commande.CommandeDao;
import fr.pizzeria.dao.ingredient.IngredientDao;
import fr.pizzeria.dao.perf.PerfDao;
import fr.pizzeria.dao.personne.client.ClientDao;
import fr.pizzeria.dao.personne.livreur.LivreurDao;
import fr.pizzeria.dao.pizza.PizzaDao;

public abstract class DAOFactory {

	PizzaDao pizzaDao;
	ClientDao clientDao;
	CommandeDao commandeDao;
	LivreurDao livreurDao;
	PerfDao perfDao;
	IngredientDao ingredientDao;
	
	public DAOFactory() {
		super();
	}

	public DAOFactory(PizzaDao pizzaDao) {
		super();
		this.pizzaDao = pizzaDao;
	}
	
	public DAOFactory(PizzaDao pizzaDao, IngredientDao ingDao) {
		super();
		this.pizzaDao = pizzaDao;
		this.ingredientDao = ingDao;
	}
	
	public DAOFactory(PizzaDao pizzaDao, ClientDao clientDao){
		super();
		this.pizzaDao = pizzaDao;
		this.clientDao = clientDao;
	}
	
	public DAOFactory(PizzaDao pizzaDao, ClientDao clientDao, CommandeDao commandeDao, LivreurDao livreurDao){
		super();
		this.pizzaDao = pizzaDao;
		this.clientDao = clientDao;
		this.commandeDao = commandeDao;
		this.livreurDao = livreurDao;
	}

	public PizzaDao getPizzaDao() {
		return this.pizzaDao;
	}
	
	public ClientDao getClientDao() {
		return this.clientDao;
	}

	public CommandeDao getCommandeDao() {
		return this.commandeDao;
	}

	public LivreurDao getLivreurDao() {
		return this.livreurDao;
	}
}
