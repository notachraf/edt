package fr.uvsq.models;
/**
 * 
 * @author Aziz, Feriel Siham, moulhat
 *
 */

public class Groupe {
	//les attributs
	   private String mNom;
	    private Promotion mPromotion;
	    private int mNbEleves;
	    
	    //Les constructeurs
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
	    //les méthodes

	    /**
	     * 
	     * @return le nom d'un groupe
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
	     * @return la promotion auquel appartient un groupe
	     */
	    public Promotion getPromotion() {
	        return mPromotion;
	    }
	    
	    /**
	     * 
	     * @param promotion
	     */
	    public void setPromotion(Promotion promotion) {
	        mPromotion = promotion;
	    }
	    /**
	     * 
	     * @return le nombre d'élèves d'un groupe
	     */
	    public int getNbEleves() {
	        return mNbEleves;
	    }
	    /**
	     * 
	     * @param nbEleves
	     */
	    public void setNbEleves(int nbEleves) {
	        this.mNbEleves = nbEleves;
	    }
}
