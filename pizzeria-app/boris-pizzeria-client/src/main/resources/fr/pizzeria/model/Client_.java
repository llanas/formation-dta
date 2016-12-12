package fr.pizzeria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-12-12T15:22:09.918+0100")
@StaticMetamodel(Client.class)
public class Client_ {
	public static volatile SingularAttribute<Client, String> mail;
	public static volatile SingularAttribute<Client, String> password;
	public static volatile SetAttribute<Client, Commande> commandes;
}
