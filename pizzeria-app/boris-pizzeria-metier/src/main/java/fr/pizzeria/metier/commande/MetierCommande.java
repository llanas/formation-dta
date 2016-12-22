package fr.pizzeria.metier.commande;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.inject.Named;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

@Named
public class MetierCommande {

	public Commande creerCommande(Client client, Livreur livreur, Date dateCommande, Set<Pizza> pizzas) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
		StringBuilder numero = new StringBuilder();
		numero.append(client.getNom()).append(livreur.getPrenom()).append(dateFormat.format(dateCommande));
		return new Commande(numero.toString(), client, livreur, dateCommande, pizzas);
	}
}
