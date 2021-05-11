package fr.uvsq.models;

import java.util.ArrayList;

/**
 * Cette classe représente l'entité groupe.
 * @Author Siham, Feriel, Aziz
 */

public class Promotion {


	private int mId;
    private String mNom;
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
        mNom = null;
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
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		if (mId != other.mId)
			return false;
		if (mNom == null) {
			if (other.mNom != null)
				return false;
		} else if (!mNom.equals(other.mNom))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		result = prime * result + ((mNom == null) ? 0 : mNom.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "Promotion [mId=" + mId + ", mNom=" + mNom + ",mNbEleves=" + mNbEleves + ", mNbGroupes=" + mNbGroupes + ", modulesId="+getModuleIds() +"]";
	}

	private String getModuleIds() {
		// TODO Auto-generated method stub
		return null;
	}
}
