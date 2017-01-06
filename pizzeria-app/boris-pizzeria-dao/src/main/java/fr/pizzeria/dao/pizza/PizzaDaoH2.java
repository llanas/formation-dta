package fr.pizzeria.dao.pizza;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoH2 implements PizzaDao {

	private JdbcTemplate template;
	
	private static final String SQL_SELECT_BY_CODE			= "SELECT * FROM pizza WHERE code_pizza = ";
	private static final String SQL_SELECT					= "SELECT * FROM pizza ORDER BY id_pizza";
	private static final String SQL_INSERT					= "INSERT INTO pizza (code_pizza, nom_pizza, prix_pizza, type_pizza) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE					= "UPDATE pizza SET code_pizza=?, nom_pizza=?, prix_pizza=?,type_pizza=? WHERE code_pizza = ?";
	private static final String SQL_DELETE					= "DELETE FROM pizza WHERE code_pizza = ?";
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init(){
		this.template = new JdbcTemplate(this.dataSource);
	}
	
	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		return this.template.queryForObject(SQL_SELECT_BY_CODE, new PizzaMapper(), code);
	}

	@Override
	public List<Pizza> getListePizza() {
		return this.template.query(SQL_SELECT, new PizzaMapper());
	}

	@Override
	public Pizza ajouter(Pizza pizza) throws PizzaException {
		this.template.update(SQL_INSERT, pizza.getCode(), pizza.getNom(), pizza.getPrix(), pizza.getType());
		return pizza;
	}

	@Override
	public Pizza modifier(Pizza pizza, String oldCode) throws PizzaException {
		this.template.update(SQL_UPDATE, pizza.getCode(), pizza.getNom(), pizza.getPrix(), pizza.getType(), oldCode);
		return pizza;
	}

	@Override
	public void supprimer(Pizza pizza) throws PizzaException {
		this.template.update(SQL_DELETE, pizza.getCode());
	}
}
