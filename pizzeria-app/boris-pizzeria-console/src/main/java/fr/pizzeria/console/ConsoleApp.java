package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;

import org.jboss.logging.Logger;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.exception.FichierException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.Menu;


/**
 * Console d'application Administrateur
 * 
 * @author llanas
 *
 */
public class ConsoleApp {
	
	private ConsoleApp() {
		
	}

	/**
	 * Méthode principale de de mon application Commande
	 * 
	 * @param args
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {

		boolean run = true;
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String config = bundle.getString("dao.impl");
		DAOFactory daoFactory;
		
		try {
			daoFactory = (DAOFactory) Class.forName(config).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			Logger.getLogger(e.getMessage());
			throw new FichierException(e);
		}
		
		IhmUtil ihm = new IhmUtil( 100, new Scanner(System.in), daoFactory);

		Menu application = new Menu(ihm);
		
		ihm.importationDonnee();
		
		ihm.systemOut("LOGICIEL ADMINISTRATEUR PIZZERIA");
		
		while (run) {
			ihm.sl(1);
			application.afficherMenu();
			run = application.getChoix();
		}
		ihm.systemOut("FERMETURE DU LOGICIEL");
		ihm.sl(1);
	}
	
	
}
