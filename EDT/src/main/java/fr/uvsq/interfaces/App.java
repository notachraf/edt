package fr.uvsq.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.ModuleDAO;
import fr.uvsq.gestionDeDonnees.ProfesseurDAO;
import fr.uvsq.gestionDeDonnees.SalleDAO;
import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promotion;
import fr.uvsq.models.Salle;
import fr.uvsq.models.TypeSalle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private ProfesseurDAO mProfDAO;
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
        initListePromotions();
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
    
    private void initListePromotions() {
    	mListePromos.addAll(FactoryDAO.getPromotionDAO()
    			.recupererListe());
	}
    
    private void initListeSalles(){
    	DAO salleDao = FactoryDAO.getSalleDAO();
    	ArrayList<Salle> salles = salleDao.recupererListe();
    	mListeSalles.addAll(salles);
    }
    private void initListeModules() {
    	DAO moduleDao = FactoryDAO.getModuleDAO();
    	ArrayList<Module> modules = moduleDao.recupererListe();
    	mListeModule.addAll(modules);
    }

    private void initListeProfs() {

    	DAO<Professeur> proDao = FactoryDAO.getProfesseurDAO();
    	mListeProfs.addAll(proDao.recupererListe());
    	
    	DAO moduleDao = FactoryDAO.getModuleDAO();
        ArrayList<Module> modules = new ArrayList<>();
        modules.addAll(moduleDao.recupererListe());
        
    }
    
    public static void main(String[] args) {
        launch();
    }
}