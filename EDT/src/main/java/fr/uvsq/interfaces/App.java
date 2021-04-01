package fr.uvsq.interfaces;

import fr.uvsq.models.Salle;
import fr.uvsq.models.TypeSalle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * JavaFX App
 */
public class App extends Application {

    private AppController mAppController;
    private Stage mAppStage;
    private ObservableList<Salle> mListeSalles = FXCollections.observableArrayList();
    /*
        === les listes à afficher dans les tables correspondantes. ===
    private ObservableList<Cours> mListeSalles = FXCollections.observableArrayList();
    private ObservableList<Groupe> mListeSalles = FXCollections.observableArrayList();
    private ObservableList<Professeur> mListeSalles = FXCollections.observableArrayList();

        === Les objets permettant d'accéder aux données de la base de données ===
    private SalleDAO mSalleDAO;
    private CoursDAO mCoursDAO;
    private ProfDAO mProfDAO;
    private GroupeDAO mGroupeDAO;
    */

    @Override
    public void start(Stage stage) throws IOException {
        mAppStage = stage;
        initListeSalles();
        initialiseApp();
    }

    private void initialiseApp(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fr/uvsq/app.fxml"));
        try {
            AnchorPane appPane = fxmlLoader.load();
            mAppController = fxmlLoader.getController();
            mAppController.setApp(this);
            Scene appScene = new Scene(appPane);
            mAppStage.setTitle("EDT");
            mAppStage.setScene(appScene);
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
        mListeSalles.add(new Salle("A", 32, TypeSalle.AMPHI));
    }

    public ObservableList<Salle> getListeSalles() {
        return mListeSalles;
    }

    public Stage getAppStage() {
        return mAppStage;
    }

    public void updateListeSalles(){
        System.out.println("======== Update liste salles.");
    }

    /*  Methode à implémenter.

    public void updateListeGroupes();
    public void updateListeProfesseurs();
    public void updateListeCours();

     */

    public static void main(String[] args) {
        launch();
    }

}