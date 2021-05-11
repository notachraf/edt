package fr.uvsq.generateurEDT;

import fr.uvsq.models.Promotion;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Salle;
import java.util.List;

public class DonneesEDT {
    
    private List<Salle> mListeSalles;
    private List<Professeur> mListeProfesseurs;
    private List<Module> mListeModules;
    private List<Promotion> mListeGroupes;

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
    public List<Promotion> getListeGroupes() {
        return mListeGroupes;
    }

    /**
     * 
     * @param listeGroupes
     */
    public void setListeGroupes(List<Promotion> listeGroupes) {
        mListeGroupes = listeGroupes;
    }


    /**
     * Créer les évenement de type cours.
     */
    private void creerEvenementCours(){

    }

    /**
     * Créer les évenement de type TP.
     */
    private void creerEvenementTP(){

    }

    /**
     * Créer les évenement de type TD.
     */
    private void creerEvenementTD(){

    }
}

