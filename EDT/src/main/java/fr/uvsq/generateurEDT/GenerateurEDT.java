package fr.uvsq.generateurEDT;
import java.util.*;

public class GenerateurEDT {
    private DonneesEDT mDonneesEDT;
    private double mConstRef;
    private final static double mTempFinal = 0.1;
    private double mTemperature;
    private List<EDT> mSolutions; // pour débugger
    private EDT mSolutionFinale;

    public GenerateurEDT(double constRef, double temperature, List<EDT> solutions, EDT solutionFinale, DonneesEDT donneesEDT) {
        mDonneesEDT = donneesEDT;
        mTemperature = temperature;
        mSolutions = solutions;
        mConstRef = constRef;
    }
    
    public GenerateurEDT() {
    	mDonneesEDT = null;
    	mConstRef = 0.5;
    	mTemperature = 10000.00;
    	mSolutions = new ArrayList<EDT>();
    	mSolutionFinale = null;
    }

    public void setSolutionFinale(EDT solutionFinale) {
        mSolutionFinale = solutionFinale;
    }

    public EDT getSolutionFinale(){ return mSolutionFinale;}

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

    public static double getTempFinal() {
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
    	int delta = energie2 - energie1;
        double prob = Math.random();
    	       	 
        if(delta<0) return (double)energie2;
    	else {
    		if (prob < Math.exp(-(delta/temp))) return (double)energie2;
    		else return (double)energie1;
    	}  
    }

    /**
     * Déroule l'algorithme du recuit simulé
     */
    
    public void recuitSimule(){

     	EDT init = solutionInitiale();
	   	init.setEnergie((int)init.calculEnergie(0, 0, 0));
	   	mSolutionFinale = new EDT(init.getListeEDTSalles(),init.getEnergie(),init.getDonneesEDT());
		System.out.println("Energie avant: " + mSolutionFinale.getEnergie());
		//System.out.println("Taille liste avant: " + mSolutions.size());
        int nbRep = 0;

        while(mTemperature > mTempFinal) {
            //while(nbRep < 5000) {

                System.out.println("In the loop : nbRep: " + nbRep);

                EDT voisin = modifierSolution(mSolutionFinale);
		       	int e2 = (int)voisin.calculEnergie(0, 0, 0);
		       	voisin.setEnergie(e2);
		       	if( accepteSolution(mSolutionFinale.getEnergie(),voisin.getEnergie(),mTemperature) == (double)voisin.getEnergie()) {
		       		mSolutions.add(mSolutionFinale);
		       		mSolutionFinale = voisin;
		       	}
		       	nbRep++;
		   	//}
		   	mTemperature = mConstRef*mTemperature;
	   	}

	   	for(EDT edt : mSolutions) {
	   		//System.out.println("Nouvelle solution Energie = " + edt.getEnergie());
	   		if(edt.getEnergie() < mSolutionFinale.getEnergie()) mSolutionFinale = edt;
	   	}
	   	System.out.println("Energie apres changement: " + mSolutionFinale.getEnergie());
		// System.out.println("Taille liste avant: " + mSolutions.size());

    }
    /**
     * Modifie l'edt passé en argument
     * @param edt
     * @return un nouveau EDT.
     */
    public EDT modifierSolution(EDT edt){
        int nbSalles = mDonneesEDT.getListeSalles().size();
        int nbCreneaux = EDTSalle.getNbHoraires() * EDTSalle.getNbJours();

        Random randSalle = new Random(System.currentTimeMillis());
        Random randJour = new Random(System.currentTimeMillis());
        Random randHoraire = new Random(System.currentTimeMillis());

        int randSalle1 = 0, randSalle2 = 0, j1 = 0, j2 = 0, h1 = 0, h2 = 0, idEven1 = -1, idEven2 = -1, i = 0;

        //Choisir deux salles différentes aléatoirement
        do {
            randSalle1 = randSalle.nextInt(nbSalles);
            randSalle2 = randSalle.nextInt(nbSalles);
        } while( randSalle1 == randSalle2 );

        //System.out.println("+++++ Salle1: " + randSalle1 + "salle2: " + randSalle2);

        while ( (idEven1 == idEven2  || idEven1 == -1 || idEven2 == -1) && (i != nbCreneaux)){
            //Choisir deux jours différents aléatoirement
            j1 = randJour.nextInt(EDTSalle.getNbJours());
            j2 = randJour.nextInt(EDTSalle.getNbJours());

            //Choisir deux horaires différents aléatoirement
            h1 = randHoraire.nextInt(EDTSalle.getNbHoraires());
            h2 = randHoraire.nextInt(EDTSalle.getNbHoraires());
            idEven1 = edt.getListeEDTSalles().get(randSalle1).getIdEvenement(j1, h1);
            idEven2 = edt.getListeEDTSalles().get(randSalle2).getIdEvenement(j2, h2);
            ++i;
        }

        //Si n'y a pas d'événement à inter changer.
        if( i > nbCreneaux && (idEven1 == -1 || idEven2 == -1) )
            return edt;

        EDT nouvelleEdt = new EDT(edt.getListeEDTSalles(),edt.getEnergie(),edt.getDonneesEDT());
        nouvelleEdt.getListeEDTSalles().get(randSalle1).modifieEvenement(j1, h1, idEven2);
        nouvelleEdt.getListeEDTSalles().get(randSalle2).modifieEvenement(j2, h2, idEven1);

        return nouvelleEdt;
    }

