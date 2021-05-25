module fr.uvsq {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.calendarfx.view;
    requires mybatis;
    requires JTeX;
    opens fr.uvsq.interfaces to javafx.fxml;
    exports fr.uvsq.interfaces;
}