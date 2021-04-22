package fr.uvsq.models;

import java.util.ArrayList;

/**
 *
 * @Author Siham, Feriel, Aziz
 */
public class Professeur {
    private String mNom;
    private int mId;
    private int mNbProfesseur;
    ArrayList<Module> mListeModules; //liste de cours

    /** Constructeur
     * @return Prefessur
     */
    public Professeur(String nom,  ArrayList<Module> listeModules) {
        mNom = nom;
        mListeModules = listeModules;
    }

    /**
     *
     * @return
     */
    public String getListeModulesAsString() {
        ArrayList<String> modules = new ArrayList<>();
        for(Module module : mListeModules) {
            modules.add(module.getNom());
        }
        return String.join(", ", modules);
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return mNom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        mNom = nom;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        mId = id;
    }

    /**
     *
     * @return
     */
    public int getNbProfesseur() {
        return mNbProfesseur;
    }

    /**
     *
     * @param nbProfesseur
     */
    public void setNbProfesseur(int nbProfesseur) {
        mNbProfesseur = nbProfesseur;
    }

    /**
     *
     * @return
     */
    public ArrayList<Module> getListeModules() {
        return mListeModules;
    }

    /**
     *
     * @param listeModules
     */
    public void setListeModules(ArrayList<Module> listeModules) {
        mListeModules = listeModules;
    }

    /**
     *
     * @param module
     */
    public void ajouteModule(Module module){
         Module m = new Module(module.getNom(), module.getId(), module.getnbCours(), module.getnbTD(), module.getnbTP(),module.getNbHeure());
       this.listemodule.add(m);
    }
    public boolean peutEnseigner(Module module){
        return listemodule.contains(module);
    }

    /**
     *
     * @param m
     * @return
     */
    private int getRepetModule(Module m) {
        int repet=0;
        for(int index = 0,index<(double)this.listemodule.size(),i++){
        if(m= (Module)this.listemodule.get(index)) repet++;          }
         return repet; 
    }

    /**
     * supp les doublons de notre liste de cours 
     */
    public void removeDoublons() {
         for (int i = 0; i < this.listemodule.size(); i++) { 
                   if (getRepetModule( this.listemodule(i))>1) 
                    this.listemodule.remove(i);
    }
}




}
