package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDConnection {
    private static Connection sConnection;
    private static final String URL  = "jdbc:sqlite:src/main/resources/sql/emploi_du_temps.db";
    private static final String USER = null;
    private static final String PASSWORD  = null;

    /**
     * Etablir une connection avec les identifiants:
     * @URL,@USER,@PASSWORD
     * @return Connection
     */
    public static Connection getConnection(){
        if( sConnection != null )
            return sConnection;

        try {
            sConnection = DriverManager.getConnection(URL);
            System.out.println("\n ------------ Connected ------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sConnection;
    }
}  
 