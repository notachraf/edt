package fr.uvsq.interfaces;

import fr.uvsq.models.Promotion;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Salle;
import fr.uvsq.models.Salle;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestion de Fichier
 */
public class  GestionFichier{

    private String mCheminFichier;
    private List<Professeur> mListeProfesseurs;
    private List<Promotion> mListeGroupes;
    private List<Salle> mListeSalles;
    private List<Module> mListeModules;

    public GestionFichier(String cheminFichier){
        setCheminFichier(cheminFichier);
        mListeGroupes = new ArrayList<>();
        mListeSalles = new ArrayList<>();
        mListeModules = new ArrayList<>();
        mListeProfesseurs = new ArrayList<>();
    }

    /**
     * @return le chemin d'un fichier
     */
    public String getCheminFichier() {
        return mCheminFichier;
    }

    /**
     * Assigne le chemin du fichier à lire à la
     * variable mCheminFichier
     * @param cheminFichier
     */
    public void setCheminFichier(String cheminFichier) {
        if( cheminFichier == null || cheminFichier.trim().isEmpty() ){
            System.out.println("Chemin inconnu :(");
        } else {
            mCheminFichier = cheminFichier;
        }
    }

    /**
     * Ouvre le fichier en mode lecture et extraire les données
     * ligne par ligne.
     */
    public void recupereDonneesFichier(){

    }

    /**
     * Créer une instance de la classe Professeur
     * depuis une chaine de caractère.
     * @param line
     * @return
     */
    public Professeur creerProf(String line){
        return null;
    }

    /**
     * Créer une instance de la classe Module
     * depuis une chaine de caractère.
     * @param line
     * @return
     */
    public Module creerModule(String line){
        return null;
    }

    /**
     * Créer une instance de la classe Groupe
     * depuis une chaine de caractère.
     * @param line
     * @return
     */

    public Promotion creerGroupe(String line){
        return null;
    }

    /**
     * Créer une instance de la classe Salle
     * depuis une chaine de caractère.
     * @param line
     * @return
     */
    public Salle creerSalle(String line){
        return null;
    }

    /**
     * @return la liste des professeurs
     */
    public List<Professeur> getListeProfesseurs() {
        return mListeProfesseurs;
    }

    /**
     * Assigne une liste de professeurs à la variable
     * mListeProfesseurs
     * @param listeProfesseurs
     */
    public void setListeProfesseurs(List<Professeur> listeProfesseurs) {
        mListeProfesseurs = listeProfesseurs;
    }

    /**
     * @return la liste des groupes
     */
    public List<Promotion> getListeGroupes() {
        return mListeGroupes;
    }

    /**
     * Assigne une liste de groupes à la variable
     * mListeProfesseurs
     * @param listeGroupes
     */
    public void setListeGroupes(List<Promotion> listeGroupes) {
        mListeGroupes = listeGroupes;
    }

    /**
     * @return la liste des Salles
     */
    public List<Salle> getListeSalles() {
        return mListeSalles;
    }

    /**
     * Assigne une liste de salles à la variable
     * mListeSalles
     * @param listeSalles
     */
    public void setListeSalles(List<Salle> listeSalles) {
        mListeSalles = listeSalles;
    }

    /**
     * @return la liste des Modules
     */
    public List<Module> getListeModules() {
        return mListeModules;
    }

    /**
     * Assigne une liste de Module à la variable
     * mListeModules
     * @param listeModules
     */
    public void setListeModules(List<Module> listeModules) {
        mListeModules = listeModules;
    }
}
