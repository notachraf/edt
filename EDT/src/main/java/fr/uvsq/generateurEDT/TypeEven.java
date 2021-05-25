package fr.uvsq.generateurEDT;
/**
 * 
 * @author Aziz, moulhat
 *
 */
public enum TypeEven {
    CM, TD, TP;

    /**
     *
     * @param t type événements
     * @return type d'événement actuel.
     */
    public static String getTypeEvenString(TypeEven t){
        if ( t == CM){
            return "CM";
        } else if (t == TD){
            return "TD";
        } else {
            return "TP";
        }
    }
}

