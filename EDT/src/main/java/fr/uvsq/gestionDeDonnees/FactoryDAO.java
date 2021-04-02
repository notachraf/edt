package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;

/**
 * Cette permet d'instancier des Classes.
 */
public class FactoryDAO {

    /**
     * Récupére la connection depuis la classe BDConnection.
     */
    public final static Connection sConnection = BDConnection.getConnection();

    /**
     * Cette retourne une instance de la classe CoursDAO
     * @return DAO
     */
    public static DA0 getCoursDAO(){
        return new ModuleDAO(sConnection);
    }
}
