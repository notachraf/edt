package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        ArrayList<Professeur> professeurs = new ArrayList<>();
        Connection connection= getConnection();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            result = stmt.executeQuery("SELECT * FROM Professeur ");

            Professeur professeur =  null;
            while(result.next())
            {
                professeur =  new Professeur();
                professeur.setId( result.getInt("prof_id"));
                professeur.setNom( result.getString("prof_nom"));
                String cours = result.getString("prof_cours");
                ArrayList<Module> listeModule = new ArrayList<>();
                String listeCours[] =  cours.split(",");
                for (String c: listeCours) {
                    ModuleDAO moduleDAO = (ModuleDAO) FactoryDAO.getModuleDAO();
                    Module m = moduleDAO.rechercher(c.trim());
                    if( m != null){
                        listeModule.add(m);
                    }
                }
                professeur.setListeModules( listeModule);
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professeurs;
    }
}
