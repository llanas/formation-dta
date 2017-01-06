package fr.pizzeria.ihm.choix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

@Component
public class AfficherCarte extends Choix {
	
	@Autowired
	public AfficherCarte( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
		this.setDescription(" AFFICHER LA CARTE ");
	}

	public void executer() {
		List<Pizza> listePizza = ihm.getPizzaDao().getListePizza();
		if( listePizza.isEmpty() ) {
			ihm.systemOut("La liste ne contient plus de pizza!");
		} else {
			listePizza.forEach(ihm::afficherPizza);
		}
	}
}