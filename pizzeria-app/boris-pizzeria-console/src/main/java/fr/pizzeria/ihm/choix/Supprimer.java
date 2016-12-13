package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;

public class Supprimer extends Choix {
	
	public Supprimer( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public Supprimer( IhmUtil ihm, int indexMenu ) {
		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - SUPPRIMER PIZZA - " + indexMenu);
	}

	@Override
	public void executer() {
		
		if( ihm.getPizzaDao().getListePizza().isEmpty() ){
			ihm.systemOut("Aucune pizza enregistré");
		} else {
			String codePizza;
			Integer indexPizza = 0;
			while(indexPizza==0){
				try {
					codePizza = ihm.getString(3, "Entrez le code de la pizza à supprimer");
					indexPizza = ihm.getPizzaDao().supprimer(codePizza);
					String message = (indexPizza == 0) ? "Erreur lors de la suppression de pizza" : "La pizza à bien été supprimé";
					ihm.systemOut(message);
				} catch (PizzaException e) {
					Logger.getLogger(e.getMessage());
				}
			}
		}
	}
}