    /**
     * Créer une solution d'EDT initiale
     * @return edt
     */
    public EDT solutionInitiale(){
    	int nbSalle = mDonneesEDT.getListeSalles().size();
    	int nbCreneaux = nbSalle * EDTSalle.getNbHoraires() * EDTSalle.getNbJours();

    	System.out.println("Nb salles: " + nbSalle + " nb Creneaux: " + nbCreneaux);

    	List<Creneau> listeCreneauxDisponibles = new ArrayList<>(nbCreneaux);
    	List<EDTSalle> listeEDTSalle = new ArrayList<>(nbSalle);

    	// Initialisation EDTSalle.
    	for( int i = 0; i < nbSalle; i++){
    	    EDTSalle edtSalle = new EDTSalle();
    	    edtSalle.setSalle(mDonneesEDT.getListeSalles().get(i));
    	    listeEDTSalle.add(edtSalle);
    	    for( int h = 0; h < EDTSalle.getNbHoraires(); h++){
    	        for( int j = 0; j < EDTSalle.getNbJours(); j++){
    	            listeCreneauxDisponibles.add(new Creneau(j, h, true, i));
                }
            }
        }

    	// Association évenement et salle.
        Random random = new Random(System.currentTimeMillis());
    	System.out.println("NB event : " + Evenement.getNbEvenements());
        for(Evenement e : mDonneesEDT.getListeEvenements()) {
            if( listeCreneauxDisponibles.size() > 0 ){
                Creneau creneau = listeCreneauxDisponibles.get(random.nextInt(listeCreneauxDisponibles.size()));
                listeEDTSalle.get(creneau.getIdsalle()).ajouterEvenement(e, creneau.getJour(), creneau.getHoraire());
                listeCreneauxDisponibles.remove(creneau);
            }
        }

        listeCreneauxDisponibles.clear();
        mSolutionFinale = new EDT(listeEDTSalle, 0, mDonneesEDT);
        return mSolutionFinale;
    }

    /**
     * Retourne un dictionnaire contenant pour chaque entrée
     * Une liste d'événement d'un chaque jour J.
     * @return Map
     */
    public Map<Integer, List<Evenement>> getEvenementsParJour(){
        Map<Integer, List<Evenement>> planning = new HashMap<>();

        for( int j = 0; j < EDTSalle.getNbJours(); j++){
            List<Evenement> listeEven = new ArrayList<>();
            for (EDTSalle edtSalle : mSolutionFinale.getListeEDTSalles()) {
                for( int h = 0; h < EDTSalle.getNbHoraires(); h++) {
                    int idEvenement = edtSalle.getIdEvenement(j, h);
                    if( idEvenement != -1 && idEvenement < mDonneesEDT.getListeEvenements().size()) {
                        listeEven.add(mDonneesEDT.getListeEvenements().get(idEvenement));
                    }
                }
            }
            planning.put(j, listeEven);
        }
        return planning;
    }

    /**
     * Réoptimise un emploi du temps
     */
    public void reoptimise(){
        recuitSimule();
    }

    /**
     * Retourne la solution de l'EDT en Latex
     * @return StringBuilder
     */
    public StringBuilder getSolutionEnLatex(){
        return null;
    }

}
