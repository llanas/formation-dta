package fr.pizzeria.dao.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import fr.pizzeria.dao.FichierMetier;
import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.exception.FichierException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFichier implements PizzaDao {
	
	private FichierMetier fichier;
	private MetierDaoPizza metier;
	private List<Pizza> listePizza = new ArrayList<>();

	@Override
	public List<Pizza> getListePizza() throws PizzaException {
		String[] listeNom = fichier.listerFichier();
		Arrays.asList(listeNom).forEach(p -> {
			try {
				listePizza.add(metier.creerPizzaDepuisFichier(p,fichier.recupererPizza(p)));
			} catch (IOException e) {
				Logger.getLogger(e.getMessage());
				throw new PizzaException(e);
			}
		});
		return listePizza;
	}

	@Override
	public Integer ajouter(String code, String nom, Double prix, String type) throws PizzaException {
		Pizza pizza = metier.creerPizza( code, nom, prix,  type);
		try {
			fichier.sauvegarderDansFichier( pizza );
			listePizza.add(pizza);
		} catch (FichierException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		return listePizza.indexOf(pizza);
	}

	@Override
	public Integer modifier( String code, String nom, Double prix, String type, String oldCode ) throws PizzaException {

		Pizza pizza = metier.creerPizza(code, nom, prix, type);
		try {
			fichier.sauvegarderDansFichier(pizza);
		} catch (FichierException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		return listePizza.indexOf(pizza);
	}

	@Override
	public Integer supprimer( String code ) throws PizzaException {

		Pizza pizza = recupererPizza(code);
		int index = pizza.getId();
		fichier.supprimerDansFichier(code);
		return index;
	}

	@Override
	public Pizza recupererPizza( String code ) {
		return null;
	}
}
