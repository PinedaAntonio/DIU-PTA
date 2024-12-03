package com.example.hotel.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.List;

public class CarruselDialogController {

    @FXML
    private Label tituloLabel;

    @FXML
    private StackPane carruselPane;

    @FXML
    private ProgressIndicator progressInd;

    private List<Image> imagenes;
    private int indiceActual = 0;
    private ImageView imageView;

    private static final int CAPACIDAD_MAXIMA_DOBLE_USO_INDIVIDUAL = 20;
    private static final int CAPACIDAD_MAXIMA_DOBLE = 80;
    private static final int CAPACIDAD_MAXIMA_JUNIOR_SUITE = 15;
    private static final int CAPACIDAD_MAXIMA_SUITE = 5;

    @FXML
    public void initialize() {
        imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        carruselPane.getChildren().add(imageView);
    }

    public void configurarCarrusel(String tipoHabitacion, List<Image> imagenes, int habitacionesOcupadas) {
        this.imagenes = imagenes;

        tituloLabel.setText("Habitación: " + tipoHabitacion. replace("_", " ").toLowerCase());

        if (!imagenes.isEmpty()) {
            imageView.setImage(imagenes.get(indiceActual));
        }

        actualizarProgreso(tipoHabitacion, habitacionesOcupadas);
    }

    private void actualizarProgreso(String tipoHabitacion, int habitacionesOcupadas) {
        double progreso = 0.0;

        switch (tipoHabitacion.toUpperCase()) {
            case "DOBLE_USO_INDIVIDUAL":
                progreso = (double) habitacionesOcupadas / CAPACIDAD_MAXIMA_DOBLE_USO_INDIVIDUAL;
                break;
            case "DOBLE":
                progreso = (double) habitacionesOcupadas / CAPACIDAD_MAXIMA_DOBLE;
                break;
            case "JUNIOR_SUITE":
                progreso = (double) habitacionesOcupadas / CAPACIDAD_MAXIMA_JUNIOR_SUITE;
                break;
            case "SUITE":
                progreso = (double) habitacionesOcupadas / CAPACIDAD_MAXIMA_SUITE;
                break;
        }

        progressInd.setProgress(Math.min(progreso, 1.0));
    }

    @FXML
    private void mostrarAnterior() {
        if (!imagenes.isEmpty()) {
            indiceActual = (indiceActual - 1 + imagenes.size()) % imagenes.size();
            imageView.setImage(imagenes.get(indiceActual));
        }
    }

    @FXML
    private void mostrarSiguiente() {
        if (!imagenes.isEmpty()) {
            indiceActual = (indiceActual + 1) % imagenes.size();
            imageView.setImage(imagenes.get(indiceActual));
        }
    }

}
