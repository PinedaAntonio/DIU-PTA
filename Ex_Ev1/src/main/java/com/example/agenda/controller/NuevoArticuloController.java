package com.example.agenda.controller;

import com.example.agenda.model.CatalogoModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.agenda.Articulo;
import javafx.scene.control.Alert;

public class NuevoArticuloController {

    @FXML
    private TextField nombreField; // Campo de texto para introducir el nombre del artículo.
    @FXML
    private TextField descripcionField; // Campo de texto para introducir la descripción del artículo.
    @FXML
    private TextField precioField; // Campo de texto para introducir el precio del artículo.
    @FXML
    private TextField stockField; // Campo de texto para introducir el stock disponible del artículo.
    @FXML
    private ProgressBar productosDisponiblesProgressBar;

    private CatalogoModel catalogoModel; // Modelo de datos que gestiona el catálogo de artículos.
    private Stage dialogStage; // Referencia al `Stage` que representa esta ventana de diálogo.
    private Articulo articulo; // Artículo que se está editando o creando.
    private boolean okClicked = false; // Indica si el usuario ha confirmado los cambios.
    private IntegerProperty disponibles = new SimpleIntegerProperty();

    @FXML
    private void initialize() {
        // No hay inicialización adicional en este caso.
    }

    // Establece el `Stage` para este diálogo.
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Configura el artículo a editar o crear.
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;

        // Inicializa los campos de texto con los valores del artículo.
        nombreField.setText(articulo.getNombre());
        descripcionField.setText(articulo.getDescripcion());
        precioField.setText(String.valueOf(articulo.getPrecio()));
        stockField.setText(Integer.toString(articulo.getStock()));
    }

    // Indica si el usuario ha confirmado la acción.
    public boolean OkClicked() {
        return okClicked;
    }

    // Maneja la acción de confirmar los datos introducidos.
    @FXML
    private void Ok() {
        if (ValidarDatos()) { // Si los datos son válidos
            articulo.setNombre(nombreField.getText());
            articulo.setDescripcion(descripcionField.getText());
            articulo.setPrecio(Double.parseDouble(precioField.getText()));
            articulo.setStock(Integer.parseInt(stockField.getText()));

            okClicked = true; // Indica que la acción fue confirmada.
            dialogStage.close(); // Cierra la ventana de diálogo.
        }
    }

    // Maneja la acción de cancelar. Cierra el diálogo sin realizar cambios.
    @FXML
    private void Cancelar() {
        dialogStage.close(); // Cierra el diálogo sin hacer cambios.
    }

    // Valida los datos introducidos en los campos de texto.
    private boolean ValidarDatos() {
        String errorMessage = ""; // Mensaje de error acumulativo.

        // Validaciones para cada campo.
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre inválido!\n"; // Si el nombre está vacío.
        }
        if (descripcionField.getText() == null || descripcionField.getText().length() == 0) {
            errorMessage += "Descripción inválida!\n"; // Si la descripción está vacía.
        }
        if (precioField.getText() == null || precioField.getText().length() == 0) {
            errorMessage += "Precio inválido!\n"; // Si el precio está vacío.
        } else {
            try {
                Double.parseDouble(precioField.getText()); // Verifica si el precio es un número válido.
            } catch (NumberFormatException e) {
                errorMessage += "Precio debe ser un número válido!\n"; // Si no es un número.
            }
        }
        if (stockField.getText() == null || stockField.getText().length() == 0) {
            errorMessage += "Stock inválido!\n"; // Si el stock está vacío.
        } else {
            try {
                Integer.parseInt(stockField.getText()); // Verifica si el stock es un número válido.
            } catch (NumberFormatException e) {
                errorMessage += "Stock debe ser un número entero válido!\n"; // Si no es un número entero.
            }
        }

        if (errorMessage.length() == 0) {
            // Si no hay errores, los datos son válidos.
            return true;
        } else {
            // Si hay errores, muestra una alerta con el mensaje.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText("Revisa el contenido de los datos");
            alert.setContentText(errorMessage); // Incluye el mensaje detallado.
            alert.showAndWait(); // Muestra la alerta.
            return false; // Indica que los datos no son válidos.
        }
    }



    public void setCatalogoModel(CatalogoModel catalogoModel) {
        this.catalogoModel = catalogoModel; // Asigna el modelo de datos del catálogo.

        // Vincula bidireccionalmente disponibles con la propiedad del modelo
        disponibles.bindBidirectional(catalogoModel.getTotalProductosProperty());

        // Listener para la propiedad disponibles
        disponibles.addListener((observable, oldValue, newValue) -> {
            // Calcula el progreso de la barra
            double progreso = calculateProgress(newValue.intValue());
            productosDisponiblesProgressBar.setProgress(progreso);
        });

        // Inicializa la barra de progreso con el valor actual
        double initialProgress = calculateProgress(disponibles.get());
        productosDisponiblesProgressBar.setProgress(initialProgress);
    }

    // Método auxiliar para calcular el progreso
    private double calculateProgress(int value) {
        int maxProductos = 50; // Máximo de productos considerados como rango completo
        return Math.min((double) value / maxProductos, 1.0); // Escala el progreso y asegura un límite máximo de 1.0
    }



    // Actualiza la etiqueta con el total de productos disponibles en el catálogo.

}
