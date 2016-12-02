package fr.pizzeria.ihm.choix;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class AfficherCarte extends Choix {
	
	public AfficherCarte( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public AfficherCarte( IhmUtil ihm, int indexMenu ) {
		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - AFFICHER LA CARTE - " + indexMenu);
	}

	public void executer() {
		
		ihm.getPizzaDao().getListePizza().forEach(p -> {
			ihm.afficherPizza(p);
		});
		if( ihm.getPizzaDao().getListePizza().size() == 0 ){
			ihm.systemOut("La liste ne contient plus de pizza!");
		}
		ihm.systemOut(Pizza.getNbPizzas() + " pizzas ajouter à ce jour.");
	}
}