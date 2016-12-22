package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.jboss.logging.Logger;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.commande.CommandeDao;
import fr.pizzeria.dao.personne.client.ClientDao;
import fr.pizzeria.dao.personne.livreur.LivreurDao;
import fr.pizzeria.dao.pizza.PizzaDao;
import fr.pizzeria.exception.ClientException;
import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
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
	
	public String getString(int taille, String message) {
		
		boolean valide = false;
		systemOut(message);
		sl(1);
		String nom = null;
		while(!valide) {
			nom = sc.next();
			if( nom.length() <= taille) {
				valide = true;
			} else if (nom.equals(ABANDONNER)) {
				nom = "0";
				valide = true;
			} else {
				systemOut("Le champ ne doit pas d�passer " + taille + " caractères");
				sl(1);
			}
		}
		return nom;
	}
	
	public int getInt( int taille, String message ) {
		
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
	
	public double getDouble(String message) {
		
		boolean valide = false;
		String valeur = null;
		systemOut(message);
		sl(1);
		while(!valide) {
			valeur = sc.next();
			if( valeur.matches("[0-9]{1,}[.]{0,1}[0-9]{0,2}")){
				valide = true;
			} else if (valeur.equals(ABANDONNER)) {
				valeur = "0";
				valide = true;
			} else {
				systemOut("Merci d'entrer un valeur valide");
				sl(1);
			}
		}
		return Double.parseDouble(valeur);
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
			if( isCategorieExist(type) || type.equals(ABANDONNER)){
				valide = true;
			} else {
				systemOut("Merci d'entrer un type valide");
				sl(1);
			}
		}
		return type;
	}
	
	public void afficherPizza( Pizza pizza ) {
		systemOut(pizza.getCode() + " -> " + pizza.getNom() + " (" + pizza.getPrix() + " �) | " + pizza.getType().toString());
	}
	
	private boolean isCategorieExist(String type) {
		return Stream.of(CategoriePizza.values()).filter(c -> c.name().equalsIgnoreCase(type)).findAny().isPresent() ;
	}

	public Scanner getSc() {
		return sc;
	}
	
	public void importationDonnee() {
		
		List<Pizza> listePizza = new ArrayList<>();
		listePizza.add(new Pizza(1, "PEP","Pépéroni",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(2, "MAR","Margherita",14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(3, "REI","La Reine",11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(4, "FRO","La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza(5, "CAN","Cannibale",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(6, "SAV","Savoyarde",13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(7, "ORI","L'Orientale",13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(8, "IND","L'Indienne",14.00, CategoriePizza.VIANDE));
		
		listePizza.forEach(p -> {
			getPizzaDao().ajouter(p);
		});
		
		
		try {
			getClientDao().ajouterClient(new Client("Boris", "Maurence", "boris@maurence.com", "1234"));
			getClientDao().ajouterClient(new Client("test", "test", "test@test.com", "1234"));
			getLivreurDao().ajouterLivreur(new Livreur("1", "Livreur1", "Livreur1"));
		} catch ( ClientException | LivreurException e) {
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		}
		
		
		
	}
	
	public PizzaDao getPizzaDao() {
		
		return daoFactory.getPizzaDao();
		
	}
	
	public ClientDao getClientDao() {
		return daoFactory.getClientDao();
	}
	
	public CommandeDao getCommandeDao() {
		return daoFactory.getCommandeDao();
	}
	
	public LivreurDao getLivreurDao() {
		return daoFactory.getLivreurDao();
	}

	
}
