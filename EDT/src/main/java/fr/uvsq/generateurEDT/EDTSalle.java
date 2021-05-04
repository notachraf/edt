package fr.uvsq.generateurEDT;

import fr.uvsq.models.Salle;
/**
 * 
 * @author moulhat
 *
 */
public class EDTSalle {
    private Salle mSalle;
    private int[][] mEdt;
    private static final int NB_JOURS = 5;
    private static final int NB_HORAIRES = 12;

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

    //les contructeurs
    
    public EDTSalle() {
    	mSalle = null;
    	mEdt = null;
    }
    
    public EDTSalle(Salle salle, int[][] edt) {
        mSalle = salle;
        mEdt = edt;
    }

    //les méthodes
    /**
     * 
     * @return la salle d'un EDTSalle
     */
    public Salle getSalle() {
        return mSalle;
    }

    /**
     * 
     * @param salle
     */
    public void setSalle(Salle salle) {
        mSalle = salle;
    }

    /**
     * 
     * @return la matrice d'un EDTSalle
     */
    public int[][] getEdt() {
        return mEdt;
    }

    /**
     * 
     * @param edt
     */
    public void setEdt(int[][] edt) {
        mEdt = edt;
    }
    
    /**
     * 
     * @return le nombre de jours que prend la matrice d'un EDTSalle
     */
    public static final int getNbJours() {
        return NB_JOURS;
    }

    /**
     * 
     * @return le nombre d'horaires que prend la mtrice d'un EDTSalle
     */
    public static final int getNbHoraires() {
        return NB_HORAIRES;
    }

    /**
     * Vérifie si un Évenement existe dans le planning salle
     * @param jours le jour
     * @param horaire l'horaire
     * @return boolean
     */
    public boolean aUnEvenement(int jours, int horaire) {
    	if(mEdt[jours][horaire] > 0) return true;
        return false;
    }

    /**
     * Ajoute un Événement dans le planning Salle 
     * @param e l'événement
     * @param jours le jour
     * @param horaire l'horaire
     */
    public void ajouterEvenement(Evenement e, int jour, int horaire){
    	
    	if( e != null) {
    		boolean dispo = !(this.aUnEvenement(jour, horaire));
    		if(dispo) {
    			mEdt[jour][horaire] = e.getId();
    			Creneau creneau = new Creneau(jour,horaire, !dispo, mSalle.getId());
    			e.setCreneau(creneau);
    		}
    	}
    }
    
}
