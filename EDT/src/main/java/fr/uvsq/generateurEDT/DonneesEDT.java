package fr.uvsq.generateurEDT;

import java.util.ArrayList;
import java.util.List;
import fr.uvsq.models.*;
import fr.uvsq.models.Module;

import fr.uvsq.gestionDeDonnees.*;

public class DonneesEDT {
    
    private List<Salle> mListeSalles;
    private List<Professeur> mListeProfesseurs;
    private List<Module> mListeModules;
    private List<Promotion> mListePromotions;
    private List<Evenement> mListeEvenements;

    public DonneesEDT() {
    	mListeSalles = getListeSalles();
    	mListeModules = getListeModules();
    	mListeProfesseurs = getListeProfesseurs();
    	mListePromotions = getListePromotions();
    	mListeEvenements = new ArrayList<>();
    	creerEvenementsCM();
    	creerEvenementsTD();
    	creerEvenementsTP();

    	System.out.println("NB EVEn cons : "+ mListeEvenements.size());
    }
    /**
     * 
     * @return
     */
    public List<Salle> getListeSalles() {
        return recupereSallesBD();
    }

    /**
     * 
     * @param listeSalles
     */
    public void setListeSalles(List<Salle> listeSalles) {
        mListeSalles = listeSalles;
    }

    /**
     * 
     * @return
     */
    public List<Professeur> getListeProfesseurs() {
        return recupereProfesseursBD();
    }

    /**
     * 
     * @param listeProfesseurs
     */
    public void setListeProfesseurs(List<Professeur> listeProfesseurs) {
        mListeProfesseurs = listeProfesseurs;
    }
    
    /**
     * 
     * @return
     */
    public List<Module> getListeModules() {
        return recupereModulesBD();
    }

    /**
     * 
     * @param listeModules
     */
    public void setListeModules(List<Module> listeModules) {
        mListeModules = listeModules;
    }

    /**
     * 
     * @return
     */
    public List<Promotion> getListePromotions() {
    	return recuperePromotionsBD();
    }
    /**
     * 
     * @param listePromotions
     */
    public void setListePromotions(List<Promotion> listePromotions) {
        mListePromotions = listePromotions;
    }
    /**
     * 
     * @param listeEvenements
     */
    public void setListeEvenements(List<Evenement> listeEvenements) {
        mListeEvenements= listeEvenements;
    }
    
    
    /**
     * 
     * @return
     */
    public List<Evenement> getListeEvenements() {
    	return mListeEvenements;
    }


    private List<Professeur> recupereProfesseursBD(){
	DAO<Professeur> pDao = FactoryDAO.getProfesseurDAO();
    	mListeProfesseurs = pDao.recupererListe();
    	return mListeProfesseurs;
    }
    private List<Module> recupereModulesBD() {
    	DAO<Module> mDao = (ModuleDAO)FactoryDAO.getModuleDAO();
    	mListeModules = mDao.recupererListe();
    	return mListeModules;
    }
    private List<Salle> recupereSallesBD() {
    	DAO<Salle> sDao = (SalleDAO)FactoryDAO.getSalleDAO();
        mListeSalles = sDao.recupererListe();
        return mListeSalles;
        
    }
    private List<Promotion> recuperePromotionsBD() {
    	DAO<Promotion> promoDao= (PromoDAO)FactoryDAO.getPromotionDAO();
    	mListePromotions = promoDao.recupererListe();
    	return mListePromotions;
    	
    }

