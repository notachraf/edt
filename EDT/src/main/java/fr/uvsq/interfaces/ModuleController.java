package fr.uvsq.interfaces;

import fr.uvsq.models.Module;
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
                mNbCoursParSemaineTextField;


        @FXML
        private CheckBox mTDCheckBox,
                mTPCheckBox;

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
                        mNbCoursParSemaineTextField.setText(String.valueOf(module.getNbCoursSemaine()));

                        mTDCheckBox.setSelected(module.isTD());
                        mTPCheckBox.setSelected(module.isTP());
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
                                        Integer.parseInt(mNbCoursParSemaineTextField.getText()),
                                        mTDCheckBox.isSelected() ? true : false,
                                        mTPCheckBox.isSelected() ? true : false
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
                                        Integer.parseInt(mNbCoursParSemaineTextField.getText()),
                                        mTDCheckBox.isSelected() ? true : false,
                                        mTPCheckBox.isSelected() ? true : false
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
