
package com.example.conversor.controller;

import Modelo.repository.MonedaRepository;
import javafx.fxml.FXML;
import com.example.conversor.Modelo.ConversorModelo;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ControladorConversor {
    ConversorModelo conversorModelo;

    public void setConversorModelo(ConversorModelo conversorModelo) {
        this.conversorModelo = conversorModelo;
    }


    @FXML
    private TextField dolares;

    @FXML
    private TextField euros;


    @FXML
    private void cambiar() {
        try {
            // Cogemos los euros
            String texto = euros.getText();
            Float valor = Float.parseFloat(texto);

            // Convertimos los euros a dólares
            valor = conversorModelo.getDolar(valor);
            String dolaresCambiados = String.valueOf(valor);

            // Actualizamos el campo de dólares
            dolares.setText(dolaresCambiados);
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato inválido", "Introduce un número válido para los euros.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo recuperar la tasa de cambio.");
            e.printStackTrace(); // Para depuración
        }
    }

    // Método para mostrar el alert
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
