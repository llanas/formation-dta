package fr.pizzeria.dao.personne.livreur;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.dao.MotherDaoJPA;
import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.model.Livreur;

public class LivreurDaoJPA extends MotherDaoJPA implements LivreurDao {
	
	private MetierDaoPizza metier = new MetierDaoPizza();
	
	public LivreurDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}

	@Override
	public Livreur recupererLivreur(Integer id) throws LivreurException {
		return execute((EntityManager em, EntityTransaction et) -> {
			return getLivreurJPA(id, em);
		});
	}

	@Override
	public List<Livreur> getListLivreur() throws LivreurException {
		return new ArrayList<>();
	}

	@Override
	public Integer ajouterLivreur( String prenom, String nom ) throws LivreurException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Livreur livreur = metier.creerLivreur(prenom, nom);
			em.persist(livreur);
			return livreur.getId();
		});
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
		
		return em.find(Livreur.class, id);
	}
	
}
