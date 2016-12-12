package fr.pizzeria.ihm.choix;

import java.util.List;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class ListeCommande extends Choix {
	
	private Client client;
	
	public ListeCommande( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public ListeCommande( IhmUtil ihm, int indexMenu, Client client ) {
		super();
		this.ihm = ihm;
		this.client = client;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - AFFICHER LA CARTE - " + indexMenu);
	}

	@Override
	public void executer() {

		try {
			List<Commande> commandes = ihm.getCommandeDao().getListCommandeByPersonne(client);
			if(commandes.isEmpty()) {
				ihm.systemOut("Vous n'avez aucune commande en cours");
			} else {
				commandes.forEach(p -> {
					ihm.systemOut(p.getNumeroCommande());
				});
			}
		} catch (CommandeException e) {
			Logger.getLogger(e.getMessage());
		}
		
	}

}
