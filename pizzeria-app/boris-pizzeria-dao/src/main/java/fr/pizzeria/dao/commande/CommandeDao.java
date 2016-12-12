package fr.pizzeria.dao.commande;

import java.util.Date;
import java.util.List;
import java.util.Set;

import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public interface CommandeDao {
	
	Commande recupererCommande( Integer numeroCommande ) throws CommandeException;

	List<Commande> getListCommande() throws CommandeException;
	
	List<Commande> getListCommandeByPersonne( Client client ) throws CommandeException;
	
	String ajouter(Client client, Livreur livreur, Date dateCommande, Set<Pizza> pizzas) throws CommandeException;
	
	Integer supprimer() throws CommandeException;
	
	Integer modifier() throws CommandeException;

}
