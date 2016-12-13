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
		Integer indexPizza = 0;
		while(indexPizza==0){
			oldPizza 		= ihm.getString(3, "Entrez le code de la pizza à modifié");
			codePizza		= ihm.getString(3, "Entrez le nouveau code de pizza");
			nomPizza		= ihm.getString(30, "Entrez le nom de la pizza");
			prixPizza		= ihm.getDouble("Entrez le prix de la pizza");
			typePizza		= ihm.getCategorie();
			try {
				indexPizza = ihm.getPizzaDao().modifier(codePizza, nomPizza, prixPizza, typePizza, oldPizza);
				String message = (indexPizza == 0) ? "Erreur lors de la modification de pizza" : "La pizza à bien été modifié";
				ihm.systemOut(message);
			} catch( PizzaException e) {
				Logger.getLogger(e.getMessage());
			}
		}
	}

	
}
