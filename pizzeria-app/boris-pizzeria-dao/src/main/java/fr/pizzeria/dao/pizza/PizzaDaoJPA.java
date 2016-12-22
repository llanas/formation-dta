package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.dao.MotherDaoJPA;
import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPA extends MotherDaoJPA implements PizzaDao {
	
	
	public PizzaDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}
	
	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		return execute((EntityManager em, EntityTransaction et) -> {
			return getPizzaJPA(code, em);
		});
	}

	@Override
	public List<Pizza> getListePizza() {
		return execute((EntityManager em, EntityTransaction et) -> {
			TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
			return query.getResultList();
		});		
	}

	@Override
	public Pizza ajouter(Pizza pizza) {
		return execute((EntityManager em, EntityTransaction et) -> {
			em.persist(pizza);
			return pizza;
		});
	}

	@Override
	public Pizza modifier(Pizza pizza, String oldCode) throws PizzaException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Pizza oldPizza = getPizzaJPA(oldCode, em);
			oldPizza.setCode(pizza.getCode());
			oldPizza.setNom(pizza.getNom());
			oldPizza.setPrix(pizza.getPrix());
			oldPizza.setType(pizza.getType());
			return pizza;
		});
	}

	@Override
	public void supprimer(Pizza pizza) throws PizzaException {
		execute((EntityManager em, EntityTransaction et) -> {
			em.remove(pizza);
			return Void.TYPE;
		});
	}
	
	public Pizza getPizzaJPA(String code, EntityManager em) {
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code =:code", Pizza.class);
		query.setParameter("code", code);
		return query.getSingleResult();
	}
	
	public String isCodeExist( String code, List<Pizza> pizzas ) throws CodeException {
		if( pizzas.stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
			throw new CodeException("Le code " + code + " existe d�j�");
		} else {
			return code;
		}
	}
}
