package fr.pizzeria.dao.personne.livreur;

import java.util.List;

import fr.pizzeria.exception.LivreurException;
import fr.pizzeria.model.Livreur;

public interface LivreurDao {
	
	Livreur recupererLivreur( Integer id ) throws LivreurException;
	
	List<Livreur> getListLivreur() throws LivreurException;
	
	Integer ajouterLivreur( String prenom, String nom ) throws LivreurException;

	Integer supprimerLivreur( String mail ) throws LivreurException;
	
	Integer modifierLivreur( String prenom, String nom, String mail, String password, String oldMail ) throws LivreurException;

	Livreur connexion(String mail, String password) throws LivreurException;

}
