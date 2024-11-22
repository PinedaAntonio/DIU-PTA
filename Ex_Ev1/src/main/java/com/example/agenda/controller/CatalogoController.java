package com.example.agenda.controller;

import com.example.agenda.MainApp;
import com.example.agenda.model.ExcepcionArticulo;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.agenda.model.CatalogoModel;
import com.example.agenda.Articulo;

public class CatalogoController {

    @FXML
    private TableView<Articulo> articuloTable;
    @FXML
    private TableColumn<Articulo, String> nombreColumn;
    @FXML
    private TableColumn<Articulo, String> precioColumn;

    @FXML
    private TextField unidadesField;
    @FXML
    private Label totalLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label descripcionLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private Label stockLabel;

    private MainApp mainApp;
    private CatalogoModel modeloCatalogo;

    public void setCatalogoModel(CatalogoModel modeloCatalogo) {
        this.modeloCatalogo = modeloCatalogo;
    }

    public CatalogoController() {
    }

    @FXML
    private void initialize() {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        precioColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));

        DetallesArticulos(null);
        articuloTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> DetallesArticulos(newValue));
    }

    private void DetallesArticulos(Articulo articulo) {
        if (articulo != null) {
            nombreLabel.setText(articulo.getNombre());
            descripcionLabel.setText(articulo.getDescripcion());
            precioLabel.setText(String.valueOf(articulo.getPrecio()));
            stockLabel.setText(Integer.toString(articulo.getStock()));
        } else {
            nombreLabel.setText("");
            descripcionLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
        }
    }

    @FXML
    private void eliminarArticulo() {
        int seleccionado = articuloTable.getSelectionModel().getSelectedIndex();
        Articulo seleccionadoArticulo = articuloTable.getSelectionModel().getSelectedItem();

        if (seleccionado >= 0) {
            articuloTable.getItems().remove(seleccionado);
            try {
                modeloCatalogo.eliminarArticulo(seleccionadoArticulo.getId());
            } catch (ExcepcionArticulo e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay artículo seleccionado");
            alert.setContentText("Por favor selecciona un artículo en la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void NuevoArticulo() {
        Articulo tempArticulo = new Articulo();
        boolean okClicked = mainApp.EditarArticulo(tempArticulo);

        if (okClicked) {
            if (mainApp.getArticulos().size() < 50) {
                try {
                    modeloCatalogo.añadirArticulo(tempArticulo);
                    tempArticulo.setId(modeloCatalogo.obtenerID());
                } catch (ExcepcionArticulo e) {
                    throw new RuntimeException(e);
                }
                mainApp.getArticulos().add(tempArticulo);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Agenda completa");
                alert.setHeaderText("No se pueden introducir más artículos");
                alert.setContentText("Lo siento, tu agenda está completa.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void editarArticulo() {
        Articulo selectedArticulo = articuloTable.getSelectionModel().getSelectedItem();
        if (selectedArticulo != null) {
            boolean okClicked = mainApp.EditarArticulo(selectedArticulo);
            articuloTable.refresh();
            try {
                modeloCatalogo.editarArticulo(selectedArticulo);
            } catch (ExcepcionArticulo e) {
                throw new RuntimeException(e);
            }

            if (okClicked) {
                DetallesArticulos(selectedArticulo);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null");
            alert.setHeaderText("No hay artículo seleccionado");
            alert.setContentText("Debe seleccionar un artículo en la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void calcularTotal() {
        try {
            if (articuloTable.getSelectionModel().getSelectedIndex() != -1) {
                if (Integer.parseInt(stockLabel.getText()) >= Integer.parseInt(unidadesField.getText())) {
                    totalLabel.setText(String.valueOf(
                            Double.parseDouble(unidadesField.getText()) *
                                    Double.parseDouble(precioLabel.getText())));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Stock insuficiente");
                    alert.setHeaderText("No hay suficientes unidades");
                    alert.setContentText("Por favor ingresa una cantidad menor al stock");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nada Seleccionado");
                alert.setHeaderText("Debe seleccionar un artículo");
                alert.setContentText("Por favor selecciona un artículo");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText("Valores inválidos");
            alert.setContentText("Por favor introduce números válidos en las unidades.");
            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        articuloTable.setItems(mainApp.getArticulos());
    }
}
