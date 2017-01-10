package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.ingredient.IngredientDao;
import fr.pizzeria.dao.pizza.PizzaDao;

@Component
@Qualifier("JPASpring")
public class DaoSpringData extends DAOFactory{

		@Autowired
		public DaoSpringData(PizzaDao piz, IngredientDao ing) {
			super(piz, ing);
		}
}
