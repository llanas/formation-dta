package fr.pizzeria.test.pizza;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.dao.pizza.PizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.test.SpringTestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class PizzaDaoJpaSpringTest {

	private Pizza test = new Pizza("JUN","JunitTest",10.0,CategoriePizza.SANS_VIANDE);
	private Pizza ajouter = new Pizza("ADD","AjouterPizza", 10.0, CategoriePizza.POISSON);
	
	@Autowired
	@Qualifier("JPASpring")
	private PizzaDao pizzaDao;
	
	@Test
	public void testRecupererPizza() {
		List<Pizza> pizzas = pizzaDao.getListePizza();
		Optional<Pizza> pizza = pizzas.stream().filter(p -> p.getCode().equals(test.getCode())).findFirst();
		if(pizza.isPresent()) {
			pizza.get().equals(test);
		}
	}

	@Test
	public void testAjouter() {
		pizzaDao.ajouter(ajouter);
		List<Pizza> pizzas = pizzaDao.getListePizza();
		Optional<Pizza> pizza = pizzas.stream().filter(p -> p.getCode().equals(ajouter.getCode())).findFirst();
		if(pizza.isPresent()) {
			pizza.get().equals(ajouter);
		}
	}

	@Test
	public void testModifier() {
		List<Pizza> pizzas = pizzaDao.getListePizza();
		Optional<Pizza> optPizza = pizzas.stream().filter(p -> p.getCode().equals(test.getCode())).findFirst();
		Pizza pizza = optPizza.get();
		String oldCode = pizza.getCode();
		pizza.setCode("MOD");
		pizzaDao.modifier(pizza, oldCode);
		optPizza = pizzas.stream().filter(p -> p.getCode().equals("MOD")).findFirst();
		if(optPizza.isPresent()) {
			optPizza.get().equals(pizza);
		}
	}

	@Test
	public void testSupprimer() {
		List<Pizza> pizzas = pizzaDao.getListePizza();
		pizzaDao.supprimer(test);
		Optional<Pizza> pizza = pizzas.stream().filter(p -> p.getCode().equals(test.getCode())).findFirst();
		if(pizza.isPresent()) {
			fail("Erreur lors de la suppression");
		}
	}

}
