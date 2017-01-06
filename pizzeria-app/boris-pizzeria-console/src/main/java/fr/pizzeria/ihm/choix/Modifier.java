package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.metier.pizza.MetierPizza;
import fr.pizzeria.model.Pizza;

@Component
public class Modifier extends Choix {
	
	@Autowired
	private MetierPizza metier;

	@Autowired
	public Modifier( IhmUtil ihm ){
		super();
		this.ihm = ihm;
		this.setDescription( " MODIFIER PIZZA ");
	}

	@Override
	public void executer() {
		
		String oldPizza 	= ihm.getString(3, "Entrez le code de la pizza à modifié");;
		String codePizza	= ihm.getString(3, "Entrez le nouveau code de pizza");
		String nomPizza 	= ihm.getString(30, "Entrez le nom de la pizza");
		Double prixPizza	= ihm.getDouble("Entrez le prix de la pizza");
		String typePizza	= ihm.getCategorie();
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
