package fr.uvsq.interfaces;

import fr.uvsq.models.Salle;
import fr.uvsq.verification.Verification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.*;
import javafx.stage.Stage;

public class SalleController {

    // private SalleDAO mSalleDAO;
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
    @FXML
    private Button mAjouterSalleBtn;


    @FXML
    private void initialize() {
        mTypeComboBox.getItems().add("TD");
        mTypeComboBox.getItems().add("TP");
        mTypeComboBox.getItems().add("COURS");
    }

    @FXML
    private void fermer() {
        mSalleStage.close();
    }

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

    @FXML
    private void handleAjouterBtn(ActionEvent event) {
        if (event.getSource() == mAjouterSalleBtn) {
            if (mAjouterSalleBtn.getText().equals("Modifier")) {
                modifierSalle();
            } else {
                ajouterNouvelleSalle();
            }
        }
    }

    private void ajouterNouvelleSalle() {
        boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());

        if( estValide ) {
            System.out.println(" ========= Les Données sont valides ============");
            System.out.println("======== Ajouter nouvelle salle");

            mApp.ajouteSalle(new Salle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue()));
            fermer();
        }
    }

    private void modifierSalle() {
        Boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());

        if( estValide ) {
            System.out.println(" ========= Les Données sont valides ============");
            System.out.println("========= Modifier la salle ");
            if(mSalle != null) {
                Salle nouvelleSalle = new Salle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());
                mApp.modifierSalle(mSalle, nouvelleSalle, mSalleTableView);
            }
            fermer();
        }
    }

    public void setApp(App app, Stage salleStage) {
        mApp = app;
        mSalleStage = salleStage;
    }
}