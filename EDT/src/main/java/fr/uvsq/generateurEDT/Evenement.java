package fr.uvsq.generateurEDT;

import fr.uvsq.models.Module;
import fr.uvsq.models.*;


public class Evenement {
    private int mId;
    private TypeEven mTypeEven;
    private Professeur mProfesseur;
    private Module mModule;
    private Groupe mGroupe;
    private Creneau mCreneau;
    private static int mNbEvenements = 0;

    
    public Evenement() {
        mNbEvenements++;
        mId = -1;
        mTypeEven = TypeEven.CM;
        mProfesseur = null;
        mModule = null;
        mGroupe = null;
        mCreneau = null;
    }

    public Evenement(int id, TypeEven typeEven, Professeur professeur, Module module, Groupe groupe, Creneau creneau) {
        mId = id;
        mTypeEven = typeEven;
        mProfesseur = professeur;
        mModule = module;
        mGroupe = groupe;
        mCreneau = creneau;
        mNbEvenements++;
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
     * @return
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
     * @return
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
     * @return
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
    
    public Creneau getCreneau() {
        return mCreneau;
    }

    public void setCreneau(Creneau creneau) {
        mCreneau = creneau;
    }


    public static int getNbEvenements() {
        return mNbEvenements;
    }

}
