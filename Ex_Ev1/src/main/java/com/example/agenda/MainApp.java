package com.example.agenda;

import java.io.IOException;
import com.example.agenda.controller.NuevoArticuloController;
import com.example.agenda.model.CatalogoModel;
import com.example.agenda.model.ExcepcionArticulo;
import com.example.agenda.model.repository.impl.ArticuloRepositoryImpl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.agenda.controller.CatalogoController;

public class MainApp extends Application {

    private static ObservableList<Articulo> articulos = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private BorderPane principal;
    private CatalogoModel modeloCatalogo;

    private final SimpleDoubleProperty progressProperty = new SimpleDoubleProperty(0);

    public MainApp() {
        modeloCatalogo = new CatalogoModel();
        ArticuloRepositoryImpl agendaRepository = new ArticuloRepositoryImpl();
        try {
            modeloCatalogo.setArticuloRepository(agendaRepository);
            articulos.addAll(modeloCatalogo.setArticulos());

            actualizarProgreso();

            articulos.addListener((ListChangeListener<Articulo>) change -> {
                while (change.next()) {
                    actualizarProgreso();
                }
            });

        } catch (ExcepcionArticulo e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("BDD ERROR");
            alert.setHeaderText("No se ha podido establecer Conexión con la base de datos");
            alert.setContentText("Por favor, inicie la base de datos");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Articulo> getArticulos() {
        return articulos;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Articulos");
        Principal();
        vistaCatalogo();
    }

    public void Principal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/articulos/Principal.fxml"));
            principal = (BorderPane) loader.load();
            Scene scene = new Scene(principal);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void vistaCatalogo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/articulos/Catalogo.fxml"));
            AnchorPane vistaArticulos = (AnchorPane) loader.load();
            principal.setCenter(vistaArticulos);

            CatalogoController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCatalogoModel(modeloCatalogo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean EditarArticulo(Articulo articulo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/articulos/NuevoArticulo.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Artículo");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NuevoArticuloController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setArticulo(articulo);
            controller.setCatalogoModel(modeloCatalogo);
            dialogStage.showAndWait();

            return controller.OkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void actualizarProgreso() {
        double progress = (double) articulos.size() / 50;
        progressProperty.set(progress);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
