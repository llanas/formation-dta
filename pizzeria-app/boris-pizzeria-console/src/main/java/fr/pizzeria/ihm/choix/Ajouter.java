package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

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
		Integer indexPizza = 0;
		while(indexPizza==0){
			codePizza = ihm.getString(3, "Merci d'entrer le code de pizza à ajouter");
			nomPizza = ihm.getString(30, "Merci d'entrer le nom de la pizza à ajouter");
			prixPizza = ihm.getDouble("Merci d'entrer le prix de la pizza à ajouter");
			typePizza = ihm.getCategorie();
			try {
				Pizza pizza = ihm.getPizzaDao().ajouter(codePizza, nomPizza, prixPizza, typePizza);
				String message = ( pizza != null ) ? "La pizza à bien été créé" : "Erreur lors de la création de pizza";
				ihm.systemOut(message);
			} catch( PizzaException e) {
				Logger.getLogger(e.getMessage());
				throw new PizzaException(e);
			}
		}
	}
}
