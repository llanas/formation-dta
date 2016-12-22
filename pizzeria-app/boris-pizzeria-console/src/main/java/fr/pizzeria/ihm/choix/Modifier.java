package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import javax.inject.Inject;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.metier.pizza.MetierPizza;
import fr.pizzeria.model.Pizza;

public class Modifier extends Choix {
	
	@Inject private MetierPizza metier;
	
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
		Integer indexPizza = 0;
		while(indexPizza==0){
			oldPizza 		= ihm.getString(3, "Entrez le code de la pizza à modifié");
			codePizza		= ihm.getString(3, "Entrez le nouveau code de pizza");
			nomPizza		= ihm.getString(30, "Entrez le nom de la pizza");
			prixPizza		= ihm.getDouble("Entrez le prix de la pizza");
			typePizza		= ihm.getCategorie();
			try {
				Pizza pizza = ihm.getPizzaDao().modifier(metier.creerPizza(codePizza, nomPizza, prixPizza, typePizza), oldPizza);
				String message = (pizza != null) ?  "La pizza à bien été modifié" : "Erreur lors de la modification de pizza";
				ihm.systemOut(message);
			} catch( PizzaException e) {
				Logger.getLogger(e.getMessage());
				throw new PizzaException(e);
			}
		}
	}

	
}
