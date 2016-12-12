package fr.pizzeria.dao.personne.client;

import java.util.List;

import fr.pizzeria.exception.ClientException;
import fr.pizzeria.model.Client;

public interface ClientDao {
	
	Client recupererClient( String email ) throws ClientException;
	
	List<Client> getListClient() throws ClientException;
	
	Integer ajouterClient( String prenom, String nom, String mail, String password ) throws ClientException;

	Integer supprimerClient( String mail ) throws ClientException;
	
	Integer modifierClient( String prenom, String nom, String mail, String password, String oldMail ) throws ClientException;

	Client connexion(String mail, String password) throws ClientException;
}
