package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;

import fr.uvsq.gestionDeDonnees.BDConnection;
import fr.uvsq.gestionDeDonnees.DAO;

/**
 * Cette permet d'instancier des Classes.
 */
public class FactoryDAO {
	/**
	 * Récupére la connection depuis la classe BDConnection.
	 */
	public final static Connection sConnection = BDConnection.getConnection();

    /**
     * Cette retourne une instance de la classe ModuleDAO
     * @return DAO
     */
    public static DAO getModuleDAO(){
        return new ModuleDAO(sConnection);
    }
    
    /**
     * Cette retourne une instance de la classe SalleDAO
     * @return DAO
     */    
    public static DAO getSalleDAO(){
        return new SalleDAO(sConnection);
    }
	/**
	 * Cette retourne une instance de la classe ProfDAO
	 * @return DAO
	 */
	public static DAO getProfesseurDAO() {
		return new ProfesseurDAO(sConnection);
	}

	/**
	 * Cette retourne une instance de la classe PromoDAO
	 * @return DAO
	 */
	public static DAO getPromotionDAO() {
		return new PromoDAO(sConnection);
	}
}
