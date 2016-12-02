package fr.pizzeria.ihm.choix;

import java.util.Collections;
import java.util.Comparator;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class AfficherPizzaPlusCher extends Choix {
	
	public AfficherPizzaPlusCher( IhmUtil ihm ) {
		
		super();
		this.ihm = ihm;
	}

	public AfficherPizzaPlusCher( IhmUtil ihm, int indexMenu ) {
		
		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - PIZZA PLUS CHER - " + indexMenu);
	}
	
	@Override
	public void executer() {

		Collections.sort(ihm.getPizzaDao().getListePizza(), Comparator.comparing(Pizza::getPrix).reversed());
		//ihm.afficherPizza(ihm.getPizzaDao().recupererPizza(0));
	}

}
