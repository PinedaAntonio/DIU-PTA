module com.example.contador_sb {
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

    opens com.example.contador_sb to javafx.fxml;
    exports com.example.contador_sb;
    exports com.example.contador_sb.controller;
    opens com.example.contador_sb.controller to javafx.fxml;
}