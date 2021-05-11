package fr.uvsq.interfaces;

import fr.uvsq.generateurEDT.TypeEven;
import fr.uvsq.gestionDeDonnees.DAO;
import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.models.Salle;
import fr.uvsq.verification.Verification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/**
 * Gérer l'inter-action avec la fenêtre de dialogue salle.
 */
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SalleController {

    /**
     * Argument qui permet la connexion avec la base de données
     */
	DAO<Salle> msSalleDao;
    private App mApp;
    private Stage mSalleStage;
    private Salle mSalle;
    private TableView<Salle> mSalleTableView;
    @FXML
    private Label mTitleLabel;
    @FXML
    private TextField mNomTextField,
            mCapaciteTextField;
    @FXML
    private ComboBox<String> mTypeComboBox;

    /**
     * Bouton qui permet d'ajouter une salle
     */
    @FXML
    private Button mAjouterSalleBtn;

    /**
     * Initialise la fenêtre de dialogue salle
     */
    @FXML
    private void initialize() {
        mTypeComboBox.getItems().addAll(TypeEven.names());
    }

    /**
     * Ferme la fenêtre de dialogue salle.
     */
    @FXML
    private void fermer() {
        mSalleStage.close();
    }

    /**
     * Initialise le contenu de la fenêtre de dialogue
     * avec les attributs de la classe salle
     * @param salle
     * @param salleTableView
     */
    public void initialiseDialogueModification(Salle salle, TableView<Salle> salleTableView) {
        mSalle = salle;
        mSalleTableView = salleTableView;
        mTitleLabel.setText("Modification de la Salle");
        mNomTextField.setText("");
        mCapaciteTextField.setText("");
        mAjouterSalleBtn.setText("Modifier");

        if (salle != null) {
            mNomTextField.setText(salle.getNom());
            mCapaciteTextField.setText(String.valueOf(salle.getCapacite()));
            mTypeComboBox.setValue(String.valueOf(salle.getTypeSalle()));
        }
    }

    /**
     * Gérer l'ajout d'une nouvelle salle dans le tableau des Salle
     * @param event
     */
    @FXML
    private void handleAjouterBtn(ActionEvent event) {
        if (event.getSource() == mAjouterSalleBtn) {
            if (mAjouterSalleBtn.getText().equals("Modifier")) {
                modifierSalle();
            } else {
                ajouterSalle();
            }
        }
    }

    /**
     * Vérifie les données d'une salle et ajoute cette salle dans la liste des salles.
     */
    @SuppressWarnings("unchecked")
	private void ajouterSalle() {
        boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());

        if( estValide ) {
            System.out.println(" ========= Les Données sont valides ============");
            System.out.println("======== Ajouter nouvelle salle");
            Salle nouvelleSalle = new Salle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());
            msSalleDao = FactoryDAO.getSalleDAO();
            boolean ok  = msSalleDao.inserer(nouvelleSalle);
            if (ok) {
            	mApp.getListeSalles().add(nouvelleSalle);	
            }
            
            fermer();
        }
    }

    /**
     * Modifie les données d'une salle
     */
    @SuppressWarnings("unchecked")
	private void modifierSalle() {
        Boolean estValide = Verification.controleDonneesSalle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());

        if( estValide ) {
            System.out.println(" ========= Les Données sont valides ============");
            System.out.println("========= Modifier la salle ");
            if(mSalle != null) {
                Salle nouvelleSalle = new Salle(mNomTextField.getText(), mCapaciteTextField.getText(), mTypeComboBox.getValue());
                nouvelleSalle.setId(mSalle.getId());
                msSalleDao = FactoryDAO.getSalleDAO();
                msSalleDao.modifier(nouvelleSalle);
                
//              mApp.modifierSalle(mSalle, nouvelleSalle, mSalleTableView);

                int index = mApp.getListeSalles().indexOf(mSalle);
                
                mApp.getListeSalles().get(index).setNom(nouvelleSalle.getNom());
                mApp.getListeSalles().get(index).setCapacite(nouvelleSalle.getCapacite());
                mApp.getListeSalles().get(index).setTypeSalle(nouvelleSalle.getTypeSalle());
                mSalleTableView.refresh();
            }
            
            fermer();
        }
    }

    /**
     * Modifie la classe App et Stage
     * @param app
     * @param salleStage
     */
    public void setApp(App app, Stage salleStage) {
        mApp = app;
        mSalleStage = salleStage;
    }
}