package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.ModuleDAO;
import fr.uvsq.models.Module;
import fr.uvsq.verification.Verification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModuleController {

        /**
         * Argument qui permet la connexion avec la base de données
         */
        private ModuleDAO mModuleDao;
        private App mApp;
        private Stage mModuleStage;
        private Module mModule = null;
        private TableView<Module> mModuleTableView;

        /**
         * Bouton qui permet d'ajouter un module
         */
        @FXML
        private Button mAjouterModuleBtn;

        @FXML
        private Label mTitleLabel;

        @FXML
        private TextField mNomTextField,
                mNbCMTextField,
                mNbTDTextField,
                mNbTPTextField,
                mDureeCMTextField,
                mDureeTDTextField,
                mDureeTPTextField;


        /**
         * Initialise la fenêtre de dialogue module
         */
        private void initialize() {

        }

        /**
         * Ferme la fenêtre de dialogue module.
         */
        @FXML
        private void fermer() {
                mModuleStage.close();
        }

        /**
         * Initialise le contenu de la fenêtre de dialogue
         * avec les attributs de la classe module
         * @param module
         * @param moduleTableView
         */
        public void initialiseDialogueModification(Module module, TableView<Module> moduleTableView) {
                mModule = module;
                mModuleTableView = moduleTableView;
                mTitleLabel.setText("Modification du module");
                mNomTextField.setText("");
                mAjouterModuleBtn.setText("Modifier");

                if (module != null) {
                        mNomTextField.setText(module.getNom());
                        mNbCMTextField.setText(String.valueOf(module.getNbCM()));
                        mNbTDTextField.setText(String.valueOf(module.getNbTD()));
                        mNbTPTextField.setText(String.valueOf(module.getNbTP()));
                        mDureeCMTextField.setText(String.valueOf(module.getDureeCM()));
                        mDureeTDTextField.setText(String.valueOf(module.getDureeTD()));
                        mDureeTPTextField.setText(String.valueOf(module.getDureeTP()));
                }
        }

        /**
         * Gérer l'ajout d'un nouveau module
         * @param event
         */
        @FXML
        private void handleAjouterBtn(ActionEvent event) {
                if (event.getSource() == mAjouterModuleBtn) {
                        if (mAjouterModuleBtn.getText().equals("Modifier")) {
                                modifierModule();
                        } else {
                                ajouterModule();
                        }
                }
        }

        /**
         * Vérifie les données d'un module et ajoute ce module dans la liste des modules
         */
        private void ajouterModule() {
                //Vérification à implementer
                Boolean estValide = Verification.controleDonneesModule( mNomTextField.getText(),
                                                                        "0",
                                                                        mNbCMTextField.getText(),
                                                                        mNbTDTextField.getText(),
                                                                        mNbTPTextField.getText(),
                                                                        mDureeCMTextField.getText(),
                                                                        mDureeTPTextField.getText(),
                                                                        mDureeTDTextField.getText()
                );

                if( estValide ) {
                        if( mModule == null ) {
                                mModuleDao = (ModuleDAO) FactoryDAO.getModuleDAO();
                        }
                        Module module = new Module(
                                mNomTextField.getText(),
                                Integer.parseInt(mNbTDTextField.getText()),
                                Integer.parseInt(mNbCMTextField.getText()),
                                Integer.parseInt(mNbTPTextField.getText()),
                                Integer.parseInt(mDureeCMTextField.getText()),
                                Integer.parseInt(mDureeTPTextField.getText()),
                                Integer.parseInt(mDureeTDTextField.getText())

                        );
                        if( mModuleDao.inserer(module) ) {
                                mApp.getListModule().add(module);
                        }

                        fermer();
                }
        }

        /**
         * Modifie les données d'un module
         */
        private void modifierModule() {
                //Vérification à implementer
                Boolean estValide = Verification.controleDonneesModule( mNomTextField.getText(),
                                                                       "0",
                                                                        mNbCMTextField.getText(),
                                                                        mNbTDTextField.getText(),
                                                                        mNbTPTextField.getText(),
                                                                        mDureeCMTextField.getText(),
                                                                        mDureeTPTextField.getText(),
                                                                        mDureeTDTextField.getText()
                                                                        );
                if( estValide ) {
                        if( mModuleDao == null) {
                                mModuleDao = (ModuleDAO) FactoryDAO.getModuleDAO();
                        }
                        if(mModule != null) {
                                Module nouveauModule = new Module(
                                        mNomTextField.getText(),
                                        Integer.parseInt(mNbCMTextField.getText()),
                                        Integer.parseInt(mNbTDTextField.getText()),
                                        Integer.parseInt(mNbTPTextField.getText()),
                                        Integer.parseInt(mDureeCMTextField.getText()),
                                        Integer.parseInt(mDureeTDTextField.getText()),
                                        Integer.parseInt(mDureeTPTextField.getText())
                                );

                                int index = mApp.getListModule().indexOf(mModule);
                                System.out.println("id module: " + mModule.getId());
                                nouveauModule.setId(mModule.getId());
                                if(mModuleDao.modifier(nouveauModule)){
                                        mApp.getListModule().get(index).setNom(nouveauModule.getNom());
                                        mApp.getListModule().get(index).setDureeCM(nouveauModule.getDureeCM());
                                        mApp.getListModule().get(index).setDureeTD(nouveauModule.getDureeTD());
                                        mApp.getListModule().get(index).setDureeTP(nouveauModule.getDureeTP());
                                        mApp.getListModule().get(index).setNbCM(nouveauModule.getNbCM());
                                        mApp.getListModule().get(index).setNbTD(nouveauModule.getNbTD());
                                        mApp.getListModule().get(index).setNbTP(nouveauModule.getNbTP());
                                        mModuleTableView.refresh();
                                }
                        }
                        fermer();
                }
        }

        public void setApp(App app, Stage moduleStage) {
                mApp = app;
                mModuleStage = moduleStage;
        }
}
