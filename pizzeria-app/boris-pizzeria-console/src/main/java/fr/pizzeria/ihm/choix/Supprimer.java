package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import javax.inject.Inject;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.metier.pizza.MetierPizza;

public class Supprimer extends Choix {
	
	@Inject private MetierPizza metier;
	
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
					ihm.getPizzaDao().supprimer(ihm.getPizzaDao().recupererPizza(codePizza));
				} catch (PizzaException e) {
					Logger.getLogger(e.getMessage());
					throw new PizzaException(e);
				}
			}
		}
	}
}