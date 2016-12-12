package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.ihm.choix.Choix;
import fr.pizzeria.ihm.choix.CommanderPizza;
import fr.pizzeria.ihm.choix.Connection;
import fr.pizzeria.ihm.choix.Inscription;
import fr.pizzeria.ihm.choix.ListeCommande;
import fr.pizzeria.model.Client;

public class Menu {
		
	private Map<Integer, Choix> navigateur = new HashMap<>();
	private IhmUtil ihm;
	private Client client;

	public Menu( IhmUtil ihm, Client client ){

		Integer i = 0;
		this.client = client;
		this.ihm = ihm;
		if(client != null){
			navigateur.clear();
			navigateur.put( ++i, new CommanderPizza(ihm, i, this.client));
			navigateur.put( ++i, new ListeCommande(ihm, i, this.client));
		} else {
			navigateur.put( ++i, new Inscription(ihm, i));
			navigateur.put( ++i, new Connection(ihm, i));
		}
	}

	public void afficherMenu() {

		navigateur.forEach((i, c) -> ihm.systemOut(c.afficher()));

	}

	public boolean getChoix() {

		int choix = ihm.getIndex(navigateur.size()+1, "Que souhaitez vous faire ?");
		if(choix == 99){
			return false;
		} else {
			navigateur.get(choix).executer();
			return true;
		}
	}
}
