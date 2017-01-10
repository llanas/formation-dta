package fr.pizzeria.dao.ingredient;

import java.util.List;

import fr.pizzeria.model.Ingredient;

public interface IngredientDao {

	Ingredient recupererIngredient( Integer id );
	
	List<Ingredient> getListIngredient();
	
	Ingredient ajouter(Ingredient ing);
	
}
