package fr.uvsq.interfaces;

import fr.uvsq.models.Salle;
import fr.uvsq.models.TypeSalle;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AppController {
    private App mApp;

    private String mEDTLatex;
    private Map<Integer, List<String>> mTableauEDT;

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

    //=================== Salle TableView ======================
    @FXML
    private TableView<Salle> mSallesTableView;
    @FXML
    private TableColumn<Salle, Integer> mIdSalleCln;
    @FXML
    private TableColumn<Salle, String> mNomSalleCln;
    @FXML
    private TableColumn<Salle, Integer> mCapaciteSalleCln;
    @FXML
    private TableColumn<Salle, TypeSalle> mTypeSalleCln;

    @FXML
    private void onHomeLabelClick(){
        mEdtPane.toFront();
    }

    /**
     * Gére l'action des bouttons onglets de l'application
     * @param event
     */
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

    /**
     * Affiche la fenêtre de dialogue salle
     * en lui passe les données de la salle
     * à afficher.
     */
    @FXML
    private void afficheDialogueSalle(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/salle.fxml"));

        try{
            AnchorPane sallePane = loader.load();
            SalleController salleController = loader.getController();
            Scene scene = new Scene(sallePane);
            Stage salleStage = new Stage(StageStyle.UNDECORATED);
            salleController.setApp(mApp, salleStage);
            salleStage.setResizable(false);
            salleStage.setScene(scene);
            salleStage.initOwner(mApp.getAppStage());
            salleStage.initModality(Modality.APPLICATION_MODAL);
            salleStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise tous les onglets et affiche
     * l'onglet EDT par défaut.
     */
    @FXML
    private void initialize(){
        mEdtPane.toFront();
        initSalleTableView();

    }

    /**
     * Iniatilise la liste des salles qui doivent
     * être afficher dans le tableau @mSallesTableView
     */
    private void initSalleTableView(){
        mIdSalleCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getId();
            }
        });

        mCapaciteSalleCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getCapacite();
            }
        });
        mTypeSalleCln.setCellValueFactory(cellData -> new ObservableValueBase<TypeSalle>(){
            @Override
            public TypeSalle getValue(){
                return cellData.getValue().getTypeSalle();
            }
        });

        mNomSalleCln.setCellValueFactory(cellData -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return cellData.getValue().getNom();
            }
        });

        mSallesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            afficheItemSalleSelectionne(newValue));
    }

    /**
     * Affiche les données de la salle sélectionné
     * dans une fenêtre de dialogue.
     * @param salle
     */
    private void afficheItemSalleSelectionne(Salle salle){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/salle.fxml"));

        try{
            AnchorPane sallePane = loader.load();
            SalleController salleController = loader.getController();
            salleController.initialiseDialogueModification(salle);
            Scene scene = new Scene(sallePane);
            Stage salleStage = new Stage(StageStyle.UNDECORATED);
            salleController.setApp(mApp, salleStage);
            salleStage.setResizable(false);
            salleStage.setScene(scene);
            salleStage.initOwner(mApp.getAppStage());
            salleStage.initModality(Modality.APPLICATION_MODAL);
            salleStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifie la classe app et associe le tableau contenant
     * les salles à une liste de salles.
     *
     * @param app
     */
    public void setApp(App app) {
        mApp = app;
        mSallesTableView.setItems(mApp.getListeSalles());
    }

    /**
     * Remplir le tableau d'EDT avec le dictionnaire
     * Map<Integer, String>
     */
    public void remplirTableauEDT(){

    }

    /**
     * Sauvegarde l'EDT dans un fichier Latex.
     */
    public void saugarderEDTEnLatex(){

    }
}
