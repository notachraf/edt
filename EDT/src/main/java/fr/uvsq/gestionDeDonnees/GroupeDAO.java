package fr.uvsq.gestionDeDonnees;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.models.Groupe;

public class GroupeDAO extends DAO<Groupe>{

	public GroupeDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inserer(Groupe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insererListe(List<Groupe> obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groupe selectionner(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimer(Groupe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groupe rechercher(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifier(Groupe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groupe rechercher(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Groupe> recupererListe() {
		return null;
	}
	
}