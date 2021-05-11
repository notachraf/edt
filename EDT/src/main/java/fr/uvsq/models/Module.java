package fr.uvsq.models;

/**
 *
 * @Author Siham, Feriel, Aziz
 */
public class Module {

	private int mId;
	private String mNom;
	private int mNbTD; // utiliser des noms parlants, pas d'abreviation !!
	private int mNbCM; // ici aussi
	private int mNbTP; // ici aussi
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

	public Module(String nom, int nbTD, int nbCM, int nbTP, int dureeCM, int dureeTP, int dureeTD, int profId) {
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

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		result = prime * result + ((mNom == null) ? 0 : mNom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (mId != other.mId)
			return false;
		if (mNom == null) {
			if (other.mNom != null)
				return false;
		} else if (!mNom.equals(other.mNom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Module [mId=" + mId + ", mNom=" + mNom + ", mNbTD=" + mNbTD + ", mNbCM=" + mNbCM + ", mNbTP=" + mNbTP
				+ ", mDureeCM=" + mDureeCM + ", mDureeTP=" + mDureeTP + ", mDureeTD=" + mDureeTD + "]";
	}

}
