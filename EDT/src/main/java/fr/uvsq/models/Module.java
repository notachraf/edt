package fr.uvsq.models;

/**
 *
 * @Author Siham, Feriel, Aziz
 */
public class Module {
    private int mId;
    private String mNom;
    private int mNbTD;
    private int mNbCM;
    private int mNbTP;
    private int mDureeCM;
    private int mDureeTP;
    private int mDureeTD;

    public Module() {
    }

    public Module(String nom, int nbTD, int nbCM, int nbTP, int dureeCM, int dureeTP, int dureeTD) {
        mNom = nom;
        mNbTD = nbTD;
        mNbCM = nbCM;
        mNbTP = nbTP;
        mDureeCM = dureeCM;
        mDureeTP = dureeTP;
        mDureeTD = dureeTD;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

 
    public int getId() { return mId; }

  

    public int getNbTD() {
        return mNbTD;
    }

    public void setNbTD(int nbTD) {
        mNbTD = nbTD;
    }

    public int getNbCM() {
        return mNbCM;
    }

    public void setNbCM(int nbCM) {
        mNbCM = nbCM;
    }

    public int getNbTP() {
        return mNbTP;
    }

    public void setNbTP(int nbTP) {
        mNbTP = nbTP;
    }

    public int getDureeCM() {
        return mDureeCM;
    }

    public void setDureeCM(int dureeCM) {
        mDureeCM = dureeCM;
    }

    public int getDureeTP() {
        return mDureeTP;
    }

    public void setDureeTP(int dureeTP) {
        mDureeTP = dureeTP;
    }

    public int getDureeTD() {
        return mDureeTD;
    }

    public void setDureeTD(int dureeTD) {
        mDureeTD = dureeTD;
    }
}
