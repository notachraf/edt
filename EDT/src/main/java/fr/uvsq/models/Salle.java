package fr.uvsq.models;

public class Salle {

    private int mId;
    private String mNom;
    private int mCapacite;
    private TypeSalle mTypeSalle;

    /**
     * Constructeur par défaut
     */
    public Salle(){
        mId = -1;
        mNom = "";
        mCapacite = 0;
        mTypeSalle = TypeSalle.CM;
    }

    /**
     * Création d'une instance de salle
     * @param nom nom de la salle
     * @param capacite capacité de la salle
     * @param typeSalle type de salle
     */
    public Salle(String nom, int capacite, TypeSalle typeSalle) {
        mId = -1;
        mNom = nom;
        mCapacite = capacite;
        mTypeSalle = typeSalle;
    }

	/**
     *
     * @return id de la salle actuel
     */
    public int getId() {
        return mId;
    }

    /**
     *
     * @param id id à fixer
     */
    public void setId(int id) {
        mId = id;
    }

    /**
     *
     * @return nom de la salle actuel
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
     * @return capacité de la salle actuelle
     */
    public int getCapacite() {
        return mCapacite;
    }

    /**
     *
     * @param capacite capacité à fixer
     */
    public void setCapacite(int capacite) {
        mCapacite = capacite;
    }

    /**
     *
     * @return type de la salle actuel
     */
    public TypeSalle getTypeSalle() {
        return mTypeSalle;
    }

    /**
     *
     * @param typeSalle typeSalle à fixer
     */
    public void setTypeSalle(TypeSalle typeSalle) {
        mTypeSalle = typeSalle;
    }
    
   @Override
	public String toString() {
		return "Salle [mId=" + mId + ", mNom=" + mNom + ", mCapacite=" + mCapacite +" , mTypeSalle="+ mTypeSalle +"]";
	}
    
    
}
