package fr.pizzeria.dao.pizza;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMapper implements RowMapper<Pizza>{

	@Override
	public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pizza p = new Pizza();
		p.setId(rs.getInt("id"));
		p.setCode(rs.getString("code"));
		p.setNom(rs.getString("nom"));
		p.setPrix(rs.getDouble("prix"));
		p.setType(CategoriePizza.valueOf(rs.getString("type")));
		return p;
	}
}
