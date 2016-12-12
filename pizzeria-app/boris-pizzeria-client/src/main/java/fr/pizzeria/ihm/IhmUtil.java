package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.commande.CommandeDao;
import fr.pizzeria.dao.personne.client.ClientDao;
import fr.pizzeria.dao.personne.livreur.LivreurDao;
import fr.pizzeria.dao.pizza.PizzaDao;
import fr.pizzeria.model.Pizza;

public class IhmUtil {
	
	private Scanner sc;
	private DAOFactory daoFactory;
	private int taille;
	
	private static final String ABANDONNER		= "99";
	

	public IhmUtil( int taille, Scanner sc, DAOFactory daoFactory ) {
		
		super();
		this.sc = sc;
		this.daoFactory = daoFactory;
		this.taille = taille;
	}
	
	public void systemOut( String texte ){
		
		StringBuilder texteFinal = new StringBuilder(taille);
		int reste = texte.length() - ( taille - 2 );
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
	
	public String getNom() {
		
		boolean valide = false;
		systemOut("Veuillez saisir votre nom (sans espaces)");
		sl(1);
		String nom = null;
		while(!valide) {
			nom = sc.next();
			if( nom.length() <= 13 || nom.equals(ABANDONNER)) {
				valide = true;
			} else {
				systemOut("Le nom ne doit pas d�passer 13 caract�res");
				sl(1);
			}
		}
		return nom;
	}
	
	public String getPrenom() {
		
		boolean valide = false;
		systemOut("Veuillez saisir votre prenom(sans espaces)");
		sl(1);
		String nom = null;
		while(!valide) {
			nom = sc.next();
			if( nom.length() <= 13 || nom.equals(ABANDONNER)) {
				valide = true;
			} else {
				systemOut("Le nom ne doit pas d�passer 13 caract�res");
				sl(1);
			}
		}
		return nom;
	}
	
	public String getMail() {
		
		boolean valide = false;
		systemOut("Veuillez saisir votre addresse Mail");
		sl(1);
		String nom = null;
		while(!valide) {
			nom = sc.next();
			if( nom.length() <= 30 || nom.equals(ABANDONNER)) {
				valide = true;
			} else {
				systemOut("Le mail ne doit pas d�passer 30 caract�res");
				sl(1);
			}
		}
		return nom;
	}
	
	public String getPassword() {
		
		boolean valide = false;
		systemOut("Veuillez saisir votre mot de passe");
		sl(1);
		String nom = null;
		while(!valide) {
			nom = sc.next();
			if( nom.length() <= 13 || nom.equals(ABANDONNER)) {
				valide = true;
			} else {
				systemOut("Le mot de passe ne doit pas d�passer 13 caract�res");
				sl(1);
			}
		}
		return nom;
	}
	
	public String getOldCode() {
		
		boolean valide = false;
		String code = null;
		systemOut("Veuillez saisir un code de pizza");
		sl(1);
		while(!valide) {
			code = sc.next();
			if( code.matches("[^0-9]{3}") ){
				code = code.toUpperCase();
				valide = true;
			} else if(code.equals(ABANDONNER)){
				valide = true;
			} else {
				systemOut("Merci d'entrer un code de valide");
				sl(1);
			}
		}
		return code;
	}
	
	public Scanner getSc() {
		return sc;
	}
	
	public ClientDao getClientDao() {
		
		return daoFactory.getClientDao();
		
	}

	public PizzaDao getPizzaDao() {

		return daoFactory.getPizzaDao();
	}

	public void afficherPizza(Pizza pizza) {
		systemOut(pizza.getCode() + " -> " + pizza.getNom() + " (" + pizza.getPrix() + " �) | " + pizza.getType().toString());
	}

	public CommandeDao getCommandeDao() {
		return daoFactory.getCommandeDao();
	}

	public LivreurDao getLivreurDao() {
		return daoFactory.getLivreurDao();
	}

}
