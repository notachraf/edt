package fr.uvsq.generateurEDT;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author moulhat
 *
 */
public class GenerateurEDT {
	//les attributs
    private DonneesEDT mDonneesEDT;
    private double mConstRef;
    private final static int mTempFinal = 0;
    private double mTemperature;
    private List<EDT> mSolutions; // pour débugger
    private EDT mSolutionFinale;

    //les constructeurs
    /**
     * 
     * @param constRef
     * @param temperature
     * @param solutions
     * @param solutionFinale
     * @param donneesEDT
     */
    public GenerateurEDT(double constRef, double temperature, List<EDT> solutions, EDT solutionFinale, DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
        mTemperature = temperature;
        mSolutions = solutions;
        mConstRef = constRef;
    }
    
    public GenerateurEDT() {}

    // les méthodes 
    
    /**
     * 
     * @param solutionFinale
     */
    public void setSolutionFinale(EDT solutionFinale) {
        mSolutionFinale = solutionFinale;
    }
    
    /**
     * 
     * @return la solution finale d'EDT générée
     */
    public EDT getSolutionFinale(){ return null;}

    /**
     * 
     * @return les donnees d'un EDT pour un generateurEDT
     */
    public DonneesEDT getDonneesEDT() {
        return mDonneesEDT;
    }

    /**
     * 
     * @param donneesEDT
     */
    public void setDonneesEDT(DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
    }

    /**
     * 
     * @return la température que prend un genereEDT
     */
    public double getTemperature() {
        return mTemperature;
    }

    /**
     * 
     * @param temperature
     */
    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    /**
     * 
     * @return une liste de solutions d'EDT créés
     */
    public List<EDT> getSolutions() {
        return mSolutions;
    }

    /**
     * 
     * @param solutions
     */
    public void setSolutions(List<EDT> solutions) {
        mSolutions = solutions;
    }

    /**
     * 
     * @return la valeur de refroidissement de température d'un generateurEDT
     */
    public double getConstanteDiminue() {
        return mConstRef;
    }

    /**
     * 
     * @param constanteDiminue
     */
    public void setConstanteDiminue(Double constanteDiminue) {
    	mConstRef = constanteDiminue;
    }

    /**
     * 
     * @return la température final que prend un generateurEDT
     */
    public static int getTempFinal() {
        return mTempFinal;
    }

    /**
     * Retourne l'energie accepteé.
     * @param energie1 energie de la solution initiale
     * @param energie2 energie de la solution voisine
     * @param temp  une température
     * @return energie acceptée.
     */
    double accepteSolution(int energie1, int energie2, double temp){
    	
    	 int delta = energie1 - energie2;
    	 double prob = Math.random();
    	 //double div = -(delta/temp);
    	 
    	 if( (prob < Math.exp(-(delta/temp))) || (energie1>energie2) ) return (double)energie2;
    	 else return (double)energie1;
     }

    /**
     * Déroule l'algorithme du recuit simulé
     */
    public void recuitSimule(){
    	EDT init = solutionInitiale();
        EDT solutionFinale = init;
    	int e1 = (int)init.calculEnergie(0, 0, 0);
    	init.setEnergie(e1);
    	double temp = 1000.2;
    	double ref = 0.5;
    	
    	while(temp > mTempFinal) {
        	EDT vois = modifierSolution(init);
        	int e2 = (int)vois.calculEnergie(0, 0, 0);
        	vois.setEnergie(e2);
        	if( accepteSolution(init.getEnergie(),vois.getEnergie(),temp) == (double)vois.getEnergie()) solutionFinale = vois;
        	temp = (1-ref)*temp;
    	}
    	
    	mSolutionFinale = solutionFinale;
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
