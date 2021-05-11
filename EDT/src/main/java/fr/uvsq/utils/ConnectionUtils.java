package fr.uvsq.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe utile pour fermer les connexions.
 */
public class ConnectionUtils {
	
	private ConnectionUtils() {}
	
	/* Fermeture silencieuse du resultset */
	public static void fermerConnection( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse du statement */
	public static void fermerConnection( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse de la connexion */
	public static void fermerConnection( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
	        }
	    }
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermerConnection( Statement statement, Connection connexion ) {
	    fermerConnection( statement );
	    fermerConnection( connexion );
	}

	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermerConnection( ResultSet resultSet, Statement statement, Connection connexion ) {
	    fermerConnection( resultSet );
	    fermerConnection( statement );
	    fermerConnection( connexion );
	}
	

}
