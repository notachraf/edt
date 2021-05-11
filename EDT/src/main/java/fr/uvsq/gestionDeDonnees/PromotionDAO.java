package fr.uvsq.gestionDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.models.Module;
import fr.uvsq.models.Promotion;
import fr.uvsq.utils.ConnectionUtils;

public class PromotionDAO extends DAO<Promotion>{


    public PromotionDAO(Connection connection) {
		super(connection);
	}
 

	@Override
	public boolean inserer(Promotion promo) {
		
		Connection connection =getConnection();
		PreparedStatement ps =  null; 
        try {
            ps = connection.prepareStatement("INSERT INTO Promotion(promo_nom, promo_nb_eleves, promo_nb_groupes) VALUES (?, ?, ?)", 
            		Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, promo.getNom());
            ps.setInt(2, promo.getNbEleves());
            ps.setInt(3, promo.getNbGroupes());
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
	public boolean insererListe(List<Promotion> obj) {
		return false;
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
		
		Connection connection = getConnection();
		Statement stmt = null;
		ResultSet rs = null; 

		try {
			stmt = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			rs = stmt.executeQuery(""
					+ "							 SELECT p.promo_id, p.promo_nom,  "
					+ "							 p.promo_nb_eleves as promo_nb_eleves, "
					+ "							 p.promo_nb_groupes as promo_nb_groupes, "
					+ "							 m.mod_id as mod_id,  "
					+ "							 m.mod_nom as mod_nom, "
					+ "							 m.mod_nb_td as mod_nb_td, "
					+ "							 m.mod_nb_cours as mod_nb_cours, "
					+ "							 m.mod_nb_tp as mod_nb_tp, "
					+ "							 m.mod_duree_td as mod_duree_td, "
					+ "							 m.mod_duree_tp as mod_duree_tp, "
					+ "							 m.mod_duree_cours as mod_duree_cours "
					+ "							 FROM Promotion p LEFT JOIN Promotion_Module pm on p.promo_id  = pm.promo_id "
					+ "							 LEFT JOIN module m on m.mod_id = pm.mod_id WHERE p.promo_id = " + id);

			return extrairePromotionDeResultSet(rs);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			//ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return null;

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
			//ConnectionUtils.fermerConnection(ps, connection);
		}
        
        return false;
	}

	@Override
	public Promotion rechercher(String nom) {
		
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = null; 
		
		try {
			stmt =  connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			
			rs = stmt.executeQuery(""
					+ "							 SELECT p.promo_id, p.promo_nom,  "
					+ "							 p.promo_nb_eleves as promo_nb_eleves, "
					+ "							 p.promo_nb_groupes as promo_nb_groupes, "
					+ "							 m.mod_id as mod_id,  "
					+ "							 m.mod_nom as mod_nom, "
					+ "							 m.mod_nb_td as mod_nb_td, "
					+ "							 m.mod_nb_cours as mod_nb_cours, "
					+ "							 m.mod_nb_tp as mod_nb_tp, "
					+ "							 m.mod_duree_td as mod_duree_td, "
					+ "							 m.mod_duree_tp as mod_duree_tp, "
					+ "							 m.mod_duree_cours as mod_duree_cours "
					+ "							 FROM Promotion p LEFT JOIN Promotion_Module pm on p.promo_id  = pm.promo_id "
					+ "							 LEFT JOIN module m on m.mod_id = pm.mod_id WHERE p.promo_nom = " + nom);

			return extrairePromotionDeResultSet(rs);

		} catch (SQLException ex) {
				ex.printStackTrace();
		} finally {
			//ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return null;

	}
	
	@Override
	public ArrayList<Promotion> recupererListe() {
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = null; 
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(""
					+ "							 SELECT p.promo_id, p.promo_nom,  "
					+ "							 p.promo_nb_eleves as promo_nb_eleves, "
					+ "							 p.promo_nb_groupes as promo_nb_groupes, "
					+ "							 m.mod_id as mod_id,  "
					+ "							 m.mod_nom as mod_nom, "
					+ "							 m.mod_nb_td as mod_nb_td, "
					+ "							 m.mod_nb_cours as mod_nb_cours, "
					+ "							 m.mod_nb_tp as mod_nb_tp, "
					+ "							 m.mod_duree_td as mod_duree_td, "
					+ "							 m.mod_duree_tp as mod_duree_tp, "
					+ "							 m.mod_duree_cours as mod_duree_cours "
					+ "							 FROM Promotion p LEFT JOIN Promotion_Module pm on p.promo_id  = pm.promo_id "
					+ "							 LEFT JOIN module m on m.mod_id = pm.mod_id ");

			return extrairePromotionsDeResultSet(rs);

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
	private Promotion extrairePromotionDeResultSet(ResultSet rs) throws SQLException {
		Promotion promotion = new Promotion();
		Module module = new Module();
		ArrayList<Module> modules = new ArrayList<Module>();
		
		while (rs.next()) {
				promotion.setId(rs.getInt("promo_id"));
				promotion.setNom(rs.getString("promo_nom"));
				promotion.setNbEleves(rs.getInt("promo_nb_eleves"));
				promotion.setNbGroupes(rs.getInt("promo_nb_groupes"));
				module.setId( rs.getInt("mod_id"));
				module.setNom( rs.getString("mod_nom"));
				module.setNbCM( rs.getInt("mod_nb_cours"));
				module.setNbTD( rs.getInt("mod_nb_td"));
				module.setNbTP( rs.getInt("mod_nb_tp"));
				module.setDureeTD( rs.getInt("mod_duree_td"));
				module.setDureeTP( rs.getInt("mod_duree_tp"));
				module.setDureeCM( rs.getInt("mod_duree_cours"));
				modules.add(module);				
		}
		
		promotion.setListeModules(modules);
		return promotion;
	}

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Promotion> extrairePromotionsDeResultSet(ResultSet rs) throws SQLException {
		
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		Promotion promotion = null;
		Module module = new Module();
		
		while (rs.next()) {
				promotion = new Promotion();
				promotion.setId(rs.getInt("promo_id"));
				promotion.setNom(rs.getString("promo_nom"));
				promotion.setNbEleves(rs.getInt("promo_nb_eleves"));
				promotion.setNbGroupes(rs.getInt("promo_nb_groupes"));
				
				module.setId( rs.getInt("mod_id"));
				module.setNom( rs.getString("mod_nom"));
				module.setNbCM( rs.getInt("mod_nb_cours"));
				module.setNbTD( rs.getInt("mod_nb_td"));
				module.setNbTP( rs.getInt("mod_nb_tp"));
				module.setDureeTD( rs.getInt("mod_duree_td"));
				module.setDureeTP( rs.getInt("mod_duree_tp"));
				module.setDureeCM( rs.getInt("mod_duree_cours")); 
				if (promotions.contains(promotion)) {
					final int index = promotions.indexOf(promotion); 
					promotions.get(index).getListeModules().add(module);
				} else {
					ArrayList<Module>modules = new ArrayList<Module>();
					promotion.setListeModules(modules);
					promotion.getListeModules().add(module);
					promotions.add(promotion);
				}
		}

		return promotions;
	
	}

	
	
	

	
	
	
	

}
