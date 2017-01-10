package fr.pizzeria.dao.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.repo.PizzaRepository;

@Component
@Qualifier("JPASpring")
public class PizzaDaoRepository implements PizzaDao {

	@Autowired
	PizzaRepository pizzaRep;

	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		return pizzaRep.getOne(code);
	}

	@Override
	public List<Pizza> getListePizza() {
		return pizzaRep.findAll();
	}

	@Override
	public Pizza ajouter(Pizza pizza) throws PizzaException {
		return pizzaRep.save(pizza);
	}

	@Override
	public Pizza modifier(Pizza pizza, String oldCode) throws PizzaException {
		pizzaRep.delete(pizzaRep.findOne(oldCode));
		return pizzaRep.save(pizza);
	}

	@Override
	public void supprimer(Pizza pizza) throws PizzaException {
		pizzaRep.delete(pizza);
	}
	
	
	
}
