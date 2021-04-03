package fr.uvsq.generateurEDT;

import fr.uvsq.gestionDeDonnees.SalleDAO;
import fr.uvsq.models.Groupe;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Salle;
import java.util.List;

public class DonneesDB {
    
    private List<Salle> mListeSalles;
    private List<Professeur> mListeProfesseurs;
    private List<Module> mListeModules;
    private List<Groupe> mListeGroupes;

    /**
     * 
     * @return
     */
    public List<Salle> getListeSalles() {
        /*
            SalleDAO s = new SalleDAO(BDConnection.getConnection);
            mListeSalles = s.recupererListeSalles();
         */
        return mListeSalles;
    }

    /**
     * 
     * @param listeSalles
     */
    public void setListeSalles(List<Salle> listeSalles) {
        mListeSalles = listeSalles;
    }

    /**
     * 
     * @return
     */
    public List<Professeur> getListeProfesseurs() {
        return mListeProfesseurs;
    }

    /**
     * 
     * @param listeProfesseurs
     */
    public void setListeProfesseurs(List<Professeur> listeProfesseurs) {
        mListeProfesseurs = listeProfesseurs;
    }
    
    /**
     * 
     * @return
     */
    public List<Module> getListeModules() {
        return mListeModules;
    }

    /**
     * 
     * @param listeModules
     */
    public void setListeModules(List<Module> listeModules) {
        mListeModules = listeModules;
    }

    /**
     * 
     * @return
     */
    public List<Groupe> getListeGroupes() {
        return mListeGroupes;
    }

    /**
     * 
     * @param listeGroupes
     */
    public void setListeGroupes(List<Groupe> listeGroupes) {
        mListeGroupes = listeGroupes;
    }
}

