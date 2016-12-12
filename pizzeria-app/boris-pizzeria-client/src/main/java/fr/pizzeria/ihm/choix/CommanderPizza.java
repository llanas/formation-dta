package fr.pizzeria.ihm.choix;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public class CommanderPizza extends Choix {
	
	Client client;
	
	public CommanderPizza(IhmUtil ihm) {

		super();
		this.ihm = ihm;
	}
	
	public CommanderPizza(IhmUtil ihm, int indexMenu, Client client) {

		super();
		this.ihm = ihm;
		this.client = client;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - SE CONNECTER - " + indexMenu);
	}

	@Override
	public void executer() {

		List<Pizza> pizzas = ihm.getPizzaDao().getListePizza();
		if(pizzas.isEmpty()){
			ihm.systemOut("Aucune pizza n'est enregistré");
		} else {
			try {
				Set<Pizza> pizzasCommande = new HashSet<Pizza>();
				int input = 0;
				while(input != 99) {
					pizzas.forEach(p -> {
						ihm.afficherPizza(p);
					});
					ihm.systemOut(" /!\\ Tapez 99 pour sortir finir la commande /!\\  ");
					ihm.systemOut("Veuillez selectionner la code la pizza à commander");
					String code = ihm.getOldCode();
					input = Integer.valueOf(code);
					pizzasCommande.add(ihm.getPizzaDao().recupererPizza(code));
				}
				Livreur livreur = ihm.getLivreurDao().recupererLivreur(1);
				Date dateCommande = new Date();
				ihm.getCommandeDao().ajouter(client, livreur, dateCommande, pizzasCommande);
			} catch (CommandeException | LivreurException e) {
				Logger.getLogger(e.getMessage());
				
			}
			
		}
		
		
	}

}
