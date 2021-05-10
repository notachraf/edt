package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Module;
import fr.uvsq.models.Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromoDAO extends DAO<Promotion>{


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
        ArrayList<Promotion> promotions = new ArrayList<>();
        Connection connection= getConnection();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            result = stmt.executeQuery("SELECT * FROM Promotion ");

            Promotion promotion =  null;
            while(result.next())
            {
                promotion =  new Promotion();
                promotion.setId( result.getInt("promo_id"));
                promotion.setNom( result.getString("promo_nom"));
                promotion.setNbEleves( result.getInt("promo_nb_eleves"));
                promotion.setNbGroupes( result.getInt("promo_nb_groupes"));
                String cours = result.getString("promo_cours");
                ArrayList<Module> listeModule = new ArrayList<>();
                String listeCours[] =  cours.split(",");
                for (String c: listeCours) {
                    ModuleDAO moduleDAO = (ModuleDAO) FactoryDAO.getModuleDAO();
                    Module m = moduleDAO.rechercher(c.trim());
                    if( m != null){
                        listeModule.add(m);
                    }
                }
                promotion.setListeModules( listeModule);
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promotions;
    }
}
