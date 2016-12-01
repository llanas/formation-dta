package fr.pizzeria.model;

public class Pizza{

	private Integer id;
	private String code, nom;
	private Double prix;
	private CategoriePizza type;
	private static int nbPizzas;
		
	public Pizza( Integer id, String code, String nom, double prix, CategoriePizza type ){
		
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.type = type;
		Pizza.nbPizzas ++;
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

	public void setPrix(double prix) {
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

}
