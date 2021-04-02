package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    private static Connection sConnection;

    private final static String URL = "";
    private final static String USER = "";
    private final static String PASSWORD = "";

    /**
     * Etablir une connection avec les identifiants:
     * @URL,@USER,@PASSWORD
     * @return Connection
     */
    public static Connection getConnection(){
        if( sConnection == null ){
            try{
                sConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return sConnection;
    }
}
