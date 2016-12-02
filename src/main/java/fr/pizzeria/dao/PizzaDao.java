package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public interface PizzaDao {
	
	

	List<Pizza> listePizza = new ArrayList<Pizza>();
	
	MetierDaoPizza metier = new MetierDaoPizza();
	
	/**
	 * Permet de récupérer un objet Pizza depuis List<Pizza> dont l'index correspond à l'entier passé en paramêtre
	 * 
	 * @param index 
	 		Entier correspondant à un index de l'objet {@link Pizza}
	 * @return Pizza - 
	 * 		Un objet {@link Pizza}
	 * @throws PizzaException 
	 */
	Pizza recupererPizza( String code ) throws PizzaException;
	
	/**
	 * Permet de récupérer une List d'objet Pizza
	 * 
	 * @return List<{@link Pizza}> - 
	 * 		Une {@link List} d'objet {@link Pizza}
	 */
	List<Pizza> getListePizza();
	
	/**
	 * Permet d'ajouter un objet {@link Pizza} à une {@link Link}
	 * 
	 * @param code - 
	 * 		String correspondant à un code unique à 3 lettre
	 * @param nom - 
	 * 		String correspondant au libellé de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'énumération)
	 * @return
	 * 		Entier correspondant à l'index de la pizza dans la liste.
	 * @throws PizzaException
	 * 		Les exceptions sont généré lors de la vérification des champs permettant la création de l'objet Pizza voir {@link PizzaDaoList}
	 */
	String ajouter( String code, String nom, Double prix, String type ) throws PizzaException;
	
	/**
	 * Permet de modifier un objet {@link Pizza} dans une {@link Link}
	 * 
	 * @param index - 
	 * 		Integer correspondant à l'index de la pizza dans la liste
	 * @param code - 
	 * 		String correspondant à un code unique à 3 lettre
	 * @param nom - 
	 * 		String correspondant au libellé de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'énumération)
	 * @return
	 * 		Entier correspondant à l'index de la pizza qui à été modifié
	 * @throws PizzaException
	 */
	String modifier( String code, String nom, Double prix, String type ) throws PizzaException;
	
	/**
	 * Permet de supprimer un objet {@link Pizza} d'une {@link List}
	 * 
	 * @param index - 
	 * 		Integer correspondant à l'objet pizza à supprimer
	 * @return
	 * 		Entier correspondant à l'index de la pizza supprimer
	 * @throws PizzaException
	 */
	String supprimer( String code ) throws PizzaException;
	
	String isCodeExist( String code ) throws CodeException;
}
