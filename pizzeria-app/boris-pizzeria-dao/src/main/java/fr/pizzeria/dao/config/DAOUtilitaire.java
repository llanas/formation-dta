package fr.pizzeria.dao.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire {
	
	private DAOUtilitaire() {
		
	}
	
	public static void fermetureSilencieuse( ResultSet resultSet ) {
		if( resultSet != null ) {
			try {
				resultSet.close();
			} catch ( SQLException e ) {
				System.out.println( "Echec de la fermeture du resultSet : " + e.getMessage() );
			}
		}
	}
	
	public static void fermetureSilencieuse( Statement statement ) {
		if( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				System.out.println( "Echec de la fermeture du statement : " + e.getMessage() );
			}
		}
	}
	
	public static void fermetureSilencieuse( Connection connexion ) {
		if( connexion != null ) {
			try {
				connexion.close();
			} catch ( SQLException e ) {
				System.out.println( "Echec de la fermeture de la connexion : " + e.getMessage() );
			}
		}
	}
	
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}
	
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGenerateKeys, Object... objets) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGenerateKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS  );
		for ( int i = 0; i < objets.length; i++ ) {
			preparedStatement.setObject( i + 1, objets[i]);
		}
		return preparedStatement;
	}

}

  