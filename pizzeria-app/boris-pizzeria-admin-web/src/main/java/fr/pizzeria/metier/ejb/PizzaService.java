package fr.pizzeria.metier.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

@Stateless
public class PizzaService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Pizza ajouterPizza( Pizza pizza ) {
		
		em.persist(pizza);
		return pizza;
	}
	
	public List<Pizza> getListePizza() {
		
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		return query.getResultList();
	}
	
	public Pizza recupererPizza(String code) {
		
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code =:code", Pizza.class);
		query.setParameter("code", code);
		return query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Pizza modifierPizza( Pizza pizza, String oldCode ) {
		
		Pizza oldPizza = recupererPizza(oldCode);
		oldPizza.setCode(pizza.getCode());
		oldPizza.setNom(pizza.getNom());
		oldPizza.setPrix(pizza.getPrix());
		oldPizza.setType(pizza.getType());
		return pizza;
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void supprimerPizza( String code ) {
		Pizza pizza = recupererPizza(code);
		em.remove(pizza);
	}
}
