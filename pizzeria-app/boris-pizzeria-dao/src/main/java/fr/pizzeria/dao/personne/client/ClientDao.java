package fr.pizzeria.dao.personne.client;

import java.util.List;

import fr.pizzeria.exception.ClientException;
import fr.pizzeria.model.Client;

public interface ClientDao {
	
	Client recupererClient( String email ) throws ClientException;
	
	List<Client> getListClient() throws ClientException;
	
	Client ajouterClient( Client client ) throws ClientException;

	void supprimerClient( Client client ) throws ClientException;
	
	Client modifierClient( Client client, String oldMail ) throws ClientException;

	Client connexion(String mail, String password) throws ClientException;
}
