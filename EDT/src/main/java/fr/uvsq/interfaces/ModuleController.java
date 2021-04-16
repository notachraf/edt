package fr.uvsq.interfaces;

import fr.uvsq.models.Module;
import fr.uvsq.verification.Verification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModuleController {

        // private ModuleDAO mModuleDao;
        private App mApp;
        private Stage mModuleStage;
        private Module mModule;
        private TableView<Module> mModuleTableView;

        @FXML
        private Button mAjouterModuleBtn;

        @FXML
        private Label mTitleLabel;

        @FXML
        private TextField mNomTextField,
                mDureeTextField,
                mNbCMTextField,
                mNbTDTextField,
                mNbTPTextField,
                mDureeCMTextField,
                mDureeTDTextField,
                mDureeTPTextField;


        @FXML
        void fermer() {
                mModuleStage.close();
        }

        public void initialiseDialogueModification(Module module, TableView<Module> moduleTableView) {
                mModule = module;
                mModuleTableView = moduleTableView;
                mTitleLabel.setText("Modification du module");
                mNomTextField.setText("");
                mAjouterModuleBtn.setText("Modifier");

                if (module != null) {
                        mNomTextField.setText(module.getNom());
                        mDureeTextField.setText(String.valueOf(module.getDuree()));
                        mNbCMTextField.setText(String.valueOf(module.getNbCM()));
                        mNbTDTextField.setText(String.valueOf(module.getNbTD()));
                        mNbTPTextField.setText(String.valueOf(module.getNbTP()));
                        mDureeCMTextField.setText(String.valueOf(module.getDureeCM()));
                        mDureeTDTextField.setText(String.valueOf(module.getDureeTD()));
                        mDureeTPTextField.setText(String.valueOf(module.getDureeTP()));
                }
        }

        @FXML
        void handleAjouterBtn(ActionEvent event) {
                if (event.getSource() == mAjouterModuleBtn) {
                        if (mAjouterModuleBtn.getText().equals("Modifier")) {
                                modifierModule();
                        } else {
                                ajouterModule();
                        }
                }
        }

        void ajouterModule() {
                //Vérification à implementer
                boolean estValide = true;

                if( estValide ) {
                        System.out.println(" ========= Les Données sont valides ============");
                        System.out.println("======== Ajouter nouveau module");

                        mApp.ajouteModule(new Module(
                                mNomTextField.getText(),
                                Integer.parseInt(mDureeTextField.getText()),
                                Integer.parseInt(mNbTDTextField.getText()),
                                Integer.parseInt(mNbCMTextField.getText()),
                                Integer.parseInt(mNbTPTextField.getText()),
                                Integer.parseInt(mDureeCMTextField.getText()),
                                Integer.parseInt(mDureeTPTextField.getText()),
                                Integer.parseInt(mDureeTDTextField.getText())

                        ));
                        fermer();
                }
        }

        void modifierModule() {
                //Vérification à implementer
                Boolean estValide = true;
                if( estValide ) {
                        System.out.println(" ========= Les Données sont valides ============");
                        System.out.println("========= Modifier le module ");
                        if(mModule != null) {
                                Module nouveauModule = new Module(
                                        mNomTextField.getText(),
                                        Integer.parseInt(mDureeTextField.getText()),
                                        Integer.parseInt(mNbTDTextField.getText()),
                                        Integer.parseInt(mNbCMTextField.getText()),
                                        Integer.parseInt(mNbTPTextField.getText()),
                                        Integer.parseInt(mDureeCMTextField.getText()),
                                        Integer.parseInt(mDureeTPTextField.getText()),
                                        Integer.parseInt(mDureeTDTextField.getText())
                                );
                                mApp.modifierModule(mModule, nouveauModule, mModuleTableView);
                        }
                        fermer();
                }
        }

        public void setApp(App app, Stage moduleStage) {
                mApp = app;
                mModuleStage = moduleStage;
        }
}
