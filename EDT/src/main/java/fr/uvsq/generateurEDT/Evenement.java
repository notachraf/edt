package fr.uvsq.generateurEDT;

import fr.uvsq.models.Groupe;
import fr.uvsq.models.Professeur;

public class Evenement {
    private int mId;
    private TypeEven mTypeEven;
    private Professeur mProfesseur;
    private Module mModule;
    private Groupe mGroupe;
    private static int NbEvenements;
    
    public Evenement() {
        NbEvenements++;
    }

    public Evenement(int id, TypeEven typeEven, Professeur professeur, Module module, Groupe groupe) {
        mId = id;
        mTypeEven = typeEven;
        mProfesseur = professeur;
        mModule = module;
        mGroupe = groupe;
        NbEvenements++;
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

    public static int getNbEvenements() {
        return NbEvenements;
    }

    public static void setNbEvenements(int mNbEvenements) {
        Evenement.NbEvenements = mNbEvenements;
    }
}
