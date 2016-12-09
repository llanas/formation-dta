package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;

public class Modifier extends Choix {
	
	public Modifier( IhmUtil ihm ){
		super();
		this.ihm = ihm;
		
	}

	public Modifier( IhmUtil ihm, int indexMenu ){
		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - MODIFIER PIZZA - " + indexMenu);
	}

	@Override
	public void executer() {
		
		String oldPizza;
		String codePizza;
		String nomPizza;
		Double prixPizza;
		String typePizza;
		if(
				backToMenu(oldPizza = ihm.getOldCode()) &&
				backToMenu(codePizza = ihm.getNewCode()) &&
				backToMenu(nomPizza = ihm.getNom()) &&
				backToMenu((String.valueOf(prixPizza = ihm.getPrix()))) &&
				backToMenu((typePizza = ihm.getCategorie()))
						) {
			try {
				ihm.getPizzaDao().modifier(codePizza, nomPizza, prixPizza, typePizza, oldPizza);
				ihm.systemOut("La pizza à bien été modifié");
			} catch( PizzaException e) {
				Logger.getLogger(e.getMessage());
				throw new PizzaException(e);
			}
		}
	}

	
}
