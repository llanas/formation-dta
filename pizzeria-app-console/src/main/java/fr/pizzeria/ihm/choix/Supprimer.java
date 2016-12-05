package fr.pizzeria.ihm.choix;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

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
		
		Choix afficher = new AfficherCarte(ihm);
		afficher.executer();
		boolean valide = false;
		do{
			if( ihm.getPizzaDao().getListePizza().size() == 0 ){
				valide = true;
				break;
			} else {
				String codePizza = ihm.getCode();
				if (codePizza.equals(abandonner)) {
					valide = true;
					break;
				} else {
					try {
						Pizza pizzaAncienne = ihm.getPizzaDao().recupererPizza(codePizza);
						ihm.systemOut("La pizza ci-dessous à été supprimer");
						ihm.afficherPizza(pizzaAncienne);
						ihm.getPizzaDao().supprimer(codePizza);
						valide = true;
					} catch (PizzaException e) {
						ihm.systemOut(e.message());
					}
				}
			}
		}while(!valide);
	}
}