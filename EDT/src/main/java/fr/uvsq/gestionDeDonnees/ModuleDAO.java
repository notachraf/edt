package fr.uvsq.gestionDeDonnees;
import fr.uvsq.models.Module;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO extends DAO<Module>{

    public ModuleDAO(Connection connection){
        super(connection);
    }

    @Override
    public boolean inserer(Module obj) {
        return false;
    }

    public boolean insererListe(List<Module> obj) {
        return false;
    }

    @Override
    public Module selectionner(int id) {
        return null;
    }

    @Override
    public boolean supprimer(Module obj) {
        return false;
    }

    @Override
    public Module rechercher(int id) {
        return null;
    }

    @Override
    public boolean modifier(Module obj) {
        return false;
    }

    @Override
    public Module rechercher(String nom) {
        return null;
    }


    /**
     * Récuper le contenu de la table Module
     * @return une liste de Modules
     */
    public ArrayList<Module> recupererListe() {
        return null;
    }
}
