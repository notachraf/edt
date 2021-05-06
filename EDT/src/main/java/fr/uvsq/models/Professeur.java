package fr.uvsq.models;

import java.util.ArrayList;

public class Professeur {
<<<<<<< HEAD
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
    }
    public boolean peutEnseigner(Module module){
        return false;
    }

    /**
     *
     * @param m
     * @return
     */
    private int getRepetModule(Module m) {
        return 0;
    }

    /**
     * supp les doublons de notre liste de cours 
     */
    public void removeDoublons() {

    }
}

=======
	   private String mNom;
	    private int mId;
	    private ArrayList<Module> mListeModules; //liste de cours

	    
	    
	    /** Constructeur vide
	     * @return Professeur
	     */
	    public Professeur() {
	    	mNom = "";
	    	mId = -1;
	    	mListeModules = null;
	    }
	   
	    /** Constructeur
	     * @return Professeur
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
	         Module m = new Module(module.getNom(), module.getNbCM(), module.getNbTD(), module.getNbTP(), module.getDureeCM(), module.getDureeTD(), module.getDureeTP());
	         mListeModules.add(m);
	    }
	    public boolean peutEnseigner(Module module){
	        return mListeModules.contains(module);
	    }

	    /**
	     *
	     * @param m
	     * @return
	     */
	    private int getRepetModule(Module m) {
	        int repet=0;
	        for(int i = 0; i < (double)this.mListeModules.size(); i++){
	        if(m == mListeModules.get(i)) repet++;          }
	         return repet; 
	    }

	    /**
	     * supp les doublons de notre liste de cours 
	     */
	    public void removeDoublons() {
	        for (int i = 0; i < this.mListeModules.size(); i++) { 
	                  if (getRepetModule( this.mListeModules.get(i))>1) 
	                   this.mListeModules.remove(i);
	   }
	}

}
>>>>>>> origin/moulhat
