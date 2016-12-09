package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPA implements PizzaDao {

	private EntityManagerFactory emf;
	private MetierDaoPizza metier;
	
	public PizzaDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-console");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}
	
	@FunctionalInterface
	interface IEntityManager<T> {
		T exec(EntityManager em, EntityTransaction et);
	}
	
	public <T> T execute(IEntityManager<T> run ) throws PizzaException {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			return run.exec(em, et);
		} catch( EntityExistsException e ) {
			et.rollback();
			Logger.getLogger(e.getMessage());
			throw new PizzaException(e);
		} finally {
			et.commit();
			if (em.isOpen()) {
				em.close();
			}
		}
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
	public String ajouter(String code, String nom, Double prix, String type) {
		return execute((EntityManager em, EntityTransaction et) -> {
			Pizza pizza = metier.creerPizza(code, nom, prix, type);
			em.persist(pizza);
			return code;
		});
	}

	@Override
	public String modifier(String code, String nom, Double prix, String type, String oldCode) throws PizzaException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Pizza pizza = getPizzaJPA(code, em);
			pizza.setCode(code);
			pizza.setNom(nom);
			pizza.setPrix(prix);
			pizza.setType(CategoriePizza.valueOf(type.toUpperCase()));
			return code;
		});
	}

	@Override
	public String supprimer(String code) throws PizzaException {
		return execute((EntityManager em, EntityTransaction et) -> {
			em.remove(getPizzaJPA(code, em));
			return code;
		});
	}
	
	public Pizza getPizzaJPA(String code, EntityManager em) throws PizzaException {
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code ='" + code + "'", Pizza.class);
		return query.getSingleResult();
	}
}
