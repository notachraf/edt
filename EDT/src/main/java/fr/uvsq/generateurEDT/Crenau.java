package fr.uvsq.generateurEDT;

// attribut doit commencer par m
public class Crenau {
    private int mJour;
    private int mHoraire;
    private boolean mDisponible ;
    private int mIdsalle;

    /**
     * 
     */
    public Crenau() {
    }

    /**
     * 
     * @param jour
     * @param horaire
     * @param disponible
     * @param idsalle
     */
    public Crenau(int jour, int horaire, boolean disponible, int idsalle) {
        mJour = jour;
        mHoraire = horaire;
        mDisponible = disponible;
        mIdsalle = idsalle;
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
