package fr.pizzeria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-09T14:26:12.804+0100")
@StaticMetamodel(Ingredient.class)
public class Ingredient_ {
	public static volatile SingularAttribute<Ingredient, Integer> id;
	public static volatile SingularAttribute<Ingredient, String> nom;
	public static volatile SingularAttribute<Ingredient, Double> prix;
	public static volatile SingularAttribute<Ingredient, Integer> quantite;
}
