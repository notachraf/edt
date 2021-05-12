package fr.uvsq.generateurEDT;
import fr.uvsq.models.*;
import java.util.List;
import java.util.ArrayList;

public class EDT {
    private List<EDTSalle> mListeEDTSalles;
    private int mEnergie;
    private DonneesEDT mDonneesEDT;

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

    public DonneesEDT getDonneesEDT() {
        return mDonneesEDT;
    }

    public void setDonneesEDT(DonneesEDT donneesEDT) {
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
	    					//even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
	    					even++;
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
	    				//	even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
							even++;
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

	    /**    private List<Evenement> mListeEvenements;

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
		    					//even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
		    					even++;
		    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				}
			    			Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
			    				
			    			if(evenement.getProfesseur() == prof) contrainteJoursHoraires++;
		    			}
	   		    	}
	   		    	
	   		    	if(contrainteJoursHoraires > 0) contrainte += (contrainteJoursHoraires-1);
	    		}
	   		}
	   	}
	    	
    	return contrainte;
    }
	   
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
					nbgroupes--;
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
		    					//even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
		    					even++;
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
	   		    	if(contrainteJoursHoraires > 0) contrainte += (contrainteJoursHoraires-1);
	    		}
	   		}
    	}
    	return contrainte;
    }
    
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
	    					//even = (int)Math.random()*mDonneesEDT.getListeEvenements().size();
	    					even++;
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
	    
	public double calculEnergie ( int conTypeSalle , int conCapSalle , int ResProf ) {
    	conCapSalle = contrainteCapaciteSalle();
    	conTypeSalle = contrainteTypeSalle() + contrainteChevauchements();
	   	ResProf = contrainteReservationProf() + contrainteResGroupes();
	   	mEnergie = conTypeSalle + conCapSalle + ResProf;
	   	return (double)mEnergie;
   }
}
