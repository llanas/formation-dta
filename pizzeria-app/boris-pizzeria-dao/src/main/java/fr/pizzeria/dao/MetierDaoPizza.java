package fr.pizzeria.dao;

import java.util.stream.Stream;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.NomException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.PrixException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class MetierDaoPizza {

	/**
	 * Permet de créer un Objet {@link Pizza}<br/>
	 * 
	 * Vérifie les différents paramètres d'entrée graçe aux méthodes :<br/>
	 * <ul>
	 * 	<li>{@link #verifierIndex(Integer)}</li>
	 * 	<li>{@link #verifierCode(String)}</li>
	 * 	<li>{@link #verifierNom(String)}</li>
	 * 	<li>{@link #verifierPrix(Double)}</>
	 * 	<li>{@link #verifierType(String)}</li>
	 * </ul>
	 * 
	 * @param index - 
	 * 		Integer correspondant à l'index de la pizza
	 * @param code - 
	 * 		String correspondant au code de la pizza
	 * @param nom - 
	 * 		String correspondant au libellé de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'énumération)
	 * @return
	 * 		{@link Pizza} l'objet créé
	 * @throws PizzaException
	 */
	public Pizza creerPizza(String code, String nom, Double prix, String type) throws PizzaException {
		return new Pizza( verifierCode(code), verifierNom(nom), verifierPrix(prix), verifierType(type));
	}

	/**
	 * Vérifie si le code passé en paramètre est bien composé de 3 Lettre et si il n'est pas déjà présent dans la {@link #listePizza}
	 * 
	 * @param code - 
	 * 		String correspondant aux code de la pizza
	 * @return
	 * 		le code passé en paramètre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link CodeException}
	 */
	public String verifierCode(String code) throws PizzaException {
		
		if( code != null && code.matches("[^0-9]{3}")) {
			return code;
		} else {
			throw new CodeException("Le code : " + code + " n'est pas valide");
		}
	}
	
	/* private String isCodeExist( String code ) {
		if( listePizza.stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
			throw new CodeException("Le code " + code + " existe déjà");
		}
	} */

	/**
	 * Vérifie si le nom passé en paramêtre n'est pas null et ne dépasse pas les 13 caractère
	 * 
	 * @param nom - 
	 * 		String correspondant au libellé de la pizza
	 * @return
	 * 		Le String passé en paramêtre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link NomException}
	 */
	public String verifierNom(String nom) throws PizzaException {

		if( nom != null && nom.length() <= 13 ) {
			return nom;
		} else {
			throw new NomException("Le nom : " + nom + " n'est pas valide");
		}
	}
	
	/**
	 * Vérifie si le prix entré en paramêtre n'est pas null
	 * 
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @return
	 * 		Le Double passé en paramêtre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link PrixException}
	 */
	public Double verifierPrix(Double prix) throws PizzaException{
		
		if( prix != null ) {
			return prix;
		} else {
			throw new PrixException("Le prix : " + prix + " n'est pas valide");
		}
	}
	
	/**
	 * Vérifie si le String passé en paramètre n'est pas null et si ce String correspond à une des valeurs de l'énumération {@link CategoriePizza}
	 * 
	 * @param type - 
	 * 		String correspondant à une catégorie de pizza
	 * @return
	 * 		Une des valeurs de l'énumération {@link CategoriePizza} si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException}
	 */
	public CategoriePizza verifierType(String type) throws PizzaException {
		
		if( type != null && isCategorieExist(type) ) {
			return CategoriePizza.valueOf(type.toUpperCase());
		} else {
			throw new PizzaException("Le type : " + type + " n'est pas valide");
		}
	}
	
	public boolean isCategorieExist(String type) {
		return Stream.of(CategoriePizza.values()).filter(c -> c.name().equalsIgnoreCase(type)).findAny().isPresent() ;
	}

	public Pizza creerPizzaDepuisFichier(String p, String[] valeurs) throws Exception {
		return creerPizza( p, valeurs[0], Double.valueOf(valeurs[1]), valeurs[2]);
	}
}
