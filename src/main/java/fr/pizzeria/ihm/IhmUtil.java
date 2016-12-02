package fr.pizzeria.ihm;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class IhmUtil {
	
	private Scanner sc;
	private PizzaDao pizzaDao;
	private int taille;
	
	private static final String ABANDONNER		= "99";
	

	public IhmUtil( int taille, Scanner sc, PizzaDao pizzaDao ) {
		
		super();
		this.sc = sc;
		this.pizzaDao = pizzaDao;
		this.taille = taille;
	}
	
	public void systemOut( String texte ){
		
		StringBuilder texteFinal = new StringBuilder(taille);
		int reste = ( texte.length() - ( taille - 2 ) );
		reste *= -1;
		int espaceGauche = ( reste % 2 == 0) ? (reste / 2) : (reste / 2) + 1;
		int espaceDroit = reste - espaceGauche;
		texteFinal.append("|");
		while( texteFinal.length() < ( taille - 1 ) ) {
			for( int i = 0 ; i < espaceGauche ; i++ ) {
				texteFinal.append(" ");
			}
			texteFinal.append(texte);
			for( int y = 0 ; y < espaceDroit ; y++ ) {
				texteFinal.append(" ");
			}
			texteFinal.append("|");
		}
		sl(1);
		System.out.println(texteFinal);
		
	}
	
	public void sl( int nbLigne ) {
		
		for( int i = 0 ; i < nbLigne ; i++ ) {
			StringBuilder sautLigne = new StringBuilder(taille);
			sautLigne.append("|");
			for( int y = 0 ; y < (taille-2) ; y++ ) {
				sautLigne.append("-");
			}
			sautLigne.append("|");
			System.out.println(sautLigne);
			}
	}
	
	public int getIndex( int taille, String message ) {
		
		boolean valide = false;
		int code = 0;
		systemOut(message);
		sl(1);
		while(!valide) {
			String codePizza = sc.next();
			if(codePizza.matches(".*\\d+.*") ){
				code = Integer.valueOf(codePizza);
				if( 0 <= code && code < taille) {
					valide = true;
				} else if(codePizza.equals(ABANDONNER)){
						code = 99;
						valide = true;
				} else {
					systemOut("Merci d'entrer un code entre 0 et " + (taille - 1));
					sl(1);
				}
			} else {
				systemOut("Merci de choisir un nombre entier");
				sl(1);
			}
		}
		return code;		
	}
	
	public String getCode() {
	
		boolean valide = false;
		String code = null;
		systemOut("Veuillez saisir un Code à 3 lettres");
		sl(1);
		while(!valide) {
			code = sc.next();
			if( code.matches("[^0-9]{3}") ){
				code = code.toUpperCase();
				valide = true;
			} else if(code.equals(ABANDONNER)){
				valide = true;
			} else {
				systemOut("Merci d'entrer un code de 3 à lettres");
				sl(1);
			}
		}
		return code;
	}

	public String getNom() {
	
		boolean valide = false;
		systemOut("Veuillez saisir le nom (sans espaces)");
		sl(1);
		String nom = null;
		while(!valide) {
			nom = sc.next();
			if( nom.length() <= 13 ) {
				valide = true;
			} else if(nom.equals(ABANDONNER)){
				valide = true;
			} else {
				systemOut("Le nom ne doit pas dépasser 13 caractères");
				sl(1);
			}
		}
		char maj = Character.toUpperCase(nom.charAt(0));
		nom = maj + nom.substring(1).toLowerCase();
		return nom;
	}

	public double getPrix() {
	
		boolean valide = false;
		String prix = null;
		systemOut("Veuillez saisir le prix");
		sl(1);
		while(!valide) {
			prix = sc.next();
			if( prix.matches("[0-9]{1,}[.]{0,1}[0-9]{0,2}") ) {
				valide = true;
			} else if(prix.equals(ABANDONNER)){
				valide = true;
			} else {
				systemOut("Merci d'entrer un prix valide");
				sl(1);
			}
		}
		double prixFinal = Double.parseDouble(prix);
		return prixFinal;
	}
	
	public String getCategorie() {
		
		boolean valide = false;
		systemOut("Veuillez choisir un type de pizza");
		sl(1);
		Arrays.asList(CategoriePizza.values()).forEach(p ->{
			systemOut(p.toString().toUpperCase());
		});
		sl(1);
		String type = null;
		while(!valide) {
			type = sc.next();
			if( isCategorieExist(type) ){
				valide = true;
			} else if(type.equals(ABANDONNER)) {
				valide = true;
			} else {
				systemOut("Merci d'entrer un type valide");
				sl(1);
			}
		}
		return type;
	}
	
	public void afficherPizza( Pizza pizza ) {
		systemOut((pizza.getCode() + " -> " + pizza.getNom() + " (" + pizza.getPrix() + " €) | " + pizza.getType().toString()));
	}
	
	private boolean isCategorieExist(String type) {
		return Stream.of(CategoriePizza.values()).filter(c -> c.name().equalsIgnoreCase(type)).findAny().isPresent() ;
	}

	public Scanner getSc() {
		return sc;
	}
	
	public PizzaDao getPizzaDao() {
		return pizzaDao;
	}
}
