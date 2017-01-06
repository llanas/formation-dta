package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.pizza.PizzaDao;

@Component
public class DaoJpaSpring extends DAOFactory{

	@Autowired
	public DaoJpaSpring(PizzaDao piz) {
		super(piz);
	}
}
