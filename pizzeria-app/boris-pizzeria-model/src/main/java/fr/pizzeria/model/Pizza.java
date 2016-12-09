package fr.pizzeria.model;

import javax.persistence.*;

@Entity
public class Pizza{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String nom;
	private Double prix;
	@Enumerated
	private CategoriePizza type;
	private static int nbPizzas;
	
	/**
	 *  Constructeur vide pour JPA persistence */
	public Pizza() {
		//VIDE
	}
	
	public Pizza( String code, String nom, Double prix, CategoriePizza type ){
		
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.type = type;
	}
		
	public Pizza( int id, String code, String nom, Double prix, CategoriePizza type ){
		
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public CategoriePizza getType() {
		return type;
	}

	public void setType(CategoriePizza type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public static int getNbPizzas() {
		return nbPizzas;
	}

	public static void setNbPizzas(int nbPizzas) {
		Pizza.nbPizzas = nbPizzas;
	}
	
	public static String compareCategorie(Pizza pizza) {
		
		return pizza.getType().name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prix == null) {
			if (other.prix != null)
				return false;
		} else if (!prix.equals(other.prix))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	

}
