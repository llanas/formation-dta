package fr.pizzeria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-12-12T15:22:09.970+0100")
@StaticMetamodel(Pizza.class)
public class Pizza_ {
	public static volatile SingularAttribute<Pizza, Integer> id;
	public static volatile SingularAttribute<Pizza, String> code;
	public static volatile SingularAttribute<Pizza, String> nom;
	public static volatile SingularAttribute<Pizza, Double> prix;
	public static volatile SingularAttribute<Pizza, CategoriePizza> type;
}
