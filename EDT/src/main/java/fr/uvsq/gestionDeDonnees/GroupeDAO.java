package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Groupe;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO extends DA0<Groupe>{


    public GroupeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean inserer(Groupe obj) {
        return false;
    }

    @Override
    public boolean insererListe(List<Groupe> obj) {
        return false;
    }

    @Override
    public Groupe selectionner(int id) {
        return null;
    }


    @Override
    public boolean supprimer(Groupe obj) {
        return false;
    }

    @Override
    public Groupe rechercher(int id) {
        return null;
    }

    @Override
    public boolean modifier(Groupe obj) {
        return false;
    }

    @Override
    public Groupe rechercher(String nom) {
        return null;
    }

    /**
     * Récuper le contenu de la table Groupe
     * @return une liste de Groupes.
     */
    public ArrayList<Groupe> recupererListeGroupe() {
        return null;
    }
}
