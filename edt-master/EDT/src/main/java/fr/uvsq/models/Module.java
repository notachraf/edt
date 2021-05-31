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

    /**
     * Constructeur par défaut.
     */
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

    /**
     * Création d'une instance de la classe Module
     * @param nom nom du module
     * @param nbCM nombre de cm
     * @param nbTD nombre de td
     * @param nbTP nombre de tp
     * @param dureeCM durée du cm
     * @param dureeTD durée des tds
     * @param dureeTP durée des tps
     */
    public Module(String nom, int nbCM, int nbTD, int nbTP, int dureeCM, int dureeTD, int dureeTP) {
        mNom = nom;
        mNbTD = nbTD;
        mNbCM = nbCM;
        mNbTP = nbTP;
        mDureeCM = dureeCM;
        mDureeTP = dureeTP;
        mDureeTD = dureeTD;
    }

    /**
     *
     * @return nom actuel
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
     * @return id actuel
     */
    public int getId() { return mId; }

    /**
     *
     * @param id id à fixer
     */
    public void setId(int id) {
    	mId = id;
    }

    /**
     *
     * @return nombre de td actuel
     */
    public int getNbTD() {
        return mNbTD;
    }

    /**
     *
     * @param nbTD nombre de td à fixer
     */
    public void setNbTD(int nbTD) {
        mNbTD = nbTD;
    }

    /**
     *
     * @return nombre de cm actuel
     */
    public int getNbCM() {
        return mNbCM;
    }

    /**
     *
     * @param nbCM nombre de cm à fixer
     */
    public void setNbCM(int nbCM) {
        mNbCM = nbCM;
    }

    /**
     *
     * @return nombre de tp actuel
     */
    public int getNbTP() {
        return mNbTP;
    }

    /**
     *
     * @param nbTP nombre de tp à fixer
     */
    public void setNbTP(int nbTP) {
        mNbTP = nbTP;
    }

    /**
     *
     * @return durée de cm actuelle
     */
    public int getDureeCM() {
        return mDureeCM;
    }

    /**
     *
     * @param dureeCM durée de cm à fixer
     */
    public void setDureeCM(int dureeCM) {
        mDureeCM = dureeCM;
    }

    /**
     *
     * @return durée de tp actuelle
     */
    public int getDureeTP() {
        return mDureeTP;
    }

    /**
     *
     * @param dureeTP durée de tp à fixer
     */
    public void setDureeTP(int dureeTP) {
        mDureeTP = dureeTP;
    }

    /**
     *
     * @return durée de Td actuelle
     */
    public int getDureeTD() {
        return mDureeTD;
    }

    /**
     *
     * @param dureeTD durée de TD à fixer
     */
    public void setDureeTD(int dureeTD) {
        mDureeTD = dureeTD;
    }

    @Override
	public String toString() {
		return "Module [mId=" + mId + ", mNom=" + mNom + ", mNbTD=" + mNbTD + ", mNbCM=" + mNbCM + ", mNbTP=" + mNbTP
				+ ", mDureeCM=" + mDureeCM + ", mDureeTP=" + mDureeTP + ", mDureeTD=" + mDureeTD + "]";
	}

}
