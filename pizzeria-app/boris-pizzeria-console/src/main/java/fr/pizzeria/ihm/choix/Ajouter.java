package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.metier.pizza.MetierPizza;
import fr.pizzeria.model.Pizza;

@Component
public class Ajouter extends Choix {
	
	@Autowired
	private MetierPizza metierPizza;
	
	@Autowired
	public Ajouter( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
		this.setDescription(" AJOUTER UNE PIZZA ");
	}

	@Override
	public void executer() {
		
		String codePizza	= ihm.getString(3, "Merci d'entrer le code de pizza à ajouter");
		String nomPizza		= ihm.getString(30, "Merci d'entrer le nom de la pizza à ajouter");
		Double prixPizza	= ihm.getDouble("Merci d'entrer le prix de la pizza à ajouter");
		String typePizza	= ihm.getCategorie();
		try {
			Pizza pizza = ihm.getPizzaDao().ajouter(metierPizza.creerPizza(codePizza, nomPizza, prixPizza, typePizza));
			String message = ( pizza != null ) ? "La pizza à bien été créé" : "Erreur lors de la création de pizza";
			ihm.systemOut(message);
		} catch( PizzaException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
	}
}
