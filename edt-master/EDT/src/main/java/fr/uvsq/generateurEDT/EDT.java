package fr.uvsq.generateurEDT;
import fr.uvsq.models.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class EDT {
    private List<EDTSalle> mListeEDTSalles;
    private int mEnergie;
    private DonneesEDT mDonneesEDT;

	/**
	 * Création d'un emploi du temps
	 * @param listeEDTSalles listes des salles
	 * @param energie l'énergie initiale
	 * @param donneesEDT données utilisées pour créer
	 *                   l'emploi du temps.
	 */
    public EDT(List<EDTSalle> listeEDTSalles, int energie, DonneesEDT donneesEDT) {
        mListeEDTSalles = listeEDTSalles;
        mEnergie = energie;
        mDonneesEDT = donneesEDT;
    }

	/**
	 * Constructeur par défaut.
	 */
	public EDT() {
		mListeEDTSalles = null;
		mEnergie = 0;
		mDonneesEDT = null;
    }

	/**
	 *
	 * @return liste edts actuelles.
	 */
	public List<EDTSalle> getListeEDTSalles() {
        return mListeEDTSalles;
    }

	/**
	 *
	 * @param listeEDTSalles listes edt à fixer
	 */
	public void setListeEDTSalles(List<EDTSalle> listeEDTSalles) {
        mListeEDTSalles = listeEDTSalles;
    }

	/**
	 *
	 * @return energie actuelle
	 */
	public int getEnergie() {
        return mEnergie;
    }

	/**
	 *
	 * @param energie energie à fixer
	 */
	public void setEnergie(int energie) {
        mEnergie = energie;
    }

	/**
	 *
	 * @return DonneesEDT actuelles
	 */
	public DonneesEDT getDonneesEDT() {
        return mDonneesEDT;
    }

	/**
	 *
	 * @param donneesEDT donnesEDT à fixer
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
		Random random = new Random();
		for(EDTSalle edtSalle : mListeEDTSalles){
			for(int i = 0; i < EDTSalle.getNbJours(); i++) {
				for(int j = 0; j < EDTSalle.getNbHoraires();j++) {

					// on récupère l'événement qui est au jour i pour le créneau j
					int even = 0;
						if(edtSalle.getEdt()[j][i] > -1) {
							int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
							while(idEven !=  edtSalle.getEdt()[j][i]  ){
								even = random.nextInt(mDonneesEDT.getListeEvenements().size());
								idEven = mDonneesEDT.getListeEvenements().get(even).getId();
							}
						Evenement evenement = mDonneesEDT.getListeEvenements().get(even);

						if(evenement.getTypeEven() == TypeEven.CM) {
							if(evenement.getGroupe().getPromotion().getNbEleves() > edtSalle.getSalle().getCapacite()) contrainte++;
						}
						else if(evenement.getGroupe().getNbEleves() > edtSalle.getSalle().getCapacite()) contrainte++;
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
    	Random random = new Random();
    	for(EDTSalle edtSalle : mListeEDTSalles){
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    				
    				// on récupère l'événement qui est au jour i pour le créneau j
    				int even = 0;
	    			if(edtSalle.getEdt()[j][i] != -1) {
	    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
	    				while(idEven !=  edtSalle.getEdt()[j][i]  ){
	    					even = random.nextInt(mDonneesEDT.getListeEvenements().size());
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
	    Random random = new Random();
    	for(Professeur prof : mDonneesEDT.getListeProfesseurs()) {
    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
    		    	int contrainteJoursHoraires = 0;
    		    	
    		    	for(EDTSalle edtSalle : mListeEDTSalles){
    		    	
	    				// on récupère l'événement qui est au jour i pour le créneau j
	    				int even = 0;

		    			if(edtSalle.getEdt()[j][i] != -1) {
		    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				while(idEven !=  edtSalle.getEdt()[j][i]  ){
		    					even = random.nextInt(mDonneesEDT.getListeEvenements().size());
		    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				}
			    			Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
			    			if(evenement.getProfesseur() == prof) { 
			    				
			    				contrainteJoursHoraires++;
			    				int duree = 0;
			    				if(evenement.getTypeEven() == TypeEven.CM) duree = evenement.getModule().getDureeCM();
			    				if(evenement.getTypeEven() == TypeEven.TD) duree = evenement.getModule().getDureeTD();
			    				if(evenement.getTypeEven() == TypeEven.TP) duree = evenement.getModule().getDureeTP();
			    				
			    				if(duree>60) {
			    					duree = (int)Math.ceil(duree/60);
			    					for(int n =j+1; duree>1 && n<EDTSalle.getNbHoraires();n++) {
			    						for(EDTSalle edtSalle2 : mListeEDTSalles){
					        		    	
						    				// on récupère l'événement qui est au jour i pour le créneau j
						    				int even2 = 0;
							    			if(edtSalle2.getEdt()[n][i] != -1) {
							    				int idEven2 = mDonneesEDT.getListeEvenements().get(even2).getId();
							    				while(idEven2 !=  edtSalle2.getEdt()[n][i]  ){
							    					even2 = random.nextInt(mDonneesEDT.getListeEvenements().size());
							    					idEven2 = mDonneesEDT.getListeEvenements().get(even2).getId();
							    				}
								    			Evenement evenement2 = mDonneesEDT.getListeEvenements().get(even2);
								    				
								    			if(evenement2.getProfesseur() == prof) contrainteJoursHoraires++;
							    			}
						   		    	}
			    						duree--;
			    					}
			    				}
			    			}
		    			}
	   		    	}

	   		    	if(contrainteJoursHoraires > 1) contrainte += (contrainteJoursHoraires-1);
	    		}
	   		}
	   	}
	    	
    	return contrainte;
    }

	/**
	 * Cacule les contraintes de double
	 * réservations de groupe.
	 * @return contrainte
	 */
	private int contrainteResGroupe() {
		    List <Groupe> lesGroupes = new ArrayList<>(); 
		    
		    for(Promotion promo : mDonneesEDT.getListePromotions()) {
				int nbeleves = promo.getNbEleves();
				int	nbgroupes = promo.getNbGroupes();      				
				
				
				for(int j=0;j<promo.getNbGroupes();j++) {
					String nomGroupe = "Groupe" + (j+1) + "";
					Groupe gpe = new Groupe(nomGroupe,(nbeleves/nbgroupes),promo);
					lesGroupes.add(gpe);
						nbeleves -= (nbeleves/nbgroupes);
						nbgroupes--;
					}
		    }
		    
		   	int contrainte = 0;
		    Random random = new Random();	
	    	for(Groupe groupe : lesGroupes) {
	    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
	    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
	    		    	int contrainteCM = 0;
	    		    	int contrainteJoursHoraires = 0;
	 		    	
	    		    	for(EDTSalle edtSalle : mListeEDTSalles){
	    		    	
		    				// on récupère l'événement qui est au jour i pour le créneau j
		    				int even = 0;
			    			if(edtSalle.getEdt()[j][i] != -1) {
			    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
			    				while(idEven !=  edtSalle.getEdt()[j][i]  ){
			    					even = random.nextInt(mDonneesEDT.getListeEvenements().size());
			    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
			    				}
				    			Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
				    				
				    			if(evenement.getGroupe() == groupe || (evenement.getGroupe().getPromotion() == groupe.getPromotion() && evenement.getTypeEven() == TypeEven.CM)) { 
				    				
				    				if(evenement.getGroupe() == groupe) contrainteJoursHoraires++;
				    				int duree = 0;
				    				if(evenement.getTypeEven() == TypeEven.CM) duree = evenement.getModule().getDureeCM();
				    				if(evenement.getTypeEven() == TypeEven.TD) duree = evenement.getModule().getDureeTD();
				    				if(evenement.getTypeEven() == TypeEven.TP) duree = evenement.getModule().getDureeTP();
				    				
				    				if(duree>60) {
				    					duree = (int)Math.ceil(duree/60);
				    					for(int n =j+1; duree>1 && n<EDTSalle.getNbHoraires();n++) {
				    						for(EDTSalle edtSalle2 : mListeEDTSalles){
						        		    	
							    				// on récupère l'événement qui est au jour i pour le créneau j
							    				int even2 = 0;
								    			if(edtSalle2.getEdt()[n][i] != -1) {
								    				int idEven2 = mDonneesEDT.getListeEvenements().get(even2).getId();
								    				while(idEven2 !=  edtSalle2.getEdt()[n][i]  ){
								    					even2 = random.nextInt(mDonneesEDT.getListeEvenements().size());
								    					idEven2 = mDonneesEDT.getListeEvenements().get(even2).getId();
								    				}
									    			Evenement evenement2 = mDonneesEDT.getListeEvenements().get(even2);
									    			
									    			if(evenement.getTypeEven() == TypeEven.CM || evenement2.getTypeEven() == TypeEven.CM) {
									    				if(evenement2.getGroupe().getPromotion() == groupe.getPromotion()) {
									    					if(evenement.getTypeEven() == TypeEven.CM) contrainteCM++;
									    					else contrainteJoursHoraires++;
									    				}
									    			}
									    			else if(evenement2.getGroupe() == groupe) contrainteJoursHoraires++;
								    			}
							   		    	}
				    						duree--;
				    					}
				    				}
				    			}
			    			}
		   		    	}
	    		    	
		   		    	
		   		    	if(contrainteJoursHoraires > 1) contrainte += contrainteJoursHoraires-1;
		   		    	contrainte += contrainteCM; 
		    		}
		   		}
		   	}
	    	return contrainte;
	}

	/**
	 * contraintes de chevauchement de chaque salle
	 * (si un evenement commence avant la fin ou
	 * juste à l'heure de fin de celui qui le precede )
 	 * @return contraine de chevauchements.
	 */
	private int contrainteChevauchements() {
	    	int contrainte = 0;
	    	Random random = new Random();
	    	for(EDTSalle edtSalle : mListeEDTSalles) {
	    		for(int i = 0; i < EDTSalle.getNbJours(); i++) {
	    			for(int j = 0; j < EDTSalle.getNbHoraires();j++) {
	    				// on récupère l'événement qui est au jour i pour le créneau j
	    				int even = 0;
		    			if(edtSalle.getEdt()[j][i] != -1) {
		    				int idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				while(idEven !=  edtSalle.getEdt()[j][i]  ){
		    					even = random.nextInt(mDonneesEDT.getListeEvenements().size());
		    					idEven = mDonneesEDT.getListeEvenements().get(even).getId();
		    				}
				    		Evenement evenement = mDonneesEDT.getListeEvenements().get(even);
				    		int n =0;
				    		if(evenement.getTypeEven() == TypeEven.CM) {
				    			int duree =  evenement.getModule().getDureeCM();
				    			if (duree>60) {
				    				duree = (int)Math.ceil(duree/60);
						    		for( n = j+1; n<EDTSalle.getNbHoraires() && duree>1; n++) {
						    			if(edtSalle.getEdt()[n][i] != -1 && edtSalle.getEdt()[n][i] != evenement.getId()) contrainte++;
						    			duree--;
						    		}
				    			}
		    				}
				    		if(evenement.getTypeEven() == TypeEven.TD) {
				    			int duree =  evenement.getModule().getDureeTD();
				    			if (duree>60) {
				    				duree = (int)Math.ceil(duree/60);
				    				for( n = j+1; n<EDTSalle.getNbHoraires() && duree>1; n++) {
					    			if(edtSalle.getEdt()[n][i] != -1 && edtSalle.getEdt()[n][i] != evenement.getId()) contrainte++;
					    			duree--;
				    				}
				    			}
		    				}
				    		if(evenement.getTypeEven() == TypeEven.TP) {
				    			int duree =  evenement.getModule().getDureeTP();
				    			if (duree>60) {
				    				duree = (int)Math.ceil(duree/60);
						    		for( n = j+1; n<EDTSalle.getNbHoraires() && 1 < duree; n++) {
						    			if(edtSalle.getEdt()[n][i] != -1 && edtSalle.getEdt()[n][i] != evenement.getId()) contrainte++;
						    			duree--;
						    		}
				    			}
		    				}
		    			}
		    			
			    		
			    		
	    			}
	    		}
	    	}

	    	return contrainte;
	    }

	/**
	 * Calcule la somme des énergies de toutes
	 * les contraintes.
	 * @param conTypeSalle containtes de type salle
	 * @param conCapSalle contraintes de type capacité salle
	 * @param ResProf restrictions pour les professeurs
	 * @return somme d'énergie.
	 */
	public double calculEnergie ( int conTypeSalle , int conCapSalle , int ResProf ) {
	    	conCapSalle = contrainteCapaciteSalle();
	    	conTypeSalle = contrainteTypeSalle();
		  	ResProf = contrainteReservationProf() ;
		   	int conGr = contrainteResGroupe();
		   	int contCh = contrainteChevauchements();
		   	mEnergie = conTypeSalle + conCapSalle + ResProf  + contCh + conGr;
		   	return mEnergie;
	   }
}
