package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.ModuleDAO;
import fr.uvsq.gestionDeDonnees.ProfDAO;
import fr.uvsq.gestionDeDonnees.SalleDAO;
import fr.uvsq.models.*;
import fr.uvsq.models.Module;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

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
    private SalleDAO mSalleDAO;
    private ModuleDAO mModuleDAO;
    private ProfDAO mProfDAO;
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
        initialiseApp();
        initListeSalles();
        initListeModules();
        initListeProfs();
//        initListeGroupes();
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
        mListeSalles.add(new Salle("Buffon", 32, TypeSalle.TD));
        mListeSalles.add(new Salle("Descartes", 32, TypeSalle.TD));
        mListeSalles.add(new Salle("Centre", 32, TypeSalle.TD));
        mListeSalles.add(new Salle("Archimede", 32, TypeSalle.TP));
    }
    private void initListeModules() {
        mListeModule.add(new Module("IN608 - Projet", 13, 13, 13, 90, 180, 180));
    }

    private void initListeProfs() {
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("IN608 - Projet", 3, 3, 3,2,2,2));
    }

    public static void main(String[] args) {
        launch();
    }
}