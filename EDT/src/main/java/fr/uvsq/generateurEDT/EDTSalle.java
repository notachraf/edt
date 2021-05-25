package fr.uvsq.generateurEDT;
import fr.uvsq.models.*;

public class EDTSalle {
    private Salle mSalle;
    private int[][] mEdt;
    private static final int NB_JOURS = 5;
    private static final int NB_HORAIRES = 11;


    /*
       *********************************************************************
       *                             EDTSALLES                             *
       *********************************************************************
       *      LUNDI   *   MARDI   *   MERCREDI   *   JEUDI   *   VENDREDI    *
       *********************************************************************
       *  8h| 
       *********************************************************************
       *  9h|
       *********************************************************************
       * 10h|
       **********************************************************************
       * 11h|
       * ...
       * 19h|
       **********************************************************************
     */

    /**
     * constructeur par défaut
     */
    public EDTSalle() {
        mSalle = null;
    	mEdt = new int[NB_HORAIRES][NB_JOURS];
        for( int h = 0; h < NB_HORAIRES; h++){
            for (int j = 0; j < NB_JOURS; j++){
                mEdt[h][j] = -1;
            }
        }
    }

    /**
     * Création d'un planning pour salle
     * @param salle salle initiale
     * @param edt edt initiale
     */
    public EDTSalle(Salle salle, int[][] edt) {
        mSalle = salle;
        for( int h = 0; h < NB_HORAIRES; h++){
            for (int j = 0; j < NB_JOURS; j++){
                mEdt[h][j] = edt[h][j];
            }
        }
    }

    /**
     *
     * @return salle actuel
     */
    public Salle getSalle() {
        return mSalle;
    }

    /**
     *
     * @param salle salle à fixer
     */
    public void setSalle(Salle salle) {
        mSalle = salle;
    }

    /**
     *
     * @return emploi du temps actuel
     */
    public int[][] getEdt() {
        return mEdt;
    }

    /**
     *
     * @param edt edt à fixer
     */
    public void setEdt(int[][] edt) {
        mEdt = edt;
    }

    /**
     *
     * @return nombre de jours actuel.
     */
    public static final int getNbJours() {
        return NB_JOURS;
    }

    /**
     *
     * @return nombre d'horaires actuel.
     */
    public static final int getNbHoraires() {
        return NB_HORAIRES;
    }

    /**
     *
     * @param j jour
     * @param h horaire
     * @return id événement actuel.
     */
    public int getIdEvenement(int j, int h){
        return mEdt[h][j];
    }

    /**
     * Vérifie si un Évenement existe dans le planning salle
     * @param jours le jour
     * @param horaire l'horaire
     * @return boolean
     */
    public boolean aUnEvenement(int jours, int horaire) {
    	return (mEdt[horaire][jours] != -1) ? true : false;
    }

    /**
     * Modifie un événement.
     * @param jours jour à fixer
     * @param horaire  horaire à fixer
     * @param idEven idEven à fixer
     */
    public void modifieEvenement(int jours, int horaire, int idEven){
        mEdt[horaire][jours] = idEven;
    }

    /**
     * Ajoute un Événement dans le planning Salle 
     * @param e l'événement
     * @param jour le jour
     * @param horaire l'horaire
     */
    public void ajouterEvenement(Evenement e, int jour, int horaire){
    	
    	if( e != null) {
    		boolean dispo = aUnEvenement(jour, horaire);
    		if(!dispo) {
    			mEdt[horaire][jour] = e.getId();
    			Creneau creneau = new Creneau(jour,horaire, dispo, mSalle.getId());
    			e.setCreneau(creneau);
    		}
    	}
    }
    
}
