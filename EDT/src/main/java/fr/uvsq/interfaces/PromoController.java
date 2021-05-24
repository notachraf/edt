package fr.uvsq.interfaces;

import fr.uvsq.gestionDeDonnees.FactoryDAO;
import fr.uvsq.gestionDeDonnees.PromoDAO;
import fr.uvsq.models.Module;
import fr.uvsq.models.Promotion;
import fr.uvsq.verification.Verification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PromoController {

    /**
     * Argument qui permet la connexion avec la base de données
     */
    private PromoDAO mPromoDAO;
    private App mApp;
    private Stage mPromoStage;
    private ObservableList<Module> mListeModule;
    private Promotion mPromo;
    private TableView<Promotion> mPromoTableView;

    @FXML
    private TextField mNomTextField,
            mNombreGroupesTextField,
            mNombreElevesTextField;
    @FXML
    private CheckComboBox<String> mModulesPromoComboBox;

    @FXML
    private DatePicker mDatePicker;

    /**
     * Bouton qui permet d'ajouter une promotions
     */
    @FXML
    private Button mAjouterPromoBtn;
    @FXML
    private Label mTitleLabel;

    /**
     * Initialise la fenêtre de dialogue promotion
     */
    private void initialize() {

    }

    /**
     * Ferme la fenêtre de dialogue promotion
     */
    @FXML
    private void fermer() {
        mPromoStage.close();
    }

    /**
     * Initialise le contenu de la fenêtre de dialogue
     * avec les attributs de la classe promo
     * @param promo
     * @param promotionTableView
     */
    public void initialiseDialogueModification(Promotion promo, TableView<Promotion> promotionTableView) {
        mPromo = promo;
        mPromoTableView = promotionTableView;
        mTitleLabel.setText("Modification de la promotion");
        mNomTextField.setText(promo.getNom());
        mNombreElevesTextField.setText(String.valueOf(promo.getNbEleves()));
        mNombreGroupesTextField.setText(String.valueOf(promo.getNbGroupes()));
        mDatePicker.setValue(LocalDate.parse(promo.getLocalDate()));
        mAjouterPromoBtn.setText("Modifier");
    }

    /**
     * Gérer l'ajout d'une nouvelle promotion
     * @param event
     */
    @FXML
    void handleAjouterBtn(ActionEvent event) {
        if (event.getSource() == mAjouterPromoBtn) {
            if (mAjouterPromoBtn.getText().equals("Modifier")) {
                modifierPromo();
            } else {
                ajouterPromo();
            }
        }
    }

    /**
     * Vérifie les données d'une promotion et ajoute cette promotion dans la liste des promotions
     */
    private void ajouterPromo() {
        boolean estValide = Verification.controleDonneesPromotion(
                mNomTextField.getText(),
                mNombreElevesTextField.getText(),
                mNombreGroupesTextField.getText()
        );
        if(estValide) {
            if( mPromoDAO == null ){
                mPromoDAO = (PromoDAO) FactoryDAO.getPromotionDAO();
            }
            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mModulesPromoComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }

            String nom = mNomTextField.getText();
            int nbEleve = Integer.parseInt(mNombreElevesTextField.getText());
            int nbGroupes = Integer.parseInt(mNombreGroupesTextField.getText());

            Promotion promo = new Promotion(nom, nbEleve, nbGroupes, modules, mDatePicker.getValue().toString());

            if( mPromoDAO.inserer(promo) ){
                mApp.getListePromos().add(promo);
                mApp.getAppController().setNbPromos(String.valueOf(mApp.getListePromos().size()));
            }

            fermer();
        }
    }

    /**
     * Modifie les données d'une promotion
     */
    private void modifierPromo() {
        boolean estValide = Verification.controleDonneesPromotion(
                mNomTextField.getText(),
                mNombreElevesTextField.getText(),
                mNombreGroupesTextField.getText()
        );
        if( estValide) {
            if( mPromoDAO == null ){
                mPromoDAO = (PromoDAO) FactoryDAO.getPromotionDAO();
            }
            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mModulesPromoComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }

            String nom = mNomTextField.getText();
            int nbEleve = Integer.parseInt(mNombreElevesTextField.getText());
            int nbGroupes = Integer.parseInt(mNombreGroupesTextField.getText());
            Promotion promo = new Promotion(nom, nbEleve, nbGroupes, modules, mDatePicker.getValue().toString());

            promo.setId(mPromo.getId());
            if( mPromoDAO.modifier(promo) ) {
                int index = mApp.getListePromos().indexOf(mPromo);
                mApp.getListePromos().get(index).setNom(promo.getNom());
                mApp.getListePromos().get(index).setNbEleves(promo.getNbEleves());
                mApp.getListePromos().get(index).setNbGroupes(promo.getNbGroupes());
                mApp.getListePromos().get(index).setListeModules(promo.getListeModules());
                mApp.getListePromos().get(index).setLocalDate(promo.getLocalDate());
                mPromoTableView.refresh();
            }

            fermer();
        }
    }

    public void setApp(App app, Stage promoStage, ObservableList<Module> listeModule) {
        mApp = app;
        mPromoStage = promoStage;
        mListeModule = listeModule;

        ObservableList<String> modules = FXCollections.observableArrayList();
        for(Module module : mListeModule) {
            modules.add(module.getNom());
        }
        mModulesPromoComboBox.getItems().addAll(modules);
    }


}
