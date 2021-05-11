package fr.uvsq.interfaces;

import java.util.ArrayList;
import java.util.Arrays;

import org.controlsfx.control.CheckComboBox;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfController {

	/**
	 * Argument qui permet la connexion avec la base de données
	 */
	private DAO<Professeur> mProfDao;
	private App mApp;
	private Stage mProfStage;
	private ObservableList<Module> mListeModule;
	private Professeur mProf;
	private TableView<Professeur> mProfTableView;

	@FXML
	private TextField mNomTextField;

	@FXML
	private CheckComboBox<String> mPeutEnseignerComboBox;

	@FXML
	private CheckBox mComboBox;

	@FXML
	private Label mTitleLabel;

	/**
	 * Bouton qui permet d'ajouter un professeur
	 */
	@FXML
	private Button mAjouterProfBtn;

	/**
	 * Initialise la fenêtre de dialogue professeur
	 */
	private void initialize() {

	}

	/**
	 * Ferme la fenêtre de dialogue professeur
	 */
	@FXML
	private void fermer() {
		mProfStage.close();
	}

	/**
	 * Initialise le contenu de la fenêtre de dialogue avec les attributs de la
	 * classe prof
	 * 
	 * @param prof
	 * @param profTableView
	 */
	public void initialiseDialogueModification(Professeur prof, TableView<Professeur> profTableView) {
		mProf = prof;
		mProfTableView = profTableView;
		mTitleLabel.setText("Modification du prof");
		mNomTextField.setText("");
		mAjouterProfBtn.setText("Modifier");

		if (prof != null) {
			mNomTextField.setText(prof.getNom());
			// Auto check already selected modules
//            mPeutEnseignerComboBox.getCheckModel().check(1);
		}
	}

	/**
	 * Gérer l'ajout d'un nouveau professeur
	 * 
	 * @param event
	 */
	@FXML
	private void handleAjouterBtn(ActionEvent event) {
		if (event.getSource() == mAjouterProfBtn) {
			if (mAjouterProfBtn.getText().equals("Modifier")) {
				modifierProf();
			} else {
				ajouterProf();
			}
		}
	}

	/**
	 * Vérifie les données d'une classe prof et ajoute cette classe dans la liste
	 * des prefesseurs
	 */
	private void ajouterProf() {
		//
		boolean estValide = true;

		if (estValide) {

			ArrayList<Module> modules = new ArrayList<>();
			for (String item : mPeutEnseignerComboBox.getCheckModel().getCheckedItems()) {
				for (Module module : mListeModule) {
					if (item.equals(module.getNom())) {
						Module mod = new Module(module.getNom(), module.getNbTD(), module.getNbCM(), module.getNbTP(),
								module.getDureeCM(), module.getDureeTP(), module.getDureeTD());
						mod.setId(module.getId());
						modules.add(mod);
					}
				}
			}

			DAO<Professeur> mProfDao = FactoryDAO.getProfesseurDAO();
			Professeur prof = new Professeur(mNomTextField.getText(), modules);
			if (mProfDao.inserer(prof)) {
				mApp.getListeProfs().add(prof);
				fermer();
			} // else {//ajouter un message d'erreur}
		}
	}

	/**
	 * Modifie les données d'une classe prof
	 */
	private void modifierProf() {
		//
		boolean estValide = true;

		if (estValide) {

			ArrayList<Module> modules = new ArrayList<>();
			for (String module : mPeutEnseignerComboBox.getCheckModel().getCheckedItems()) {
				for (Module m : mListeModule) {
					if (module.equals(m.getNom())) {
						Module current = new Module(m.getNom(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(),
								m.getDureeTP(), m.getDureeTD());
						current.setId(m.getId());
						modules.add(current);
					}
				}
			}
			Professeur nouveauProf = new Professeur(mNomTextField.getText(), modules);
			nouveauProf.setId(mProf.getId());

			mProfDao = FactoryDAO.getProfesseurDAO();
			if (mProfDao.modifier(nouveauProf)) {
				int index = mApp.getListeProfs().indexOf(mProf);
				mApp.getListeProfs().get(index).setNom(nouveauProf.getNom());
				mApp.getListeProfs().get(index).setListeModules(nouveauProf.getListeModules());
				mProfTableView.refresh();
				fermer();
			}
			// else {//ajouter un message d'erreur}

		}

	}

	public void setApp(App app, Stage profStage, ObservableList<Module> listeModule, Professeur prof) {
		mApp = app;
		mProfStage = profStage;
		mListeModule = listeModule;

		ObservableList<String> modules = FXCollections.observableArrayList();
		for (Module module : mListeModule) {
			modules.add(module.getNom());
		}
		mPeutEnseignerComboBox.getItems().addAll(modules);
		if (prof != null) {
			for (Module mod : prof.getListeModules()) {
				mPeutEnseignerComboBox.getCheckModel().check(mod.getNom());
			}
		}
	}

}
