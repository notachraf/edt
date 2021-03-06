package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.ProfesseurDAO;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.verification.Verification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;

public class ProfController {

    /**
     * Argument qui permet la connexion avec la base de données
     */
    private ProfesseurDAO mProfDao;
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
    private Label mTitleLabel;

    /**
     * Bouton qui permet d'ajouter un professeur
     */
    @FXML
    private Button mAjouterProfBtn;

    /**
     * Ferme la fenêtre de dialogue professeur
     */
    @FXML
    private void fermer() {
        mProfStage.close();
    }

    /**
     * Initialise le contenu de la fenêtre de dialogue
     * avec les attributs de la classe prof
     * @param prof
     * @param profTableView
     */
    public void initialiseDialogueModification(Professeur prof, TableView<Professeur> profTableView) {
        mProf = prof;
        mProfTableView = profTableView;
        mTitleLabel.setText("Modification du prof");
        mNomTextField.setText("");
        mAjouterProfBtn.setText("Modifier");

        if(prof != null) {
            mNomTextField.setText(prof.getNom());
            //Auto check already selected modules
//            mPeutEnseignerComboBox.getCheckModel().check(0);
//            mPeutEnseignerComboBox.getCheckModel().check(1);
        }
    }

    /**
     * Gérer l'ajout d'un nouveau professeur
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
     * Vérifie les données d'une classe prof et ajoute cette classe dans la liste des prefesseurs
     */
    private void ajouterProf() {
        //
        boolean estValide = Verification.controleDonneesProfesseur(mNomTextField.getText(), "0");

        if( estValide ) {

            if( mProfDao == null ){
                mProfDao = (ProfesseurDAO) FactoryDAO.getProfesseurDAO();
            }
            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mPeutEnseignerComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }
            Professeur nouveauProfesser = new Professeur(mNomTextField.getText(), modules);
            if( mProfDao.inserer(nouveauProfesser) ) {
                mApp.getListeProfs().add(nouveauProfesser);
                mApp.getAppController().setNbProfs(String.valueOf(mApp.getListeProfs().size()));
            }
            fermer();
        }
    }

    /**
     * Modifie les données d'une classe prof
     */
    private void modifierProf() {
        //
        boolean estValide = Verification.controleDonneesProfesseur(mNomTextField.getText(), "0");

        if( estValide ) {
            if( mProfDao == null ){
                mProfDao = (ProfesseurDAO) FactoryDAO.getProfesseurDAO();
            }

            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mPeutEnseignerComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }
            Professeur nouveauProf = new Professeur(mNomTextField.getText(), modules);
            nouveauProf.setId(mProf.getId());
            if( mProfDao.modifier(nouveauProf) ) {
                int index = mApp.getListeProfs().indexOf(mProf);
                mApp.getListeProfs().get(index).setNom(nouveauProf.getNom());
                mApp.getListeProfs().get(index).setListeModules(nouveauProf.getListeModules());
                mProfTableView.refresh();
            }
            fermer();
        }

    }

    /**
     * fixe la fenêtre principale et initialise la
     * liste de des modules
     * @param app fenêtre principale
     * @param profStage fenête de modification
     * @param listeModule liste des module
     */
    public void setApp(App app, Stage profStage, ObservableList<Module> listeModule) {
        mApp = app;
        mProfStage = profStage;
        mListeModule = listeModule;

        ObservableList<String> modules = FXCollections.observableArrayList();
        for(Module module : mListeModule) {
            modules.add(module.getNom());
        }
        mPeutEnseignerComboBox.getItems().addAll(modules);
    }

}
