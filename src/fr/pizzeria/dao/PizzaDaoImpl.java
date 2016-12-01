package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.IndexException;
import fr.pizzeria.exception.NomException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.PrixException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements PizzaDao {
	
	private List<Pizza> listePizza = new ArrayList<Pizza>();
	
	private FichierMetier fichier = new FichierMetier();
	
	/**
	 * Constructeur par défault de la classe PizzaDaoImpl qui implémente l'interface PizzaDao
	 * Ce constructeur permet de créer une liste d'objet {@link Pizza} prédéfinie.
	 * 
	 */
	public PizzaDaoImpl() {
		
		listePizza.add(new Pizza(0,"PEP","Pépéroni",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(1,"MAR","Margherita",14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(2,"REI","La Reine",11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(3,"FRO","La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza(4,"CAN","Cannibale",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(5,"SAV","Savoyarde",13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(6,"ORI","L'Orientale",13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(7,"IND","L'Indienne",14.00, CategoriePizza.VIANDE));
		
		listePizza.forEach(p -> {
			fichier.SauvegarderDansFichier(p);
	});
	}
	
	@Override
	public List<Pizza> getListePizza() {
		return this.listePizza;
	}
	
	@Override
	public int getNombrePizza() {
		
		return listePizza.size();
	}
	
	@Override
	public Pizza recupererPizza( int index ){
		
		return listePizza.get(index);
	}

	
	/**
	 * Créer un nouvel objet {@link Pizza} en récupérant le retour de la fonction {@link #creerPizza(Integer, String, String, Double, String)} <br/>
	 * Ajoute cet objet à {@link #listePizza} <br/>
	 * Créer un fichier texte de sauvegarde en appellant la méthode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * Retourne la taille de {@link #listePizza} (qui correspond à l'index de la dernière pizza ajouter)
	 */
	@Override
	public int ajouter( String code, String nom, Double prix, String type ) throws PizzaException {
		
		Pizza pizza = creerPizza( listePizza.size(), code, nom, prix,  type);
		listePizza.add( pizza );
		fichier.SauvegarderDansFichier( pizza );
		return (listePizza.size()-1);
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
	public int modifier( Integer index, String code, String nom, Double prix, String type ) throws PizzaException {
		
		listePizza.get(index).setCode(verifierCode(code));
		listePizza.get(index).setNom(verifierNom(nom));
		listePizza.get(index).setPrix(verifierPrix(prix));
		fichier.SauvegarderDansFichier(listePizza.get(index));
		return index;	
	}
	
	
	/**
	 * Vérifie les différents paramètres d'entrée graçe aux méthodes :<br/>
	 * <ul>
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
	public int modifier( Integer index, String nom, Double prix, String type ) throws PizzaException {
		
		listePizza.get(index).setNom(nom);
		listePizza.get(index).setPrix(prix);
		fichier.SauvegarderDansFichier(listePizza.get(index));
		return index;
	}

	/**
	 * Vérifie si l'index passé en paramètre est valide grace à la méthode {@link #verifierIndex(Integer)}<br/>
	 * Supprimer le fichier texte de sauvegarde de la pizza correspondante au paramêtre
	 * Supprime la pizza de {@link #ListePizza}<br/>
	 * 
	 */
	@Override
	public int supprimer( int index ) throws PizzaException {
		
		verifierIndex(index);
		fichier.SupprimerDansFichier(listePizza.get(index));
		listePizza.remove(index);
		return index;	
	}
	
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
	private Pizza creerPizza( Integer index, String code, String nom, Double prix, String type) throws PizzaException {	
		
		return new Pizza( verifierIndex(index), verifierCode(code), verifierNom(nom), verifierPrix(prix), verifierType(type));
	}
	
	
	/* -------------------- METHODE DE VERIFICATION ------------------------------ */
	
	
	/**
	 * Vérifie si l'index passé en paramêtre est positif et si il est présent dans la {@link #listePizza}
	 * 
	 * @param index - 
	 * 		Integer correspondant à l'index de la pizza
	 * @return
	 * 		l'index passé en paramêtre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link IndexException}
	 */
	private Integer verifierIndex(Integer index) throws PizzaException {
		
		if( index != null && index >= 0 && index <= listePizza.size() ) {
			return index;
		} else {
			throw new IndexException("L'index : " + index + " n'est pas valide");
		}
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
	private String verifierCode(String code) throws PizzaException {
		
		if( code != null && code.matches("[^0-9]{3}")) {
			if( listePizza.stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
				throw new CodeException("Le code " + code + " n'est pas valide");
			}
		}
		return code;		
	}

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
	private String verifierNom(String nom) throws PizzaException {

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
	private Double verifierPrix(Double prix) throws PizzaException{
		
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
	private CategoriePizza verifierType(String type) throws PizzaException {
		
		if( type != null && isCategorieExist(type) ) {
			return CategoriePizza.valueOf(type.toUpperCase());
		} else {
			throw new PizzaException("Le type : " + type + " n'est pas valide");
		}
	}

	@Override
	public boolean isCategorieExist(String type) {
		return Stream.of(CategoriePizza.values()).filter(c -> c.name().equalsIgnoreCase(type)).findAny().isPresent() ;
	}
}