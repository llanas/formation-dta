package fr.pizzeria.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="livreur")
public class Livreur extends Personne {

	@Column(name="code")
	private String code;
	
	@OneToMany(mappedBy="livreur")
	private Set<Commande> commandes;

	public Livreur() {
		super();
		commandes = new HashSet<>();
	}
	
	public Livreur( String code, String prenom, String nom ) {
		
		this.code = code;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	
}
