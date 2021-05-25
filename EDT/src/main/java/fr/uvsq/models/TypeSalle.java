package fr.uvsq.models;

public enum TypeSalle {
    CM,
    TD,
    TP;

    /**
     * type de salle actuel
     * @param type
     * @return le type de salle actuel
     */
    public static TypeSalle getType(String type){
        if( type.equals("CM") )
            return CM;
        else if(type.equals("TD"))
            return TD;

        return TP;
    }
}
