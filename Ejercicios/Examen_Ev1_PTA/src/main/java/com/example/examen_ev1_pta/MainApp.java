package com.example.examen_ev1_pta;

import Modelo.ExcepcionMoneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.examen_ev1_pta.Controller.ConversorController;
import com.example.examen_ev1_pta.Controller.ImagenController;
import com.example.examen_ev1_pta.Modelo.Moneda;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.examen_ev1_pta.Modelo.ConversorModelo;
import java.io.IOException;

public class MainApp extends Application {
    
    ObservableList<Moneda> monedas = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private BorderPane principal;
    private ConversorModelo conversorModelo;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Conversor");
        Principal();
        vistaConversor();
    }

    public void Principal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/examen_ev1_pta/Principal.fxml"));
            principal = (BorderPane) loader.load();
            Scene scene = new Scene(principal);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void vistaConversor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/examen_ev1_pta/VistaConversor.fxml"));
            AnchorPane vistaArticulos = (AnchorPane) loader.load();
            principal.setCenter(vistaArticulos);

            ConversorController controller = loader.getController();
            controller.setMainApp(this);
            controller.setConversorModelo(conversorModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarMonedas() {

    }

    public boolean vistaImagen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/articulos/VistaImagen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Monedas");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ImagenController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

            return controller.OkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MainApp(){
        MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
        conversorModelo = new ConversorModelo();
        try {
            conversorModelo.setMonedaRepository(monedaRepository);
            monedas.addAll(conversorModelo.setMonedas());

            actualizarMonedas();

            monedas.addListener((ListChangeListener<Moneda>) change -> {
                while (change.next()) {
                    actualizarMonedas();
                }
            });

        } catch (ExcepcionMoneda e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR BBDD");
            alert.setHeaderText("No se ha podido conectar a la base de datos");
            alert.setContentText("Conéctese a la base de datos");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Moneda> getMonedas(){
        return monedas;
    }

    public static void main(String[] args) {


        launch();
    }
}
