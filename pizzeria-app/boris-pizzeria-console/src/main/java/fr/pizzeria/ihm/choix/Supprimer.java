package fr.pizzeria.ihm.choix;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;

@Component
public class Supprimer extends Choix {
	
	@Autowired
	public Supprimer( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
		this.setDescription(" SUPPRIMER PIZZA ");
	}

	@Override
	public void executer() {
		
		if( ihm.getPizzaDao().getListePizza().isEmpty() ){
			ihm.systemOut("Aucune pizza enregistré");
		} else {
			String codePizza;
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