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
import fr.uvsq.models.Professeur;
import fr.uvsq.utils.ConnectionUtils;

public class ProfesseurDAO extends DAO<Professeur>  {

	public ProfesseurDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean inserer(Professeur prof) {
		 
		boolean professeurInsere = insererProfesseur(prof);
		if (!professeurInsere) return false; 
		
		boolean result = true;
		if (!prof.getListeModules().isEmpty()) {
			result = insererAssociationProfesseurModule(prof);
		}
		
		return result; 
	}
	
	
	@Override
	public boolean insererListe(List<Professeur> obj) {
		return false;
	}

	@Override
	public Professeur selectionner(int id) {
		return rechercher(id);
	}

	@Override
	public boolean supprimer(Professeur prof) {
	
		boolean moduleSupprimes = supprimerModuleDuProfesseur(prof); //c'est deja fait par la base grace CASCADE. 
		if (!moduleSupprimes) {
			return false;
		}
		
		Connection connection = getConnection();
		Statement stmt = null;
		ResultSet rs = null; 
		try {
			stmt = connection.createStatement();
				int i = stmt.executeUpdate("DELETE FROM Professeur WHERE prof_id=" + prof.getId());
				if (i ==1)
					return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return false;
	}

	@Override
	public Professeur rechercher(int id) {
		
		Connection connection = getConnection();
		Statement stmt = null;
		ResultSet rs = null; 

		try {
			stmt = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			
			rs = stmt.executeQuery(					
					  "			SELECT p.prof_id, p.prof_nom as prof_nom,  "
					+ "							 m.mod_id as mod_id,  "
					+ "							 m.mod_nom as mod_nom, "
					+ "							 m.mod_nb_td as mod_nb_td, "
					+ "							 m.mod_nb_cours as mod_nb_cours, "
					+ "							 m.mod_nb_tp as mod_nb_tp, "
					+ "							 m.mod_duree_td as mod_duree_td, "
					+ "							 m.mod_duree_tp as mod_duree_tp, "
					+ "							 m.mod_duree_cours as mod_duree_cours "
					+ "							 FROM professeur p "
					+ "							 LEFT OUTER JOIN Professeur_Module pf ON p.prof_id = pf.prof_id"
					+ "							 LEFT OUTER JOIN Module m ON m.mod_id = pf.mod_id "
					+ "							 WHERE p.prof_id =  "+ id);

			return extraireProfesseurDeResultSet(rs);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return null;

	}

	@Override
	public boolean modifier(Professeur prof) {
		
		if (modifierJusteProfesseur(prof)) {
			return updateAssociationProfesseurModule(prof);
		}
        return false;
	}

	
	private boolean modifierJusteProfesseur(Professeur prof) {
		
		Connection connection = getConnection();
		PreparedStatement ps = null;  
		
        try {
            ps = connection.prepareStatement("UPDATE Professeur SET prof_nom=? WHERE prof_id = ? ");
            ps.setString(1, prof.getNom());
            ps.setInt(2, prof.getId());
            int i = ps.executeUpdate();

          if(i == 1) {
        	  return true; 
          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
			ConnectionUtils.fermerConnection(ps, connection);
		}
        
        return false;
    
	}
	
	@Override
	public Professeur rechercher(String nom) {
		
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = null; 
		
		try {
			stmt =  connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			
			rs = stmt.executeQuery("SELECT p.prof_id, p.prof_nom as prof_nom, " 
							+ "m.mod_id as mod_id, " 
							+ "m.mod_nom as mod_nom, "
							+ "m.mod_nb_td as mod_nb_td, " 
							+ "m.mod_nb_cours as mod_nb_cours, "
							+ "m.mod_nb_tp as mod_nb_tp, "
							+ "m.mod_duree_td as mod_duree_td, "
							+ "m.mod_duree_tp as mod_duree_tp, " 
							+ "m.mod_duree_cours as mod_duree_cours "
							+ "							 FROM professeur p "
							+ "							 LEFT OUTER JOIN Professeur_Module pf ON p.prof_id = pf.prof_id"
							+ "							 LEFT OUTER JOIN Module m ON m.mod_id = pf.mod_id "
							+ "							 WHERE p.prof_nom = "+nom);

			return extraireProfesseurDeResultSet(rs);

		} catch (SQLException ex) {
				ex.printStackTrace();
		} finally {
			ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return null;

	}

	@Override
	public ArrayList<Professeur> recupererListe() {
		
		ArrayList<Professeur> professeurs = new ArrayList<Professeur>();

		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = null; 
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT p.prof_id, p.prof_nom as prof_nom, " 
					+ "m.mod_id as mod_id, " 
					+ "m.mod_nom as mod_nom, "
					+ "m.mod_nb_td as mod_nb_td, " 
					+ "m.mod_nb_cours as mod_nb_cours, "
					+ "m.mod_nb_tp as mod_nb_tp, "
					+ "m.mod_duree_td as mod_duree_td, "
					+ "m.mod_duree_tp as mod_duree_tp, " 
					+ "m.mod_duree_cours as mod_duree_cours "
					+ "							 FROM professeur p "
					+ "							 LEFT OUTER JOIN Professeur_Module pf ON p.prof_id = pf.prof_id"
					+ "							 LEFT OUTER JOIN Module m ON m.mod_id = pf.mod_id ");

			
			return extraireProfesseursDeResultSet(rs);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.fermerConnection(rs, stmt, connection);
		}

		return professeurs;
	}


	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Professeur extraireProfesseurDeResultSet(ResultSet rs) throws SQLException {
		
		Professeur professeur = new Professeur();
		Module module = new Module();
		ArrayList<Module> modules = new ArrayList<Module>();
		
		while (rs.next()) {
				professeur.setId(rs.getInt("prof_id"));
				professeur.setNom(rs.getString("prof_nom"));
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
		
		professeur.setListeModules(modules);
		return professeur;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Professeur> extraireProfesseursDeResultSet(ResultSet rs) throws SQLException {
		
		ArrayList<Professeur> professeurs = new ArrayList<Professeur>();
		Professeur professeur = null;
		Module module =new Module();
		
		while (rs.next()) {
				professeur = new Professeur();
				professeur.setId(rs.getInt("prof_id"));
				professeur.setNom(rs.getString("prof_nom"));
				
				module.setId( rs.getInt("mod_id"));
				module.setNom( rs.getString("mod_nom"));
				module.setNbCM( rs.getInt("mod_nb_cours"));
				module.setNbTD( rs.getInt("mod_nb_td"));
				module.setNbTP( rs.getInt("mod_nb_tp"));
				module.setDureeTD( rs.getInt("mod_duree_td"));
				module.setDureeTP( rs.getInt("mod_duree_tp"));
				module.setDureeCM( rs.getInt("mod_duree_cours")); 
				if (professeurs.contains(professeur)) {
					final int index = professeurs.indexOf(professeur); 
					professeurs.get(index).getListeModules().add(module);
				} else {
					ArrayList<Module>modules = new ArrayList<Module>();
					professeur.setListeModules(modules);
					professeur.getListeModules().add(module);
					professeurs.add(professeur);
				}
		}

		return professeurs;
	}

	/**
	 * on supprime les modules associés au professeur
	 * 
	 * @param prof
	 * @return
	 */
	public boolean supprimerModuleDuProfesseur(Professeur prof) {
		Statement stmt = null;
		Connection connection =getConnection(); 
		try {
			stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM Professeur_Module WHERE prof_id=" + prof.getId());

			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.fermerConnection(stmt, connection);
		}

		return false;
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return
	 */
	private boolean insererProfesseur(Professeur prof) {
		
		Connection connection =getConnection();
		PreparedStatement ps =  null; 
        try {
            ps = connection.prepareStatement("INSERT INTO Professeur(prof_id, prof_nom) VALUES (NULL, ?)", 
            		Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prof.getNom());
            int i = ps.executeUpdate();
	          if(i == 1) {
	        	  
	        	  try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	                  if (generatedKeys.next()) {
	                	  prof.setId(generatedKeys.getInt(1));
	                  }
	                  	else {
	                  		throw new SQLException("Creation de Professeur a echoue.");
	                  }
	              }
	        	  	return true;
	          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }  finally {
			ConnectionUtils.fermerConnection(ps, connection);
		}

		return false;
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return
	 */
	boolean updateAssociationProfesseurModule(Professeur prof) {
		
		if (!prof.getListeModules().isEmpty()) {
			supprimerAssociationProfesseurModules(prof);	
		}
		
		return insererAssociationProfesseurIdModuleIds(prof.getId(), prof.getModuleIds());
	}

	/**
	 * 
	 * @param prof
	 * @return
	 */
	boolean insererAssociationProfesseurModule(Professeur prof) {
		return insererAssociationProfesseurIdModuleIds(prof.getId(), prof.getModuleIds());
	}
	
	/**
	 * 
	 * @param profId
	 * @param modules
	 * @return
	 */
	boolean insererAssociationProfesseurIdModuleIds(Integer profId, List<Integer> modules) {
		System.out.println("insertion dans Professeur_Module"+profId+"---"+modules);		
		Connection connection = getConnection(); 
		String SQL = "INSERT INTO Professeur_Module (prof_id, mod_id) VALUES (?, ?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(SQL);			
			for (Integer module : modules) {
				System.out.println(profId+"--"+module);
				stmt.setInt(1, profId);
				stmt.setInt(2, module);
				stmt.addBatch();				
			}
			int[] inserted = stmt.executeBatch();
			boolean insere = IntStream.of(inserted).allMatch(x -> x == 1);
			if (insere) return true; 
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false; 
		} finally {
			ConnectionUtils.fermerConnection(stmt, connection);
		}
		
		return false; 
	}
	
	
	/**
	 * 
	 * @param profId
	 * @param modules
	 * @return
	 */
	void supprimerAssociationProfesseurModules(Professeur prof) {
		Connection connection = getConnection();
		Statement stmt = null;
		ResultSet rs = null; 

		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM Professeur_Module WHERE prof_id=" + prof.getId());
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.fermerConnection(rs, stmt, connection);
		}
	}
}
