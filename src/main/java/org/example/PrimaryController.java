package org.example;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PrimaryController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private MenuItem Open;

    @FXML
    private MenuItem Quit;

    @FXML
    private Pane PnlCours;

    @FXML
    private Pane PnlProf;

    @FXML
    private Pane PnlDashboard;

    @FXML
    private Pane PnlSalles;

    @FXML
    private TextArea textArea;

    @FXML
    private Button addNewRoomBtn;

    @FXML
    private TableView salleTableView;

    @FXML
    private TableColumn<Salle, String> capaciteColonne;

    @FXML
    private TableColumn<Salle, String> nomColonne;


    private App mApp;

    private ObservableList<Salle> mListSalles = FXCollections.observableArrayList();

    public PrimaryController(){
        mListSalles.add(new Salle("Buffon", "1", "34"));
        mListSalles.add(new Salle("Ferma", "2", "32"));
        mListSalles.add(new Salle("Descarte", "3", "31"));
        mListSalles.add(new Salle("Gauss", "4", "34"));
        mListSalles.add(new Salle("Centre", "5", "34"));
    }

    public static void addSale() {
        System.out.println("salle added");
    }


    @FXML
    private void HandleLoadClicked(ActionEvent event) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//        Window stage = mainBorderPane.getScene().getWindow();
//        fileChooser.setTitle("Load a file");
//        fileChooser.getExtensionFilters().addAll(
//                new ExtensionFilter("Text Files", "*.txt"));
//        File selectedFile = fileChooser.showOpenDialog(stage);
//        fileChooser.setInitialDirectory(selectedFile.getParentFile());
//
//        String content = Files.readString(Paths.get(selectedFile.getAbsolutePath()));
//        textArea.setText(content);
    }

    @FXML
    void MenuDashboard(ActionEvent event) {
        PnlDashboard.toFront();
    }

    @FXML
    void MenuSalle(ActionEvent event) {
        PnlSalles.toFront();
    }

    @FXML
    void MenuProf(ActionEvent event) {
        PnlProf.toFront();
    }

    @FXML
    void addNewRoom(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initModality(Modality.WINDOW_MODAL);

        SecondaryController dialogueController = fxmlLoader.getController();

        dialogueController.setDialogueStage(stage);

        stage.show();
    }

    @FXML
    private void Quit() {
        Platform.exit();
    }

    @FXML
    public void initialize(){
        System.out.println("initialized");
        capaciteColonne.setCellValueFactory( cellData -> cellData.getValue().getCapacite());
        nomColonne.setCellValueFactory( cellData -> cellData.getValue().getNom());
    }
}
