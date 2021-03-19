package org.example;

import javafx.beans.property.*;
import javafx.beans.property.SimpleIntegerProperty;

public class Salle {
    private StringProperty mNom = new SimpleStringProperty();
    private StringProperty mId = new SimpleStringProperty();
    private StringProperty mCapacite = new SimpleStringProperty();
    //Type

    public Salle(String nom, String id, String capacite) {
        mNom.set(nom);
        mId.set(id);
        mCapacite.set(capacite);
    }

    public Salle(){
        mNom.set("");
        mId.set("-1");
        mCapacite.set("");
    }

    public StringProperty getNom() {
        return mNom;
    }

    public StringProperty getId() {
        return mId;
    }

    public StringProperty getCapacite() {
        return mCapacite;
    }
}
