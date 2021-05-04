package fr.uvsq.gestionDeDonnees;
import fr.uvsq.models.Salle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO extends DA0<Salle>{
    public SalleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean inserer(Salle obj) {
        return false;
    }

    @Override
    public boolean insererListe(List<Salle> obj) {
        return false;
    }

    @Override
    public Salle selectionner(int id) {
        return null;
    }

    @Override
    public boolean supprimer(Salle obj) {
        return false;
    }

    @Override
    public Salle rechercher(int id) {
        return null;
    }

    @Override
    public boolean modifier(Salle obj) {
        return false;
    }

    @Override
    public Salle rechercher(String nom) {
        return null;
    }
    
    /**
     * Récuper le contenu de la table Groupe
     * @return une liste de Groupes.
     */
    public ArrayList<Salle> recupererListe() {
        return null;
    }
}
