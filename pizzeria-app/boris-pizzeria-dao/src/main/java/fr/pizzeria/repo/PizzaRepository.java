package fr.pizzeria.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, String> {

}
