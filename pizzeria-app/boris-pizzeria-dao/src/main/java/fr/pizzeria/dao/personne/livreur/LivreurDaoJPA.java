package fr.pizzeria.dao.personne.livreur;

import java.util.List;
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
import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.model.Livreur;

public class LivreurDaoJPA implements LivreurDao{
	
	private EntityManagerFactory emf;
	private MetierDaoPizza metier = new MetierDaoPizza();
	
	public LivreurDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}
	
	@FunctionalInterface
	interface IEntityManager<T> {
		T exec(EntityManager em, EntityTransaction et);
	}
	
	public <T> T execute(IEntityManager<T> run ) throws LivreurException {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			return run.exec(em, et);
		} catch( PersistenceException e ) {
			et.rollback();
			Logger.getLogger(e.getMessage());
			throw new LivreurException(e);
		} finally {
			et.commit();
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Livreur recupererLivreur(Integer id) throws LivreurException {
		return execute((EntityManager em, EntityTransaction et) -> {
			return getLivreurJPA(id, em);
		});
	}

	@Override
	public List<Livreur> getListLivreur() throws LivreurException {
		return null;
	}

	@Override
	public Integer ajouterLivreur(String prenom, String nom, String mail, String password) throws LivreurException {
		return null;
	}

	@Override
	public Integer supprimerLivreur(String mail) throws LivreurException {
		return null;
	}

	@Override
	public Integer modifierLivreur(String prenom, String nom, String mail, String password, String oldMail)
			throws LivreurException {
		return null;
	}

	@Override
	public Livreur connexion(String mail, String password) throws LivreurException {
		return null;
	}

	private Livreur getLivreurJPA(Integer id, EntityManager em) {
		TypedQuery<Livreur> query = em.createQuery("SELECT l FROM Livreur l WHERE l.id='" + id + "'", Livreur.class);
		return query.getSingleResult();
	}
	
}
