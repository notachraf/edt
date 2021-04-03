package fr.uvsq.generateurEDT;

import fr.uvsq.models.Salle;

public class EDTSalle {
    private Salle mSalle;
    private int[][] mEdt;
    private static final int NB_JOURS = 5;
    private static final int NB_HORAIRES = 6;

    /*
       *********************************************************************
       *                             EDTSALLES                             *
       *********************************************************************
       *    LUNDI   *   MARDI   *   MERCREDI   *   JEUDI   *   VENDREDI    *
       *********************************************************************
       * 1 | 
       *********************************************************************
       * 2 |
       *********************************************************************
       * 3 |
       **********************************************************************
       * 4 |
       **********************************************************************
     */

    public EDTSalle(Salle salle, int[][] edt) {
        mSalle = salle;
        mEdt = edt;
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

    public static int getNbJours() {
        return NB_JOURS;
    }

    public static int getNbHoraires() {
        return NB_HORAIRES;
    }

    /**
     * Vérifie si un Évenement exite dans le planning salle
     * @param jours le jour
     * @param horaire l'horaire
     * @return boolean
     */
    public boolean aUnEvenement(int jours, int horaire) {
        return false;
    }

    /**
     * Ajoute un Événement dans le planning Salle 
     * @param e l'événement
     * @param jours le jour
     * @param horaire l'horaire
     */
    public void ajouterEvenement(Evenement e, int jours, int horaire){
    }
    
}
