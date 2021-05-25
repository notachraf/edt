package fr.uvsq.generateurEDT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fr.uvsq.models.*;
import fr.uvsq.models.Module;

import fr.uvsq.gestionDeDonnees.*;

public class DonneesEDT {
    
    private List<Salle> mListeSalles;
    private List<Professeur> mListeProfesseurs;
    private List<Module> mListeModules;
    private List<Promotion> mListePromotions;
    private List<Evenement> mListeEvenements;

	/**
	 * constructeur par défaut
	 */
	public DonneesEDT() {
    	mListeSalles = getListeSalles();
    	mListeModules = getListeModules();
    	mListeProfesseurs = getListeProfesseurs();
    	mListePromotions = getListePromotions();
    	mListeEvenements = new ArrayList<>();
    	creerEvenementsCM();
    	creerEvenementsTD();
    	creerEvenementsTP();
    }

    /**
     * 
     * @return liste de salles actuelle
     */
    public List<Salle> getListeSalles() {
        return recupereSallesBD();
    }

    /**
     * 
     * @param listeSalles liste de salles à fixer
     */
    public void setListeSalles(List<Salle> listeSalles) {
        mListeSalles = listeSalles;
    }

    /**
     * 
     * @return liste de professeurs actuelle
     */
    public List<Professeur> getListeProfesseurs() {
        return recupereProfesseursBD();
    }

    /**
     * 
     * @param listeProfesseurs liste de professeurs à fixer
     */
    public void setListeProfesseurs(List<Professeur> listeProfesseurs) {
        mListeProfesseurs = listeProfesseurs;
    }
    
    /**
     * 
     * @return liste de modules actuelle
     */
    public List<Module> getListeModules() {
        return recupereModulesBD();
    }

    /**
     * 
     * @param listeModules liste de modules à fixer
     */
    public void setListeModules(List<Module> listeModules) {
        mListeModules = listeModules;
    }

    /**
     * 
     * @return liste de promotions actuelles
     */
    public List<Promotion> getListePromotions() {
    	return recuperePromotionsBD();
    }
    /**
     * 
     * @param listePromotions liste de promotions à fixer
     */
    public void setListePromotions(List<Promotion> listePromotions) {
        mListePromotions = listePromotions;
    }
    /**
     * 
     * @param listeEvenements liste d'événements à fixer
     */
    public void setListeEvenements(List<Evenement> listeEvenements) {
        mListeEvenements= listeEvenements;
    }
    
    
    /**
     * 
     * @return liste d'événement actuelle
     */
    public List<Evenement> getListeEvenements() {
    	return mListeEvenements;
    }


	/**
	 *
	 * @return liste des professeurs.
	 */
	private List<Professeur> recupereProfesseursBD(){
    	DAO<Professeur> pDao = (ProfesseurDAO)FactoryDAO.getProfesseurDAO();
    	mListeProfesseurs = pDao.recupererListe();
    	return mListeProfesseurs;
    }

	/**
	 *
	 * @return liste des modules
	 */
	private List<Module> recupereModulesBD() {
    	DAO<Module> mDao = (ModuleDAO)FactoryDAO.getModuleDAO();
    	mListeModules = mDao.recupererListe();
    	return mListeModules;
    }

	/**
	 *
	 * @return liste des salles
	 */
	private List<Salle> recupereSallesBD() {
    	DAO<Salle> sDao = (SalleDAO)FactoryDAO.getSalleDAO();
        mListeSalles = sDao.recupererListe();
        return mListeSalles;
        
    }

	/**
	 *
	 * @return liste des promotions
	 */
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
		Random random = new Random(System.currentTimeMillis());
    	for(Promotion promo : mListePromotions) {
    		nbCm += promo.getListeModules().size();
    		for(Module module : promo.getListeModules()) {

    			if(module.getNbCM() > 0) {
					// creer un groupe pour attribuer la promotion de l'événement
    				Groupe maPromo = new Groupe("",0,promo);
    				
    				//choisi un prof
    				int indiceProf = 0;
    				while(indiceProf < mListeProfesseurs.size() && mListeProfesseurs.get(indiceProf).peutEnseigner(module) == false) {
    					++indiceProf; // = random.nextInt(mListeProfesseurs.size());
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
    }

    /**
     * Créer les évenement de type TP.
     */
    public void creerEvenementsTP(){
    	Random random = new Random(System.currentTimeMillis());
    	int i = 0;
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
   	    					++indiceProf;// = random.nextInt(mListeProfesseurs.size());

						Professeur prof = new Professeur();
						if( indiceProf < mListeProfesseurs.size() )
							prof = mListeProfesseurs.get(indiceProf);

        				Creneau creneau = new Creneau();
   	    				Evenement even = new Evenement(Evenement.getNbEvenements(),TypeEven.TP,prof,module,unGroupe,creneau);
  	    				mListeEvenements.add(even);
       				}
        		}
        	}
        	i++;
			}
		}
    }


    /**
     * Créer les évenement de type TD.
     */
    public void creerEvenementsTD(){
    	Random random = new Random(System.currentTimeMillis());
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
       				for(Groupe unGroupe : groupesPromo) {
   	    				int indiceProf = 0;
   	    				while( indiceProf < mListeProfesseurs.size() && !mListeProfesseurs.get(indiceProf).peutEnseigner(module)) {
   	    					++indiceProf; // = random.nextInt(mListeProfesseurs.size());
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
