package fr.uvsq.interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class AppController {
    @FXML
    private Button mDashboardBtn,
                   mSallesBtn,
                   mProfBtn,
                   mCoursBtn,
                   mLatexBtn,
                   mGroupeBtn;

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
    private void initialize(){
        mEdtPane.toFront();
    }
}
