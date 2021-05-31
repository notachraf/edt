package fr.uvsq.generateurEDT;

public class Creneau {
    private int mJour;
    private int mHoraire;
    private boolean mDisponible ;
    private int mIdsalle;

    /**
     * Constructeur par défaut.
     */
    public Creneau() {
    	mJour = -1;
    	mHoraire = -1;
    	mDisponible = true;
    	mIdsalle = -1;
    }

    /**
     * Creation de Créneau
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
     * @return jour actuel
     */
    public int getJour() {
        return mJour;
    }

    /**
     * 
     * @param jour jour à fixer
     */
    public void setJour(int jour) {
        mJour = jour;
    }

    /**
     * 
     * @return horaire actuel
     */
    public int getHoraire() {
        return mHoraire;
    }

    /**
     * 
     * @param horaire horaire à fixer
     */
    public void setHoraire(int horaire) {
        mHoraire = horaire;
    }

    /**
     * 
     * @return un boolean selon la disponibilité
     */
    public boolean isDisponible() {
        return mDisponible;
    }

    /**
     * 
     * @param disponible disponibilité à fixer
     */
    public void setDisponible(boolean disponible) {
        mDisponible = disponible;
    }

    /**
     * 
     * @return idSalle actuel
     */
    public int getIdsalle() {
        return mIdsalle;
    }

    /**
     * 
     * @param idsalle idSalle à fixer
     */
    public void setIdsalle(int idsalle) {
        mIdsalle = idsalle;
    }
}
