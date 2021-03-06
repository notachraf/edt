package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.SalleDAO;
import fr.uvsq.models.Salle;
import fr.uvsq.models.TypeSalle;
import fr.uvsq.verification.Verification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Gérer l'inter-action avec la fenêtre de dialogue salle.
 */
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SalleController {

    /**
     * Argument qui permet la connexion avec la base de données
     */
    private SalleDAO mSalleDAO;
    private App mApp;
    private Stage mSalleStage;
    private Salle mSalle;
    private TableView<Salle> mSalleTableView;
    @FXML
    private Label mTitleLabel;
    @FXML
    private TextField mNomTextField,
            mCapaciteTextField;
    @FXML
    private ComboBox<String> mTypeComboBox;

    /**
     * Bouton qui permet d'ajouter une salle
     */
    @FXML
    private Button mAjouterSalleBtn;

    /**
     * Initialise la fenêtre de dialogue salle
     */
    @FXML
    private void initialize() {
        mTypeComboBox.getItems().add("TD");
        mTypeComboBox.getItems().add("TP");
        mTypeComboBox.getItems().add("CM");
    }

    /**
     * Ferme la fenêtre de dialogue salle.
     */
    @FXML
    private void fermer() {
        mSalleStage.close();
    }

    /**
     * Initialise le contenu de la fenêtre de dialogue
     * avec les attributs de la classe salle
     * @param salle
     * @param salleTableView
     */
    public void initialiseDialogueModification(Salle salle, TableView<Salle> salleTableView) {
        mSalle = salle;
        mSalleTableView = salleTableView;
        mTitleLabel.setText("Modification de la Salle");
        mNomTextField.setText("");
        mCapaciteTextField.setText("");
        mAjouterSalleBtn.setText("Modifier");

        if (salle != null) {
            mNomTextField.setText(salle.getNom());
            mCapaciteTextField.setText(String.valueOf(salle.getCapacite()));
            mTypeComboBox.setValue(String.valueOf(salle.getTypeSalle()));
        }
    }

    /**
     * Gérer l'ajout d'une nouvelle salle dans le tableau des Salle
     * @param event
     */
    @FXML
    private void handleAjouterBtn(ActionEvent event) {
        if (event.getSource() == mAjouterSalleBtn) {
            if (mAjouterSalleBtn.getText().equals("Modifier")) {
                modifierSalle();
            } else {
                ajouterSalle();
            }
        }
    }

    /**
     * Vérifie les données d'une salle et ajoute cette salle dans la liste des salles.
     */
    private void ajouterSalle() {
        boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());

        if( estValide ) {
            if( mSalleDAO == null ){
                mSalleDAO = (SalleDAO) FactoryDAO.getSalleDAO();
            }
            Salle salle = new Salle(mNomTextField.getText(), Integer.parseInt(mCapaciteTextField.getText()), TypeSalle.getType(mTypeComboBox.getValue()));
            if( mSalleDAO.inserer(salle)) {
                mApp.getListeSalles().add(salle);
                mApp.getAppController().setNbSalles(String.valueOf(mApp.getListeSalles().size()));
            }
            fermer();
        }
    }

    /**
     * Modifie les données d'une salle
     */
    private void modifierSalle() {
        Boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());

        if( estValide ) {
            if( mSalleDAO == null ){
                mSalleDAO = (SalleDAO) FactoryDAO.getSalleDAO();
            }
            if(mSalle != null) {
                Salle nouvelleSalle = new Salle(mNomTextField.getText(), Integer.parseInt(mCapaciteTextField.getText()), TypeSalle.getType(mTypeComboBox.getValue()));

                nouvelleSalle.setId(mSalle.getId());
                if( mSalleDAO.modifier(nouvelleSalle) ) {
                    int index = mApp.getListeSalles().indexOf(mSalle);
                    mApp.getListeSalles().get(index).setNom(nouvelleSalle.getNom());
                    mApp.getListeSalles().get(index).setCapacite(nouvelleSalle.getCapacite());
                    mApp.getListeSalles().get(index).setTypeSalle(nouvelleSalle.getTypeSalle());
                    mSalleTableView.refresh();
                }
            }
            fermer();
        }
    }

    /**
     * Fixe la fenêtre principale et la fenêtre de modification
     * @param app fenêtre principale
     * @param salleStage fenêtre de modification
     */
    public void setApp(App app, Stage salleStage) {
        mApp = app;
        mSalleStage = salleStage;
    }
}