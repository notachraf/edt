package fr.uvsq.gestionDeDonnees;

import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAO extends DAO<Professeur> {

    public ProfesseurDAO(Connection connection) {
        super(connection);
    }

	@Override
	public boolean inserer(Professeur prof) {
		Connection connection =getConnection();
		PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO professeur(prof_id, prof_nom,prof_cours) VALUES (NULL, ?, ?)", 
            		Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prof.getNom());
            ps.setString(2, prof.getListeModulesAsString());
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
        }

		return false;
	}
	
	
	@Override
	public boolean insererListe(List<Professeur> Professeurs) {
		   Connection connection = getConnection();
	        PreparedStatement ps = null;
	        int insertBon = 1; 
	        try {
	            ps = connection.prepareStatement("INSERT INTO professeur (prof_id, prof_nom, prof_cours) VALUES(NULL, ?, ?)"); 
	                   // Statement.RETURN_GENERATED_KEYS);   //Pas compris ici, pourquoi on a pas Ã§a, comme en haut ?

	            for (Professeur prof: Professeurs) {
	                ps.setString(1, prof.getNom());
	            	ps.setString(2, prof.getListeModulesAsString());
	                insertBon = ps.executeUpdate();

	                if (insertBon != 1 ) {
	                    return false; 
	                }
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return insertBon == 1;
	    }

	@Override
	public Professeur selectionner(int id) {
		return rechercher(id);
	}

	@Override
	public boolean supprimer(Professeur prof) {
	
		
		
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
		}

		return false;
	}

	@Override
	public Professeur rechercher(int id) {
		Professeur professeur=new Professeur();
		Connection connection = getConnection();
		Statement stmt = null;
		ResultSet rs = null; 

		try {
			stmt = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			rs = stmt.executeQuery("SELECT * FROM professeur WHERE prof_id = " + id);

			 while(rs.next())
	            {
	                
	                professeur.setId( rs.getInt("prof_id"));
	                professeur.setNom( rs.getString("prof_nom"));
	                String cours = rs.getString("prof_cours");
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
	            }

		} catch (SQLException ex) {
				ex.printStackTrace();
		}

		return professeur;

	}

	@Override
	public boolean modifier(Professeur prof) {
		
		PreparedStatement ps = null;
		
        try {
            ps =getConnection().prepareStatement("UPDATE Professeur SET prof_nom=?, prof_cours = ? WHERE prof_id = ? ");
            String cours = "";
            int i;
            for( i = 0; i < prof.getListeModules().size() - 1; i++){
				cours += prof.getListeModules().get(i).getNom() + ",";
			}
			cours += prof.getListeModules().get(i).getNom();
            ps.setString(1, prof.getNom());
			ps.setString(2, cours);
            ps.setInt(3, prof.getId());
            int r = ps.executeUpdate();

          if(r == 1) {
        	  return true; 
          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    
	}
	
	@Override
	public Professeur rechercher(String nom) {
		Professeur professeur =  new Professeur();
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = null; 
		
		try {
			stmt =  connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			
			rs = stmt.executeQuery("SELECT * FROM professeur WHERE prof_nom = "+nom);

			 
	            while(rs.next())
	            {
	                
	                professeur.setId( rs.getInt("prof_id"));
	                professeur.setNom( rs.getString("prof_nom"));
	                String cours = rs.getString("prof_cours");
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
	            }

		} catch (SQLException ex) {
				ex.printStackTrace();
		}

		return professeur;

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
            stmt = connection.createStatement();
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