    /**
     * Créer les évenement de type cours.
     */
    public void creerEvenementsCM(){
		int nbCm = 0;
    	for(Promotion promo : mListePromotions) {
    		nbCm += promo.getListeModules().size();
    		for(Module module : promo.getListeModules()) {

    			if(module.getNbCM() > 0) {
					// creer un groupe pour attribuer la promotion de l'événement
    				Groupe maPromo = new Groupe("",0,promo);
    				
    				//choisi un prof
    				int indiceProf = 0;
    				while(indiceProf < mListeProfesseurs.size() && mListeProfesseurs.get(indiceProf).peutEnseigner(module) == false) {
    				//		indiceProf = (int)Math.random()*mListeProfesseurs.size();
						indiceProf++;
    				}
    				Professeur prof = new Professeur();
    				if( indiceProf < mListeProfesseurs.size())
    					prof = mListeProfesseurs.get(indiceProf);

    				Creneau creneau = new Creneau();
    				Evenement even = new Evenement(Evenement.getNbEvenements(),TypeEven.CM,prof,module,maPromo,creneau);
    				mListeEvenements.add(even);

    			}
    		}
    	}
    	System.out.println("+++++ NB CM: " + nbCm);
		System.out.println("Nb even CM: " + mListeEvenements.size() );
    }

    /**
     * Créer les évenement de type TP.
     */
    public void creerEvenementsTP(){
		for(Promotion promo : mListePromotions) {
			List<Groupe> groupesPromo = new ArrayList<>();
			int nbeleves = promo.getNbEleves();
			int nbgroupes = promo.getNbGroupes();

			for(int j=0;j<promo.getNbGroupes();j++) {
				String nomGroupe = "Groupe" + (j+1) + "";
				Groupe gpe = new Groupe(nomGroupe,(nbeleves/nbgroupes),promo);
				groupesPromo.add(gpe);
				nbeleves -= (nbeleves/nbgroupes);
				nbgroupes--;
			}

        	for(Module module : promo.getListeModules()) {
       			if(module.getNbTP() > 0) {
        				//créer les événements TP pour chaque groupe de de la promotion
        				//choisi un prof
       				for(Groupe unGroupe : groupesPromo) {
   	    				int indiceProf = 0;
   	    				while(indiceProf < mListeProfesseurs.size() && !mListeProfesseurs.get(indiceProf).peutEnseigner(module)) {
   	    					//indiceProf = (int)Math.random()*mListeProfesseurs.size();
   	    					indiceProf++;
   	    				}

						Professeur prof = new Professeur();
						if( indiceProf < mListeProfesseurs.size() )
							prof = mListeProfesseurs.get(indiceProf);

        				Creneau creneau = new Creneau();
   	    				Evenement even = new Evenement(Evenement.getNbEvenements(),TypeEven.TP,prof,module,unGroupe,creneau);
  	    				mListeEvenements.add(even);
       				}
        		}
        	}
		}
	}

    /**
     * Créer les évenement de type TD.
     */
    public void creerEvenementsTD(){
        for(Promotion promo : mListePromotions) {
			List<Groupe> groupesPromo = new ArrayList<>();
			int nbeleves = promo.getNbEleves();
			int nbgroupes = promo.getNbGroupes();
			for(int j=0;j<promo.getNbGroupes();j++) {
				String nomGroupe = "Groupe" + (j+1) + "";
				Groupe gpe = new Groupe(nomGroupe,(nbeleves/nbgroupes),promo);
				groupesPromo.add(gpe);
				nbeleves -= (nbeleves/nbgroupes);
				nbgroupes--;
			}
        	for(Module module : promo.getListeModules()) {

       			if(module.getNbTD() > 0) {
        				//créer les événements TD pour chaque groupe de de la promotion
        				//choisi un prof
       				for(Groupe unGroupe : groupesPromo) {
   	    				int indiceProf = 0;
   	    				while( indiceProf < mListeProfesseurs.size() && !mListeProfesseurs.get(indiceProf).peutEnseigner(module)) {
   	    					//indiceProf = (int)Math.random()*mListeProfesseurs.size();
   	    					indiceProf++;
   	    				}

   	    				Professeur prof = new Professeur();
   	    				if( indiceProf < mListeProfesseurs.size() )
    	   					prof = mListeProfesseurs.get(indiceProf);
        				Creneau creneau = new Creneau();
   	    				Evenement even = new Evenement(Evenement.getNbEvenements(),TypeEven.TD,prof,module,unGroupe,creneau);
  	    				mListeEvenements.add(even);
       				}
        		}
        	}
        }
    }
}
