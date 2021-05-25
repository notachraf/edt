package fr.uvsq.models;

import java.util.ArrayList;

public class Promotion {
    private String mNom;
    private int mId;
    private int mNbEleves;
    private int mNbGroupes;
    private ArrayList<Module> mListeModules;

    private String mLocalDate;

    /**
     * Création d'une instance de la classe
     * @param nom nom de la promotion
     * @param nbEleves nombre d'éléves
     * @param nbGroupes nombre de groupes
     * @param listeModules liste des modules
     * @param date la date de début des cours.
     */
    public Promotion(String nom, int nbEleves, int nbGroupes, ArrayList<Module> listeModules, String date) {
        mNom = nom;
        mNbEleves = nbEleves;
        mNbGroupes = nbGroupes;
        mListeModules = listeModules;
        mLocalDate = date;
    }

    /**
     * Constructeur par  défaut
     */
    public Promotion(){
        mId = -1;
        mNom = "";
        mNbEleves = 0;
        mNbGroupes = 0;
        mListeModules = null;
    }

    /**
     *
     * @return nom promotion actuel
     */
    public String getNom() {
        return mNom;
    }

    /**
     *
     * @param nom nom promotion à fixer
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
     * @return nbEleves à fixer
     */
    public int getNbEleves() {
        return mNbEleves;
    }

    /**
     *
     * @param nbEleves NbEleves actuel
     */
    public void setNbEleves(int nbEleves) {
        mNbEleves = nbEleves;
    }

    /**
     *
     * @return nbGroupe actuel
     */
    public int getNbGroupes() {
        return mNbGroupes;
    }

    /**
     *
     * @param nbGroupes nbGroupe à fixer
     */
    public void setNbGroupes(int nbGroupes) {
        mNbGroupes = nbGroupes;
    }

    /**
     *
     * @return liste modules actuelle
     */
    public ArrayList<Module> getListeModules() {
        return mListeModules;
    }

    /**
     *
     * @param listeModules liste modules à fixer
     */
    public void setListeModules(ArrayList<Module> listeModules) {
        mListeModules = listeModules;
    }

    /**
     * Concaténe le nom de tous les modules
     * @return concaténation des nom
     * de tous les modules
     */
    public String getListeModulesAsString() {
        ArrayList<String> modules = new ArrayList<>();
        for(Module module : mListeModules) {
            modules.add(module.getNom());
        }
        return String.join(", ", modules);
    }

    /**
     * Ajoute un module dans liste modules
     * @param module
     */
    public void ajouteModule ( Module module ) {
        Module m = new Module(module.getNom(), module.getNbCM(), module.getNbTD(), module.getNbTP(), module.getDureeCM(), module.getDureeTD(), module.getDureeTP());
        mListeModules.add(m);    	
    }

    /**
     * vérifie si un module est présent dans
     * liste module
     * @param module
     * @return boolean
     */
    public boolean peutSuivre ( Module module ) {
        return mListeModules.contains(module);

    }

    /**
     *
     * @param module
     * @return le nombre d'occurence d'un module
     */
    private int getRepetModule ( Module module ) {
        int repet=0;
        for(int i = 0; i < this.mListeModules.size(); i++){
        if(module == mListeModules.get(i)) repet++;          }
         return repet;
    }


    /**
     *
     * @return date actuelle
     */
    public String getLocalDate() {
        return mLocalDate;
    }

    /**
     *
     * @param localDate localDate à fixer
     */
    public void setLocalDate(String localDate) {
        mLocalDate = localDate;
    }

    @Override
	public String toString() {
		return "Promotion [mId=" + mId + ", mNom=" + mNom + ", mNbEleves="+mNbEleves +", mNbGroupes=" + mNbGroupes + ", promo_cours= " +getListeModulesAsString() +"]";
	}
}
