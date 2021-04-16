package fr.uvsq.interfaces;

import fr.uvsq.models.Module;
import fr.uvsq.models.Professeur;
import fr.uvsq.models.Promo;
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

public class PromoController {

    private App mApp;
    private Stage mPromoStage;
    private ObservableList<Module> mListeModule;
    private Promo mPromo;
    private TableView<Promo> mPromoTableView;

    @FXML
    private TextField mNomTextField,
            mNombreGroupesTextField,
            mNombreElevesTextField;
    @FXML
    private CheckComboBox<String> mModulesPromoComboBox;
    @FXML
    private Button mAnnulerDialogueProfBtn,
            mAjouterPromoBtn;
    @FXML
    private Label mTitleLabel;

    @FXML
    void fermer() {
        mPromoStage.close();
    }

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

    private void ajouterPromo() {
        //
        boolean estValide = true;
        if(estValide) {
            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mModulesPromoComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getDuree(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }

            String nom = mNomTextField.getText();
            int nbEleve = Integer.parseInt(mNombreElevesTextField.getText());
            int nbGroupes = Integer.parseInt(mNombreGroupesTextField.getText());

            Promo promo = new Promo(nom, nbEleve, nbGroupes, modules);

            mApp.ajouterPromo(promo);
            fermer();

        }
    }

    private void modifierPromo() {
        //
        boolean estValide = true;
        if(estValide) {
            ArrayList<Module> modules = new ArrayList<>();
            for(String module : mModulesPromoComboBox.getCheckModel().getCheckedItems()) {
                for(Module m : mListeModule) {
                    if(module.equals(m.getNom())) {
                        modules.add(new Module(m.getNom(), m.getDuree(), m.getNbTD(), m.getNbCM(), m.getNbTP(), m.getDureeCM(), m.getDureeTP(), m.getDureeTD()));
                    }
                }
            }

            String nom = mNomTextField.getText();
            int nbEleve = Integer.parseInt(mNombreElevesTextField.getText());
            int nbGroupes = Integer.parseInt(mNombreGroupesTextField.getText());

            Promo promo = new Promo(nom, nbEleve, nbGroupes, modules);

            mApp.modifierPromo(mPromo, promo, mPromoTableView);
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

    public void initialiseDialogueModification(Promo promo, TableView<Promo> promotionTableView) {
        mPromo = promo;
        mPromoTableView = promotionTableView;
        mTitleLabel.setText("Modification de la promotion");
        mNomTextField.setText(promo.getNom());
        mNombreElevesTextField.setText(String.valueOf(promo.getNbEleves()));
        mNombreGroupesTextField.setText(String.valueOf(promo.getNbGroupes()));

        mAjouterPromoBtn.setText("Modifier");
    }
}
