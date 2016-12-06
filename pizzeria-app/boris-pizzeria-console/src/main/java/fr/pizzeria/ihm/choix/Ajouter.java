package fr.pizzeria.ihm.choix;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;

public class Ajouter extends Choix {
	
	public Ajouter( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public Ajouter( IhmUtil ihm, int indexMenu ) {
		super();
		this.indexMenu = indexMenu;
		this.ihm = ihm;
		this.setDescription(indexMenu + " - AJOUTER UNE PIZZA - " + indexMenu);
	}

	@Override
	public void executer() {
		
		boolean valide = false;
		while(!valide) {
			String codePizza = ihm.getCode();
			if( codePizza.equals(abandonner) ){
				valide = true;
				break;
			}
			String nomPizza = ihm.getNom();
			if( nomPizza.equals(abandonner) ){
				valide = true;
				break;
			}
			Double prixPizza = ihm.getPrix();
			if( String.valueOf(prixPizza).equals(abandonner) ){
				valide = true;
				break;
			}
			String typePizza = ihm.getCategorie();
			if( typePizza.equals(abandonner) ) {
				valide = true;
				break;
			}
			try {
				String code = ihm.getPizzaDao().ajouter(codePizza, nomPizza, prixPizza, typePizza);
				ihm.systemOut("La pizza ci-dessous � �t� ajouter");
				ihm.afficherPizza(ihm.getPizzaDao().recupererPizza(code));
				valide = true;
			} catch (PizzaException e) {
				ihm.systemOut(e.message());
			}
		}
	}
}
