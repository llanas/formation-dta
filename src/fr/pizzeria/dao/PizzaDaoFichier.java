package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFichier implements PizzaDao {

	@Override
	public List<Pizza> getListePizza() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCategorieExist(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int ajouter(String code, String nom, Double prix, String type) throws PizzaException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifier(Integer index, String code, String nom, Double prix, String type) throws PizzaException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifier(Integer index, String nom, Double prix, String type) throws PizzaException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprimer(int index) throws PizzaException {
		// TODO Auto-generated method stub
		return 0;
	}

}
