package fr.uvsq.interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private final static int WIDTH_WINDOW = 1000;
    private final static int HEIGHT_WINDOW = 700;
    private AppController mAppController;
    private Stage mAppStage;

    @Override
    public void start(Stage stage) throws IOException {
        mAppStage = stage;
        initialiseApp();
    }

    private void initialiseApp(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fr/uvsq/app.fxml"));
        try {
            AnchorPane appPane = fxmlLoader.load();
            mAppController = fxmlLoader.getController();
            mAppController.setAppStage(mAppStage);
            Scene appScene = new Scene(appPane);
            mAppStage.setTitle("EDT");
            mAppStage.setScene(appScene);
            mAppStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }

}