package fr.uvsq.models;

import java.util.ArrayList;

public class Professeur {
	   private String mNom;
	    private int mId;
	    private ArrayList<Module> mListeModules; //liste de cours

	    
	    
	    /**
		 * Constructeur par défaut
	     */
	    public Professeur() {
	    	mNom = "";
	    	mId = -1;
	    	mListeModules = null;
	    }
	   
	    /**
		 * Création d'une instance de professeur
		 * @param nom nom professeur
		 * @param listeModules liste modules dont peut enseigné un professeur
	     */
	    public Professeur(String nom,  ArrayList<Module> listeModules) {
	        mNom = nom;
	        mListeModules = listeModules;
	    }

	    /**
	     * Concaténe le nom de tous les modules
	     * @return le nom de tous les modules
		 * concaténés
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
	     * @return nom actuel
	     */
	    public String getNom() {
	        return mNom;
	    }

	    /**
	     *
	     * @param nom nom à fixer
	     */
	    public void setNom(String nom) {
	        mNom = nom;
	    }

	    /**
	     *
	     * @return id actuel
	     */
	    public int getId() {
	        return mId;
	    }

	    /**
	     *
	     * @param id id à fixer
	     */
	    public void setId(int id) {
	        mId = id;
	    }


	    /**
	     *
	     * @return listeModules actuelle
	     */
	    public ArrayList<Module> getListeModules() {
	        return mListeModules;
	    }

	    /**
	     *
	     * @param listeModules listeModules à fixer
	     */
	    public void setListeModules(ArrayList<Module> listeModules) {
	        mListeModules = listeModules;
	    }

	    /**
	     * Ajoute un module dans la liste des modules
	     * @param module module à ajouter
	     */
	    public void ajouteModule(Module module){
	         Module m = new Module(module.getNom(), module.getNbCM(), module.getNbTD(), module.getNbTP(), module.getDureeCM(), module.getDureeTD(), module.getDureeTP());
	         mListeModules.add(m);
	    }
	    public boolean peutEnseigner(Module module){
	        return mListeModules.contains(module);
	    }

	    /**
	     * Calcul le nombre d'occurence d'un module
	     * @param m module
	     * @return le nombre d'occurence d'un module
	     */
	    private int getRepetModule(Module m) {
	        int repet=0;
	        for(int i = 0; i < (double)this.mListeModules.size(); i++){
	        	if(m == mListeModules.get(i)) repet++;
	        }
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

	    @Override
		public String toString() {
			return "Professeur [mId=" + mId + ", mNom=" + mNom + ", modulesId="+getListeModules() +"]";
		}

}
