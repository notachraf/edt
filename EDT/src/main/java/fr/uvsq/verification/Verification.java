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
        List<String> listeType = Arrays.asList(new String[]{"TP", "TD", "CM"});
        if ( ! listeType.contains(type)){
            isOK = false;
            listErreur.add("Le champ <Type> est inconnu \n (TP, TD,CM)");
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
     * Méthode qui controle les données saisies par l'utilisateur pour l'entité Module.
     * Soit pour modifier ou ajouter une module.
     * static : pour avoir accès à la méthode sans instancier un objet Verification
     *
     * @param nom nom du module
     * @param nbHeureTotal la durée
     * @param nbCM nombre de CM
     * @param nbTD nombre de TD
     * @param nbTP nombre de TP
     * @param DureeCM la durée d'un CM
     * @param DureeTD la durée d'un TD
     * @param DureeTP la durée d'un TP
     * @return boolean
     */
    public static boolean controleDonneesModule(String nom, String nbHeureTotal,String nbCM,String nbTD, String nbTP, String DureeCM , String DureeTD , String DureeTP){
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mNbHeureTextField n'est pas vide
        if (nbHeureTotal== null || nbHeureTotal.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbHeure> est vide ");
        }

        //Vérifie le contenu du champ mNbCMTextField n'est pas vide
        if (nbCM== null || nbCM.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbCM> est vide ");
        }

        //Vérifie le contenu du champ mNbTDTextField n'est pas vide
        if (nbTD== null || nbTD.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbTD> est vide ");
        }

        //Vérifie le contenu du champ mNbTPTextField n'est pas vide
        if (nbTP== null || nbTP.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbTP> est vide ");
        }

        //Vérifie le contenu du champ mNbCMTextField n'est pas vide
        if (DureeCM== null || DureeCM.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <DureeCM> est vide ");
        }


        //Vérifie le contenu du champ mNbTPTextField n'est pas vide
        if (DureeTD== null || DureeTD.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <DureeTD> est vide ");
        }

        //Vérifie le contenu du champ mNbTPTextField n'est pas vide
        if (DureeTP== null || DureeTP.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <DureeTP> est vide ");
        }

        if( ! estUnEntier(nbHeureTotal) ){
            isOK = false;
            listErreur.add("Le champ <nbHeureTotal> n'est pas un entier");
        }

        if( ! estUnEntier(nbCM) ){
            isOK = false;
            listErreur.add("Le champ <nbCM> n'est pas un entier");
        }

        if( ! estUnEntier(nbTD) ){
            isOK = false;
            listErreur.add("Le champ <nbTD> n'est pas un entier");
        }

        if( ! estUnEntier(nbTP) ){
            isOK = false;
            listErreur.add("Le champ <nbTP> n'est pas un entier");
        }

        if( ! estUnEntier(DureeCM) ){
            isOK = false;
            listErreur.add("Le champ <DureeCM> n'est pas un entier");
        }

        if( ! estUnEntier(DureeTP) ){
            isOK = false;
            listErreur.add("Le champ <DureeTP> n'est pas un entier");
        }

        if( ! estUnEntier(DureeTD) ){
            isOK = false;
            listErreur.add("Le champ <DureeTD> n'est pas un entier");
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
     * Méthode qui controle les données saisies par l'utilisateur pour l'entité Professeur.
     * Soit pour modifier ou ajouter un Professeur.
     * static : pour avoir accès à la méthode sans instancier un objet Verification
     *
     * @param nom nom de professeur
     * @param nbProfesseur nombre de professeur
     * @return boolean
     */
    public static boolean controleDonneesProfesseur(String nom, String nbProfesseur){
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mnbProfesseurTextField n'est pas vide
        if (nbProfesseur== null || nbProfesseur.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbProfesseur> est vide ");
        }

        //Verifie que le nom de professeur est une chaine alphabétique
        if( !contientAlphabet(nom) ){
            isOK = false;
            listErreur.add("Le champ <Nom> n'est pas une chaîne alphabétique");
        }

        //Verifie que nbProfesseur est un entier
        if( ! estUnEntier(nbProfesseur) ){
            isOK = false;
            listErreur.add("Le champ <nbProfesseur> n'est pas un entier");
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
     * Méthode qui contrôle les données saisies par l'utilisateur pour l'entité Promotion
     * Soit pour modifier ou ajouter un groupe
     * static : pour avoir accès à la méthode sans instancier un objet Verification
     *
     * @param nom nom de la promotion
     * @param nbEleve nombre d'élèves dans une promotion
     * @param nbGroupe nombre de groupe
     * @return
     *
     */
    public static boolean controleDonneesPromotion ( String nom , String nbEleve , String nbGroupe ) {
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mnbEleveTextField n'est pas vide
        if (nbEleve== null || nbEleve.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Taille> est vide ");
        }

        //Vérifie le contenu du champ mnbGroupeTextField n'est pas vide
        if (nbGroupe== null || nbGroupe.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <nbGroupe> est vide ");
        }

        //Verifie que nbEleve est un entier
        if( ! estUnEntier(nbEleve) ){
            isOK = false;
            listErreur.add("Le champ <nbEleve> n'est pas un entier");
        }

        //Verifie que nbGroupe est un entier
        if( ! estUnEntier(nbGroupe) ){
            isOK = false;
            listErreur.add("Le champ <nbGroupe> n'est pas un entier");
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
     * @param messageErreur l'ensemble des erreurs
     * @param typeErreur warning
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
     *  La méthode matched() de la classe String qui prend regex comme argument et retourne un booléen
     * @param mot le mot à verifier
     * @return boolean
     */
    private static boolean estUnEntier(String mot){
        return mot.matches("[+-]?\\d*(\\.\\d+)?");
    }

    /**
     *  Renvoie vrai si le mot contient de A-Z, a-z
     *
     * @param mot le mot à vérifier
     * @return boolean
     */
    private static boolean contientAlphabet(String mot){
        return mot.matches("[a-zA-Z]+");
      /*  return mot.matches("\\w+\\.?");
        [A-Za-z ]* -> ici il y a les espaces
       */
    }

    /**
     *  Renvoie vrai si le mot contient des caracteres speciaux
     *
     * @param mot le mot à vérifier
     * @return boolean
     */
    private static boolean contientCaractereSpeciaux(String mot){
        return mot.matches(" ");
    }
}
