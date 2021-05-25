package fr.uvsq.gestionDeDonnees;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.models.Salle;
import fr.uvsq.models.TypeSalle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO extends DAO<Salle> {

    public SalleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean inserer(Salle salle) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO salle VALUES (NULL, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, salle.getNom());
            ps.setInt(2, salle.getCapacite());
            ps.setString(3, salle.getTypeSalle().name());
            int i = ps.executeUpdate();

            if (i == 1) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        salle.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creation de la salle a echoue.");
                    }
                }

                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
        @Override
        public boolean insererListe (List < Salle > liste) {

            Connection connection = getConnection();
            PreparedStatement ps = null;
            int i = 1;
            try {
                ps = connection.prepareStatement("INSERT INTO salle VALUES (NULL, ?, ?, ?)");

                for (Salle salle : liste) {
                    ps.setString(1, salle.getNom());
                    ps.setInt(2, salle.getCapacite());
                    ps.setString(3, salle.getTypeSalle().name());
                    i = ps.executeUpdate();
                    if (i != 1) {
                        return false;
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return true;
        }

    // methode redondante
    @Override
    public Salle selectionner ( int id){
        return rechercher(id);
    }

    @Override
    public boolean supprimer (Salle obj){

        Connection connection = getConnection();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Salle WHERE salle_id=" + obj.getId());
            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public Salle rechercher(int id) {
        
         Salle salle = null; 
         ResultSet result = null; 
         Connection connection = this.getConnection();
         Statement stmt = null; 
        try {
            stmt = connection.createStatement();
            
            result = stmt.executeQuery(
                                                "SELECT * FROM salle WHERE salle_id = " + id
                                             );
            if(result.first()) {
            		salle = extractSalleFromResultSet(result);
             }
                     } catch (SQLException e) {
                e.printStackTrace();
            }
            return salle;

        }

        @Override
        public boolean modifier (Salle salle){
            Connection connection = this.getConnection();
            PreparedStatement ps = null;

            try {
                ps = connection.prepareStatement("UPDATE Salle SET salle_nom=?, salle_capacite=?, salle_type=? WHERE salle_id=?");
                ps.setString(1, salle.getNom());
                ps.setInt(2, salle.getCapacite());
                ps.setString(3, salle.getTypeSalle().name());
                ps.setInt(4, salle.getId());
                int i = ps.executeUpdate();

                if (i == 1) {
                    return true;
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }

        @Override
        public Salle rechercher (String nom){
            Salle salle = new Salle();

            Connection connection = this.getConnection();
            Statement stmt = null;
            ResultSet result = null;

            try {
                stmt = connection
                        .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE
                        );

                result = stmt.executeQuery(
                        "SELECT * FROM salle WHERE salle_nom = " + nom
                );
                if (result.first()) {
                    salle.setId(result.getInt("salle_id"));
                    salle.setNom(result.getString("salle_nom"));
                    salle.setCapacite(result.getInt("salle_capacite"));
                    salle.setTypeSalle(TypeSalle.valueOf(result.getString("salle_type")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //ConnectionUtils.fermerConnection(result, stmt, connection);
            }
            return salle;
        }

    /**
     * Récuper le contenu de la table Groupe
     * @return une liste de Groupes.
     */
    @Override
    public ArrayList<Salle> recupererListe () {

        Connection connection = this.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM salle ");

            ArrayList<Salle> salles = new ArrayList<Salle>();

            while (rs.next()) {
                Salle salle = new Salle();
                salle.setId(rs.getInt("salle_id"));
                salle.setNom(rs.getString("salle_nom"));
                salle.setCapacite(rs.getInt("salle_capacite"));
                salle.setTypeSalle(TypeSalle.valueOf(rs.getString("salle_type")));
                salles.add(salle);
            }

            return salles;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //ConnectionUtils.fermerConnection(rs, stmt, connection);
        }
        return null;
    }
    
    /**
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Salle extractSalleFromResultSet(ResultSet rs) throws SQLException {
    	
    	Salle salle = new Salle();
    	salle.setId( rs.getInt("salle_id") );
    	salle.setNom( rs.getString("salle_nom") );
    	salle.setCapacite( rs.getInt("salle_capacite") );
    	salle.setTypeSalle(TypeSalle.valueOf(rs.getString("salle_type")));

        return salle;
    	
    }
}

