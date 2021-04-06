package fr.uvsq.interfaces;

import fr.uvsq.models.Salle;
import fr.uvsq.models.Module;
import fr.uvsq.models.TypeSalle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private AppController mAppController;
    private Stage mAppStage;
    private ObservableList<Salle> mListeSalles = FXCollections.observableArrayList();
    private ObservableList<Module> mListeModule = FXCollections.observableArrayList();

    /*
        === ..les listes à afficher dans les tables correspondantes.. ===
    private ObservableList<Groupe> mListeGroupes = FXCollections.observableArrayList();
    private ObservableList<Professeur> mListeProf = FXCollections.observableArrayList();

        === Les objets permettant d'accéder aux données de la base de données ===
    private SalleDAO mSalleDAO;
    private CoursDAO mCoursDAO;
    private ProfDAO mProfDAO;
    private GroupeDAO mGroupeDAO;
    */

    @Override
    public void start(Stage stage) throws IOException {
        mAppStage = stage;
        initialiseApp();
        initListeSalles();
        initListeModules();
//        initListeProfs();
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
        mListeModule.add(new Module("IN608 - Projet", 50, 3, true, false));
    }

    public ObservableList<Salle> getListeSalles() {
        return mListeSalles;
    }
    public ObservableList<Module> getListModule() { return mListeModule; }

    public Stage getAppStage() {
        return mAppStage;
    }

    public void ajouteSalle(Salle salle){
        mListeSalles.add(salle);
        System.out.println("======== Add a new room.");
    }

    public void supprimerSalle(Salle salle){
        mListeSalles.remove(salle);
        System.out.println("======== remove a room.");
    }

    public void modifierSalle(Salle salle, Salle nouvellSalle, TableView<Salle> salleTableView){
        int index = mListeSalles.indexOf(salle);
        mListeSalles.get(index).setId(nouvellSalle.getId());
        mListeSalles.get(index).setNom(nouvellSalle.getNom());
        mListeSalles.get(index).setCapacite(nouvellSalle.getCapacite());
        mListeSalles.get(index).setTypeSalle(nouvellSalle.getTypeSalle());
        salleTableView.refresh();
        System.out.println("======== update a room.");
    }

    public void ajouteModule(Module module){
        mListeModule.add(module);
        System.out.println("======== Add a new course.");
    }
    public void supprimerModule(Module module){
        mListeModule.remove(module);
        System.out.println("======== remove a course.");
    }

    public void modifierModule(Module module, Module nouveauModule, TableView<Module> moduleTableView){
        int index = mListeModule.indexOf(module);
        mListeModule.get(index).setId(nouveauModule.getId());
        mListeModule.get(index).setNom(nouveauModule.getNom());
        mListeModule.get(index).setDuree(nouveauModule.getDuree());
        mListeModule.get(index).setNbCoursSemaine(nouveauModule.getNbCoursSemaine());
        mListeModule.get(index).setTD(nouveauModule.isTD());
        mListeModule.get(index).setTP(nouveauModule.isTP());
        moduleTableView.refresh();
        System.out.println("======== update a course.");
    }


    /*  Methode à implémenter.

    public void ajouteProf();
    public void supprimerProf();
    public void modifierProf();
    public void ajouterGroupe();
    public void supprimerProf();
    public void modifierProf();

     */

    public static void main(String[] args) {
        launch();
    }

}