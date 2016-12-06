package fr.pizzeria.ihm.choix;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

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
		
		Choix afficher = new AfficherCarte(ihm);
		afficher.executer();
		boolean valide = false;
		do {
			if (ihm.getPizzaDao().getListePizza().size() == 0) {
				valide = true;
				break;
			} else {
				String codePizza = ihm.getCode();
				if (codePizza.equals(abandonner)) {
					valide = true;
					break;
				}
				String nomPizza = ihm.getNom();
				if (nomPizza.equals(abandonner)) {
					valide = true;
					break;
				}
				Double prixPizza = ihm.getPrix();
				if (String.valueOf(prixPizza).equals(abandonner)) {
					valide = true;
					break;
				}
				String typePizza = ihm.getCategorie();
				if (typePizza.equals(abandonner)) {
					valide = true;
					break;
				}
				try {
					Pizza anciennePizza = ihm.getPizzaDao().recupererPizza(codePizza);
					ihm.systemOut("PIZZA AVANT MODIFICATION");
					ihm.afficherPizza(anciennePizza);
					ihm.getPizzaDao().modifier(codePizza, nomPizza, prixPizza, typePizza);
					ihm.systemOut("PIZZA APRES MODIFICATION");
					ihm.afficherPizza(ihm.getPizzaDao().recupererPizza(codePizza));
					valide = true;
				} catch (PizzaException e) {
					ihm.systemOut(e.message());
				} 
			}
			
		}while(!valide);
	}

	
}
