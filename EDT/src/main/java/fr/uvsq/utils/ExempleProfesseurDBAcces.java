package fr.uvsq.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.ProfesseurDAO;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promotion;
import fr.uvsq.models.Module;

public class ExempleProfesseurDBAcces {
	
	
public static void main(String[] args) {
		
		DAO<Professeur> proDAO  = FactoryDAO.getProfesseurDAO(); 
		DAO<Promotion> promoDAO  = FactoryDAO.getPromotionDAO(); 
		DAO<Module> ModuleDAO  = FactoryDAO.getModuleDAO();
		/* Module modul = ModuleDAO.rechercher(3);
	        ArrayList<Module> modules = new ArrayList<>();
	            modules.add(modul);*/
		/*Professeur profs=new Professeur() ;
		profs.setNom("feriel");
		profs.setId(13);
		profs.setListeModules(modules);
		proDAO.inserer(profs);*/
		
		/*Promotion promo=new Promotion() ;
		promo.setId(1);
		promo.setNom("lol");
		promo.setNbEleves(22);
		promo.setNbGroupes(3);
		promo.setListeModules(modules);
		LocalDate localDate = LocalDate.of(2021,2,10);
		promo.setLocalDate(localDate);
		promoDAO.inserer(promo);
		Promotion promo=new Promotion() ;
		promo= promoDAO.rechercher(2);
		System.out.println(promo);*/
		Professeur prof=new Professeur() ;
		 ArrayList<Professeur> profs = new ArrayList<>();
		profs=proDAO.recupererListe();
		System.out.println(profs);
		
		
	}
	

}
