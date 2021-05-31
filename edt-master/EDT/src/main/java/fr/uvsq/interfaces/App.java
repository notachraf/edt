package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.*;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promotion;
import fr.uvsq.models.Salle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;


/**
 * JavaFX App
 */
public class App extends Application {
    private AppController mAppController;
    private Stage mAppStage;
    private ObservableList<Salle> mListeSalles = FXCollections.observableArrayList();
    private ObservableList<Module> mListeModule = FXCollections.observableArrayList();
    private ObservableList<Professeur> mListeProfs = FXCollections.observableArrayList();
    private ObservableList<Promotion> mListePromos = FXCollections.observableArrayList();
    private SalleDAO mSalleDAO = (SalleDAO) FactoryDAO.getSalleDAO();
    private ModuleDAO mModuleDAO = (ModuleDAO) FactoryDAO.getModuleDAO();
    private PromoDAO mPromoDAO = (PromoDAO) FactoryDAO.getPromotionDAO();
    private ProfesseurDAO mProfDAO = (ProfesseurDAO) FactoryDAO.getProfesseurDAO();

    /**
     *
     * @return appController actuel
     */
    public AppController getAppController() {
        return mAppController;
    }

    /**
     *
     * @return liste des salles actuelle
     */
    public ObservableList<Salle> getListeSalles() {
        return mListeSalles;
    }

    /**
     *
     * @return liste des modules actuelle
     */
    public ObservableList<Module> getListModule() { return mListeModule; }

    /**
     *
     * @return liste des professeurs actuelle
     */
    public ObservableList<Professeur> getListeProfs() { return mListeProfs; }

    /**
     *
     * @return liste des promotions actuelle
     */
    public ObservableList<Promotion> getListePromos() { return mListePromos; }

    /**
     *
     * @return appStage actuelle
     */
    public Stage getAppStage() {
        return mAppStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        initBaseDonnes();
        mAppStage = stage;
        initialiseApp();
        initListeSalles();
        initListeModules();
        initListeProfs();
        initListePromotions();

    }

    /**
     * Initialisation de la fenêtre principale
     */
    private void initialiseApp(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fr/uvsq/app.fxml"));
        try {
            BorderPane appPane = fxmlLoader.load();
            mAppController = fxmlLoader.getController();
            mAppController.setApp(this);
            Scene appScene = new Scene(appPane);
            mAppStage.setTitle("EDT");
            mAppStage.setScene(appScene);

            mAppStage.setResizable(false);
            mAppStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Initialisation de la liste des Salles
     */
    private void initListeSalles(){
        if( mSalleDAO.recupererListe() != null ) {
            mListeSalles.addAll(mSalleDAO.recupererListe());
            mAppController.setNbSalles(String.valueOf(mListeSalles.size()));
        }
    }

    /**
     * Initialisation de la liste des Promotion
     */
    private void initListePromotions(){
        if( mPromoDAO.recupererListe() != null ) {
            mListePromos.addAll(mPromoDAO.recupererListe());
            mAppController.setNbPromos(String.valueOf(mListePromos.size()));
        }
    }

    /**
     * Initialisation de la liste des Modules
     */
    private void initListeModules() {
        if( mModuleDAO.recupererListe() != null ) {
            mListeModule.addAll(mModuleDAO.recupererListe());
            mAppController.setNbModules(String.valueOf(mListeModule.size()));
        }
    }
    /**
     * Initialisation de la liste des Professeurs
     */
    private void initListeProfs() {
        if(mProfDAO.recupererListe() != null ) {
            mListeProfs.addAll(mProfDAO.recupererListe());
            mAppController.setNbProfs(String.valueOf(mListeProfs.size()));
        }
    }

    /**
     * Initialisation de la base de données.
     */
    private void initBaseDonnes() {
        ScriptRunner runner = new ScriptRunner(BDConnection.getConnection());
        try {
            InputStream in = getClass().getResourceAsStream("/sql/scriptCreation.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            runner.setEscapeProcessing(false);
            runner.runScript(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance l'application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}