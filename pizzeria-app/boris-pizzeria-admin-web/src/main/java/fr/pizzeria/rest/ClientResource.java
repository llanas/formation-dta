package fr.pizzeria.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pizzeria.metier.ejb.ClientService;
import fr.pizzeria.model.Client;

@Path("/clients")
public class ClientResource {

	private static final String URI_ID 		= "/{id}";
	@EJB private ClientService sClient;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> listerClient() {
		return sClient.getListClient();
	}
	
	@GET
	@Path(URI_ID)
	@Produces(MediaType.APPLICATION_JSON)
	public void getClient(String id) {
		sClient.recupereClient(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void ajouter(Client client) {
		sClient.ajouterClient(client);
	}
	
	@PUT
	@Path(URI_ID)
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifier(String id, Client client) {
		sClient.modifierClient(client, id);
	}
	
	@DELETE
	@Path(URI_ID)
	public void supprimer(@PathParam("id") String id) {
		sClient.supprimerClient(id);
	}
}
