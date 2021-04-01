package fr.uvsq.models;

public class Salle {

    private int mId;
    private String mNom;
    private int mCapacite;
    private TypeSalle mTypeSalle;

    public Salle(){
        mId = -1;
        mNom = "";
        mCapacite = 0;
        mTypeSalle = TypeSalle.INCONNU;
    }

    public Salle(String nom, int capacite, TypeSalle typeSalle) {
        mId = -1;
        mNom = nom;
        mCapacite = capacite;
        mTypeSalle = typeSalle;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public int getCapacite() {
        return mCapacite;
    }

    public void setCapacite(int capacite) {
        mCapacite = capacite;
    }

    public TypeSalle getTypeSalle() {
        return mTypeSalle;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        mTypeSalle = typeSalle;
    }
}
