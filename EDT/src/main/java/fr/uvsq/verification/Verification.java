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
     * Méthode qui controle les données saisies par l'utilisateur pour l'entité Module.
     * Soit pour modifier ou ajouter une module.
     * static : pour avoir accès à la méthode sans instancier un objet Verification
     *
     * @param mNom
     * @param capacite
     * @param type
     * @return boolean
     */
    public static boolean controleDonneesModule(String Nom, int id, int nbModule, int nbTD, int nbTP, int nbHeure){
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mIdTextField n'est pas vide
        if (id== null || id.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Id> est vide ");
        }

        //Vérifie le contenu du champ mNbModuleTextField n'est pas vide
        if (nbModule== null || nbModule.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbModule> est vide ");
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

        //Vérifie le contenu du champ mNbHeureTextField n'est pas vide
        if (nbHeure== null || nbHeure.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <NbHeure> est vide ");
        }

        if( ! estUnEntier(id) ){
            isOK = false;
            listErreur.add("Le champ <Id> n'est pas un entier");
        }

        if( ! estUnEntier(nbModule) ){
            isOK = false;
            listErreur.add("Le champ <nbModule> n'est pas un entier");
        }

        if( ! estUnEntier(nbTD) ){
            isOK = false;
            listErreur.add("Le champ <nbTD> n'est pas un entier");
        }

        if( ! estUnEntier(nbTP) ){
            isOK = false;
            listErreur.add("Le champ <nbTP> n'est pas un entier");
        }

        if( ! estUnEntier(nbHeure) ){
            isOK = false;
            listErreur.add("Le champ <nbHeure> n'est pas un entier");
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
     * @param nom
     * @param id
     * @param nbProfesseur
     * @return boolean
     */
    ********  ????? DOIT-T-ON RAJOUTER 'listemodule' en arguments ?********
    public static boolean controleDonneesProfesseur(String nom, Int id, Int nbProfesseur){
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mIdTextField n'est pas vide
        if (id== null || id.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Id> est vide ");
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

        //Verifie que id est un entier
        if( ! estUnEntier(id) ){
            isOK = false;
            listErreur.add("Le champ <Id> n'est pas un entier");
        }

        //Verifie que nbProfesseur est un entier
        if( ! estUnEntier(nbProfesseur) ){
            isOK = false;
            listErreur.add("Le champ <nbProfesseur> n'est pas un entier");
        }

        // DOIT-ON VERIFIER QUE LE ID N'EST PAS DÉJÀ PRIS ?
        // Peut-être dans la gestion de données (comme dans salle pour la redondance)

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
     * Méthode qui controle les données saisies par l'utilisateur pour l'entité Groupe
     * Soit pour modifier ou ajouter un groupe
     * static : pour avoir accès à la méthode sans instancier un objet Verification
     *
     * @param nom
     * @param id
     * @param taille
     * @param Promo
     * @param nbGroupe
     * @param  ??????? Arraylist <Module> listemodule
     * @return
     *
     */
    public static boolean controleDonneesGroupe(String nom, Int id, Int taille, String promo,INT nbGroupe){
        boolean isOK = true;

        List<String> listErreur = new ArrayList<>();

        // Vérifie le contenu du champ mNomTextField n'est pas vide
        if (nom == null || nom.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Nom> est vide ");
        }

        //Vérifie le contenu du champ mIdTextField n'est pas vide
        if (id== null || id.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <id> est vide ");
        }

        //Vérifie le contenu du champ mTailleTextField n'est pas vide
        if (taille== null || taille.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Taille> est vide ");
        }

        //Vérifie le contenu du champ mPromoTextField n'est pas vide
        if (promo== null || promo.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <Promo> est vide ");
        }

        //Vérifie le contenu du champ mnbGroupeTextField n'est pas vide
        if (nbGroupe== null || nbGroupe.isEmpty()) {
            isOK = false;
            listErreur.add("Le champ <nbGroupe> est vide ");
        }

        //Verifie que id est un entier
        if( ! estUnEntier(id) ){
            isOK = false;
            listErreur.add("Le champ <Id> n'est pas un entier");
        }

        //Verifie que taille est un entier
        if( ! estUnEntier(taille) ){
            isOK = false;
            listErreur.add("Le champ <Taille> n'est pas un entier");
        }

        //Verifie que promo est un entier
        if( ! estUnEntier(promo) ){
            isOK = false;
            listErreur.add("Le champ <Promo> n'est pas un entier");
        }
        //Verifie que groupe est un entier
        if( ! estUnEntier(nbGroupe) ){
            isOK = false;
            listErreur.add("Le champ <NbGroupe> n'est pas un entier");
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
     *  La méthode matched() de la classe String qui prend regex comme argument et retourne un booléen
     * @param mot
     * @return boolean
     */
    private static boolean estUnEntier(String mot){
        return mot.matches("[+-]?\\d*(\\.\\d+)?");
    }


    /**
     *  Renvoie vrai si le mot contient de A-Z, a-z
     *
     * @param mot
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
     * @param mot
     * @return boolean
     */
    private static boolean contientCaractereSpeciaux(String mot){
        return mot.matches(" ");
    }
}
