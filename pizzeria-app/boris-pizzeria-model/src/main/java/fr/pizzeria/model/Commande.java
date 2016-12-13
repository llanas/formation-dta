package fr.pizzeria.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="commande")
public class Commande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="numero_commande")
	private String numeroCommande;
	@Column(name="statut")
	private Integer statut;
	@Column(name="date_commande")
	private Date dateCommande;
	@ManyToOne
	@JoinColumn(name="livreur_id")
	private Livreur livreur;
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	@ManyToMany
	@JoinTable(name="commande_pizza",
		joinColumns=
			@JoinColumn(name="id_commande", referencedColumnName="id"),
		inverseJoinColumns=
			@JoinColumn(name="id_pizza", referencedColumnName="id")
	)
	private Set<Pizza> pizzas;
	
	public Commande() {
		super();
		pizzas = new HashSet<>();
	}
	
	public Commande(String numero, Client client, Livreur livreur, Date dateCommande, Set<Pizza> pizzas) {

		super();
		this.numeroCommande = numero;
		this.statut = 0;
		this.client = client;
		this.livreur = livreur;
		this.dateCommande = dateCommande;
		this.pizzas = pizzas;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroCommande() {
		return numeroCommande;
	}
	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	public Integer getStatut() {
		return statut;
	}
	public void setStatut(Integer statut) {
		this.statut = statut;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public Livreur getLivreur() {
		return livreur;
	}
	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
}
