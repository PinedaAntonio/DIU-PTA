
package com.example.conversor;

import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.ControladorConversor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import com.example.conversor.Modelo.ConversorModelo;
import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("VistaConversor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 240);
        stage.setTitle("Conversor de Monedas");
        stage.setScene(scene);
        stage.show();

        try {
            // Instanciar el repositorio y el modelo
            MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
            ConversorModelo conversorModelo = new ConversorModelo();
            conversorModelo.setMonedaRepository(monedaRepository);

            // Obtener el controlador
            ControladorConversor controller = fxmlLoader.getController();

            // Asignar el modelo al controlador
            controller.setConversorModelo(conversorModelo);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public static void main(String[] args) {


        launch();
    }
}
