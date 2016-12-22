package fr.pizzeria.metier.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;


public class ClientProducer {

	
	@Produces
	@ApplicationScoped
	public MetierClient getMetierClient() {
		return new MetierClient();
	}
}
