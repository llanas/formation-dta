package fr.pizzeria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-12-09T15:21:09.707+0100")
@StaticMetamodel(Pizza.class)
public class Pizza_ {
	public static volatile SingularAttribute<Pizza, Integer> id;
	public static volatile SingularAttribute<Pizza, String> code;
	public static volatile SingularAttribute<Pizza, String> nom;
	public static volatile SingularAttribute<Pizza, Double> prix;
	public static volatile SingularAttribute<Pizza, CategoriePizza> type;
}
