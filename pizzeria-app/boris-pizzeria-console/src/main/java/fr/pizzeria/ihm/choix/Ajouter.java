package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.CategoriePizza;

public class Ajouter extends Choix {
	
	public Ajouter( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public Ajouter( IhmUtil ihm, int indexMenu ) {
		super();
		this.indexMenu = indexMenu;
		this.ihm = ihm;
		this.setDescription(indexMenu + " - AJOUTER UNE PIZZA - " + indexMenu);
	}

	@Override
	public void executer() {
		
		String codePizza;
		String nomPizza;
		Double prixPizza;
		String typePizza;
		if(backToMenu(codePizza = ihm.getNewCode()) &&
				backToMenu(nomPizza = ihm.getNom()) &&
				backToMenu((String.valueOf(prixPizza = ihm.getPrix()))) &&
				backToMenu((typePizza = ihm.getCategorie()))
						) {
			try {
				ihm.getPizzaDao().ajouter(codePizza, nomPizza, prixPizza, typePizza);
				ihm.systemOut("La pizza à bien été ajouter.");
			} catch( PizzaException e) {
				Logger.getLogger(e.getMessage());
				throw new PizzaException(e);
			}
		}
	}
}
