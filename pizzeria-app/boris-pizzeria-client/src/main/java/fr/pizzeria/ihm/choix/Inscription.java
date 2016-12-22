package fr.pizzeria.ihm.choix;

import javax.enterprise.inject.Produces;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.ClientException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.metier.client.MetierClient;
import fr.pizzeria.model.Client;

public class Inscription extends Choix {

	@Produces private MetierClient metier = new MetierClient();
	
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
				Client client = ihm.getClientDao().ajouterClient(metier.creerClient(prenomClient, nomClient, mailClient, mdpClient));
				String message = ( client == null ) ? "Impossible de vous inscrire" : "Bienvenue sur l'application!";
				ihm.systemOut(message);
			} catch( ClientException e ) {
				Logger.getLogger(e.getMessage());
				throw new ClientException(e);
			}
		}
	}

}
