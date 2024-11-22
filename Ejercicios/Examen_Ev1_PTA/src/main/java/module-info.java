module com.example.examen_ev1_pta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires AccesoBBDDExamenV2;
    requires java.sql;

    opens com.example.examen_ev1_pta to javafx.fxml;
    exports com.example.examen_ev1_pta;
    exports com.example.examen_ev1_pta.Controller;
    opens com.example.examen_ev1_pta.Controller to javafx.fxml;
}