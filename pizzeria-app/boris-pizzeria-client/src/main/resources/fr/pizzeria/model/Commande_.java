package fr.pizzeria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-12-12T15:22:09.955+0100")
@StaticMetamodel(Commande.class)
public class Commande_ {
	public static volatile SingularAttribute<Commande, Integer> id;
	public static volatile SingularAttribute<Commande, Integer> numeroCommande;
	public static volatile SingularAttribute<Commande, String> statut;
	public static volatile SingularAttribute<Commande, Date> dateCommande;
	public static volatile SingularAttribute<Commande, Livreur> livreur;
	public static volatile SingularAttribute<Commande, Client> client;
	public static volatile SetAttribute<Commande, Pizza> pizzas;
}
