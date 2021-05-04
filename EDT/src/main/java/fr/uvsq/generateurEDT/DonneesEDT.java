package fr.uvsq.generateurEDT;

import fr.uvsq.gestionDeDonnees.ProfDAO;
import fr.uvsq.gestionDeDonnees.*;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promotion;
import fr.uvsq.models.Salle;
import fr.uvsq.models.Groupe;

import java.util.List;
import java.util.ArrayList;
/**
 * 
 * @author moulhat
 *
 */
public class DonneesEDT {
	   // les attributs
    private List<Salle> mListeSalles;
    private List<Professeur> mListeProfesseurs;
    private List<Module> mListeModules;
    private List<Promotion> mListePromotions;
    private List<Evenement> mListeEvenements;

    // le constructeur
    public DonneesEDT() {
    	mListeSalles = null;
    	mListeModules = null;
    	mListeProfesseurs = null;
    	mListePromotions = null;
    	mListeEvenements = null;
    }
    
    // les méthodes
    /**
     * 
     * @return la liste des salles d'une donneesEDT
     */
    public List<Salle> getListeSalles() {
        return mListeSalles;
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
     * @return la liste des professeurs d'une DonneesEDT
     */
    public List<Professeur> getListeProfesseurs() {
        return mListeProfesseurs;
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
     * @return la liste des modules d'une DonneesEDT
     */
    public List<Module> getListeModules() {
        return mListeModules;
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
     * @return la liste des promotions d'une DonneesEDT
     */
    public List<Promotion> getListePromotions() {
        return mListePromotions;
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
     * @return la liste des événements d'une DonneesEDT
     */
    public List<Evenement> getListeEvenements() {
        return mListeEvenements;
    }


    /**
     * 
     * @return la liste des professeurs récupérés de la base de données
     */
    public List<Professeur> recupereProfesseursBD(){
    	DAO<Professeur> pDao = (ProfDAO)FactoryDAO.getProfDAO();
    	mListeProfesseurs = pDao.recupererListe();
    	return mListeProfesseurs;
    }
    
    /**
     * 
     * @return la liste des modules récupérés de la base de données
     */
    public List<Module> recupereModulesBD() {
    	DAO<Module> mDao = (ModuleDAO)FactoryDAO.getModuleDAO();
    	mListeModules = mDao.recupererListe();
    	return mListeModules;
    }
    
    /**
     * 
     * @return la liste des salles récupérées de la base de données
     */
    public List<Salle> recupereSallesBD() {
    	DAO<Salle> sDao = (SalleDAO)FactoryDAO.getSalleDAO();
        mListeSalles = sDao.recupererListe();
        return mListeSalles;
        
    }
    
    /**
     * 
     * @return la liste des promotions récupérées de la base de données
     */
    public List<Promotion> recuperePromotionsBD() {
    	DAO<Promotion> promoDao = (PromoDAO)FactoryDAO.getPromoDAO();
    	mListePromotions = promoDao.recupererListe();
    	return mListePromotions;
    	
    }

    /**
     * Créer les évenement de type cours.
     */
    public void creerEvenementsCM(){
    	for(Promotion promo : mListePromotions) {
    		for(Module module : promo.getListeModules()) {
    			if(module.getNbCM() > 0) {
    				// creer un groupe pour attribuer la promotion de l'événement
    				Groupe maPromo = new Groupe("",0,promo);
    				
    				//choisi un prof
    				int indiceProf = 0;
    				while(!mListeProfesseurs.get(indiceProf).peutEnseigner(module)) {
    						indiceProf = (int)Math.random()*mListeProfesseurs.size();
    				}
    				
    				Professeur prof = mListeProfesseurs.get(indiceProf);
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
    	int nbeleves; int nbgroupes;
        for(Promotion promo : mListePromotions) {
    		List<Groupe> groupesPromo = new ArrayList<>();

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
        	
        	for(Module module : promo.getListeModules()) {
       			if(module.getNbTP() > 0) {
        				//créer les événements TP pour chaque groupe de de la promotion
        				//choisi un prof
       				for(Groupe unGroupe : groupesPromo) {
   	    				int indiceProf = 0;
   	    				while(!mListeProfesseurs.get(indiceProf).peutEnseigner(module)) {
   	    					indiceProf = (int)Math.random()*mListeProfesseurs.size();
   	    				}
    	    				
    	   				Professeur prof = mListeProfesseurs.get(indiceProf);
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
    	int nbeleves; int nbgroupes;
        for(Promotion promo : mListePromotions) {
    		List<Groupe> groupesPromo = new ArrayList<>();

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
        	
        	for(Module module : promo.getListeModules()) {
       			if(module.getNbTD() > 0) {
        				//créer les événements TD pour chaque groupe de de la promotion
        				//choisi un prof
       				for(Groupe unGroupe : groupesPromo) {
   	    				int indiceProf = 0;
   	    				while(!mListeProfesseurs.get(indiceProf).peutEnseigner(module)) {
   	    					indiceProf = (int)Math.random()*mListeProfesseurs.size();
   	    				}
    	    				
    	   				Professeur prof = mListeProfesseurs.get(indiceProf);
        				Creneau creneau = new Creneau();
   	    				Evenement even = new Evenement(Evenement.getNbEvenements(),TypeEven.TD,prof,module,unGroupe,creneau);
  	    				mListeEvenements.add(even);
       				}
        		}
        	}
        }
    }
}

