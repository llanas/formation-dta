package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.IndexException;
import fr.pizzeria.exception.NomException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.PrixException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoList implements PizzaDao {
	
	
	/**
	 * Constructeur par défault de la classe PizzaDaoImpl qui implémente l'interface PizzaDao
	 * Ce constructeur permet de créer une liste d'objet {@link Pizza} prédéfinie.
	 * 
	 */
	public PizzaDaoList() {
		
		listePizza.add(new Pizza("PEP","Pépéroni",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR","Margherita",14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("REI","La Reine",11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO","La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza("CAN","Cannibale",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV","Savoyarde",13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI","L'Orientale",13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("IND","L'Indienne",14.00, CategoriePizza.VIANDE));
		
	}
	
	@Override
	public List<Pizza> getListePizza() {
		return this.listePizza;
	}
	
	@Override
	public Pizza recupererPizza( String code ) throws PizzaException{
		
		metier.verifierCode(code);
		return listePizza.stream().filter(p -> p.getCode().equals(code)).findFirst().get();
	}

	
	/**
	 * Créer un nouvel objet {@link Pizza} en récupérant le retour de la fonction {@link #creerPizza(Integer, String, String, Double, String)} <br/>
	 * Ajoute cet objet à {@link #listePizza} <br/>
	 * Créer un fichier texte de sauvegarde en appellant la méthode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * Retourne la taille de {@link #listePizza} (qui correspond à l'index de la dernière pizza ajouter)
	 */
	@Override
	public String ajouter( String code, String nom, Double prix, String type ) throws PizzaException {
		
		listePizza.add( metier.creerPizza( code, nom, prix,  type ));
		return (code);
	}

	/**
	 * Vérifie les différents paramètres d'entrée graçe aux méthodes :<br/>
	 * <ul>
	 * 	<li>{@link #verifierCode(String)}</li>
	 * 	<li>{@link #verifierNom(String)}</li>
	 * 	<li>{@link #verifierPrix(Double)}</>
	 * 	<li>{@link #verifierType(String)}</li>
	 * </ul>
	 * 
	 * Ecrase le fichier de sauvegarde de cette pizza avec les nouvelles valeurs en utilisant la méthode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * 
	 */
	@Override
	public String modifier( String code, String nom, Double prix, String type ) throws PizzaException {
		
		metier.verifierCode(code);
		recupererPizza(code).setNom(metier.verifierNom(nom));
		recupererPizza(code).setPrix(metier.verifierPrix(prix));
		return code;	
	}

	/**
	 * Vérifie si l'index passé en paramètre est valide grace à la méthode {@link #verifierIndex(Integer)}<br/>
	 * Supprimer le fichier texte de sauvegarde de la pizza correspondante au paramêtre
	 * Supprime la pizza de {@link #ListePizza}<br/>
	 * 
	 */
	@Override
	public String supprimer( String code ) throws PizzaException {
		
		metier.verifierCode(code);
		listePizza.remove(recupererPizza(code));
		return code;	
	}
	
	@Override
	public String isCodeExist( String code ) throws CodeException {
		if( listePizza.stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
			throw new CodeException("Le code " + code + " existe déjà");
		} else {
			return code;
		}
	}
	
}