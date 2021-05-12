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

    public EDTSalle() {
        mSalle = null;
    	mEdt = new int[NB_HORAIRES][NB_JOURS];
        for( int h = 0; h < NB_HORAIRES; h++){
            for (int j = 0; j < NB_JOURS; j++){
                mEdt[h][j] = -1;
            }
        }
    }
    
    public EDTSalle(Salle salle, int[][] edt) {
        mSalle = salle;
        for( int h = 0; h < NB_HORAIRES; h++){
            for (int j = 0; j < NB_JOURS; j++){
                mEdt[h][j] = edt[h][j];
            }
        }
    }

    public Salle getSalle() {
        return mSalle;
    }

    public void setSalle(Salle salle) {
        mSalle = salle;
    }

    public int[][] getEdt() {
        return mEdt;
    }

    public void setEdt(int[][] edt) {
        mEdt = edt;
    }

    public static final int getNbJours() {
        return NB_JOURS;
    }

    public static final int getNbHoraires() {
        return NB_HORAIRES;
    }

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
