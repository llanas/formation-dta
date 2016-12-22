package fr.pizzeria.dao.personne.livreur;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.pizzeria.dao.MotherDaoJPA;
import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.model.Livreur;

public class LivreurDaoJPA extends MotherDaoJPA implements LivreurDao {
	
	public LivreurDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}

	@Override
	public Livreur recupererLivreur( String code ) throws LivreurException {
		return execute((EntityManager em, EntityTransaction et) -> {
			return getLivreurJPA(code, em);
		});
	}

	@Override
	public List<Livreur> getListLivreur() throws LivreurException {
		return new ArrayList<>();
	}

	@Override
	public Livreur ajouterLivreur( Livreur livreur ) throws LivreurException {
		return execute((EntityManager em, EntityTransaction et) -> {
			em.persist(livreur);
			return livreur;
		});
	}

	@Override
	public void supprimerLivreur( String code ) throws LivreurException {
		//TODO
	}

	@Override
	public Livreur modifierLivreur( Livreur livreur, String code )	throws LivreurException {
		return null;
	}

	@Override
	public Livreur connexion(String code, String password) throws LivreurException {
		return null;
	}

	private Livreur getLivreurJPA( String code, EntityManager em) {
		
		return em.find(Livreur.class, code);
	}
	
}
