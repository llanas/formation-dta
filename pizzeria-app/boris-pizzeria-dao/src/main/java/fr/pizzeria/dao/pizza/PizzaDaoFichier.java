package fr.pizzeria.dao.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.pizzeria.dao.FichierMetier;
import fr.pizzeria.exception.FichierException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFichier implements PizzaDao {
	
	private FichierMetier fichier;
	private List<Pizza> listePizza = new ArrayList<>();

	@Override
	public List<Pizza> getListePizza() throws PizzaException {
//		Arrays.asList(listeNom).forEach(p -> {
//			try {
//				listePizza.add(metier.creerPizzaDepuisFichier(p,fichier.recupererPizza(p)));
//			} catch (IOException e) {
//				Logger.getLogger(e.getMessage());
//				throw new PizzaException(e);
//			}
//		});
		return listePizza;
	}

	@Override
	public Pizza ajouter( Pizza pizza ) throws PizzaException {
		try {
			fichier.sauvegarderDansFichier( pizza );
			listePizza.add(pizza);
		} catch (FichierException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		return pizza;
	}

	@Override
	public Pizza modifier( Pizza pizza, String oldCode ) throws PizzaException {

		try {
			fichier.sauvegarderDansFichier(pizza);
		} catch (FichierException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		return pizza;
	}

	@Override
	public void supprimer( Pizza pizza ) throws PizzaException {

		//fichier.supprimerDansFichier(code);
	}

	@Override
	public Pizza recupererPizza( String code ) {
		return null;
	}
}
