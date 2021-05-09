package fr.uvsq.models;

/**
 *
 * @Author Siham, Feriel, Aziz
 */

public enum TypeSalle {
    CM,
    TD,
    TP;

    public static TypeSalle getType(String type){
        if( type.equals("CM") )
            return CM;
        else if(type.equals("TD"))
            return TD;

        return TP;
    }
}
