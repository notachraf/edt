package fr.uvsq.interfaces;

import fr.uvsq.models.Salle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void initialize(){

    }

    @FXML
    private void fermer(){
        mSalleStage.close();
    }

    public void initialiseDialogueModification(Salle salle){
        mTitleLabel.setText("Modification de la Salle");
        mNomTextField.setText("");
        mCapaciteTextField.setText("");
        mTypeTextField.setText("");
        mAjouterSalleBtn.setText("Modifier");

        if( salle != null ){
            mNomTextField.setText(salle.getNom());
            mCapaciteTextField.setText(String.valueOf(salle.getCapacite()));
            mTypeTextField.setText(String.valueOf(salle.getTypeSalle()));
        }
    }

    @FXML
    private void handleAjouterBtn(ActionEvent event){
        if( event.getSource() == mAjouterSalleBtn ){
            if( mAjouterSalleBtn.getText() == "Modifier"){
                modifierSalle();
            }else {
                ajouterNouvelleSalle();
            }

        }
    }

    private void ajouterNouvelleSalle(){
        System.out.println("======== Ajouter nouvelle salle");
        mApp.updateListeSalles();
        fermer();
    }

    private void modifierSalle(){
        System.out.println("========= Modifier la salle ");
        mApp.updateListeSalles();
        fermer();
    }

    public void setApp(App app, Stage salleStage){
        mApp = app;
        mSalleStage = salleStage;
    }
}
