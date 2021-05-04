package fr.uvsq.generateurEDT;

import java.util.ArrayList;
import java.util.List;
import fr.uvsq.models.*;

/**
 * 
 * @author moulhat
 *
 */
public class EDT {
	//les attributs
	private List<EDTSalle> mListeEDTSalles;
	private int mEnergie;
	private DonneesEDT mDonneesEDT;
	
	// les constructeurs
	/**
	 * 
	 * @param listeEDTSalles
	 * @param energie
	 * @param donneesEDT
	 */
	public EDT(List<EDTSalle> listeEDTSalles, int energie, DonneesEDT donneesEDT) {
        mListeEDTSalles = listeEDTSalles;
        mEnergie = energie;
        mDonneesEDT = donneesEDT;
    }

    public EDT() {
    	mListeEDTSalles = null;
    	mEnergie = -1;
    	mDonneesEDT = null;
    }

    	// les méthodes
    
    /**
     * 
     * @return la liste des emplois du temps de salles d'un EDT
     */
	public List<EDTSalle> getListeEDTSalles() {
        return mListeEDTSalles;
    }

	/**
	 * 
	 * @param listeEDTSalles
	 */
    public void setListeEDTSalles(List<EDTSalle> listeEDTSalles) {
        mListeEDTSalles = listeEDTSalles;
    }
    
    /**
     * 
     * @return l'énergie d'un EDT
     */
    public int getEnergie() {
        return mEnergie;
    }
    
    /**
     * 
     * @param energie
     */
    public void setEnergie(int energie) {
        mEnergie = energie;
    }

