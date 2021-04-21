package fr.uvsq.models;

public class Groupe {
    private int mId;
    private int mNom;
    private Promo mPromo;
    private int nbEleves;

    public Groupe(){

    }

    public Groupe(int nom, Promo promo, int nbEleves) {
        mNom = nom;
        mPromo = promo;
        this.nbEleves = nbEleves;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getNom() {
        return mNom;
    }

    public void setNom(int nom) {
        mNom = nom;
    }

    public Promo getPromo() {
        return mPromo;
    }

    public void setPromo(Promo promo) {
        mPromo = promo;
    }

    public int getNbEleves() {
        return nbEleves;
    }

    public void setNbEleves(int nbEleves) {
        this.nbEleves = nbEleves;
    }
}
