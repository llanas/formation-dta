package fr.pizzeria.ihm.choix;

import fr.pizzeria.ihm.IhmUtil;

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
		
		
		if( ihm.getPizzaDao().getListePizza().isEmpty() ) {
			ihm.systemOut("La liste ne contient plus de pizza!");
		} else {
			ihm.getPizzaDao().getListePizza().forEach(ihm::afficherPizza);
		}
	}
}