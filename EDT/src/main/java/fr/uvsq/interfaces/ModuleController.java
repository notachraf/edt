package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.models.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModuleController {

        /**
         * Argument qui permet la connexion avec la base de donn�es
         */
        private DAO<Module> mModuleDao;
        private App mApp;
        private Stage mModuleStage;
        private Module mModule;
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
                mDureeTextField,
                mNbCMTextField,
                mNbTDTextField,
                mNbTPTextField,
                mDureeCMTextField,
                mDureeTDTextField,
                mDureeTPTextField;


        /**
         * Initialise la fen�tre de dialogue module
         */
        private void initialize() {

        }

        /**
         * Ferme la fen�tre de dialogue module.
         */
        @FXML
        private void fermer() {
                mModuleStage.close();
        }

        /**
         * Initialise le contenu de la fen�tre de dialogue
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
         * G�rer l'ajout d'un nouveau module
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
         * V�rifie les donn�es d'un module et ajoute ce module dans la liste des modules
         */
        private void ajouterModule() {
                //V�rification � implementer
                boolean estValide = true;

                if( estValide ) {
                        System.out.println(" ========= Les Donn�es sont valides ============");
                        System.out.println("======== Ajouter nouveau module");

                        Module module = new Module(
                                mNomTextField.getText(),
                                Integer.parseInt(mDureeTextField.getText()),
                                Integer.parseInt(mNbTDTextField.getText()),
                                Integer.parseInt(mNbCMTextField.getText()),
                                Integer.parseInt(mNbTPTextField.getText()),
                                Integer.parseInt(mDureeCMTextField.getText()),
                                Integer.parseInt(mDureeTPTextField.getText()),
                                Integer.parseInt(mDureeTDTextField.getText())

                        );

                        mModuleDao = FactoryDAO.getModuleDAO(); 
                        if (mModuleDao.inserer(module)) {                       
                        	mApp.getListModule().add(module);
                        	fermer();
                        }// erreur
                }
        }

        /**
         * Modifie les donn�es d'un module
         */
        private void modifierModule() {
                //V�rification � implementer
                Boolean estValide = true;
                if( estValide ) {
                        System.out.println(" ========= Les Donn�es sont valides ============");
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
                                
                                
                                nouveauModule.setId(mModule.getId());
                                mModuleDao = FactoryDAO.getModuleDAO(); 
                                if (mModuleDao.modifier(nouveauModule)) {
                                	int index = mApp.getListModule().indexOf(mModule);
                                    mApp.getListModule().get(index).setNom(nouveauModule.getNom());
                                    mApp.getListModule().get(index).setDureeCM(nouveauModule.getDureeCM());
                                    mApp.getListModule().get(index).setDureeTD(nouveauModule.getDureeTD());
                                    mApp.getListModule().get(index).setDureeTP(nouveauModule.getDureeTP());
                                    mApp.getListModule().get(index).setNbCM(nouveauModule.getNbCM());
                                    mApp.getListModule().get(index).setNbTD(nouveauModule.getNbTD());
                                    mApp.getListModule().get(index).setNbTP(nouveauModule.getNbTP());
                                    mModuleTableView.refresh();
                                } // else :erreur
                        }
                        
                        fermer();
                         
                }
        }

        public void setApp(App app, Stage moduleStage) {
                mApp = app;
                mModuleStage = moduleStage;
        }
}