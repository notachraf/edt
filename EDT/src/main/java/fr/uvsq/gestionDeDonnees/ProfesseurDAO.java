package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Professeur;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAO extends DAO<Professeur> {

    public ProfesseurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean inserer(Professeur obj) {
        return false;
    }

    public boolean insererListe(List<Professeur> obj) {
        return false;
    }

    @Override
    public Professeur selectionner(int id) {
        return null;
    }


    @Override
    public boolean supprimer(Professeur obj) {
        return false;
    }

    @Override
    public Professeur rechercher(int id) {
        return null;
    }

    @Override
    public boolean modifier(Professeur obj) {
        return false;
    }

    @Override
    public Professeur rechercher(String nom) {
        return null;
    }

    /**
     * Récuper le contenu de la table Professeur
     *
     * @return une liste de Professeur
     */
    public ArrayList<Professeur> recupererListe() {
        return null;
    }
}
