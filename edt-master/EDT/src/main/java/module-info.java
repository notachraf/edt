module fr.uvsq {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.calendarfx.view;
    requires mybatis;
    requires org.usajusaj.jtex;
    opens fr.uvsq.interfaces to javafx.fxml;
    exports fr.uvsq.interfaces;
}