package fr.uvsq.models;

/**
 *
 * @Author Siham, Feriel, Aziz
 */
public class Module {
	//les attributs
	   private int mId;
	    private String mNom;
	    private int mNbTD;
	    private int mNbCM;
	    private int mNbTP;
	    private int mDureeCM;
	    private int mDureeTP;
	    private int mDureeTD;
	    
	    //les constructeurs
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
	    
	    //les méthodes
	    /**
	     * 
	     * @return le nom d'un module
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
	     * @return l'identifiant d'un module
	     */
	    public int getId() { return mId; }

	    /**
	     * 
	     * @param id
	     */
	    public void setId(int id) {
	    	mId = id;
	    }

	    /**
	     * 
	     * @return le nombre de cours de type TD d'un module sur un semestre
	     */
	    public int getNbTD() {
	        return mNbTD;
	    }

	    /**
	     * 
	     * @param nbTD
	     */
	    public void setNbTD(int nbTD) {
	        mNbTD = nbTD;
	    }

	    /**
	     * 
	     * @return le nombre de cours de type CM d'un module sur un semestre

	     */
	    public int getNbCM() {
	        return mNbCM;
	    }

	    /**
	     * 
	     * @param nbCM
	     */
	    public void setNbCM(int nbCM) {
	        mNbCM = nbCM;
	    }

	    /**
	     * 
	     * @return le nombre de cours de type TP d'un module sur un semestre
	     */
	    public int getNbTP() {
	        return mNbTP;
	    }

	    /**
	     * 
	     * @param nbTP
	     */
	    public void setNbTP(int nbTP) {
	        mNbTP = nbTP;
	    }

	    /**
	     * 
	     * @return la duree de cours de type CM d'un module
	     */
	    public int getDureeCM() {
	        return mDureeCM;
	    }

	    /**
	     * 
	     * @param dureeCM
	     */
	    public void setDureeCM(int dureeCM) {
	        mDureeCM = dureeCM;
	    }

	    /**
	     * 
	     * @return la duree de cours de type TP d'un module
	     */
	    public int getDureeTP() {
	        return mDureeTP;
	    }
	    
	    /**
	     * 
	     * @param dureeTP
	     */
	    public void setDureeTP(int dureeTP) {
	        mDureeTP = dureeTP;
	    }

	    /**
	     * 
	     * @return la duree de cours de type TD d'un module
	     */
	    public int getDureeTD() {
	        return mDureeTD;
	    }
	    
	    /**
	     * 
	     * @param dureeTD
	     */
	    public void setDureeTD(int dureeTD) {
	        mDureeTD = dureeTD;
	    }
}
