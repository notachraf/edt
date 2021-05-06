package fr.uvsq.generateurEDT;
import java.util.List;
import java.util.Map;

public class GenerateurEDT {
    private DonneesEDT mDonneesEDT;
    private double mConstRef;
    private final static int mTempFinal = 0;
    private double mTemperature;
    private List<EDT> mSolutions; // pour débugger
    private EDT mSolutionFinale;

    public GenerateurEDT(double constRef, double temperature, List<EDT> solutions, EDT solutionFinale, DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
        mTemperature = temperature;
        mSolutions = solutions;
        mConstRef = constRef;
    }
    
    public GenerateurEDT() {}

    public void setSolutionFinale(EDT solutionFinale) {
        mSolutionFinale = solutionFinale;
    }
    public EDT getSolutionFinale(){ return null;}

    public DonneesEDT getDonneesEDT() {
        return mDonneesEDT;
    }

    public void setDonneesEDT(DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
    }

    public double getTemperature() {
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

    public double getConstanteDiminue() {
        return mConstRef;
    }

    public void setConstanteDiminue(Double constanteDiminue) {
    	mConstRef = constanteDiminue;
    }

    public static int getTempFinal() {
        return mTempFinal;
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
    public Map<Integer, List<Evenement>> getEvenementsParJour(){
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
