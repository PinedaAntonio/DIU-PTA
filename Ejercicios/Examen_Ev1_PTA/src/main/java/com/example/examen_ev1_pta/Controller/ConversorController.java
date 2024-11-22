package com.example.examen_ev1_pta.Controller;

import com.example.examen_ev1_pta.MainApp;
import com.example.examen_ev1_pta.Modelo.Moneda;
import javafx.fxml.FXML;
import com.example.examen_ev1_pta.Modelo.ConversorModelo;
import javafx.scene.control.*;

public class ConversorController {

    private MainApp mainApp;
    private ConversorModelo conversorModelo;

    public void setConversorModelo(ConversorModelo conversorModelo) {
        this.conversorModelo = conversorModelo;
    }

    @FXML
    private TextField monedaTextField;

    @FXML
    private TextField eurosTextField;

    @FXML
    private Label monedaLabel;

    @FXML
    private Button convertirButton;

    @FXML
    private TableView<Moneda> monedaTable;

    @FXML
    private TableColumn<Moneda, String> monedaColumn;

    @FXML
    private void initialize() {
        monedaColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        DetallesMoneda(null);
        monedaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> DetallesMoneda(newValue));

    }

    @FXML
    private void convertir() {
        if(!eurosTextField.getText().isEmpty()){
            Moneda selectedmoneda = monedaTable.getSelectionModel().getSelectedItem();
            try {
                // Cogemos los euros
                String texto = eurosTextField.getText();
                Float valor = Float.parseFloat(texto);

                // Convertimos los euros a dólares
                valor = (float) conversorModelo.getConversion(valor, selectedmoneda.getMultiplicador());
                String dolaresCambiados = String.valueOf(valor);

                // Actualizamos el campo de dólares
                monedaTextField.setText(dolaresCambiados);
            } catch (NumberFormatException e) {
                mostrarAlerta("Formato inválido", "Introduce un número válido para las monedas.");
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo recuperar la tasa de cambio.");
                e.printStackTrace(); // Para depuración
            }
        } else if (!monedaTextField.getText().isEmpty()) {
            Moneda selectedmoneda = monedaTable.getSelectionModel().getSelectedItem();
            try {
                // Cogemos los euros
                String texto = monedaTextField.getText();
                Float valor = Float.parseFloat(texto);

                // Convertimos los euros a dólares
                valor = (float) conversorModelo.getConversionInversa(valor, selectedmoneda.getMultiplicador());
                String dineroCambiado = String.valueOf(valor);

                // Actualizamos el campo de dólares
                eurosTextField.setText(dineroCambiado);
            } catch (NumberFormatException e) {
                mostrarAlerta("Formato inválido", "Introduce un número válido para las monedas.");
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo recuperar la tasa de cambio.");
                e.printStackTrace(); // Para depuración
            }
        } else {
            mostrarAlerta("Formato inválido", "Introduce un número válido para las monedas.");
        }

    }

    private void DetallesMoneda(Moneda moneda) {
        if (moneda != null) {
            monedaLabel.setText(moneda.getNombre());
        } else {
            monedaLabel.setText("Moneda");
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        monedaTable.setItems(mainApp.getMonedas());
    }

    // Método para mostrar el alert
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void abrir(){
        mainApp.vistaImagen();
    }

}