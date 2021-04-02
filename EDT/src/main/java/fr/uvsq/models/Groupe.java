package fr.uvsq.models;

import java.util.ArrayList;

/**
 * Cette classe représente l'entité groupe.
 * @Author Siham, Feriel, Aziz
 */

public class Groupe {
    private String mNom;
    private int mId;
    private int mTaille;
    private String mPromo;
    private static int mNbGroupe;
    private ArrayList<Module> mModuleAjouter= new ArrayList();

    /**
     *
     */
    public Groupe() {
    }

    /**
     *
     * @param nom
     * @param taille
     * @param promo
     * @param moduleAjouter
     */
    public Groupe(String nom, int taille, String promo, ArrayList<Module> moduleAjouter) {
        mNom = nom;
        mTaille = taille;
        mPromo = promo;
        mModuleAjouter = moduleAjouter;
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
    public int getTaille() {
        return mTaille;
    }

    /**
     *
     * @param taille
     */
    public void setTaille(int taille) {
        mTaille = taille;
    }

    /**
     *
     * @return
     */
    public String getPromo() {
        return mPromo;
    }

    /**
     *
     * @param promo
     */
    public void setPromo(String promo) {
        mPromo = promo;
    }

    /**
     *
     * @return
     */
    public static int getmNbGroupe() {
        return mNbGroupe;
    }

    /**
     *
     * @param mNbGroupe
     */
    public static void setmNbGroupe(int mNbGroupe) {
        Groupe.mNbGroupe = mNbGroupe;
    }

    /**
     *
     * @return
     */
    public ArrayList<Module> getModuleAjouter() {
        return mModuleAjouter;
    }

    /**
     * 
     * @param moduleAjouter
     */
    public void setModuleAjouter(ArrayList<Module> moduleAjouter) {
        mModuleAjouter = moduleAjouter;
    }
}
