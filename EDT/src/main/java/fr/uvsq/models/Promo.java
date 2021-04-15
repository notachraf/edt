package fr.uvsq.models;

import java.util.ArrayList;

/**
 * Cette classe représente l'entité groupe.
 * @Author Siham, Feriel, Aziz
 */

public class Promo {
    private String mNom;
    private int mId;
    private int mNbEleves;
    private int mNbGroupes;
    private static int mNbPromotion;
    private ArrayList<Module> mListeModules = new ArrayList();

    public Promo(){

    }

    public Promo(String nom, int nbEleves, int nbGroupes, ArrayList<Module> listeModules) {
        mNom = nom;
        mNbEleves = nbEleves;
        mNbGroupes = nbGroupes;
        mListeModules = listeModules;
    }

    public static int getmNbPromotion() {
        return mNbPromotion;
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
}
