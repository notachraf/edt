package fr.uvsq.generateurEDT;
/**
 * Cette classe représente l'entité Creneau.
 * @Author Moulhat
 */

public class Creneau {
	// les attributs
	   private int mJour;
	    private int mHoraire;
	    private boolean mDisponible ;
	    private int mIdsalle;

	    // les constructeurs
	    public Creneau() {
	    	mJour = -1;
	    	mHoraire = -1;
	    	mDisponible = true;
	    	mIdsalle = -1;
	    }

	    /**
	     * 
	     * @param jour
	     * @param horaire
	     * @param disponible
	     * @param idSalle
	     */
	    public Creneau(int jour, int horaire, boolean disponible, int idsalle) {
	        mJour = jour;
	        mHoraire = horaire;
	        mDisponible = disponible;
	        mIdsalle = idsalle;
	    }
	    
	    // les méthodes

	    /**
	     * 
	     * @return l'attribut mJour de type entier de Creneau
	     */
	    public int getJour() {
	        return mJour;
	    }

	    /**
	     * 
	     * @param jour
	     */
	    public void setJour(int jour) {
	        mJour = jour;
	    }

	    /**
	     * 
	     * @return l'attribut mHoraire de type entier de Creneau
	     */
	    public int getHoraire() {
	        return mHoraire;
	    }

	    /**
	     * 
	     * @param horaire
	     */
	    public void setHoraire(int horaire) {
	        mHoraire = horaire;
	    }

	    /**
	     * 
	     * @return la disponibilité d'un Creneau
	     */
	    public boolean isDisponible() {
	        return mDisponible;
	    }

	    /**
	     * 
	     * @param disponible
	     */
	    public void setDisponible(boolean disponible) {
	        mDisponible = disponible;
	    }

	    /**
	     * 
	     * @return l'identifiant de la salle d'un Creneau
	     */
	    public int getIdsalle() {
	        return mIdsalle;
	    }

	    /**
	     * 
	     * @param idsalle
	     */
	    public void setIdsalle(int idsalle) {
	        mIdsalle = idsalle;
	    }
}
