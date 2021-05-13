package fr.uvsq.interfaces;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import fr.uvsq.generateurEDT.*;
import fr.uvsq.gestionDeDonnees.*;
import fr.uvsq.models.*;
import fr.uvsq.models.Module;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AppController {
    private App mApp;
    private StringBuilder mEDTEnLatex;
    private Map<Integer, List<Evenement>> mEvenementParJour;
    private GenerateurEDT mGenerateurEDT;
    private Calendar mCalendarCM;
    private Calendar mCalendarTD;
    private Calendar mCalendarTP;
    private static final List<String> sHORAIRE = Arrays.asList(new String []{"8H00", "9H00",
            "10H00", "11H00", "12H00", "13H00", "14H00", "15H00", "16H00", "17H00", "18H00"});
    private static final List<String> sDAYS = Arrays.asList(new String []{"MO", "TU", "WE", "TH", "FR"});

    @FXML
    CalendarView mEDTCalendarPane;

    @FXML
    private Button mDashboardBtn,
            mSallesBtn,
            mModuleBtn,
            mProfBtn,
            mPromoBtn,
            mLatexBtn;

    @FXML
    private Label mNbSalles;
    @FXML
    private Label mNbProfs;
    @FXML
    private Label mNbPromos;
    @FXML
    private Label mNbModules;

    @FXML
    private Pane mDashboardPane,
            mSallesPane,
            mProfPane,
            mModulePane,
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
    private TableColumn<Module, Integer> mNbCMCln;
    @FXML
    private TableColumn<Module, Integer> mNbTDCln;
    @FXML
    private TableColumn<Module, Integer> mNbTPCln;
    @FXML
    private TableColumn<Module, Integer> mDureeCMCln;
    @FXML
    private TableColumn<Module, Integer> mDureeTDCln;
    @FXML
    private TableColumn<Module, Integer> mDureeTPCln;
    @FXML
    private TableColumn<Module, Void> mActionModuleCln;

    //=================== Prof TableView ======================
    @FXML
    private TableView<Professeur> mProfTableView;
    @FXML
    private TableColumn<Professeur, Integer> mIdProfCln;
    @FXML
    private TableColumn<Professeur, String> mNomProfCln;
    @FXML
    private TableColumn<Professeur, String> mModuleProfCln;
    @FXML
    private TableColumn<Professeur, Void> mActionProfCln;

    //=================== Promo TableView ======================
    @FXML
    private TableView<Promotion> mPromotionTableView;
    @FXML
    private TableColumn<Promotion, Integer> mIdPromoCln;
    @FXML
    private TableColumn<Promotion, String> mNomPromoCln;
    @FXML
    private TableColumn<Promotion, Integer> mNombreElevesPromoCln;
    @FXML
    private TableColumn<Promotion, Integer> mNombreGroupesPromoCln;
    @FXML
    private TableColumn<Promotion, String> mModulesPromoCln;
    @FXML
    private TableColumn<Promotion, LocalDate> mDatePromoCln;
    @FXML
    private TableColumn<Promotion, Void> mActionPromoCln;

    /**
     *  Affiche la page EDT
     */
    @FXML
    private void onHomeLabelClick(){
        System.out.println("=========== click on Home label +++++++++++");
        mEDTCalendarPane.showMonthPage();
        mEDTCalendarPane.toFront();
    }

    /**
     * Gére l'action des boutons onglets de l'application
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
        } else if (event.getSource() == mModuleBtn) {
            System.out.println(" =========== cours button ===========");
            mModulePane.toFront();
        } else if (event.getSource() == mPromoBtn ){
            System.out.println(" =========== Groupe button ===========");
            mPromoPane.toFront();
        } else if (event.getSource() == mLatexBtn){
            System.out.println(" =========== Latex button ===========");
            mLatexPane.toFront();
        }
    }

    /**
     * Initialise le calendrier.
     */
    private void initCalendarView(){
    }

    /**
     * Initialise tous les onglets et affiche
     * un emploi du temps vide.
     */
    @FXML
    private void initialize(){
        mEDTCalendarPane.toFront();
        initSalleTableView();
        initModuleTableView();
        initProfTableView();
        initPromoTableView();

        ajouteBoutonsActionSalle();
        ajouteBoutonsActionModule();
        ajouteBoutonsActionProf();
        ajouteBoutonsActionPromo();

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

    /**
     * Iniatilise la liste des modules qui doivent
     * être afficher dans le tableau @mModuleTableView
     */
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
        /*mDureeModuleCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getDuree();
            }
        });*/
        mNbCMCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getNbCM();
            }
        });
        mNbTDCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getNbTD();
            }
        });
        mNbTPCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getNbTP();
            }
        });
        mDureeCMCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getDureeCM();
            }
        });
        mDureeTDCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getDureeTD();
            }
        });
        mDureeTPCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getDureeTP();
            }
        });
    }

    /**
     * Iniatilise la liste des Professeurs qui doivent
     * être afficher dans le tableau @mProfTbaleView
     */
    private void initProfTableView() {

        mIdProfCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getId();
            }
        });
        mNomProfCln.setCellValueFactory(cellData -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return cellData.getValue().getNom();
            }
        });

        mModuleProfCln.setCellValueFactory(cellData -> new ObservableValueBase<>() {
            @Override
            public String getValue() {
                return cellData.getValue().getListeModulesAsString();
            }
        });
    }

    /**
     * Iniatilise la liste des Promotions qui doivent
     * être afficher dans le tableau @mPromotionTableView
     */
    private void initPromoTableView() {
        mIdPromoCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getId();
            }
        });
        mNomPromoCln.setCellValueFactory(cellData -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return cellData.getValue().getNom();
            }
        });
        mNombreElevesPromoCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getNbEleves();
            }
        });
        mNombreGroupesPromoCln.setCellValueFactory(cellData -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return cellData.getValue().getNbGroupes();
            }
        });
        mModulesPromoCln.setCellValueFactory(cellData -> new ObservableValueBase<>() {
            @Override
            public String getValue() {
                return cellData.getValue().getListeModulesAsString();
            }
        });
    }

    /**
     * Affiche une fenêtre qui permet d'ajouter une salle
     */
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

    /**
     * Affiche une fenêtre qui permet d'ajouter un module
     */
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

    /**
     * Affiche une fenêtre qui permet d'ajouter un prof
     */
    @FXML
    private void afficherDialogueAjouterProf() {
        System.out.println("Prof added");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/prof.fxml"));

        try{
            AnchorPane profPane = loader.load();
            ProfController profController = loader.getController();
            Scene scene = new Scene(profPane);
            Stage profStage = new Stage(StageStyle.UNDECORATED);
            profController.setApp(mApp, profStage, mApp.getListModule());
            profStage.setResizable(false);
            profStage.setScene(scene);
            profStage.initOwner(mApp.getAppStage());
            profStage.initModality(Modality.APPLICATION_MODAL);
            profStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Affiche une fenêtre qui permet d'ajouter un prof
     */

    /**
     * Affiche une fenêtre qui permet d'ajouter une promotion
     */
    @FXML
    private void afficherDialogueAjouterPromo() {
        System.out.println("Promo added");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/promo.fxml"));

        try{
            AnchorPane promoPane = loader.load();
            PromoController promoController = loader.getController();
            Scene scene = new Scene(promoPane);
            Stage promoStage = new Stage(StageStyle.UNDECORATED);
            promoController.setApp(mApp, promoStage, mApp.getListModule());
            promoStage.setResizable(false);
            promoStage.setScene(scene);
            promoStage.initOwner(mApp.getAppStage());
            promoStage.initModality(Modality.APPLICATION_MODAL);
            promoStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche une fenêtre qui permet de modifier une salle
     * @param salle correspond à la salle à modifier
     */
    private void afficherDialogueModifierSalle(Salle salle){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/salle.fxml"));

        try{
            AnchorPane sallePane = loader.load();
            SalleController salleController = loader.getController();
            salleController.initialiseDialogueModification(salle, mSallesTableView);
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
     * Affiche une fenêtre qui permet de modifier un prof
     * @param prof correspond à au prof à modifier
     */
    private void afficherDialogueModifierProf(Professeur prof) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/prof.fxml"));

        try{
            AnchorPane profPane = loader.load();
            ProfController profController = loader.getController();
            profController.initialiseDialogueModification(prof, mProfTableView);
            Scene scene = new Scene(profPane);
            Stage profStage = new Stage(StageStyle.UNDECORATED);
            profController.setApp(mApp, profStage, mApp.getListModule());
            profStage.setResizable(false);
            profStage.setScene(scene);
            profStage.initOwner(mApp.getAppStage());
            profStage.initModality(Modality.APPLICATION_MODAL);
            profStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche une fenêtre qui permet de modifier un module
     * @param module correspond à le module à modifier
     */
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

    /**
     * Affiche une fenêtre qui permet de modifier une promotion
     * @param promo correspond à la promotion à modifier
     */
    private void afficherDialogueModifierPromo(Promotion promo) {
        System.out.println("Promo added");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fr/uvsq/promo.fxml"));

        try{
            AnchorPane promoPane = loader.load();
            PromoController promoController = loader.getController();
            promoController.initialiseDialogueModification(promo, mPromotionTableView);
            Scene scene = new Scene(promoPane);
            Stage promoStage = new Stage(StageStyle.UNDECORATED);
            promoController.setApp(mApp, promoStage, mApp.getListModule());
            promoStage.setResizable(false);
            promoStage.setScene(scene);
            promoStage.initOwner(mApp.getAppStage());
            promoStage.initModality(Modality.APPLICATION_MODAL);
            promoStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute des boutons qui permettent de modifier ou de supprimer une salle
     */
    private void ajouteBoutonsActionSalle() {
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
                            SalleDAO salleDAO = (SalleDAO) FactoryDAO.getSalleDAO();
                            if( salleDAO.supprimer(salle) ) {
                                mApp.getListeSalles().remove(salle);
                                mNbSalles.setText(String.valueOf(mApp.getListeSalles().size()));
                            }


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

    /**
     * Ajoute des boutons qui permettent de modifier ou de supprimer un module
     */
    private void ajouteBoutonsActionModule() {
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
                            ModuleDAO moduleDAO = (ModuleDAO) FactoryDAO.getModuleDAO();
                            if( moduleDAO.supprimer(module) ) {
                                mApp.getListModule().remove(module);
                                mNbModules.setText(String.valueOf(mApp.getListModule().size()));
                            }
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

    /**
     * Ajoute des boutons qui permettent de modifier ou de supprimer un professeur
     */
    private void ajouteBoutonsActionProf() {
        Callback<TableColumn<Professeur, Void>, TableCell<Professeur, Void>> cellFactory = new Callback<TableColumn<Professeur, Void>, TableCell<Professeur, Void>>() {
            @Override
            public TableCell<Professeur, Void> call(final TableColumn<Professeur, Void> param) {
                final TableCell<Professeur, Void> cell = new TableCell<Professeur, Void>() {

                    Button editBtn = new Button("Modifier");
                    Button removeBtn = new Button("Supprimer");

                    {
                        editBtn.setStyle("-fx-text-fill: #ffffff");
                        removeBtn.setStyle("-fx-text-fill: #ffffff");
                        editBtn.setOnAction((ActionEvent event) -> {
                            Professeur prof = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + prof.getNom());
                            afficherDialogueModifierProf(prof);
                        });

                        removeBtn.setOnAction((ActionEvent event) -> {
                            Professeur prof = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + prof.getNom());
                            ProfesseurDAO professeurDAO = (ProfesseurDAO) FactoryDAO.getProfesseurDAO();
                            if( professeurDAO.supprimer(prof) ) {
                                mApp.getListeProfs().remove(prof);
                                mNbProfs.setText(String.valueOf(mApp.getListeProfs().size()));
                            }
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

        mActionProfCln.setCellFactory(cellFactory);

    }

    /**
     * Ajoute des boutons qui permettent de modifier ou de supprimer une promotion
     */
    private void ajouteBoutonsActionPromo() {
        Callback<TableColumn<Promotion, Void>, TableCell<Promotion, Void>> cellFactory = new Callback<TableColumn<Promotion, Void>, TableCell<Promotion, Void>>() {
            @Override
            public TableCell<Promotion, Void> call(final TableColumn<Promotion, Void> param) {
                final TableCell<Promotion, Void> cell = new TableCell<Promotion, Void>() {

                    Button editBtn = new Button("Modifier");
                    Button removeBtn = new Button("Supprimer");

                    {
                        editBtn.setStyle("-fx-text-fill: #ffffff");
                        removeBtn.setStyle("-fx-text-fill: #ffffff");
                        editBtn.setOnAction((ActionEvent event) -> {
                            Promotion promo = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + promo.getNom());
                            afficherDialogueModifierPromo(promo);
                        });

                        removeBtn.setOnAction((ActionEvent event) -> {
                            Promotion promo = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + promo.getNom());
                            PromoDAO promoDAO = (PromoDAO) FactoryDAO.getPromotionDAO();
                            if( promoDAO.supprimer(promo) ){
                                mApp.getListePromos().remove(promo);
                                mNbPromos.setText(String.valueOf(mApp.getListePromos().size()));
                            }
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

        mActionPromoCln.setCellFactory(cellFactory);

    }


    /**
     * Récuper les contraintes saisies par l'utilisateur
     * et appelle méthode de vérification de la classe Vérification.
     */
    @FXML
    private void validerContraintes(){

    }

    /**
     * Lance la génération de l'emploi du temps
     */
    public void genereEDT(){

    }

    /**
     * Affiche les évenements sur le calendrier.
     */
    public void afficheEDT(){

        mEDTCalendarPane.showMonthPage();
        mEDTCalendarPane.setShowAddCalendarButton(false);
        mEDTCalendarPane.setWeekFields(WeekFields.ISO);
        mEDTCalendarPane.setShowPrintButton(false);
        mEDTCalendarPane.setShowDeveloperConsole(false);
        mCalendarCM = new Calendar();
        mCalendarTD = new Calendar();
        mCalendarTP = new Calendar();
        mCalendarCM.setStyle(Calendar.Style.STYLE1);
        mCalendarTP.setStyle(Calendar.Style.STYLE4);
        mCalendarTD.setStyle(Calendar.Style.STYLE6);

        mGenerateurEDT= new GenerateurEDT(0.0, 0.0, null, null, new DonneesEDT());
        mGenerateurEDT.solutionInitiale();
        Map<Integer, List<Evenement>> map = mGenerateurEDT.getEvenementsParJour();

        for( Map.Entry<Integer, List<Evenement>> even : map.entrySet()){

            for (Evenement e : even.getValue()) {
                String nomGroupe = (e.getTypeEven() == TypeEven.CM) ? "" : e.getGroupe().getNom();
                int heure = e.getCreneau().getHoraire() + 8;
                String nomSalle = mGenerateurEDT.getSolutionFinale().getListeEDTSalles().get(e.getCreneau()
                                                .getIdsalle() - 1).getSalle().getNom();
                String title = TypeEven.getTypeEvenString(e.getTypeEven()) + " "
                        + " " +  e.getModule().getNom() + " "
                        + " " +  nomGroupe + " "
                        + " " + e.getGroupe().getPromotion().getNom() + " "
                        + " Salle " + nomSalle;
                Entry entry = new Entry();

                entry.setInterval(e.getGroupe().getPromotion().getLocalDate());
                if( e.getTypeEven() == TypeEven.CM){
                    entry.setRecurrenceRule("RRULE:FREQ=WEEKLY;BYDAY="+sDAYS.get(e.getCreneau().getJour())
                                            +";COUNT="+ e.getModule().getNbCM());
                    int dureeHeure = e.getModule().getDureeCM() / 60;
                    int minute = e.getModule().getDureeCM() % 60;
                    entry.setInterval(LocalTime.of(heure, 0), LocalTime.of(heure + dureeHeure, minute));
                    entry.setTitle(title);
                    mCalendarCM.addEntry(entry);
                } else if ( e.getTypeEven() == TypeEven.TD){
                    entry.setRecurrenceRule("RRULE:FREQ=WEEKLY;BYDAY="+sDAYS.get(e.getCreneau().getJour())
                            +";COUNT="+ e.getModule().getNbTD());
                    int dureeHeure = e.getModule().getDureeTD() / 60;
                    int minute = e.getModule().getDureeTD() % 60;
                    entry.setInterval(LocalTime.of(heure, 0), LocalTime.of(heure + dureeHeure, minute));
                    entry.setTitle(title);
                    mCalendarTD.addEntry(entry);
                } else {
                    entry.setRecurrenceRule("RRULE:FREQ=WEEKLY;BYDAY="+sDAYS.get(e.getCreneau().getJour())
                            +";COUNT="+ e.getModule().getNbTP());
                    int dureeHeure = e.getModule().getDureeTP() / 60;
                    int minute = e.getModule().getDureeTP() % 60;
                    entry.setInterval(LocalTime.of(heure, 0), LocalTime.of(heure + dureeHeure, minute));
                    entry.setTitle(title);
                    mCalendarTP.addEntry(entry);
                }
            }
        }
        CalendarSource csrc = new CalendarSource();
        csrc.getCalendars().addAll(mCalendarCM, mCalendarTP, mCalendarTD);
        mEDTCalendarPane.getCalendarSources().add(csrc);
        //mEDTCalendarPane.toFront();

    }

    /**
     * Sauvegarde l'EDT dans un fichier Latex.
     */
    public void saugarderEDTEnLatex(){

    }

    public void setApp(App app) {
        mApp = app;
        mSallesTableView.setItems(mApp.getListeSalles());
        mModuleTableView.setItems(mApp.getListModule());
        mProfTableView.setItems(mApp.getListeProfs());
        mPromotionTableView.setItems(mApp.getListePromos());
    }

    public void setNbSalles(String label) {
        mNbSalles.setText(label);
    }

    public void setNbProfs(String nbProfs) {
        mNbProfs.setText(nbProfs);
    }

    public void setNbPromos(String nbPromos) {
        mNbPromos.setText(nbPromos);
    }

    public void setNbModules(String nbModules) {
        mNbModules.setText(nbModules);
    }
}
