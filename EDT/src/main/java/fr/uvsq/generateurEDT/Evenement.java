package fr.uvsq.generateurEDT;

import fr.uvsq.models.Module;
import fr.uvsq.models.*;


public class Evenement {
	//les attributs
    private int mId;
    private TypeEven mTypeEven;
    private Professeur mProfesseur;
    private Module mModule;
    private Groupe mGroupe;
    private Creneau mCreneau;
    private static int mNbEvenements = 0;

    //les constructeurs
    public Evenement() {
        mNbEvenements++;
        mId = -1;
        mTypeEven = TypeEven.CM;
        mProfesseur = null;
        mModule = null;
        mGroupe = null;
        mCreneau = null;
    }

    /**
     * 
     * @param id
     * @param typeEven
     * @param professeur
     * @param module
     * @param groupe
     * @param creneau
     */
    public Evenement(int id, TypeEven typeEven, Professeur professeur, Module module, Groupe groupe, Creneau creneau) {
        mId = id;
        mTypeEven = typeEven;
        mProfesseur = professeur;
        mModule = module;
        mGroupe = groupe;
        mCreneau = creneau;
        mNbEvenements++;
    }

    
    // les méthodes
    /**
     * 
     * @return l'identifiant d'un événement
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
     * @return le type d'événement 
     */
    public TypeEven getTypeEven() {
        return mTypeEven;
    }

    /**
     * 
     * @param typeEven
     */
    public void setTypeEven(TypeEven typeEven) {
        mTypeEven = typeEven;
    }

    /**
     * 
     * @return le professeur d'un événement
     */
    public Professeur getProfesseur() {
        return mProfesseur;
    }

    /**
     * 
     * @param professeur
     */
    public void setProfesseur(Professeur professeur) {
        mProfesseur = professeur;
    }

    /**
     * 
     * @return le module d'un événement
     */
    public Module getModule() {
        return mModule;
    }

    /**
     * 
     * @param module
     */
    public void setModule(Module module) {
        mModule = module;
    }

    /**
     * 
     * @return le groupe d'un événement
     */
    public Groupe getGroupe() {
        return mGroupe;
    }

    /**
     * 
     * @param groupe
     */
    public void setGroupe(Groupe groupe) {
        mGroupe = groupe;
    }
    
    /**
     * 
     * @return le créneau d'un événement
     */
    public Creneau getCreneau() {
        return mCreneau;
    }

    /**
     * 
     * @param creneau
     */
    public void setCreneau(Creneau creneau) {
        mCreneau = creneau;
    }

    /**
     * 
     * @return le nombre d'événements créés
     */
    public static int getNbEvenements() {
        return mNbEvenements;
    }

}
