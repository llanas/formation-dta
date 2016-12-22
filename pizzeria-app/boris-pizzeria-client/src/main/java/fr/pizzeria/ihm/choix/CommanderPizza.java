package fr.pizzeria.ihm.choix;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Produces;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.metier.commande.MetierCommande;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public class CommanderPizza extends Choix {
	
	Client client;
	
	@Produces private MetierCommande metier = new MetierCommande();
	
	public CommanderPizza(IhmUtil ihm) {

		super();
		this.ihm = ihm;
	}
	
	public CommanderPizza(IhmUtil ihm, int indexMenu, Client client) {

		super();
		this.ihm = ihm;
		this.client = client;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - Commander une Pizza - " + indexMenu);
	}

	@Override
	public void executer() {

		
		try {
			Set<Pizza> pizzasCommande = new HashSet<>();
			String code = "0";
			while(code=="0"){
				List<Pizza> pizzas = ihm.getPizzaDao().getListePizza();
				if( ihm.getPizzaDao().getListePizza().isEmpty() ){ 
					ihm.systemOut("La liste ne contient plus de pizza!");
				} else {
					pizzas.forEach(ihm::afficherPizza);
					code = ihm.getString(3, "Veuillez selectionner la code la pizza à commander");
					if(ihm.isCodeExist(code, pizzas)) {
						int nbPizza = ihm.getInt(10, "Combien de " + ihm.getPizzaDao().recupererPizza(code).getNom() + " voulez vous?");
						for(int i = 0 ; i < nbPizza ; i++ ){
							pizzasCommande.add(ihm.getPizzaDao().recupererPizza(code));
						}
					} else {
						ihm.systemOut("Le code est incorecte");
					}
				}
			}
			Livreur livreur = ihm.getLivreurDao().recupererLivreur("1");
			Date date = new Date();
			ihm.getCommandeDao().ajouter(metier.creerCommande(this.client, livreur, date, pizzasCommande));
		} catch (CommandeException | LivreurException e) {
			Logger.getLogger(e.getMessage());
			throw new CommandeException(e);
		}
		
		
	}

}
