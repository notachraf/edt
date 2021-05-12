package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    private static Connection sConnection;
    
    
    private static final String PROPERTY_URL             = "jdbc:mysql://localhost:3306/emploi_du_temps";
    //private static final String PROPERTY_DRIVER          = "com.mysql.jdbc.Driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "root";
    private static final String PROPERTY_MOT_DE_PASSE    = "";

    /**
     * 
     * 
     * Etablir une connection avec les identifiants:
     * @URL,@USER,@PASSWORD
     * @return Connection
     */
    public static Connection getConnection(){
    		try {
    			
    			sConnection = DriverManager.getConnection(PROPERTY_URL,
    					PROPERTY_NOM_UTILISATEUR,
    					PROPERTY_MOT_DE_PASSE);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        return sConnection;
    }
}  
 