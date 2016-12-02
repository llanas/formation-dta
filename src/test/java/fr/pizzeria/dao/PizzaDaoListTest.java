package fr.pizzeria.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoListTest {

	@Test
	public void ajouterPizzaTest() {
		
		PizzaDaoList pizzaDao = new PizzaDaoList();
		
		String code = "TES";
		String nom = "Teste";
		Double prix = 12.0;
		String type = "Viande";
		
		try {
			pizzaDao.ajouter(code, nom, prix, type);
			assertEquals(new Pizza(code, nom, prix, CategoriePizza.VIANDE), pizzaDao.recupererPizza(code));
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
