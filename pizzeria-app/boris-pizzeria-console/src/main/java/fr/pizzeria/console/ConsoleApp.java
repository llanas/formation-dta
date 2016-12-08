package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.FichierMetier;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.Menu;

public class ConsoleApp {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		boolean run = true;
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String config = bundle.getString("dao.impl");
		DAOFactory daoFactory = (DAOFactory) Class.forName(config).newInstance();
		
		IhmUtil ihm = new IhmUtil( 55, new Scanner(System.in), daoFactory);

		Menu application = new Menu(ihm);

		FichierMetier fichier = new FichierMetier();
		
		ihm.systemOut("LOGICIEL ADMINISTRATEUR PIZZERIA");
		
		while (run) {
			ihm.sl(1);
			application.afficherMenu();
			run = application.getChoix();
		}
		fichier.SupprimerDossier("src/fr/pizzeria/dao/data");
		ihm.systemOut("FERMETURE DU LOGICIEL");
		ihm.sl(1);
	}
	
	
}
