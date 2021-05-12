package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Module;
import fr.uvsq.models.Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.models.Module;
import fr.uvsq.models.Promotion;
import fr.uvsq.utils.ConnectionUtils;

public class PromoDAO extends DAO<Promotion>{


    public PromoDAO(Connection connection) {
        super(connection);
    }
 

	@Override
	public boolean inserer(Promotion promo) {
		String input=null ;
		Connection connection =getConnection();
		PreparedStatement ps =  null; 
        try {
            ps = connection.prepareStatement("INSERT INTO Promotion(promo_nom, promo_nb_eleves, promo_nb_groupes ,promo_cours ,promo_date ) VALUES (?, ?, ?, ?, ?)", 
            		Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, promo.getNom());
            ps.setInt(2, promo.getNbEleves());
            ps.setInt(3, promo.getNbGroupes());
            ps.setString(4,promo.getListeModulesAsString());
            ps.setObject(5, promo.getLocalDate());
            int i = ps.executeUpdate();
	          if(i == 1) {
	        	  try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	                  if (generatedKeys.next()) {
	                	  promo.setId(generatedKeys.getInt(1));
	                  }
	                  	else {
	                  		throw new SQLException("Creation de Promotion a echoue.");
	                  }
	              }
	        	  
	        	  	return true;
	          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }  finally {
			//ConnectionUtils.fermerConnection(ps, connection);
		}

		return false;
	}
	
	@Override
	public boolean insererListe(List<Promotion> promotions) {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        int insertBon = 1; 
        try {
            ps = connection.prepareStatement("INSERT INTO promotion VALUES (NULL, ?, ?, ?, ?, ?)");
            for (Promotion promotion:promotions) {            
            	ps.setString(1, promotion.getNom());
            	ps.setInt(2, promotion.getNbEleves());
            	ps.setInt(3, promotion.getNbGroupes());
            	ps.setString(4,promotion.getListeModulesAsString());
                ps.setObject(5, promotion.getLocalDate());         
            	insertBon = ps.executeUpdate();
            	if (insertBon != 1 ) {
            		return false; 
            	}
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //ConnectionUtils.fermerConnection(ps, connection);
        }
        
        return insertBon == 1; 
    }
	

	@Override
	public Promotion selectionner(int id) {
		return rechercher(id);
	}

	@Override
	public boolean supprimer(Promotion promo) {
	
		Connection connection = getConnection();		
		Statement stmt = null;
		ResultSet rs = null; 
		
		try {
			stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM Promotion WHERE promo_id=" + promo.getId());

			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			//ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return false;
	}

	@Override
	public Promotion rechercher(int id) {
		Promotion promotion = new Promotion();
		Connection connection = getConnection();
		Statement stmt = null;
		ResultSet rs = null; 

		try {
			stmt = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			rs = stmt.executeQuery("SELECT * FROM promotion WHERE promo_id = " + id);

			 while(rs.next())
	            {
	                
	                promotion.setId( rs.getInt("promo_id"));
	                promotion.setNom( rs.getString("promo_nom"));
	                promotion.setNbEleves( rs.getInt("promo_nb_eleves"));
	                promotion.setNbGroupes( rs.getInt("promo_nb_groupes"));
	                String cours = rs.getString("promo_cours");
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
	            }

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			//ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return promotion;

	}

	@Override
	public boolean modifier(Promotion promo) {
		Connection connection = getConnection();
		PreparedStatement ps = null;  
		
        try {
            ps = connection.prepareStatement("UPDATE Promotion SET promo_nom=?, promo_nb_eleves= ? , promo_nb_groupes=? WHERE promo_id = ?");
            ps.setString(1, promo.getNom());
            ps.setInt(2, promo.getNbEleves());
            ps.setInt(3, promo.getNbGroupes());
            ps.setInt(4, promo.getId());
            int i = ps.executeUpdate();

          if(i == 1) {
        	  return true;
          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
		//	ConnectionUtils.fermerConnection(ps, connection);
		}
        
        return false;
	}

	@Override
	public Promotion rechercher(String nom) {
		Promotion promotion =  new Promotion();
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = null; 
		
		try {
			stmt =  connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			
			rs = stmt.executeQuery("SELECT * FROM promotion WHERE promo_nom = " + nom);

			
            while(rs.next())
            {
                
                promotion.setId( rs.getInt("promo_id"));
                promotion.setNom( rs.getString("promo_nom"));
                promotion.setNbEleves( rs.getInt("promo_nb_eleves"));
                promotion.setNbGroupes( rs.getInt("promo_nb_groupes"));
                String cours = rs.getString("promo_cours");
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
            }

		} catch (SQLException ex) {
				ex.printStackTrace();
		} finally {
			//ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return promotion;

	}
	
	@Override
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
                promotion.setLocalDate(result.getDate("promo_date").toLocalDate());
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
