package fr.pizzeria.ihm.choix;

import java.util.Collections;
import java.util.Comparator;

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
		
		Collections.sort(ihm.getPizzaDao().getListePizza(), Comparator.comparing(Pizza::getId));
		ihm.getPizzaDao().getListePizza().forEach(p -> {
			ihm.afficherPizza(p);
		});
		if( ihm.getPizzaDao().getNombrePizza() == 0 ){
			ihm.systemOut("La liste ne contient plus de pizza!");
		}
		ihm.systemOut(Pizza.getNbPizzas() + " pizzas ajouter à ce jour.");
	}
}