package fr.pizzeria.dao.pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.pizzeria.exception.DAOException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import static fr.pizzeria.dao.config.DAOUtilitaire.initialisationRequetePreparee;

public class PizzaDaoJDBC implements PizzaDao {
	
	private static final String SQL_SELECT_BY_CODE			= "SELECT * FROM pizza WHERE code_pizza = ";
	private static final String SQL_SELECT					= "SELECT * FROM pizza ORDER BY id_pizza";
	private static final String SQL_INSERT					= "INSERT INTO pizza (code_pizza, nom_pizza, prix_pizza, type_pizza) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE					= "UPDATE pizza SET code_pizza=?, nom_pizza=?, prix_pizza=?,type_pizza=? WHERE code_pizza = ?";
	private static final String SQL_DELETE					= "DELETE FROM pizza WHERE code_pizza = ?";
	
	
	
	/**
	 * Constructeur par défaut permettant de l'implémentation du Pattern Abstract Factory
	 */
	public PizzaDaoJDBC() {

		//VIDE
	}

	@FunctionalInterface
	interface IExecuterSqlSt<T> {
		T exec(Statement st) throws SQLException;
	}
	
	@FunctionalInterface
	interface IExecuterSqlConn<T> {
		T exec(Connection conn) throws SQLException;
	}
	
	public <T> T execute(IExecuterSqlSt<T> run ) throws PizzaException {
		
		try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeriaapp", "root", "");
				Statement statement = connexion.createStatement();
				) {
			return run.exec(statement);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoJDBC.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}
	}
	
	public <T> T executePrep(IExecuterSqlConn<T> run ) throws PizzaException {
		
		try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeriaapp", "root", "");
				Statement statement = connexion.createStatement();
				) {
			
			return run.exec(connexion);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoJDBC.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}
	}

	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee( conn, SQL_SELECT_BY_CODE, false, code.toUpperCase());
			ResultSet rs = ps.executeQuery();
			Pizza pizza = null;
			if( rs.next() ) {
				pizza = map(rs);
			}
			return pizza;
		});
	}

	@Override
	public List<Pizza> getListePizza() {
		return execute((Statement st) -> {
			ResultSet rs = st.executeQuery(SQL_SELECT);
			List<Pizza> pizzas = new ArrayList<>();
			while( rs.next() ){
				pizzas.add(map(rs));
			}
			return pizzas;
		});
	}

	@Override
	public Integer ajouter(String code, String nom, Double prix, String type) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee(conn, SQL_INSERT, true,
					code, nom, prix, type);
			int statut = ps.executeUpdate();
			if( statut == 0 ){
				throw new DAOException("Echec de l'ajout de pizza.");
			}
			return statut;
		});
	}

	@Override
	public Integer modifier( String code, String nom, Double prix, String type, String oldCode ) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee(conn, SQL_UPDATE, true, 
					code, nom, prix, type, oldCode);
			int statut = ps.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException("Echec de la modification de la pizza");
			}
			return statut;
		});
	}

	@Override
	public Integer supprimer(String code) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee(conn, SQL_DELETE, false, code.toUpperCase());
			int statut = ps.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException("Echec de la suppression de la pizza");
			}
			return statut;			
		});
	}
	
	private Pizza map( ResultSet rs ) throws SQLException {
		
		return new Pizza(
				rs.getInt("id_pizza"),
				rs.getString("code_pizza"),
				rs.getString("nom_pizza"),
				rs.getDouble("prix_pizza"),
				CategoriePizza.valueOf(rs.getString("type_pizza").toUpperCase()));
	}
}
