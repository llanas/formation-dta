package fr.pizzeria.dao.pizza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;


@Component
public class PizzaDaoJpaSpring implements PizzaDao{

	private static final String SQL_SELECT_BY_CODE			= "SELECT p FROM Pizza p WHERE p.code =:code";
	private static final String SQL_SELECT					= "SELECT p FROM Pizza p ORDER BY p.id";
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		TypedQuery<Pizza> query = em.createQuery(SQL_SELECT_BY_CODE, Pizza.class);
		query.setParameter("code", code);
		return query.getSingleResult();
	}

	@Override
	public List<Pizza> getListePizza() {
		TypedQuery<Pizza> query = em.createQuery(SQL_SELECT, Pizza.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Pizza ajouter(Pizza pizza) throws PizzaException {
		em.persist(pizza);
		return pizza;
	}

	@Override
	@Transactional
	public Pizza modifier(Pizza pizza, String oldCode) throws PizzaException {
		Pizza oldPizza = recupererPizza(oldCode);
		oldPizza.setCode(pizza.getCode());
		oldPizza.setNom(pizza.getNom());
		oldPizza.setPrix(pizza.getPrix());
		oldPizza.setType(pizza.getType());
		return pizza;
	}

	@Override
	public void supprimer(Pizza pizza) throws PizzaException {
		em.remove(pizza);
	}

}
