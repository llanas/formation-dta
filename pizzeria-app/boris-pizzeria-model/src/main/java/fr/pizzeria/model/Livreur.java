package fr.pizzeria.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="livreur")
public class Livreur extends Personne {

	@OneToMany(mappedBy="livreur")
	private Set<Commande> commandes;

	public Livreur() {
		super();
		commandes = new HashSet<Commande>();
	}
	
	
}
