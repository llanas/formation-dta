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

import fr.pizzeria.dao.DaoFactoryBDD;
import fr.pizzeria.dao.MetierDaoPizza;
import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.DAOException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;
import static fr.pizzeria.dao.config.DAOUtilitaire.initialisationRequetePreparee;

public class PizzaDaoFactoryBDD implements PizzaDao {
	
	private static final String SQL_SELECT_BY_CODE			= "SELECT * FROM pizza WHERE code_pizza = ";
	private static final String SQL_SELECT					= "SELECT * FROM pizza ORDER BY id_pizza";
	private static final String SQL_INSERT					= "INSERT INTO pizza (code_pizza, nom_pizza, prix_pizza, type_pizza) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE					= "UPDATE pizza SET code_pizza=?, nom_pizza=?, prix_pizza=?,type_pizza=? WHERE code_pizza = ?";
	private static final String SQL_DELETE					= "DELETE FROM pizza WHERE code_pizza = ?";
	
	private DaoFactoryBDD daoFactory;
	private MetierDaoPizza metier;
	
	public PizzaDaoFactoryBDD() {

	
	}
	
	PizzaDaoFactoryBDD(DaoFactoryBDD daoFactory){

		this.daoFactory = daoFactory;
	}
	
	interface IExecuterSqlSt<T> {
		T exec(Statement st) throws SQLException;
	}
	
	interface IExecuterSqlConn<T> {
		T exec(Connection conn) throws SQLException;
	}
	
	public <T> T execute(IExecuterSqlSt<T> run ) throws PizzaException {
		
		try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria-app", "root", "");
				Statement statement = connexion.createStatement();
				) {
			
			return run.exec(statement);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoFactoryBDD.class.getName()).severe(e.getMessage());
			throw new PizzaException();
		}
	}
	
	public <T> T executePrep(IExecuterSqlConn<T> run ) throws PizzaException {
		
		try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria-app", "root", "");
				Statement statement = connexion.createStatement();
				) {
			
			return run.exec(connexion);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoFactoryBDD.class.getName()).severe(e.getMessage());
			throw new PizzaException();
		}
	}

	@Override
	public Pizza recupererPizza(String code) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee( conn, SQL_SELECT_BY_CODE, false, code);
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
			List<Pizza> pizzas = new ArrayList<Pizza>();
			while( rs.next() ){
				pizzas.add(map(rs));
			}
			return pizzas;
		});
	}

	@Override
	public String ajouter(String code, String nom, Double prix, String type) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee(conn, SQL_INSERT, true,
					code, nom, prix, type);
			int statut = ps.executeUpdate();
			if( statut == 0 ){
				throw new DAOException("Echec de l'ajout de pizza.");
			}
			return code;
		});
	}

	@Override
	public String modifier(String code, String nom, Double prix, String type) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee(conn, SQL_UPDATE, true, 
					code, nom, prix, type);
			int statut = ps.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException("Echec de la modification de pizza");
			}
			return code;
		});
	}

	@Override
	public String supprimer(String code) throws PizzaException {
		return executePrep((Connection conn) -> {
			PreparedStatement ps = initialisationRequetePreparee(conn, SQL_DELETE, true, code);
			int statut = ps.executeUpdate();
			if( statut == 0 ) {
				throw new DAOException("Echec de la suppression de la pizza");
			}
			return code;			
		});
	}

	// A CHANGER
	
	@Override
	public String isCodeExist(String code) throws CodeException {
			if( getListePizza().stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
				throw new CodeException("Le code " + code + " existe d�j�");
			} else {
				return code;
			}
	}
	
	private Pizza map( ResultSet rs ) throws SQLException {
		
		return metier.creerPizza(
				rs.getString("code_pizza"),
				rs.getString("nom_pizza"),
				rs.getDouble("columnIndex"),
				rs.getString("type_pizza"));
	}

}
