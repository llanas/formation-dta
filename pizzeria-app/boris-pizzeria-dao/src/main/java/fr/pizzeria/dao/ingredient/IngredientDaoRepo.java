package fr.pizzeria.dao.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.repo.IngredientRepository;

@Component
@Qualifier("JPASpring")
public class IngredientDaoRepo implements IngredientDao {

	@Autowired
	IngredientRepository ingRep;
	
	@Override
	public Ingredient recupererIngredient(Integer id) {
		return ingRep.findOne(id);
	}

	@Override
	public List<Ingredient> getListIngredient() {
		return ingRep.findAll();
	}

	@Override
	public Ingredient ajouter(Ingredient ing) {
		return ingRep.save(ing);
	}

	
}
