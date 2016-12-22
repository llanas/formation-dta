package fr.pizzeria.dao.commande;

import java.util.List;

import fr.pizzeria.exception.CommandeException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public interface CommandeDao {
	
	Commande recupererCommande( Integer numeroCommande ) throws CommandeException;

	List<Commande> getListCommande() throws CommandeException;
	
	List<Commande> getListCommandeByPersonne( Client client ) throws CommandeException;
		
	void supprimer(Commande commande) throws CommandeException;
	
	Commande modifier(Commande commande, String nCommande) throws CommandeException;

	Commande ajouter(Commande commande) throws CommandeException;

}
