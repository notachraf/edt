package fr.uvsq.generateurEDT;
import fr.uvsq.models.*;
import fr.uvsq.models.Module;
public class Evenement {
	
    private int mId;
    private TypeEven mTypeEven;
    private Professeur mProfesseur;
    private Groupe mGroupe;
    private Module mModule;
    private Creneau mCreneau;
    private static int mNbEvenements = 0;

    /**
     * Initialisation d'un événement par défaut.
     */
    public Evenement() {
        mNbEvenements++;
        mId = -1;
        mTypeEven = TypeEven.CM;
        mProfesseur = null;
        mModule = null;
        mGroupe = null;
        mCreneau = null;
    }

    /**
     * Création d'événement
     * @param id id d'événement
     * @param typeEven type d'événement
     * @param professeur professeur associé à l'événement
     * @param module module associé à l'événement.
     * @param groupe groupe associé à l'événement.
     * @param creneau créneau associé à l'événement.
     */
    public Evenement(int id, TypeEven typeEven, Professeur professeur, Module module, Groupe groupe, Creneau creneau) {
        mId = id;
        mTypeEven = typeEven;
        mProfesseur = professeur;
        mModule = module;
		mGroupe = groupe;
        mCreneau = creneau;
        mNbEvenements++;
    }

    

    /**
     * 
     * @return Id actuel
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
     * @return type d'événement actuel.
     */
    public TypeEven getTypeEven() {
        return mTypeEven;
    }

    /**
     * 
     * @param typeEven typeEven à fixer
     */
    public void setTypeEven(TypeEven typeEven) {
        mTypeEven = typeEven;
    }

    /**
     * 
     * @return professeur actuel.
     */
    public Professeur getProfesseur() {
        return mProfesseur;
    }

    /**
     * 
     * @param professeur professeur à fixer
     */
    public void setProfesseur(Professeur professeur) {
        mProfesseur = professeur;
    }

    /**
     * 
     * @return module actuel
     */
    public Module getModule() {
        return mModule;
    }

    /**
     *
     * @param module module à fixer
     */
    public void setModule(Module module) {
        mModule = module;
    }

    /**
     *
     * @return groupe actuel
     */
    public Groupe getGroupe() {
        return mGroupe;
    }

    /**
     *
     * @param groupe gorupe à fixer
     */
    public void setGroupe(Groupe groupe) {
        mGroupe = groupe;
    }

    /**
     *
     * @return créneau actuel
     */
    public Creneau getCreneau() {
        return mCreneau;
    }

    /**
     *
     * @param creneau créneau à fixer
     */
    public void setCreneau(Creneau creneau) {
        mCreneau = creneau;
    }

    /**
     *
     * @return nombre d'événements actuel
     */
    public static int getNbEvenements() {
        return mNbEvenements;
    }

    public static void setmNbEvenements(int mNbEvenements) {
        Evenement.mNbEvenements = mNbEvenements;
    }
}
