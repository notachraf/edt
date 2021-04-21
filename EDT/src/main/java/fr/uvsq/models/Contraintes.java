package fr.uvsq.models;

public class Contraintes {
    private int mNbSallesMax,
                mNbPromoMax,
                mNbCreneauxMax,
                mNbProfsMax;

    public Contraintes(int nbSallesMax, int nbPromoMax, int nbCreneauxMax, int nbProfsMax) {
        mNbSallesMax = nbSallesMax;
        mNbPromoMax = nbPromoMax;
        mNbCreneauxMax = nbCreneauxMax;
        mNbProfsMax = nbProfsMax;
    }

    public int getNbSallesMax() {
        return mNbSallesMax;
    }

    public void setNbSallesMax(int nbSallesMax) {
        mNbSallesMax = nbSallesMax;
    }

    public int getNbPromoMax() {
        return mNbPromoMax;
    }

    public void setNbPromoMax(int nbPromoMax) {
        mNbPromoMax = nbPromoMax;
    }

    public int getNbCreneauxMax() {
        return mNbCreneauxMax;
    }

    public void setNbCreneauxMax(int nbCreneauxMax) {
        mNbCreneauxMax = nbCreneauxMax;
    }

    public int getNbProfsMax() {
        return mNbProfsMax;
    }

    public void setNbProfsMax(int nbProfsMax) {
        mNbProfsMax = nbProfsMax;
    }
}
