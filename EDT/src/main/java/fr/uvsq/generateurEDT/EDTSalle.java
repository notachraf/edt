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

    public EDTSalle() {
    	mSalle = null;
    	mEdt = null;
    }
    
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

    public static final int getNbJours() {
        return NB_JOURS;
    }

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
    	if(mEdt[jours][horaire] != -1) return true;
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
