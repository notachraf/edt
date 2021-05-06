package fr.uvsq.models;

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
    	mId = -1;
    	mNom = "";
        mNbTD = -1;
        mNbCM = -1;
        mNbTP = -1;
        mDureeCM = -1;
        mDureeTP = -1;
        mDureeTD = -1;
    }

    public Module(String nom, int nbCM, int nbTD, int nbTP, int dureeCM, int dureeTD, int dureeTP) {
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

    public void setId(int id) {
    	mId = id;
    }

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
