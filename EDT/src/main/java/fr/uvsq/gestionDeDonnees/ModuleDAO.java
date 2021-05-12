package fr.uvsq.gestionDeDonnees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.uvsq.models.Module;

public class ModuleDAO extends DAO<Module>{

    public ModuleDAO(Connection connection){
        super(connection);
    }

    @Override
    public boolean inserer(Module module) {
        Connection connection = getConnection();
        PreparedStatement ps = null; 
        try {
            ps = connection.prepareStatement("INSERT INTO module(mod_nom, mod_nb_td, mod_nb_tp, mod_nb_cm, mod_duree_td, mod_duree_tp, mod_duree_cm) VALUES(?, ?, ?, ?, ?, ?, ?)", 
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
        }

        return false;
    }

    @Override
    public boolean insererListe(List<Module> modules) {
    	
        Connection connection = getConnection();
        PreparedStatement ps = null;
        int insertBon = 1; 
        try {
            ps = connection.prepareStatement("INSERT INTO module VALUES (NULL, ?, ?, ?)");
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
        try {
            stmt = getConnection()
            		.createStatement();
            int i = stmt.executeUpdate("DELETE FROM module WHERE mod_id=" + obj.getId());

          if(i == 1) {
        	  	return true;
          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public Module rechercher(int id) {
        
    	Module module = new Module();
    	ResultSet result = null; 
    	Statement stmt = null;
       try {
           stmt = this.getConnection()
                                   .createStatement(
                                               ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                               ResultSet.CONCUR_UPDATABLE
                                            ); 
           
                 
           result = stmt.executeQuery("SELECT * FROM module WHERE mod_id = " + id);
           if(result.first()) {
        	module.setId( result.getInt("mod_id"));
       		module.setNom( result.getString("mod_nom"));
       		module.setNbCM( result.getInt("mod_nb_cm"));
       		module.setNbTD( result.getInt("mod_nb_td"));
       		module.setNbTP( result.getInt("mod_nb_tp"));
       		module.setDureeTD( result.getInt("mod_duree_td"));
       		module.setDureeTP( result.getInt("mod_duree_tp"));
       		module.setDureeCM( result.getInt("mod_duree_cm"));
           	}
           
           } catch (SQLException e) {
                   e.printStackTrace();
           }
       
          return module;
    }

    @Override
    public boolean modifier(Module module) {

        try {
            PreparedStatement ps = getConnection().prepareStatement("UPDATE Module SET mod_nom = ?,"
                    + " mod_nb_cm = ?,"
                    + " mod_nb_td = ?,"
                    + " mod_nb_tp = ?,"
                    + " mod_duree_td = ?,"
                    + " mod_duree_tp = ?,"
                    + " mod_duree_cm = ? "
                    + "WHERE mod_id = ?");
            
            ps.setString(1, module.getNom());
            ps.setInt(2, module.getNbCM());
            ps.setInt(3, module.getNbTD());
            ps.setInt(4, module.getNbTP());
            ps.setInt(5, module.getDureeTD());
            ps.setInt(6, module.getDureeTP());
            ps.setInt(7, module.getDureeCM());
            ps.setInt(8, module.getId());
            System.out.println("id module: " + module.getId());
            int i = ps.executeUpdate();
          if(i == 0) {
        	  	return false;
          }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public Module rechercher(String nom) {
    	Module module = new Module();
    	PreparedStatement stmt = null;
    	ResultSet result = null; 
       try {
        stmt = getConnection().prepareStatement("SELECT * FROM Module WHERE mod_nom=?",
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
        stmt.setString(1, nom);
        result = stmt.executeQuery();
       if(result.first()) {
           module.setId( result.getInt("mod_id"));
            module.setNom( result.getString("mod_nom"));
            module.setNbCM( result.getInt("mod_nb_cm"));
            module.setNbTD( result.getInt("mod_nb_td"));
            module.setNbTP( result.getInt("mod_nb_tp"));
            module.setDureeTD( result.getInt("mod_duree_td"));
            module.setDureeTP( result.getInt("mod_duree_tp"));
            module.setDureeCM( result.getInt("mod_duree_cm"));
        }

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return module;
    }

	@Override
	public ArrayList<Module> recupererListe() {
    	ArrayList<Module> modules = new ArrayList<Module>(); 
    	Statement stmt = null;
    	ResultSet result = null; 
    	
       try {
           stmt = getConnection().createStatement(
                                               ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                               ResultSet.CONCUR_UPDATABLE
                                            );
           result = stmt.executeQuery("SELECT * FROM Module ");

           Module module = null;

           while(result.next())
           {
               module = new Module();
        	    module.setId( result.getInt("mod_id"));
               module.setNom( result.getString("mod_nom"));
          		module.setNbCM( result.getInt("mod_nb_cm"));
          		module.setNbTD( result.getInt("mod_nb_td"));
          		module.setNbTP( result.getInt("mod_nb_tp"));
          		module.setDureeTD( result.getInt("mod_duree_td"));
          		module.setDureeTP( result.getInt("mod_duree_tp"));
          		module.setDureeCM( result.getInt("mod_duree_cm"));
               modules.add(module);
           }
           
           } catch (SQLException e) {
                   e.printStackTrace();
           }
          return modules;
	}
}
