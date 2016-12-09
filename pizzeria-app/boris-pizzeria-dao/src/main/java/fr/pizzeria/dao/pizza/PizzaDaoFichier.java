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
	private List<Pizza> listePizza = new ArrayList<Pizza>();

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
	public String ajouter(String code, String nom, Double prix, String type) throws PizzaException {
		Pizza pizza = metier.creerPizza( code, nom, prix,  type);
		try {
			fichier.sauvegarderDansFichier( pizza );
		} catch (FichierException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		return code;
	}

	@Override
	public String modifier( String code, String nom, Double prix, String type, String oldCode ) throws PizzaException {

		try {
			fichier.sauvegarderDansFichier(metier.creerPizza( code, nom, prix,  type ));
		} catch (FichierException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		return code;
	}

	@Override
	public String supprimer( String code ) throws PizzaException {

		fichier.supprimerDansFichier(code);
		return code;
	}

	@Override
	public Pizza recupererPizza( String code ) {
		//TODO
		return null;
	}
}
