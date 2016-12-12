package fr.pizzeria.dao.personne.client;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.logging.Logger;

import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.exception.ClientException;
import fr.pizzeria.model.Client;

public class ClientDaoJPA implements ClientDao {
	
	private EntityManagerFactory emf;
	private MetierDaoPizza metier = new MetierDaoPizza();
	
	public ClientDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}
	
	@FunctionalInterface
	interface IEntityManager<T> {
		T exec(EntityManager em, EntityTransaction et);
	}
	
	public <T> T execute(IEntityManager<T> run ) throws ClientException {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			return run.exec(em, et);
		} catch( PersistenceException e ) {
			et.rollback();
			Logger.getLogger(e.getMessage());
			throw new ClientException(e);
		} finally {
			et.commit();
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Client recupererClient(String email) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			return getClientJPA(email, em);
		});
	}

	@Override
	public List<Client> getListClient() throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
			return query.getResultList();
		});
	}

	@Override
	public Integer ajouterClient(String prenom, String nom, String mail, String password) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Client client = metier.creerClient(prenom, nom, mail, password);
			em.persist(client);
			return client.getId();
		});
	}

	@Override
	public Integer supprimerClient(String mail) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			em.remove(getClientJPA(mail, em));
			return 1;
		});
	}

	@Override
	public Integer modifierClient(String prenom, String nom, String mail, String password, String oldMail)
			throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Client client = getClientJPA(oldMail, em);
			client.setPrenom(prenom);
			client.setNom(nom);
			client.setMail(mail);
			client.setPassword(password);
			return client.getId();
		});
	}
	
	@Override
	public Client connexion(String mail, String password) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail ='" + mail + "' AND c.password='" + DigestUtils.md5Hex(password) + "'", Client.class);
			return query.getSingleResult();
		});
	}
	
	private Client getClientJPA(String mail, EntityManager em) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail ='" + mail + "'", Client.class);
		return query.getSingleResult();
	}

	


}