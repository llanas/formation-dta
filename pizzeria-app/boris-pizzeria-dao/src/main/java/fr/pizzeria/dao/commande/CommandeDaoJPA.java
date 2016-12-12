package fr.pizzeria.dao.commande;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.exception.ClientException;
import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public class CommandeDaoJPA implements CommandeDao {
	
	private EntityManagerFactory emf;
	private MetierDaoPizza metier = new MetierDaoPizza();

	public CommandeDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}
	
	@FunctionalInterface
	interface IEntityManager<T> {
		T exec(EntityManager em, EntityTransaction et);
	}
	
	public <T> T execute(IEntityManager<T> run ) throws CommandeException {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			return run.exec(em, et);
		} catch( PersistenceException e ) {
			et.rollback();
			Logger.getLogger(e.getMessage());
			throw new CommandeException(e);
		} finally {
			et.commit();
			if (em.isOpen()) {
				em.close();
			}
		}
	}
	
	@Override
	public Commande recupererCommande(Integer numeroCommande) throws CommandeException {
		return execute((EntityManager em, EntityTransaction et) -> {
			return getCommandeJPA(numeroCommande, em);
		});
	}
	
	@Override
	public List<Commande> getListCommande() throws CommandeException {
		return execute((EntityManager em, EntityTransaction et) -> {
			TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c", Commande.class);
			return query.getResultList();
		});
	}

	@Override
	public List<Commande> getListCommandeByPersonne( Client client ) throws CommandeException {
		return execute((EntityManager em, EntityTransaction et) -> {
			TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.client=" + client.getId(), Commande.class);
			return query.getResultList();
		});
	}

	@Override
	public String ajouter(Client client, Livreur livreur, Date dateCommande, Set<Pizza> pizzas) throws CommandeException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Commande commande = metier.creerCommande(client, livreur, dateCommande, pizzas);
			em.persist(commande);
			return commande.getNumeroCommande();
		});
	}

	@Override
	public Integer supprimer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modifier() {
		// TODO Auto-generated method stub
		return null;
	}


	private Commande getCommandeJPA(Integer numeroCommande, EntityManager em) {
		TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.numero='" + numeroCommande + "'", Commande.class);
		return query.getSingleResult();
	}

}
