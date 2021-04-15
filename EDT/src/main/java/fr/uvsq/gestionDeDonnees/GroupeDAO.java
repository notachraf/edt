package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Promo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO extends DA0<Promo>{


    public GroupeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean inserer(Promo obj) {
        return false;
    }

    @Override
    public boolean insererListe(List<Promo> obj) {
        return false;
    }

    @Override
    public Promo selectionner(int id) {
        return null;
    }


    @Override
    public boolean supprimer(Promo obj) {
        return false;
    }

    @Override
    public Promo rechercher(int id) {
        return null;
    }

    @Override
    public boolean modifier(Promo obj) {
        return false;
    }

    @Override
    public Promo rechercher(String nom) {
        return null;
    }

    /**
     * Récuper le contenu de la table Groupe
     * @return une liste de Groupes.
     */
    public ArrayList<Promo> recupererListeGroupe() {
        return null;
    }
}
