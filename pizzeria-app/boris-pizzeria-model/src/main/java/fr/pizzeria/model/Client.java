package fr.pizzeria.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name="client")
public class Client extends Personne {
	
	@Column(name="mail")
	private String mail;
	@Column(name="password")
	private String password;
	@OneToMany(mappedBy="client")
	private Set<Commande> commandes;
	@Transient
	private boolean connected;
	
	public Client() {
		super();
		commandes = new HashSet<>();
	}
	
	public Client(String prenom, String nom, String mail, String password) {
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.password = DigestUtils.md5Hex(password);
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}
}
