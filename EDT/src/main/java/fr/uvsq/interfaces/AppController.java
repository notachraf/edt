package fr.uvsq.interfaces;

import com.calendarfx.model.Calendar;
import com.calendarfx.view.CalendarView;
import fr.uvsq.generateurEDT.Evenement;
import fr.uvsq.generateurEDT.GenerateurEDT;
import fr.uvsq.models.Module;
import fr.uvsq.models.Salle;
import fr.uvsq.models.TypeSalle;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.TableCell;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AppController {
    private App mApp;

    private StringBuilder mEDTEnLatex;
    private Map<Integer, List<Evenement>> mEvenementParJour;
    private GenerateurEDT mGenerateurEDT;
    private Calendar mCalendar;

    @FXML
    CalendarView mEDTCalendarPane;

    @FXML
    private Button mDashboardBtn,
                   mSallesBtn,
                   mProfBtn,
                   mCoursBtn,
                   mLatexBtn,
                   mPromoBtn;

    @FXML
    private Pane mDashboardPane,
                 mSallesPane,
                 mProfPane,
                 mCoursPane,
                 mLatexPane,
                 mPromoPane;

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
    private TableColumn<Salle, Void> mActionSalleCln;

    //=================== Module TableView ======================
    @FXML
    private TableView<Module> mModuleTableView;
    @FXML
    private TableColumn<Module, Integer> mIdModuleCln;
    @FXML
    private TableColumn<Module, String> mNomModuleCln;
    @FXML
    private TableColumn<Module, Integer> mDureeModuleCln;
    @FXML
    private TableColumn<Module, Integer> mNbCoursSemaineCln;
    @FXML
    private TableColumn<Module, Boolean> mTDModuleCln;
    @FXML
    private TableColumn<Module, Boolean> mTPModuleCln;
    @FXML
    private TableColumn<Module, Void> mActionModuleCln;

    //=================== Prof TableView ======================
    //=================== Promo TableView ======================

    @FXML
    private void onHomeLabelClick(){
        System.out.println("=========== click on Home label +++++++++++");
        mEDTCalendarPane.showMonthPage();
        mEDTCalendarPane.toFront();
    }

    /**
     * Gére l'action des bouttons onglets de l'application
     * @param event
     */
    @FXML
    private void handleSideMenuButtons(ActionEvent event){
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
        } else if (event.getSource() == mPromoBtn ){
            System.out.println(" =========== Groupe button ===========");
            mPromoPane.toFront();
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
        /*mEDTCalendarPane.showMonthPage();
        mEDTCalendarPane.toFront();
        mEDTCalendarPane.setShowAddCalendarButton(false);*/
        mDashboardPane.toFront();
        initSalleTableView();
        initModuleTableView();
        ajouteBouttonsActionSalle();
        ajouteBouttonsActionModule();

        mIdSalleCln.setResizable(false);
        mNomSalleCln.setResizable(false);
        mCapaciteSalleCln.setResizable(false);
        mTypeSalleCln.setResizable(false);
        mActionSalleCln.setResizable(false);
        mCapaciteSalleCln.setStyle("-fx-alignment:center");
        mTypeSalleCln.setStyle("-fx-alignment:center");
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
    }

    private void initModuleTableView() {
        mIdModuleCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getId();
            }
        });
        mNomModuleCln.setCellValueFactory(cellData -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return cellData.getValue().getNom();
            }
        });
        mNbCoursSemaineCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getNbCoursSemaine();
            }
        });
        mDureeModuleCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getDuree();
            }
        });
        mTDModuleCln.setCellValueFactory(cellData -> new ObservableValueBase<Boolean>() {
            @Override
            public Boolean getValue() {
                return cellData.getValue().isTD();
            }
        });
        mTPModuleCln.setCellValueFactory(cellData -> new ObservableValueBase<Boolean>() {
            @Override
            public Boolean getValue() {
                return cellData.getValue().isTP();
            }
        });
    }

    @FXML
    private void afficherDialogueAjouterSalle(){
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

    @FXML
    private void afficherDialogueAjouterModule(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/module.fxml"));

        try{
            AnchorPane modulePane = loader.load();
            ModuleController moduleController = loader.getController();
            Scene scene = new Scene(modulePane);
            Stage moduleStage = new Stage(StageStyle.UNDECORATED);
            moduleController.setApp(mApp, moduleStage);
            moduleStage.setResizable(false);
            moduleStage.setScene(scene);
            moduleStage.initOwner(mApp.getAppStage());
            moduleStage.initModality(Modality.APPLICATION_MODAL);
            moduleStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherDialogueModifierSalle(Salle salle){
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

    private void afficherDialogueModifierModule(Module module){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/module.fxml"));

        try{
            AnchorPane sallePane = loader.load();
            ModuleController moduleController = loader.getController();
            moduleController.initialiseDialogueModification(module, mModuleTableView);
            Scene scene = new Scene(sallePane);
            Stage moduleStage = new Stage(StageStyle.UNDECORATED);
            moduleController.setApp(mApp, moduleStage);
            moduleStage.setResizable(false);
            moduleStage.setScene(scene);
            moduleStage.initOwner(mApp.getAppStage());
            moduleStage.initModality(Modality.APPLICATION_MODAL);
            moduleStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void ajouteBouttonsActionSalle() {
        Callback<TableColumn<Salle, Void>, TableCell<Salle, Void>> cellFactory = new Callback<TableColumn<Salle, Void>, TableCell<Salle, Void>>() {
            @Override
            public TableCell<Salle, Void> call(final TableColumn<Salle, Void> param) {
                final TableCell<Salle, Void> cell = new TableCell<Salle, Void>() {

                    Button editBtn = new Button("Modifier");
                    Button removeBtn = new Button("Supprimer");

                    {
                        editBtn.setStyle("-fx-text-fill: #ffffff");
                        removeBtn.setStyle("-fx-text-fill: #ffffff");
                        editBtn.setOnAction((ActionEvent event) -> {
                            Salle salle = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + salle.getNom());
                            afficherDialogueModifierSalle(salle);
                        });

                        removeBtn.setOnAction((ActionEvent event) -> {
                            Salle salle = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + salle.getNom());

                            mApp.supprimerSalle(salle);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        HBox managebtn = new HBox(editBtn, removeBtn);
                        HBox.setMargin(editBtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(removeBtn, new Insets(2, 3, 0, 2));

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(managebtn);
                        }
                    }
                };
                return cell;
            }
        };

        mActionSalleCln.setCellFactory(cellFactory);
    }

    private void ajouteBouttonsActionModule() {
        Callback<TableColumn<Module, Void>, TableCell<Module, Void>> cellFactory = new Callback<TableColumn<Module, Void>, TableCell<Module, Void>>() {
            @Override
            public TableCell<Module, Void> call(final TableColumn<Module, Void> param) {
                final TableCell<Module, Void> cell = new TableCell<Module, Void>() {

                    Button editBtn = new Button("Modifier");
                    Button removeBtn = new Button("Supprimer");

                    {
                        editBtn.setStyle("-fx-text-fill: #ffffff");
                        removeBtn.setStyle("-fx-text-fill: #ffffff");
                        editBtn.setOnAction((ActionEvent event) -> {
                            Module module = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + module.getNom());
                            afficherDialogueModifierModule(module);
                        });

                        removeBtn.setOnAction((ActionEvent event) -> {
                            Module module = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + module.getNom());

                            mApp.supprimerModule(module);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        HBox managebtn = new HBox(editBtn, removeBtn);
                        HBox.setMargin(editBtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(removeBtn, new Insets(2, 3, 0, 2));

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(managebtn);
                        }
                    }
                };
                return cell;
            }
        };

        mActionModuleCln.setCellFactory(cellFactory);
    }

    public void setApp(App app) {
        mApp = app;
        mSallesTableView.setItems(mApp.getListeSalles());
        mModuleTableView.setItems(mApp.getListModule());
    }

    /**
     * Remplir le tableau d'EDT avec le dictionnaire
     * Map<Integer, String>
     */
    public void genereEDT(){

    }

    /**
     * Affiche les évenements dans le calandrier.
     */
    public void afficheEDT(){

    }

    /**
     * Sauvegarde l'EDT dans un fichier Latex.
     */
    public void saugarderEDTEnLatex(){

    }
}
