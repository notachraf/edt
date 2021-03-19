package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController {

    private Stage mDialogueStage;

    @FXML
    private Button cancelBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField capaciteTextField;


    public void setDialogueStage(Stage dialogueStage) {
        mDialogueStage = dialogueStage;
    }

    @FXML
    void cancelBtnHandler(ActionEvent event) {
        mDialogueStage.close();
    }

    @FXML
    void addBtnHandler(ActionEvent event) {
        String nom = nomTextField.getText();
        String capacite = capaciteTextField.getText();
        String id = "0";

        Salle salle = new Salle(nom, capacite, id);

        System.out.println("nom = " + salle.getNom());
        System.out.println("capacite = " + salle.getCapacite());
        System.out.println("id = " + salle.getId());

        PrimaryController.addSale();
    }

    @FXML
    void initialize() {}

}