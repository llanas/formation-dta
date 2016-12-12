package fr.pizzeria.ihm.choix;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.ClientException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;

public class Inscription extends Choix {

	public Inscription( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public Inscription( IhmUtil ihm, int indexMenu ) {

		super();
		this.indexMenu = indexMenu;
		this.ihm = ihm;
		this.setDescription(indexMenu + " - S'INSCRIRE - " + indexMenu);
	}
	
	@Override
	public void executer() {

		String nomClient;
		String prenomClient;
		String mailClient;
		String mdpClient;
		if(backToMenu(prenomClient = ihm.getPrenom()) &&
				backToMenu(nomClient = ihm.getNom()) &&
				backToMenu(mailClient = ihm.getMail()) &&
				backToMenu(mdpClient = ihm.getPassword())
				) {
			try{
				ihm.getClientDao().ajouterClient(prenomClient, nomClient, mailClient, mdpClient);
				ihm.systemOut("Vous êtes maintenant inscrit à la Pizzeria! Merci");
			} catch( ClientException e ) {
				Logger.getLogger(e.getMessage());
				throw new PizzaException(e);
			}
		}
	}

}
