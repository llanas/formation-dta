package fr.pizzeria.dao.pizza;

import java.util.List;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public interface PizzaDao {
	
	/**
	 * Permet de r�cup�rer un objet Pizza depuis List<Pizza> dont l'index correspond � l'entier pass� en param�tre
	 * 
	 * @param index 
	 		Entier correspondant � un index de l'objet {@link Pizza}
	 * @return Pizza - 
	 * 		Un objet {@link Pizza}
	 * @throws PizzaException 
	 */
	Pizza recupererPizza( String code ) throws PizzaException;
	
	/**
	 * Permet de r�cup�rer une List d'objet Pizza
	 * 
	 * @return List<{@link Pizza}> - 
	 * 		Une {@link List} d'objet {@link Pizza}
	 */
	List<Pizza> getListePizza();
	
	/**
	 * Permet d'ajouter un objet {@link Pizza} � une {@link Link}
	 * 
	 * @param code - 
	 * 		String correspondant � un code unique � 3 lettre
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'�num�ration)
	 * @return
	 * 		Entier correspondant � l'index de la pizza dans la liste.
	 * @throws PizzaException
	 * 		Les exceptions sont g�n�r� lors de la v�rification des champs permettant la cr�ation de l'objet Pizza voir {@link PizzaDaoList}
	 */
	String ajouter( String code, String nom, Double prix, String type ) throws PizzaException;
	
	/**
	 * Permet de modifier un objet {@link Pizza} dans une {@link Link}
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'index de la pizza dans la liste
	 * @param code - 
	 * 		String correspondant � un code unique � 3 lettre
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'�num�ration)
	 * @return
	 * 		Entier correspondant � l'index de la pizza qui � �t� modifi�
	 * @throws PizzaException
	 */
	String modifier( String code, String nom, Double prix, String type, String oldCode) throws PizzaException;
	
	/**
	 * Permet de supprimer un objet {@link Pizza} d'une {@link List}
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'objet pizza � supprimer
	 * @return
	 * 		Entier correspondant � l'index de la pizza supprimer
	 * @throws PizzaException
	 */
	String supprimer( String code ) throws PizzaException;
}
