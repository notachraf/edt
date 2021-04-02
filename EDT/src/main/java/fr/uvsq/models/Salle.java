package fr.uvsq.models;

/**
 *
 * @Author Siham, Feriel, Aziz
 */
public class Salle {

    private int mId;
    private String mNom;
    private int mCapacite;
    private TypeSalle mTypeSalle;

    /**
     * 
     */
    public Salle(){
        mId = -1;
        mNom = "";
        mCapacite = 0;
        mTypeSalle = TypeSalle.COURS;
    }

    /**
     * 
     * @param nom
     * @param capacite
     * @param typeSalle
     */
    public Salle(String nom, int capacite, TypeSalle typeSalle) {
        mId = -1;
        mNom = nom;
        mCapacite = capacite;
        mTypeSalle = typeSalle;
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
    public int getCapacite() {
        return mCapacite;
    }

    /**
     * 
     * @param capacite
     */
    public void setCapacite(int capacite) {
        mCapacite = capacite;
    }

    /**
     * 
     * @return
     */
    public TypeSalle getTypeSalle() {
        return mTypeSalle;
    }

    /**
     * 
     * @param typeSalle
     */
    public void setTypeSalle(TypeSalle typeSalle) {
        mTypeSalle = typeSalle;
    }
}