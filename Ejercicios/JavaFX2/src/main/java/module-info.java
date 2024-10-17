module com.example.javafx2 {
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

    opens com.example.javafx2 to javafx.fxml;
    exports com.example.javafx2;
    exports ejercicios;
    opens ejercicios to javafx.fxml;
    exports ejercicios.bill;
    opens ejercicios.bill to javafx.fxml;
}