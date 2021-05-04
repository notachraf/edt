package fr.uvsq.models;

import java.util.ArrayList;


/**
 * Cette classe représente l'entité groupe.
 * @Author Siham, Feriel, Aziz
 */

public class Promotion {
    private String mNom;
    private int mId;
    private int mNbEleves;
    private int mNbGroupes;
    private ArrayList<Module> mListeModules;

    public Promotion(String nom, int nbEleves, int nbGroupes, ArrayList<Module> listeModules) {
        mNom = nom;
        mNbEleves = nbEleves;
        mNbGroupes = nbGroupes;
        mListeModules = listeModules;
    }

    public Promotion(){
        mId = -1;
        mNom = "";
        mNbEleves = 0;
        mNbGroupes = 0;
        mListeModules = null;
    }


    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getNbEleves() {
        return mNbEleves;
    }

    public void setNbEleves(int nbEleves) {
        mNbEleves = nbEleves;
    }

    public int getNbGroupes() {
        return mNbGroupes;
    }

    public void setNbGroupes(int nbGroupes) {
        mNbGroupes = nbGroupes;
    }

    public ArrayList<Module> getListeModules() {
        return mListeModules;
    }

    public void setListeModules(ArrayList<Module> listeModules) {
        mListeModules = listeModules;
    }

    public String getListeModulesAsString() {
        ArrayList<String> modules = new ArrayList<>();
        for(Module module : mListeModules) {
            modules.add(module.getNom());
        }
        return String.join(", ", modules);
    }
    
    public void ajouteModule ( Module module ) {
        Module m = new Module(module.getNom(), module.getNbCM(), module.getNbTD(), module.getNbTP(), module.getDureeCM(), module.getDureeTD(), module.getDureeTP());
        mListeModules.add(m);    	
    }
    public boolean peutSuivre ( Module module ) {
        return mListeModules.contains(module);

    }
    private int getRepetModule ( Module module ) {
        int repet=0;
        for(int i = 0; i < this.mListeModules.size(); i++){
        if(module == (Module)mListeModules.get(i)) repet++;          }
         return repet;    	
    }
    public void removeDoublons () {
    	for (int i = 0; i < this.mListeModules.size(); i++) { 
    		if (getRepetModule( this.mListeModules.get(i))>1) 
    			this.mListeModules.remove(i);
    	}
    }
}
