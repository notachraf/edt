package fr.uvsq.gestionDeDonnees;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {

    private Connection mConnection = null;

    public DAO(Connection connection){
        mConnection = connection;
    }

    /**
     * InsÃ©rer un objet dans la Table T
     * @param obj
     * @return boolean
     */
    public abstract boolean inserer(T obj);

    /**
     * InsÃ©rer une liste d' objet dans la Table T
     * @param obj
     * @return boolean
     */
    public abstract boolean insererListe(List<T> obj);

    /**
     * Recupere une entrÃ©e dans la table T
     * @param id
     * @return T
     */
    public abstract T selectionner(int id);

    /**
     * Supprime une entrÃ©e dans T
     * @param obj
     * @return
     */
    public abstract boolean supprimer(T obj);

    /**
     * Cherche une entrÃ©e qui a pour identifiant id
     * dans la table T
     * @param id
     * @return
     */
    public abstract T rechercher(int id);

    /**
     * Modifie une entrÃ©e de la table T.
     * @param obj
     * @return
     */
    public abstract boolean modifier(T obj);

    /**
     * Cherche une entrÃ©e avec l'attribut nom
     * dans la table T
     * @param nom
     * @return
     */
    public abstract T rechercher(String nom);
    
    
    /**
     * 
     * @return
     */
    public abstract ArrayList<T> recupererListe();
    
    
    /**
     * retourne une connection
     * 
     * @return
     */
	protected Connection getConnection() {
		try {
			if (mConnection == null || mConnection.isClosed()) {
				mConnection = BDConnection.getConnection(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mConnection;
	}
	
}
