package fr.pizzeria.dao.personne.client;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;

import fr.pizzeria.dao.MotherDaoJPA;
import fr.pizzeria.exception.ClientException;
import fr.pizzeria.model.Client;

public class ClientDaoJPA extends MotherDaoJPA implements ClientDao {
	
	public ClientDaoJPA() {
		this.emf = Persistence.createEntityManagerFactory("boris-pizzeria-app");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
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
	public Client ajouterClient(Client client) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			em.persist(client);
			return client;
		});
	}

	@Override
	public void supprimerClient(Client client) throws ClientException {
		execute((EntityManager em, EntityTransaction et) -> {
			em.remove(client);
			return Void.TYPE;
		});
	}

	@Override
	public Client modifierClient(Client client, String oldMail) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			Client oldClient = getClientJPA(oldMail, em);
			oldClient.setPrenom(client.getPrenom());
			oldClient.setNom(client.getNom());
			oldClient.setMail(client.getMail());
			oldClient.setPassword(client.getPassword());
			return oldClient;
		});
	}
	
	@Override
	public Client connexion(String mail, String password) throws ClientException {
		return execute((EntityManager em, EntityTransaction et) -> {
			TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail =:mail AND c.password=:pwd", Client.class);
			query.setParameter("mail", mail);
			query.setParameter("pwd", DigestUtils.md5Hex(password));
			return query.getSingleResult();
		});
	}
	
	private Client getClientJPA(String mail, EntityManager em) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail ='" + mail + "'", Client.class);
		return query.getSingleResult();
	}

	


}
