package fr.uvsq.interfaces;

import fr.uvsq.models.Salle;
import fr.uvsq.verification.Verification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Gérer l'inter-action avec la fenêtre de dialogue salle.
 */
public class SalleController {

    // private SalleDAO mSalleDAO;
    private App mApp;
    private Stage mSalleStage;

    @FXML
    private Label mTitleLabel;
    @FXML
    private TextField mNomTextField,
            mCapaciteTextField,
            mTypeTextField;

    @FXML
    private Button mAjouterSalleBtn;

    @FXML
    private void initialize() {

    }

    @FXML
    private void fermer() {
        mSalleStage.close();
    }

    /**
     * Initialise le contenu de la fenêtre de dialogue
     * avec les attributs de la classe salle
     * @param salle
     */
    public void initialiseDialogueModification(Salle salle) {
        mTitleLabel.setText("Modification de la Salle");
        mNomTextField.setText("");
        mCapaciteTextField.setText("");
        mTypeTextField.setText("");
        mAjouterSalleBtn.setText("Modifier");

        if (salle != null) {
            mNomTextField.setText(salle.getNom());
            mCapaciteTextField.setText(String.valueOf(salle.getCapacite()));
            mTypeTextField.setText(String.valueOf(salle.getTypeSalle()));
        }
    }

    /**
     * Gérer l'ajout d'une nouvelle salle dans le tableau des Salle
     * @param event
     */
    @FXML
    private void handleAjouterBtn(ActionEvent event) {
        if (event.getSource() == mAjouterSalleBtn) {
            if (mAjouterSalleBtn.getText() == "Modifier") {
                modifierSalle();
            } else {
                ajouterNouvelleSalle();
            }

        }
    }

    /**
     * Vérifie les données de une salle et ajoute cette salle dans la liste des salles.
     */
    private void ajouterNouvelleSalle() {
        Boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeTextField.getText());
        if( estValide ) {
            System.out.println(" ========= Les Données sont valides ============");
            System.out.println("======== Ajouter nouvelle salle");
            mApp.updateListeSalles();
            fermer();
        }
    }

    /**
     * Modifie les données d'une salle
     */
    private void modifierSalle() {
        Boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeTextField.getText());
        if( estValide ) {
            System.out.println(" ========= Les Données sont valides ============");
            System.out.println("========= Modifier la salle ");
            mApp.updateListeSalles();
            fermer();
        }
    }

    /**
     * Modifie la classe App et Stage
     * @param app
     * @param salleStage
     */
    public void setApp(App app, Stage salleStage) {
        mApp = app;
        mSalleStage = salleStage;
    }

}



