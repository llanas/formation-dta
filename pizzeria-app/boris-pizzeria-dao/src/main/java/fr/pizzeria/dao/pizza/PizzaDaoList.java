package fr.pizzeria.dao.pizza;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoList implements PizzaDao {
	
	private MetierDaoPizza metier;
	
	List<Pizza> listePizza = new ArrayList<Pizza>();
	
	/**
	 * Constructeur par d�fault de la classe PizzaDaoImpl qui impl�mente l'interface PizzaDao
	 * Ce constructeur permet de cr�er une liste d'objet {@link Pizza} pr�d�finie.
	 * 
	 */
	public PizzaDaoList() {
		
		listePizza.add(new Pizza(1, "PEP","P�p�roni",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(2, "MAR","Margherita",14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(3, "REI","La Reine",11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(4, "FRO","La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza(5, "CAN","Cannibale",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(6, "SAV","Savoyarde",13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(7, "ORI","L'Orientale",13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(8, "IND","L'Indienne",14.00, CategoriePizza.VIANDE));
		
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
	 * Cr�er un nouvel objet {@link Pizza} en r�cup�rant le retour de la fonction {@link #creerPizza(Integer, String, String, Double, String)} <br/>
	 * Ajoute cet objet � {@link #listePizza} <br/>
	 * Cr�er un fichier texte de sauvegarde en appellant la m�thode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * Retourne la taille de {@link #listePizza} (qui correspond � l'index de la derni�re pizza ajouter)
	 */
	@Override
	public String ajouter( String code, String nom, Double prix, String type ) throws PizzaException {
		
		listePizza.add( metier.creerPizza( code, nom, prix,  type ));
		return code;
	}

	/**
	 * V�rifie les diff�rents param�tres d'entr�e gra�e aux m�thodes :<br/>
	 * <ul>
	 * 	<li>{@link #verifierCode(String)}</li>
	 * 	<li>{@link #verifierNom(String)}</li>
	 * 	<li>{@link #verifierPrix(Double)}</>
	 * 	<li>{@link #verifierType(String)}</li>
	 * </ul>
	 * 
	 * Ecrase le fichier de sauvegarde de cette pizza avec les nouvelles valeurs en utilisant la m�thode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * 
	 */
	@Override
	public String modifier( String code, String nom, Double prix, String type, String oldCode ) throws PizzaException {
		
		Pizza pizza = recupererPizza(oldCode);
		pizza.setCode(metier.verifierCode(code));
		pizza.setNom(metier.verifierNom(nom));
		pizza.setPrix(metier.verifierPrix(prix));
		pizza.setType(metier.verifierType(type));
		return code;	
	}

	/**
	 * V�rifie si l'index pass� en param�tre est valide grace � la m�thode {@link #verifierIndex(Integer)}<br/>
	 * Supprimer le fichier texte de sauvegarde de la pizza correspondante au param�tre
	 * Supprime la pizza de {@link #ListePizza}<br/>
	 * 
	 */
	@Override
	public String supprimer( String code ) throws PizzaException {
		
		metier.verifierCode(code);
		listePizza.remove(recupererPizza(code));
		return code;	
	}	
}