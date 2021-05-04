package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public abstract class DAO<T> {

    protected Connection mConnection = null;

    public DAO(Connection connection){
        mConnection = connection;
    }

    /**
     * Insérer un objet dans la Table T
     * @param obj
     * @return boolean
     */
    public abstract boolean inserer(T obj);

    /**
     * Insérer une liste d' objet dans la Table T
     * @param obj
     * @return boolean
     */
    public abstract boolean insererListe(List<T> obj);

    /**
     * Recupere une entrée dans la table T
     * @param id
     * @return T
     */
    public abstract T selectionner(int id);

    /**
     * Supprime une entrée dans T
     * @param obj
     * @return
     */
    public abstract boolean supprimer(T obj);

    /**
     * Cherche une entrée qui a pour identifiant id
     * dans la table T
     * @param id
     * @return
     */
    public abstract T rechercher(int id);

    /**
     * Modifie une entrée de la table T.
     * @param obj
     * @return
     */
    public abstract boolean modifier(T obj);

    /**
     * Cherche une entrée avec l'attribut nom
     * dans la table T
     * @param nom
     * @return
     */
    public abstract T rechercher(String nom);
    
    /**
     * Récuper le contenu de la table Salle
     * @return une liste de Salle
     */
    public abstract ArrayList<T> recupererListe();
}
