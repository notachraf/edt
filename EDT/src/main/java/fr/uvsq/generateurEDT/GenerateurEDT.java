package fr.uvsq.generateurEDT;

import java.util.List;

public class GenerateurEDT {
    private DonneesDB mDonneesDB;
    private Double mTemperature;
    private List<EDT> mSolutions;
    private double mConstanteDiminue;
    private final static int TEMP_FINAL = 0;

    public GenerateurEDT(DonneesDB donneesDB, Double temperature, List<EDT> solutions, Double constanteDiminue) {
        mDonneesDB = donneesDB;
        mTemperature = temperature;
        mSolutions = solutions;
        mConstanteDiminue = constanteDiminue;
    }

    public DonneesDB getDonneesDB() {
        return mDonneesDB;
    }

    public void setDonneesDB(DonneesDB donneesDB) {
        mDonneesDB = donneesDB;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public List<EDT> getSolutions() {
        return mSolutions;
    }

    public void setSolutions(List<EDT> solutions) {
        mSolutions = solutions;
    }

    public Double getConstanteDiminue() {
        return mConstanteDiminue;
    }

    public void setConstanteDiminue(Double constanteDiminue) {
        mConstanteDiminue = constanteDiminue;
    }

    public static int getTempFinal() {
        return TEMP_FINAL;
    }

    /**
     * Retourne l'energie accepteé.
     * @param energie1 energie de la solution initiale
     * @param energie2 energie de la solution voisine
     * @param temp  temperature
     * @return energie acceptée.
     */
    double accepteSolution(int energie1, int energie2, double temp){
        return 0.0;
    }

    /**
     * Déroule l'algorithme du recuit simulé
     */
    public void recuitSimule(){
        
    }

    /**
     * Modifie l'edt passé en argument
     * @param edt
     * @return un nouveau EDT.
     */
    public EDT modifierSolution(EDT edt){
        return edt;
    }

    /**
     * Créer une solution d'EDT initiale
     * @return edt
     */
    public EDT solutionInitiale(){
        return new EDT();
    }
    
    
}
