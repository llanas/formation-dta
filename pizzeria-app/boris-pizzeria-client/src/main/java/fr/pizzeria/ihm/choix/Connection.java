package fr.pizzeria.ihm.choix;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.ClientException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.MenuConnecter;
import fr.pizzeria.model.Client;

public class Connection extends Choix {
	
	public Connection(IhmUtil ihm) {

		super();
		this.ihm = ihm;
	}
	
	public Connection(IhmUtil ihm, int indexMenu) {

		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - SE CONNECTER - " + indexMenu);
	}

	@Override
	public void executer()  {

		String mail;
		String password;
		Integer indexClient = 0;
		boolean run = true;
		while(indexClient==0) {
			try {
				mail = ihm.getString(30, "Entrez votre adresse mail");
				password = ihm.getString(30, "Entrez votre mot de passe");
				Client client = ihm.getClientDao().connexion(mail, password);
				indexClient = client.getId();
				ihm.systemOut("Bonjour " + client.getPrenom() + " " + client.getNom() + "!");
				MenuConnecter application = new MenuConnecter(ihm, client);
				while(run) {
					ihm.sl(1);
					application.afficherMenu();
					run = application.getChoix();
				}
			} catch( ClientException e ) {
				Logger.getLogger(e.getMessage());
			}
		}
	}
}