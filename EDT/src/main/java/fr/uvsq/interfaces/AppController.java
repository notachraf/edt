package fr.uvsq.interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppController {
    private Stage mAppStage;

    @FXML
    private Button mDashboardBtn,
                   mSallesBtn,
                   mProfBtn,
                   mCoursBtn,
                   mLatexBtn,
                   mGroupeBtn,
                   mAjouteSalleBtn;

    @FXML
    private Pane mDashboardPane,
                 mSallesPane,
                 mProfPane,
                 mCoursPane,
                 mLatexPane,
                 mGroupePane,
                 mEdtPane;

    @FXML
    private void onHomeLabelClick(){
        mEdtPane.toFront();
    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        if( event.getSource() == mDashboardBtn){
            System.out.println(" =========== Dashboard button ===========");
            mDashboardPane.toFront();
        } else if ( event.getSource() == mSallesBtn ){
            System.out.println(" =========== Salle button ===========");
            mSallesPane.toFront();
        } else if (event.getSource() == mProfBtn ){
            System.out.println(" =========== Prof button ===========");
            mProfPane.toFront();
        } else if (event.getSource() == mCoursBtn ) {
            System.out.println(" =========== cours button ===========");
            mCoursPane.toFront();
        } else if (event.getSource() == mGroupeBtn ){
            System.out.println(" =========== Groupe button ===========");
            mGroupePane.toFront();
        } else if (event.getSource() == mLatexBtn){
            System.out.println(" =========== Latex button ===========");
            mLatexPane.toFront();
        }
    }

    @FXML
    private void afficheDialogueSalle(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/salle.fxml"));

        try{
            AnchorPane sallePane = loader.load();
            SalleController salleController = loader.getController();
            Scene scene = new Scene(sallePane);
            Stage salleStage = new Stage(StageStyle.UNDECORATED);
            salleController.setSalleDialogueStage(salleStage);
            salleStage.setResizable(false);
            salleStage.setScene(scene);
            salleStage.initOwner(mAppStage);
            salleStage.initModality(Modality.APPLICATION_MODAL);
            salleStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize(){
        mEdtPane.toFront();
    }

    public void setAppStage(Stage appStage){
        mAppStage = appStage;
    }
}
