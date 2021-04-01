package fr.uvsq.verification;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Verification {

    /**
     * Méthode qui controle les données saisies par l'utilisateur pour l'entité Salle.
     * Soit pour modifier ou ajouter une salle.
     * static : pour avoir accès à la méthode sans instancier un objet Verification
     *
     * @param nom
     * @param capacite
     * @param type
     * @return boolean
     */
    public static boolean controleDonneesSalle(String nom, String capacite, String type){
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mCapaciteTextField n'est pas vide
        if (capacite== null || capacite.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Capacite> est vide ");
        }

        //Vérifie le contenu du champ mTypeTextField n'est pas vide
        if (type== null || type.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Type> est vide ");
        }

        if( ! estUnEntier(capacite) ){
            isOK = false;
            listErreur.add("Le champ <Capacite> n'est pas un entier");
        }

        //On doit vérifier que c'est soit TD, TP, COURS
        List<String> listeType = Arrays.asList(new String[]{"TP", "TD", "COURS"});
        if ( ! listeType.contains(type)){
            isOK = false;
            listErreur.add("Le champ <Type> est inconnu \n (TP, TD, COURS) ");

        }

        // On vérifie s'il ya des erreurs dans la liste d'erreurs.
        if (!isOK) {
            StringBuilder sb = new StringBuilder();
            listErreur.stream().forEach((x) -> sb.append("\n" + x));
            /*for (String erreur: listErreur) {
                sb.append("\n" + erreur);
            }*/
            afficheErreur(sb.toString(), Alert.AlertType.WARNING);
        }

        return isOK;
    }


    /**
     * Affiche une fenêtre d'erreur en fonction du message et du type d'erreur passés en paramètres
     * @param messageErreur
     * @param typeErreur
     */
    private static void afficheErreur(String messageErreur, Alert.AlertType typeErreur) {
        Alert erreurAlert = new Alert(typeErreur);
        erreurAlert.setTitle("Erreur");
        erreurAlert.setHeaderText(messageErreur);
        erreurAlert.showAndWait();
    }

    /**
     *  Renvoie vrai si le mot est un entier, sinon faux
     *  Pour vérifier une chaîne numérique, nous pourrions utiliser
     *  La méthode matched() de la classe String qui prend regex comme argument et retourne une
     * @param mot
     * @return boolean
     */

    private static boolean estUnEntier(String mot){
        return mot.matches("[+-]?\\d*(\\.\\d+)?");
    }
}
