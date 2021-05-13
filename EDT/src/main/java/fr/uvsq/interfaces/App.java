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
    private FactoryDAO mFactoryDAO;

    public AppController getAppController() {
        return mAppController;
    }

    public ObservableList<Salle> getListeSalles() {
        return mListeSalles;
    }
    public ObservableList<Module> getListModule() { return mListeModule; }
    public ObservableList<Professeur> getListeProfs() { return mListeProfs; }
    public ObservableList<Promotion> getListePromos() { return mListePromos; }
    public Stage getAppStage() {
        return mAppStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        mAppStage = stage;
        initialiseApp();
        initBaseDonnes();
        initListeSalles();
        initListeModules();
        initListeProfs();
        initListePromotions();
        mAppController.afficheEDT();
    }

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
            //mAppStage.setResizable(false);
            mAppStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void initListeSalles(){
        mListeSalles.addAll(mSalleDAO.recupererListe());
        mAppController.setNbSalles(String.valueOf(mListeSalles.size()));

    }

    private void initListePromotions(){
        mListePromos.addAll(mPromoDAO.recupererListe());
        mAppController.setNbPromos(String.valueOf(mListePromos.size()));
    }
    private void initListeModules() {
        mListeModule.addAll(mModuleDAO.recupererListe());
        mAppController.setNbModules(String.valueOf(mListeModule.size()));
    }

    private void initListeProfs() {
        mListeProfs.addAll(mProfDAO.recupererListe());
        mAppController.setNbProfs(String.valueOf(mListeProfs.size()));
    }

    private void initBaseDonnes() {
        ScriptRunner runner = new ScriptRunner(BDConnection.getConnection());
        try {
            Reader reader = new BufferedReader(new FileReader("src/main/resources/sql/scriptCreation.sql"));
            runner.runScript(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void testSolutionInit(){
    }
    public static void main(String[] args) {
        launch();
    }
}