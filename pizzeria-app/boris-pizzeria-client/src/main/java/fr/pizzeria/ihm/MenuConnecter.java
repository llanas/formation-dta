package fr.pizzeria.ihm;

import fr.pizzeria.ihm.choix.CommanderPizza;
import fr.pizzeria.ihm.choix.ListeCommande;
import fr.pizzeria.model.Client;

public class MenuConnecter extends Nav {
	
	public MenuConnecter( IhmUtil ihm, Client client ) {

		int i = 0;
		this.ihm = ihm;
		this.client = client;
		navigateur.put( ++i, new CommanderPizza(ihm, i, this.client));
		navigateur.put( ++i, new ListeCommande(ihm, i, this.client));
	}
}
