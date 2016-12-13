package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.DaoJPA;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.Menu;

public class ClientApp {
	
	private ClientApp() {

	}

	public static void main(String[] args) {
		
		boolean run = true;
		DAOFactory daoFactory = new DaoJPA();
		
		IhmUtil ihm = new IhmUtil( 100, new Scanner(System.in), daoFactory);
		
		Menu application = new Menu(ihm);
		
		ihm.systemOut("LOGICIEL CLIENT PIZZERIA");
		
		while(run) {
			ihm.sl(1);
			application.afficherMenu();
			run = application.getChoix();
		}
		ihm.systemOut("FERMETURE DE L'APPLICATION");
		ihm.sl(1);
	}
}
