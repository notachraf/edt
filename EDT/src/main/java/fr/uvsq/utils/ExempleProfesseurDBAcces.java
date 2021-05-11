package fr.uvsq.utils;

import java.util.List;
import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promotion;

public class ExempleProfesseurDBAcces {
	
	
	public static void main(String[] args) {
		
		DAO<Promotion> promoDAO  = FactoryDAO.getPromotionDAO();
		List<Promotion> promo =promoDAO.recupererListe();
		System.out.println(promo);
		
		
		
	}
	

}
