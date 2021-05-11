package fr.uvsq.models;

public class Groupe {
	
    private int mNom;
    private Promotion mPromo;
    private int nbEleves;

    public Groupe(){

    }

    public Groupe(int nom, Promotion promo, int nbEleves) {
        mNom = nom;
        mPromo = promo;
        this.nbEleves = nbEleves;
    }

    public int getNom() {
        return mNom;
    }

    public void setNom(int nom) {
        mNom = nom;
    }

    public Promotion getPromo() {
        return mPromo;
    }

    public void setPromo(Promotion promo) {
        mPromo = promo;
    }

    public int getNbEleves() {
        return nbEleves;
    }

    public void setNbEleves(int nbEleves) {
        this.nbEleves = nbEleves;
    }
}
