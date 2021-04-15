package fr.uvsq.generateurEDT;

import java.util.List;
import java.util.Map;

public class GenerateurEDT {
    private DonneesEDT mDonneesEDT;
    private Double mTemperature;
    private List<EDT> mSolutions; // pour débugger
    private EDT mSolutionFinale;
    private double mConstanteDiminue;
    private final static int TEMP_FINAL = 0;

    public GenerateurEDT(DonneesEDT donneesEDT, Double temperature, List<EDT> solutions, Double constanteDiminue) {
        mDonneesEDT = donneesEDT;
        mTemperature = temperature;
        mSolutions = solutions;
        mConstanteDiminue = constanteDiminue;
    }

    public void setSolutionFinale(EDT solutionFinale) {
        mSolutionFinale = solutionFinale;
    }
    public EDT getSolutionFinale(){ return null;}

    public DonneesEDT getDonneesDB() {
        return mDonneesEDT;
    }

    public void setDonneesDB(DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
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

    /**
     * Retourne un dictionnaire contenant pour chaque entrée
     * Une liste d'événement pour chaque jour.
     * @return Map
     */
    public Map<Integer, List<Evenement>> getEvenementParJour(){
        return null;
    }

    /**
     * Réoptimise un emploi du temps
     */
    public void reoptimise(){

    }

    /**
     * Retourne la solution de l'EDT en Latex
     * @return StringBuilder
     */
    public StringBuilder getSolutionEnLatex(){
        return null;
    }

}
