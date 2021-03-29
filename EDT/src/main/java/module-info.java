module fr.uvsq {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.uvsq.interfaces to javafx.fxml;
    exports fr.uvsq.interfaces;
}