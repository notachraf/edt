package fr.uvsq.models;

public class Groupe {
    private String mNom;
    private Promotion mPromotion;
    private int mNbEleves;

    /**
     * Constructeur par défaut.
     */
    public Groupe(){
    	mNom = "";
    	mPromotion = null;
    	mNbEleves = 0;
    	
    }

    /**
     * Creation d'une instance de Groupe
     * @param nom nom du groupe
     * @param nbEleves nombre d'élèves du groupe
     * @param promotion la promotion
     */
    public Groupe(String nom, int nbEleves, Promotion promotion) {
        mNom = nom;
        mPromotion = promotion;
        mNbEleves = nbEleves;
    }

    /**
     *
     * @return nom actuel.
     */
    public String getNom() {
        return mNom;
    }

    /**
     *
     * @param nom nom à fixer
     */
    public void setNom(String nom) {
        mNom = nom;
    }

    /**
     *
     * @return promotion actuel
     */
    public Promotion getPromotion() {
        return mPromotion;
    }

    /**
     *
     * @param promotion promotion à fixer
     */
    public void setPromotion(Promotion promotion) {
        mPromotion = promotion;
    }

    /**
     *
     * @return nbEleves actuel
     */
    public int getNbEleves() {
        return mNbEleves;
    }

    /**
     *
     * @param nbEleves nbEleves à fixer
     */
    public void setNbEleves(int nbEleves) {
        this.mNbEleves = nbEleves;
    }
}
