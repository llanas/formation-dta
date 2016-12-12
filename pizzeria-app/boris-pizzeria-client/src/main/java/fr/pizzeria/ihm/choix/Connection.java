package fr.pizzeria.ihm.choix;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.ClientException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.Menu;
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
		if(backToMenu(mail = ihm.getMail()) &&
				backToMenu(password = ihm.getPassword())
				) {
			try {
				Client clientConnecter = ihm.getClientDao().connexion(mail, password);
				Menu menu = new Menu(this.ihm, clientConnecter);
			} catch(ClientException e ) {
				Logger.getLogger(e.getMessage());
			}
		}
	}

}
