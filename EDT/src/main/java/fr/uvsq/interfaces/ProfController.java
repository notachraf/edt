package fr.uvsq.interfaces;

import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;

public class ProfController {

    private App mApp;
    private Stage mProfStage;
    private ObservableList<Module> mListeModule;
    private Professeur mProf;
    private TableView<Professeur> mProfTableView;


    @FXML
    private TextField mNomTextField;

    @FXML
    private CheckComboBox<String> mPeutEnseignerComboBox;

    @FXML
    private Button mAnnulerDialogueProfBtn;

    @FXML
    private Label mTitleLabel;

    @FXML
    private Button mAjouterProfBtn;

    @FXML
    void fermer() {
        mProfStage.close();
    }

    public void initialiseDialogueModification(Professeur prof, TableView<Professeur> profTableView) {
        mProf = prof;
        mProfTableView = profTableView;
        mTitleLabel.setText("Modification du prof");
        mNomTextField.setText("");
        mAjouterProfBtn.setText("Modifier");

        if(prof != null) {
            mNomTextField.setText(prof.getNom());
            //Auto check already selected modules
//            mPeutEnseignerComboBox.getCheckModel().check(0);
//            mPeutEnseignerComboBox.getCheckModel().check(1);
        }
    }

    @FXML
    void handleAjouterBtn(ActionEvent event) {
        if (event.getSource() == mAjouterProfBtn) {
            if (mAjouterProfBtn.getText().equals("Modifier")) {
                modifierProf();
            } else {
                ajouterNouveauProf();
            }
        }
    }

    private void ajouterNouveauProf() {
        //
        boolean estValide = true;

        if( estValide ) {

            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mPeutEnseignerComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getDuree(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }

            mApp.ajouterProf(new Professeur(mNomTextField.getText(), modules));
            fermer();
        }
    }

    private void modifierProf() {
        //
        boolean estValide = true;

        if( estValide ) {

            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mPeutEnseignerComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getDuree(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }
            Professeur nouveauProf = new Professeur(mNomTextField.getText(), modules);
            mApp.modifierProf(mProf, nouveauProf, mProfTableView);
            fermer();
        }

    }

    public void setApp(App app, Stage profStage, ObservableList<Module> listeModule) {
        mApp = app;
        mProfStage = profStage;
        mListeModule = listeModule;

        ObservableList<String> modules = FXCollections.observableArrayList();
        for(Module module : mListeModule) {
            modules.add(module.getNom());
        }
        mPeutEnseignerComboBox.getItems().addAll(modules);
    }


}
