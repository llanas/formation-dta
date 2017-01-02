package fr.pizzeria.metier.pizza;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class PizzaProducer {

	@Produces
	@ApplicationScoped
	public MetierPizza getMetierPizza() {
		return new MetierPizza();
	}
}
