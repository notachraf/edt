package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Promotion;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PromoDAO extends DA0<Promotion>{


    public PromoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean inserer(Promotion obj) {
        return false;
    }

    @Override
    public boolean insererListe(List<Promotion> obj) {
        return false;
    }

    @Override
    public Promotion selectionner(int id) {
        return null;
    }


    @Override
    public boolean supprimer(Promotion obj) {
        return false;
    }

    @Override
    public Promotion rechercher(int id) {
        return null;
    }

    @Override
    public boolean modifier(Promotion obj) {
        return false;
    }

    @Override
    public Promotion rechercher(String nom) {
        return null;
    }

    /**
     * Récuper le contenu de la table Groupe
     * @return une liste de Groupes.
     */
    public ArrayList<Promotion> recupererListe() {
        return null;
    }
}
