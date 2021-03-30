package fr.uvsq.interfaces;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SalleController {

    private Stage mSalleDialogueStage;
    @FXML
    private void initialize(){

    }

    @FXML
    private void fermer(){
        mSalleDialogueStage.close();
    }

    public void setSalleDialogueStage(Stage salleDialogueStage){
        mSalleDialogueStage = salleDialogueStage;
    }
}
