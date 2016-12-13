package fr.pizzeria.ihm.choix;

import java.util.Collections;
import java.util.Comparator;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class ListeCategorie extends Choix {
	
	public ListeCategorie( IhmUtil ihm ) {
		
		super();
		this.ihm = ihm;
	}

	public ListeCategorie( IhmUtil ihm, int indexMenu ) {

		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - AFFICHER PIZZA PAR CATEGORIE - " + indexMenu);
	}
	
	@Override
	public void executer() {
		
		Collections.sort(ihm.getPizzaDao().getListePizza(), Comparator.comparing(Pizza::getType));
		ihm.getPizzaDao().getListePizza().forEach(ihm::afficherPizza);
	}

}
