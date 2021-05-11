package fr.uvsq.gestionDeDonnees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.models.Module;
import fr.uvsq.utils.ConnectionUtils;

public class ModuleDAO extends DAO<Module>{

    public ModuleDAO(Connection connection){
        super(connection);
    }

    @Override
    public boolean inserer(Module module) {
        Connection connection = getConnection();
        PreparedStatement ps = null; 
        try {
            ps = connection.prepareStatement("INSERT INTO Module(mod_nom, mod_nb_td, mod_nb_tp, mod_nb_cours, mod_duree_td, mod_duree_tp, mod_duree_cours) VALUES(?, ?, ?, ?, ?, ?, ?)", 
            		Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, module.getNom());
            ps.setInt(2, module.getDureeTP());
            ps.setInt(3, module.getDureeTD());
            ps.setInt(4, module.getDureeCM());
            ps.setInt(5, module.getNbCM());
            ps.setInt(6, module.getNbTD());
            ps.setInt(7, module.getNbTP());
            
            int i = ps.executeUpdate();

          if(i == 1) {
        	  try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                  if (generatedKeys.next()) {
                	  module.setId(generatedKeys.getInt(1));
                  }
                  	else {
                  		throw new SQLException("Creation de Module a echoue.");
                  }
              }
        	  
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
    public boolean insererListe(List<Module> modules) {
    	
        Connection connection = getConnection();
        PreparedStatement ps = null;
        int insertBon = 1; 
        try {
            ps = connection.prepareStatement("INSERT INTO Module VALUES (NULL, ?, ?, ?)");
            for (Module module:modules) {            
            	ps.setString(1, module.getNom());
            	ps.setInt(2, module.getDureeTP());
            	ps.setInt(3, module.getDureeTD());
            	ps.setInt(4, module.getDureeCM());
            	ps.setInt(5, module.getNbCM());
            	ps.setInt(6, module.getNbTD());
            	ps.setInt(7, module.getNbTP());            
            	insertBon = ps.executeUpdate();
            	if (insertBon != 1 ) {
            		return false; 
            	}
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionUtils.fermerConnection(ps, connection);
        }
        
        return insertBon == 1; 
    }

    @Override
    public Module selectionner(int id) {
        return rechercher(id);
    }

    @Override
    public boolean supprimer(Module obj) {
    	
    	Statement stmt = null; 
    	Connection connection = null; 
        try {
        	connection = getConnection();
            stmt = getConnection()
            		.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Module WHERE mod_id=" + obj.getId());

          if(i == 1) {
        	  	return true;
          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionUtils.fermerConnection(stmt, connection);
        }

        return false;
    }

    @Override
    public Module rechercher(int id) {
        
    	Module module = new Module();
    	ResultSet result = null; 
    	Connection connection= null; 
    	Statement stmt = null; 
       try {
    	   connection = this.getConnection(); 
           stmt = this.getConnection()
                                   .createStatement(
                                               ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                               ResultSet.CONCUR_UPDATABLE
                                            ); 
           
                 
           result = stmt.executeQuery("SELECT * FROM Module WHERE mod_id = " + id);
           if(result.first()) {
        	module.setId( result.getInt("mod_id"));
       		module.setNom( result.getString("mod_nom"));
       		module.setNbCM( result.getInt("mod_nb_cours"));
       		module.setNbTD( result.getInt("mod_nb_td"));
       		module.setNbTP( result.getInt("mod_nb_tp"));
       		module.setDureeTD( result.getInt("mod_duree_td"));
       		module.setDureeTP( result.getInt("mod_duree_tp"));
       		module.setDureeCM( result.getInt("mod_duree_cours"));
           	}
           
           } catch (SQLException e) {
                   e.printStackTrace();
           } finally {
               ConnectionUtils.fermerConnection(result, stmt,  connection);
           }
       
          return module;
    }

    @Override
    public boolean modifier(Module module) {
    	
    	Connection connection= getConnection(); 
    	PreparedStatement ps = null; 
        try {
            ps = connection.prepareStatement("UPDATE Module SET mod_nom=?, "
            		+ " mod_nb_cours=?, "
            		+ " mod_nb_td=?, "
            		+ " mod_nb_tp=?, "
            		+ " mod_duree_td=?, "
            		+ " mod_duree_tp=?, "
            		+ " mod_duree_cours=? "
            		+ "WHERE mod_id=?");
            
            ps.setString(1, module.getNom());
            ps.setInt(2, module.getNbCM());
            ps.setInt(3, module.getNbTD());
            ps.setInt(4, module.getNbTP());
            ps.setInt(5, module.getDureeTD());
            ps.setInt(6, module.getDureeTP());
            ps.setInt(7, module.getDureeCM());
            ps.setInt(8, module.getId());
            
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
    public Module rechercher(String nom) {
    	Module module = new Module(); 
    	Connection connection= getConnection(); 
    	Statement stmt = null;
    	ResultSet result = null; 
       try {
           	stmt = this.getConnection()
                                   .createStatement(
                                               ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                               ResultSet.CONCUR_UPDATABLE
                                            ); 
           	result = stmt.executeQuery(
                                               "SELECT * FROM Module WHERE mod_nom = " + nom
                                            );
           if(result.first()) {
        	    module.setId( result.getInt("mod_id"));
          		module.setNom( result.getString("mod_nom"));
          		module.setNbCM( result.getInt("mod_nb_cours"));
          		module.setNbTD( result.getInt("mod_nb_td"));
          		module.setNbTP( result.getInt("mod_nb_tp"));
          		module.setDureeTD( result.getInt("mod_duree_td"));
          		module.setDureeTP( result.getInt("mod_duree_tp"));
          		module.setDureeCM( result.getInt("mod_duree_cours"));
           	}
           
           } catch (SQLException e) {
                   e.printStackTrace();
           } finally {
        	   ConnectionUtils.fermerConnection(result, stmt, connection);
   		   }
       
          return module;
    
    }

	@Override
	public ArrayList<Module> recupererListe() {
    	ArrayList<Module> modules = new ArrayList<Module>(); 
     	Connection connection= getConnection(); 
    	Statement stmt = null;
    	ResultSet result = null; 
    	
       try {
           stmt = connection
                                   .createStatement(
                                               ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                               ResultSet.CONCUR_UPDATABLE
                                            ); 
           result = stmt.executeQuery(
                                               "SELECT * FROM Module "
                                            );
           
           Module module =  new Module(); 
           while(result.next())
           {
        	    module.setId( result.getInt("mod_id"));
          		module.setNom( result.getString("mod_nom"));
          		module.setNbCM( result.getInt("mod_nb_cours"));
          		module.setNbTD( result.getInt("mod_nb_td"));
          		module.setNbTP( result.getInt("mod_nb_tp"));
          		module.setDureeTD( result.getInt("mod_duree_td"));
          		module.setDureeTP( result.getInt("mod_duree_tp"));
          		module.setDureeCM( result.getInt("mod_duree_cours"));
               modules.add(module);
           }
           
           } catch (SQLException e) {
                   e.printStackTrace();
           } finally {
        	   ConnectionUtils.fermerConnection(result, stmt, connection);
   		   }
       
          return modules;
	}
	
}
