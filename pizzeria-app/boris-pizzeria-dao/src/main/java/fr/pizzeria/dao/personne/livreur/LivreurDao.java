package fr.pizzeria.dao.personne.livreur;

import java.util.List;

import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.model.Livreur;

public interface LivreurDao {
	
	Livreur recupererLivreur( String code ) throws LivreurException;
	
	List<Livreur> getListLivreur() throws LivreurException;
	
	Livreur ajouterLivreur( Livreur livreur ) throws LivreurException;

	void supprimerLivreur( String code ) throws LivreurException;
	
	Livreur modifierLivreur( Livreur livreur, String code ) throws LivreurException;

	Livreur connexion(String code, String password) throws LivreurException;

}
