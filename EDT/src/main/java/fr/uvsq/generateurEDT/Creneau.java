package fr.uvsq.generateurEDT;

public class Creneau {
    private int mJour;
    private int mHoraire;
    private boolean mDisponible ;
    private int mIdsalle;

    /**
     * 
     */
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
    public Creneau(int jour, int horaire, boolean disponible, int idSalle) {
        mJour = jour;
        mHoraire = horaire;
        mDisponible = disponible;
        mIdsalle = idSalle;
    }

    /**
     * 
     * @return
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
     * @return
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
     * @return
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
     * @return
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
