package fr.uvsq.models;

/**
 * 
 * @Author Siham, Feriel, Aziz
 */
public class Module {
    private String mNom;
    private int mId;
    private int mDuree;
    private int mNbCoursSemaine;
    private boolean mTD;
    private boolean mTP;

    public Module() {
    }

    /**
     * 
     * @param nom
     * @param duree
     * @param nbCoursSemaine
     * @param TD
     * @param TP
     */
    public Module(String nom, int duree, int nbCoursSemaine, boolean TD, boolean TP) {
        mNom = nom;
        mDuree = duree;
        mNbCoursSemaine = nbCoursSemaine;
        mTD = TD;
        mTP = TP;
    }

    /**
     * 
     * @return
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
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        mId = id;
    }

    /**
     * 
     * @return
     */
    public int getDuree() {
        return mDuree;
    }

    /**
     * 
     * @param duree
     */
    public void setDuree(int duree) {
        mDuree = duree;
    }

    /**
     * 
     * @return
     */
    public int getNbCoursSemaine() {
        return mNbCoursSemaine;
    }

    /**
     * 
     * @param nbCoursSemaine
     */
    public void setNbCoursSemaine(int nbCoursSemaine) {
        mNbCoursSemaine = nbCoursSemaine;
    }

    /**
     * 
     * @return
     */
    public boolean isTD() {
        return mTD;
    }

    /**
     * 
     * @param TD
     */
    public void setTD(boolean TD) {
        mTD = TD;
    }

    /**
     * 
     * @return
     */
    public boolean isTP() {
        return mTP;
    }

    /**
     * 
     * @param TP
     */
    public void setTP(boolean TP) {
        mTP = TP;
    }
}
