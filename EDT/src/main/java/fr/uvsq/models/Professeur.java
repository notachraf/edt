package fr.uvsq.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Author Siham, Feriel, Aziz
 */
public class Professeur {


	private int mId;
	private String mNom;

	private int mNbProfesseur; // ce n'est pas sa place !! 
	private ArrayList<Module> mListeModules; // liste de cours

	public Professeur() {
	}
	/**
	 * Constructeur
	 * 
	 * @return Prefessur
	 */
	public Professeur(String nom ) {
		this();
		this.mNom = nom;
	}
	
	public Professeur(String nom, ArrayList<Module> mListeModules) {
		this();
		this.mNom = nom;
		this.mListeModules = mListeModules;
	}

	/**
	 *
	 * @return
	 */
	public String getListeModulesAsString() {

		ArrayList<String> modules = new ArrayList<>();
		for (Module module : mListeModules) {
			modules.add(module.getNom());
		}
		return String.join(", ", modules);
	}
	
	/**
	 *
	 * @return
	 */
	public List<String> getModuleNames() {
		ArrayList<String> modules = new ArrayList<>();
		for (Module module : mListeModules) {
			modules.add(module.getNom());
		}
		return modules;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Integer> getModuleIds() {
		ArrayList<Integer> ids = new ArrayList<>();
		for (Module module : mListeModules) {
			ids.add(module.getId());
		}
		return ids;
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
	public void ajouteModule(Module module) {
		this.mListeModules.add(module);
	}

	public boolean peutEnseigner(Module module) {
		return mListeModules.contains(module);
	}

	/**
	 *
	 * @param m
	 * @return
	 */
	private int getRepetModule(Module pModule) {
		int repet = 0;

		for (int index = 0; index < this.mListeModules.size(); index++) {
			if (pModule == this.mListeModules.get(index))
				repet++;
		}
		return repet;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professeur other = (Professeur) obj;
		if (mId != other.mId)
			return false;
		if (mNom == null) {
			if (other.mNom != null)
				return false;
		} else if (!mNom.equals(other.mNom))
			return false;
		return true;
	}
	/**
	 * supp les doublons de notre liste de cours
	 */
	public void removeDoublons() {
		for (int i = 0; i < this.mListeModules.size(); i++) {
			if (getRepetModule(this.mListeModules.get(i)) > 1)
				this.mListeModules.remove(i);
		}
	}
	
	@Override
	public String toString() {
		return "Professeur [mId=" + mId + ", mNom=" + mNom + ", modulesId="+getModuleIds() +"]";
	}
}
