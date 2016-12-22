package fr.pizzeria.dao.commande;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.dao.MotherDaoJPA;
import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class CommandeDaoJPA extends MotherDaoJPA implements CommandeDao {
	
	public CommandeDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
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
			TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.client='" + client.getId() + "' AND c.statut=0", Commande.class);
			return query.getResultList();
		});
	}

	@Override
	public Commande ajouter(Commande commande) throws CommandeException {
		return execute((EntityManager em, EntityTransaction et) -> {
			em.persist(commande);
			return commande;
		});
	}

	@Override
	public void supprimer(Commande commande) {
		// TODO Auto-generated method stub
	}

	@Override
	public Commande modifier(Commande commande, String nCommande) {
		// TODO Auto-generated method stub
		return null;
	}


	private Commande getCommandeJPA(Integer numeroCommande, EntityManager em) {
		TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.numero='" + numeroCommande + "'", Commande.class);
		return query.getSingleResult();
	}

}
