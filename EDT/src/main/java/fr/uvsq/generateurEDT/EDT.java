package fr.uvsq.generateurEDT;

import java.util.List;

public class EDT {
    private List<EDTSalle> mListeEDTSalles;
    private int mEnergie;
    private DonneesEDT mDonneesEDT;
    private List<Evenement> mListeEvenements;

    public EDT(List<EDTSalle> listeEDTSalles, int energie, DonneesEDT donneesEDT) {
        mListeEDTSalles = listeEDTSalles;
        mEnergie = energie;
        mDonneesEDT = donneesEDT;
    }

    public EDT() {
    }

    public List<EDTSalle> getListeEDTSalles() {
        return mListeEDTSalles;
    }

    public void setListeEDTSalles(List<EDTSalle> listeEDTSalles) {
        mListeEDTSalles = listeEDTSalles;
    }

    public int getEnergie() {
        return mEnergie;
    }

    public void setEnergie(int energie) {
        mEnergie = energie;
    }

    public DonneesEDT getDonneesDB() {
        return mDonneesEDT;
    }

    public void setDonneesDB(DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
    }

    /**
     * Contrainte pour l'heure de déroulement des cours
     * Contrainte pour l'écart entre deux Evénements.
     */

    /**
     * Calcule le nombre de fois que la capacité d'une
     * Salle n'est pas respecté.
     * @return le nombre de contraintes
     */
    private int contrainteCapaciteSalle(){
        return 0;
    }

    /**
     * Calcule le nombre de fois que le type de cours
     * effectué dans une salle n'est pas respecté.
     * @return le nombre de contraintes.
     */
    private int contrainteTypeSalle(){
        return 0;
    }

    /**
     * Calcule le nombre de salles réservées par un prof
     * en même temps.
     * @return le nombre ce contraintes.
     */
    private int contrainteReservationProf(){
        return 0;
    }
}
