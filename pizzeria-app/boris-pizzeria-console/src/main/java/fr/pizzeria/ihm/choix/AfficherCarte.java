package fr.pizzeria.ihm.choix;

import fr.pizzeria.dao.pizza.PizzaDao;
import fr.pizzeria.ihm.IhmUtil;

public class AfficherCarte extends Choix {

	private PizzaDao pizzaDao;
	
	public AfficherCarte( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public AfficherCarte( IhmUtil ihm, int indexMenu ) {
		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		pizzaDao = ihm.getPizzaDao();
		this.setDescription(indexMenu + " - AFFICHER LA CARTE - " + indexMenu);
	}

	public void executer() {
		
		if( pizzaDao.getListePizza().isEmpty() ) {
			ihm.systemOut("La liste ne contient plus de pizza!");
		} else {
			pizzaDao.getListePizza().forEach(ihm::afficherPizza);
		}
	}
}