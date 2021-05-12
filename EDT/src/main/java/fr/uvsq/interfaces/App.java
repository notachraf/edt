package fr.uvsq.interfaces;

import fr.uvsq.generateurEDT.DonneesEDT;
import fr.uvsq.generateurEDT.EDT;
import fr.uvsq.generateurEDT.Evenement;
import fr.uvsq.generateurEDT.GenerateurEDT;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        initBaseDonnes();
        testSolutionInit();
        initialiseApp();
        initListeSalles();
        initListeModules();
        initListeProfs();
        initListePromotions();
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
            mAppStage.setResizable(false);
            mAppStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void initListeSalles(){
        for (Salle salle : mSalleDAO.recupererListe()) {
            mListeSalles.add(salle);
        }
    }

    private void initListePromotions(){
        for( Promotion promotion : mPromoDAO.recupererListe() ){
            mListePromos.add(promotion);
        }
    }
    private void initListeModules() {
        for (Module module : mModuleDAO.recupererListe()) {
            mListeModule.add(module);
        }
    }

    private void initListeProfs() {
        for (Professeur professeur : mProfDAO.recupererListe()) {
            mListeProfs.add(professeur);
        }
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
        GenerateurEDT gen = new GenerateurEDT(0.0, 0.0, null, null, new DonneesEDT());
        EDT edt = gen.solutionInitiale();
        Map<Integer, List<Evenement>> map = gen.getEvenementsParJour();
        int nbEvent = 0;
        for( Map.Entry<Integer, List<Evenement>> entry : map.entrySet()){
            System.out.println("Jour: " + entry.getKey());
            nbEvent += entry.getValue().size();
            for (Evenement e : entry.getValue()) {
                System.out.print(" " + e.getTypeEven());
            }
            System.out.println("");
        }

        System.out.println("NB even bis "  + nbEvent);

    }
    public static void main(String[] args) {
        launch();
    }
}