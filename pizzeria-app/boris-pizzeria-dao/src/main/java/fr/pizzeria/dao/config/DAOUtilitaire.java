package fr.pizzeria.dao.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire {
	
	private DAOUtilitaire() {
		
	}
	
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGenerateKeys, Object... objets) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGenerateKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS  );
		for ( int i = 0; i < objets.length; i++ ) {
			preparedStatement.setObject( i + 1, objets[i]);
		}
		return preparedStatement;
	}

}

  