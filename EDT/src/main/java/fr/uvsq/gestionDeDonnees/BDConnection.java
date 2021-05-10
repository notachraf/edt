package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    private static Connection sConnection;
    private static final String URL  = "jdbc:mysql://localhost:3306/emploi_du_temps";
    private static final String USER = "root";
    private static final String PASSWORD  = "123456789";

    /**
     * 
     * 
     * Etablir une connection avec les identifiants:
     * @URL,@USER,@PASSWORD
     * @return Connection
     */
    public static Connection getConnection(){
        if( sConnection != null )
            return sConnection;

        try {
            sConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("\n ------------ Connected ------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sConnection;
    }
 
}
