package fr.pizzeria.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFichier implements PizzaDao {
	
	private FichierMetier fichier = new FichierMetier();

	@Override
	public List<Pizza> getListePizza() {
		String[] listeNom = fichier.listerFichier();
		List<Pizza> listePizzaFichier = new ArrayList<Pizza>();
		Arrays.asList(listeNom).forEach(p -> {
			try {
				listePizzaFichier.add(metier.creerPizzaDepuisFichier(p,fichier.recupererPizza(p)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return listePizzaFichier;
	}

	@Override
	public String ajouter(String code, String nom, Double prix, String type) throws PizzaException {
		Pizza pizza = metier.creerPizza( code, nom, prix,  type);
		fichier.SauvegarderDansFichier( pizza );
		return code;
	}

	@Override
	public String modifier( String code, String nom, Double prix, String type ) throws PizzaException {

		fichier.SauvegarderDansFichier(metier.creerPizza( code, nom, prix,  type));
		return code;
	}

	@Override
	public String supprimer( String code ) throws PizzaException {

		fichier.SupprimerDansFichier(code);
		return code;
	}

	@Override
	public Pizza recupererPizza( String code ) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String isCodeExist(String code) throws CodeException {
		if(code.isEmpty()) {
			throw new CodeException("Le code " + code + " existe déjà");
		} else {
			return code;
		}
	}

}
