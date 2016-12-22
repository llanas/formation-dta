package fr.pizzeria.metier.client;

import fr.pizzeria.model.Client;

public class MetierClient {

	public Client creerClient(String prenom, String nom, String mail, String password) {
		return new Client(prenom, nom, mail, password);
	}
}