    /**
     * 
     * @return retourne les données d'un EDT
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
	     * Calcule le nombre de fois que la capacité d'une
	     * Salle n'est pas respecté.
	     * @return le nombre de contraintes
	     */
    private int contrainteCapaciteSalle(){
    	// pour chaque salle on regarde ses horaires de chaque jour
    	int contrainte = 0;
    	for(EDTSalle edtSalle : mListeEDTSalles){
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    				
    				// on récupère l'événement qui est au jour i pour le créneau j
    				int even = 0;
	    			if(edtSalle.getEdt()[i][j] > -1) {
	    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				while(idEven !=  edtSalle.getEdt()[i][j]  ){
	    					even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
	    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				}
	    			Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
	    				
	    			if(evenement.getGroupe().getNbEleves() > edtSalle.getSalle().getCapacite()) contrainte++;
	    			}
	    		}
    		}
    	}
        return contrainte;
    }
	    /**
     * Calcule le nombre de fois que le type de cours
	     * effectué dans une salle n'est pas respecté.
	     * @return le nombre de contraintes.
	     */
    private int contrainteTypeSalle(){
    	int contrainte = 0;
    	for(EDTSalle edtSalle : mListeEDTSalles){
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    				
    				// on récupère l'événement qui est au jour i pour le créneau j
    				int even = 0;
	    			if(edtSalle.getEdt()[i][j] != -1) {
	    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				while(idEven !=  edtSalle.getEdt()[i][j]  ){
	    					even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
	    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				}
	    				Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
	    				
	    				if(evenement.getTypeEven().toString() != edtSalle.getSalle().getTypeSalle().toString()) contrainte++;
	    			}
		    	}
	    	}
	    }
	    return contrainte;
    }

	    /**

	     * Calcule le nombre de salles réservées pour un prof
	     * en même temps.
	     * @return le nombre de contraintes.
	     */
    private int contrainteReservationProf(){
	   	int contrainte = 0;
	    	
    	for(Professeur prof : mDonneesEDT.getListeProfesseurs()) {
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    		    	int contrainteJoursHoraires = 0;
    		    	
    		    	for(EDTSalle edtSalle : mListeEDTSalles){
    		    	
	    				// on récupère l'événement qui est au jour i pour le créneau j
	    				int even = 0;
		    			if(edtSalle.getEdt()[i][j] != -1) {
		    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				while(idEven !=  edtSalle.getEdt()[i][j]  ){
		    					even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
		    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				}
			    			Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
			    				
			    			if(evenement.getProfesseur() == prof) contrainteJoursHoraires++;
		    			}
	   		    	}
	   		    	contrainte += (contrainteJoursHoraires-1);
	    		}
	   		}
	   	}
	    	
    	return contrainte;
    }
	
    /**
     * calcule le nombre de salle réservés pour 
     * un groupe en même temps
     * @return le nombre de contrainte
     */
    private int contrainteResGroupes() {
    	
    	//créer les groupes
    	List<Groupe> listeGroupes = new ArrayList<>();
    	List<Groupe> groupesPromo = new ArrayList<>();		

    	int nbeleves, nbgroupes;
    	for(Promotion promo : mDonneesEDT.getListePromotions()) {
        	if(promo.getNbGroupes()>0) {
        		//creer les groupes
				nbeleves = promo.getNbEleves();
				nbgroupes = promo.getNbGroupes();      				
				
			for(int j=0;j<promo.getNbGroupes();j++) {
				String nomGroupe = "Groupe" + (j+1) + "";
				Groupe gpe = new Groupe(nomGroupe,(nbeleves/nbgroupes),promo);
					groupesPromo.add(gpe);
					nbeleves -= (nbeleves/nbgroupes);
			}
				int n=0; int p = 0;
				if(nbeleves > 0) {
					for(n=0;n<nbeleves;n++) {
					if(p>promo.getNbGroupes()) p = 0;
					groupesPromo.get(p).setNbEleves(groupesPromo.get(p).getNbEleves()+1);
					p++;
					}
				}
        	}
        	
        	for(Groupe G : groupesPromo) listeGroupes.add(G);
    	}
    	
    	int contrainte = 0;
    	for(Groupe unGroupe : listeGroupes) {
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    		    	int contrainteJoursHoraires = 0;
    		    	
    		    	for(EDTSalle edtSalle : mListeEDTSalles){
    		    	
	    				// on récupère l'événement qui est au jour i pour le créneau j
	    				int even = 0;
		    			if(edtSalle.getEdt()[i][j] != -1) {
		    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				while(idEven !=  edtSalle.getEdt()[i][j]  ){
		    					even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
		    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				}
			    			Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
			    			
			    			if(evenement.getTypeEven() == TypeEven.TD || evenement.getTypeEven() == TypeEven.TP) {
				    			if(evenement.getGroupe() == unGroupe) contrainteJoursHoraires++;
			    			}
			    			
			    			if(evenement.getTypeEven() == TypeEven.CM) {
				    			if(evenement.getGroupe().getPromotion() == unGroupe.getPromotion()) contrainteJoursHoraires++;
			    			}
		    			}
	   		    	}
	   		    	contrainte += (contrainteJoursHoraires-1);
	    		}
	   		}
    	}
    	return contrainte;
    }
    
    /**
     * compte le nombre de fois que des cours se chevauchement sur les horaires
     * @return le nombre de contraintes
     */
    private int contrainteChevauchements() {
    	int contrainte = 0;
    	for(EDTSalle edtSalle : mListeEDTSalles) {
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    				// on récupère l'événement qui est au jour i pour le créneau j
    				int even = 0;
	    			if(edtSalle.getEdt()[i][j] != -1) {
	    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				while(idEven !=  edtSalle.getEdt()[i][j]  ){
	    					even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
	    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				}
	    			}
	    			
		    		Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
		    		int n;
		    		if(evenement.getTypeEven() == TypeEven.CM) {
			    		for( n = j+1; n < evenement.getModule().getDureeCM(); n++) {
			    			if(edtSalle.getEdt()[i][j] != evenement.getId()) contrainte++;
			    		}
			    		j = n-1;
    				}
		    		if(evenement.getTypeEven() == TypeEven.TD) {
			    		for( n = j+1; n < evenement.getModule().getDureeTD(); n++) {
			    			if(edtSalle.getEdt()[i][j] != evenement.getId()) contrainte++;
			    		}
			    		j = n-1;
    				}
		    		if(evenement.getTypeEven() == TypeEven.TP) {
			    		for( n = j+1; n < evenement.getModule().getDureeTP(); n++) {
			    			if(edtSalle.getEdt()[i][j] != evenement.getId()) contrainte++;
			    		}
			    		j = n-1;
    				}
		    		
		    		
    			}
    		}
    	}
    return contrainte;
    }
	    
    /**
     * Calcule l'énergie un emploi du temps en sommant toutes les contraintes
     * @param conTypeSalle
     * @param conCapSalle
     * @param ResProf
     * @return l'énergie d'un EDT
     */
	public double calculEnergie ( int conTypeSalle , int conCapSalle , int ResProf ) {
    	conTypeSalle = contrainteTypeSalle();
    	conCapSalle = contrainteCapaciteSalle() + contrainteChevauchements();
	   	ResProf = contrainteReservationProf() + contrainteResGroupes();
	   	mEnergie = conTypeSalle + conCapSalle + ResProf;
	   	return (conTypeSalle + conCapSalle + ResProf);
   }
}
