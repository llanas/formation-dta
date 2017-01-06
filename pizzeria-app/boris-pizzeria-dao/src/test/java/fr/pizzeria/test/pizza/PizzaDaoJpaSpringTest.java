package fr.pizzeria.test.pizza;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.dao.pizza.PizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.test.SpringTestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class PizzaDaoJpaSpringTest {
	
	@Autowired
	private PizzaDao pizzaDao;
	
	@Test
	public void testRecupererPizza() {
		Pizza test = new Pizza("JUN","JunitTest",10.0,CategoriePizza.SANS_VIANDE);
		pizzaDao.ajouter(test);
		assertEquals(test, pizzaDao.recupererPizza("JUN"));
	}

//	@Test
//	public void testGetListePizza() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAjouter() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testModifier() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSupprimer() {
//		fail("Not yet implemented");
//	}

}
