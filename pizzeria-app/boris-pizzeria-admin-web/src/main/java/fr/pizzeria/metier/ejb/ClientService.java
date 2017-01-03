package fr.pizzeria.metier.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;

import fr.pizzeria.model.Client;

@Stateless
public class ClientService {

	@PersistenceContext
	private EntityManager em;
	
	public Client recupereClient( String id ) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.id =:id", Client.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public List<Client> getListClient() {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		return query.getResultList();
	}
	
	public Client ajouterClient( Client client ) {
		em.persist(client);
		return client;
	}
	
	public void supprimerClient( String id ) {
		Client client = recupereClient(id);
		em.persist(client);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Client modifierClient( Client client, String id ) {
		Client oldClient = recupereClient(id);
		oldClient.setPrenom(client.getPrenom());
		oldClient.setNom(client.getNom());
		oldClient.setMail(client.getMail());
		oldClient.setPassword(client.getPassword());
		return oldClient;
	}
	
	public Client connexion( String mail, String password ) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail =:mail AND c.password=:pwd", Client.class);
		query.setParameter("mail", mail);
		query.setParameter("pwd", DigestUtils.md5Hex(password));
		return query.getSingleResult();
	}
	
}
