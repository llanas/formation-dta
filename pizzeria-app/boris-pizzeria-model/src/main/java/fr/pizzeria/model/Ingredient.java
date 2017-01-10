package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nom")
	private String nom;
	@Column(name="prix")
	private Double prix;
	@Column(name="quantite")
	private Integer quantite;
	
	
	/**
	 * Constructeur vide pour JPA persistence
	 */
	public Ingredient() {
		super();
	}

	public Ingredient(Integer id, String nom, Double prix, Integer quantite) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	
	
}
