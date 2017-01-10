package fr.pizzeria.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
