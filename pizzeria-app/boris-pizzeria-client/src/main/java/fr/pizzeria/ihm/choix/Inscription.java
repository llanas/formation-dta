package fr.pizzeria.ihm.choix;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.ClientException;
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
		int indexClient = 0;
		while(indexClient==0){
			nomClient = ihm.getString(30, "Entrez votre nom");
			prenomClient = ihm.getString(30, "Entrez votre prenom");
			mailClient = ihm.getString(30, "Entrez votre e-mail");
			mdpClient = ihm.getString(30, "Entrez votre mot de passe");
			try{
				indexClient = ihm.getClientDao().ajouterClient(prenomClient, nomClient, mailClient, mdpClient);
				String message = ( indexClient == 0 ) ? "Impossible de vous inscrire" : "Bienvenue sur l'application!";
				ihm.systemOut(message);
			} catch( ClientException e ) {
				Logger.getLogger(e.getMessage());
				throw new ClientException(e);
			}
		}
	}

}
