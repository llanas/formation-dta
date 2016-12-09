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
		
		Choix afficher = new AfficherCarte(ihm);
		afficher.executer();
		boolean valide = false;
		do{
			if( ihm.getPizzaDao().getListePizza().size() == 0 ){
				valide = true;
				break;
			} else {
				String codePizza = ihm.getOldCode();
				if (codePizza.equals(abandonner)) {
					valide = true;
					break;
				} else {
					try {
						ihm.getPizzaDao().supprimer(codePizza);
						ihm.systemOut("La pizza a bien été supprimer");
						valide = true;
					} catch (PizzaException e) {
						Logger.getLogger(e.getMessage());
						throw new PizzaException(e);
					}
				}
			}
		}while(!valide);
	}
}