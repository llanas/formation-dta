package fr.pizzeria.console;

import java.util.logging.Level;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
		Menu application;
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PizzeriaAdminSpringConfig.class)){
			application = context.getBean(Menu.class);
			
			application.getIhm().systemOut("LOGICIEL ADMINISTRATEUR PIZZERIA");
			
			while (run) {
				application.getIhm().sl(1);
				application.afficherMenu();
				run = application.getChoix();
			}
			application.getIhm().systemOut("FERMETURE DU LOGICIEL");
			application.getIhm().sl(1);
		}
	}
}
