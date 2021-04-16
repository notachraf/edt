package fr.uvsq.interfaces;

import fr.uvsq.models.*;
import fr.uvsq.models.Module;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * JavaFX App
 */
public class App extends Application {
    private AppController mAppController;
    private Stage mAppStage;
    private ObservableList<Salle> mListeSalles = FXCollections.observableArrayList();
    private ObservableList<Module> mListeModule = FXCollections.observableArrayList();
    private ObservableList<Professeur> mListeProfs = FXCollections.observableArrayList();
    private ObservableList<Promo> mListePromos = FXCollections.observableArrayList();

    /*
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
        mListeModule.add(new Module("IN608 - Projet", 50, 13, 13, 13, 90, 180, 180));
    }

    private void initListeProfs() {
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("IN608 - Projet", 50, 3, 3, 3,2,2,2));
    }

    public ObservableList<Salle> getListeSalles() {
        return mListeSalles;
    }
    public ObservableList<Module> getListModule() { return mListeModule; }
    public ObservableList<Professeur> getListeProfs() { return mListeProfs; }
    public ObservableList<Promo> getListePromos() { return mListePromos; }

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
//        mListeSalles.get(index).setId(nouvellSalle.getId());
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
        mListeModule.get(index).setNom(nouveauModule.getNom());
        mListeModule.get(index).setDuree(nouveauModule.getDuree());
        mListeModule.get(index).setDureeCM(nouveauModule.getDureeCM());
        mListeModule.get(index).setDureeTD(nouveauModule.getDureeTD());
        mListeModule.get(index).setDureeTP(nouveauModule.getDureeTP());
        mListeModule.get(index).setNbCM(nouveauModule.getNbCM());
        mListeModule.get(index).setNbTD(nouveauModule.getNbTD());
        mListeModule.get(index).setNbTP(nouveauModule.getNbTP());
        moduleTableView.refresh();
        System.out.println("======== update a course.");
    }

    public void ajouterProf(Professeur prof){
        mListeProfs.add(prof);
    }
    public void supprimerProf(Professeur prof){
        mListeProfs.remove(prof);
    }
    public void modifierProf(Professeur prof, Professeur nouveauProf, TableView<Professeur> profTableView){
        int index = mListeProfs.indexOf(prof);
        mListeProfs.get(index).setNom(nouveauProf.getNom());
        mListeProfs.get(index).setListeModules(nouveauProf.getListeModules());
        profTableView.refresh();
    }

    public void ajouterPromo(Promo promo) {
        mListePromos.add(promo);
        System.out.println(promo.getNom());
        System.out.println(promo.getNbEleves());
        System.out.println(promo.getNbGroupes());
        System.out.println(promo.getListeModulesAsString());
    }
    public void supprimerPromo(Promo promo) {
        mListeModule.remove(promo);
    }
    public void modifierPromo(Promo promo, Promo nouvellePromo, TableView<Promo> promoTableView) {
        int index = mListePromos.indexOf(promo);
        mListePromos.get(index).setNom(nouvellePromo.getNom());
        mListePromos.get(index).setNbEleves(nouvellePromo.getNbEleves());
        mListePromos.get(index).setNbGroupes(nouvellePromo.getNbGroupes());
        mListePromos.get(index).setListeModules(nouvellePromo.getListeModules());
        promoTableView.refresh();
    }

    public static void main(String[] args) {
        launch();
    }

}