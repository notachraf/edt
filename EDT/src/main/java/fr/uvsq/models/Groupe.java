public class Groupe {
    private String mNom;
    private Promotion mPromotion;
    private int mNbEleves;

    public Groupe(){
    	mNom = "";
    	mPromotion = null;
    	mNbEleves = 0;
    	
    }

    public Groupe(String nom, int nbEleves, Promotion promotion) {
        mNom = nom;
        mPromotion = promotion;
        mNbEleves = nbEleves;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public Promotion getPromotion() {
        return mPromotion;
    }

    public void setPromotion(Promotion promotion) {
        mPromotion = promotion;
    }

    public int getNbEleves() {
        return mNbEleves;
    }

    public void setNbEleves(int nbEleves) {
        this.mNbEleves = nbEleves;
    }
}
