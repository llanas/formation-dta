package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.FichierMetier;
import fr.pizzeria.dao.PizzaDaoFichier;
import fr.pizzeria.dao.PizzaDaoList;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.Menu;
import fr.pizzeria.model.Pizza;

public class ConsoleApp {

	public static void main(String[] args) {

		boolean run = true;
		
		IhmUtil ihm = new IhmUtil( 55, new Scanner(System.in), new PizzaDaoFichier() );

		Menu application = new Menu(ihm);

		FichierMetier fichier = new FichierMetier();
		
		ihm.systemOut("LOGICIEL ADMINISTRATEUR PIZZERIA");
		
		Pizza p1 = new Pizza();
        p1.setCode("MAR");
        
        Pizza p2 = new Pizza();
        p2.setCode("MAR");
        
        System.out.println("p1=p2 ? " + p1.equals(p2));
		
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
