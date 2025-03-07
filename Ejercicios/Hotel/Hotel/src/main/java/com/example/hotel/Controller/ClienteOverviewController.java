package com.example.hotel.Controller;

import com.example.hotel.Cliente;
import com.example.hotel.MainApp;
import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.HotelModelo;
import com.example.hotel.Modelo.Repository.ClienteRepository;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import com.example.hotel.Modelo.Repository.Impl.ReservaRepositoryImpl;
import com.example.hotel.Modelo.Repository.ReservaRepository;
import com.example.hotel.Util.ClienteUtil;
import com.example.hotel.Util.ReservaUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.hotel.Reserva;
import com.example.hotel.Modelo.ReservaVO;

import java.util.ArrayList;

public class ClienteOverviewController {
    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, String> NameColumn;
    @FXML
    private TableColumn<Cliente, String> ApellidoColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;
    @FXML
    private Label dniLabel;

    @FXML
    private TableView<Reserva> reservaTable;
    @FXML
    private TableColumn<Reserva, String> ReservaColumn;

    @FXML
    private Label arrivalLabel;
    @FXML
    private Label departureLabel;
    @FXML
    private Label nHabLabel;
    @FXML
    private Label tHabLabel;
    @FXML
    private Label fumadorLabel;
    @FXML
    private Label regimenLabel;
    @FXML
    private TextField DniField;


    private MainApp mainApp;
    private ClienteUtil clienteUtil = new ClienteUtil();
    private HotelModelo modelo;
    private ReservaUtil reservaUtil = new ReservaUtil();

    private ClienteRepository clienteRepository = new ClienteRepositoryImpl(); // Repositorio
    private ReservaRepository reservaRepository = new ReservaRepositoryImpl();

    public ClienteOverviewController() {
    }

    @FXML
    private void initialize() {
        // Configuración de las columnas de las tablas
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        ApellidoColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        ReservaColumn.setCellValueFactory(cellData -> cellData.getValue().fecha_LlegadaProperty().asString());

        showClienteDetails(null);
        showReservaDetails(null);

        // Listener para cuando se selecciona un cliente en la tabla
        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showClienteDetails(newValue);
                        loadReservasData(newValue.getDni());
                    } else {
                        reservaTable.getItems().clear(); // Limpiar las reservas si no hay cliente seleccionado
                    }
                });

        // Listener para cuando se selecciona una reserva en la tabla
        reservaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showReservaDetails(newValue);
                }
        );
    }

    public void loadReservasData(String dniCliente) {
        try {
            if (dniCliente != null && !dniCliente.isEmpty()) {
                ObservableList<Reserva> reservaData = FXCollections.observableArrayList(modelo.mostrarReservas(dniCliente));

                if (reservaData.isEmpty()) {
                    mostrarAlertaError("Sin reservas", "Este cliente no tiene reservas.");
                    reservaTable.getItems().clear();
                } else {
                    reservaData.sort((reserva1, reserva2) -> reserva1.getFecha_Llegada().compareTo(reserva2.getFecha_Llegada()));
                    reservaTable.setItems(reservaData);
                }
            } else {
                mostrarAlertaError("Error de DNI", "El DNI del cliente no es válido.");
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al cargar reservas", "Ha ocurrido un error: " + e.getMessage());
        }
    }

    private void showReservaDetails(Reserva reserva) {
        if (reserva != null) {
            arrivalLabel.setText(reserva.getFecha_Llegada().toString());
            departureLabel.setText(reserva.getFecha_Salida().toString());
            nHabLabel.setText(String.valueOf(reserva.getNHabitaciones()));
            tHabLabel.setText(reserva.getTipo_Habitacion());
            fumadorLabel.setText(String.valueOf(reserva.isFumador()));
            regimenLabel.setText(reserva.getRegimen());
        } else {
            arrivalLabel.setText("");
            departureLabel.setText("");
            nHabLabel.setText("");
            tHabLabel.setText("");
            fumadorLabel.setText("");
            regimenLabel.setText("");
        }
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Por favor corrija los campos no válidos");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadPersonData(); // Cargar datos al inicio
    }

    public void setHotelModelo(HotelModelo modelo) {
        this.modelo = modelo;
    }

    private void loadPersonData() {
        try {
            ArrayList<ClienteVO> clienteVOList = clienteRepository.ObtenerListaClientes();
            ArrayList<Cliente> clienteList = clienteUtil.getPersons(clienteVOList);
            clienteList.sort((cliente1, cliente2) -> cliente1.getNombre().compareToIgnoreCase(cliente2.getNombre()));
            clienteTable.getItems().setAll(clienteList); // Actualiza la tabla co// Actualiza la tabla con nuevos datos
        } catch (ExcepcionHotel e) {
            mostrarAlertaError("Error al cargar la lista de personas", e.getMessage());
        }
    }

    private void showClienteDetails(Cliente cliente) {
        if (cliente != null) {
            nameLabel.setText(cliente.getNombre());
            apellidoLabel.setText(cliente.getApellidos());
            direccionLabel.setText(cliente.getDireccion());
            provinciaLabel.setText(cliente.getProvincia());
            localidadLabel.setText(cliente.getLocalidad());
            dniLabel.setText(cliente.getDni());
        } else {
            nameLabel.setText("");
            apellidoLabel.setText("");
            direccionLabel.setText("");
            provinciaLabel.setText("");
            localidadLabel.setText("");
            dniLabel.setText("");
        }
    }


    @FXML
    private void handleDeletePerson() {
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente clienteToDelete = clienteTable.getItems().get(selectedIndex);
            try {
                ClienteVO clienteVO = clienteUtil.convertToClienteVO(clienteToDelete);
                modelo.borrarPerson(clienteVO);
                loadPersonData(); // Recargar la lista después de borrar
            } catch (ExcepcionHotel e) {
                mostrarAlertaError("Error al borrar el cliente", e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccion");
            alert.setHeaderText("No Hay Persona Seleccionada");
            alert.setContentText("Seleccione a una persona de la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showNewClienteEditDialog(tempCliente);
        if (okClicked) {
            try {
                ClienteVO clienteVO = clienteUtil.convertToClienteVO(tempCliente);
                modelo.nuevaPerson(clienteVO);
                loadPersonData(); // Recargar la lista después de añadir
            } catch (ExcepcionHotel e) {
                mostrarAlertaError("Error al guardar la persona", e.getMessage());
            }
        }
    }

    @FXML
    private void handleEditPerson() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showClienteEditDialog(selectedCliente);
            if (okClicked) {
                try {
                    ClienteVO clienteVO = clienteUtil.convertToClienteVO(selectedCliente);
                    modelo.editarPerson(clienteVO); // Actualiza la persona
                    loadPersonData(); // Recargar la lista después de editar
                    showClienteDetails(selectedCliente); // Muestra los detalles actualizados
                } catch (ExcepcionHotel e) {
                    mostrarAlertaError("Error al editar la persona", e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccion");
            alert.setHeaderText("No se ha seleccionado ninguna persona");
            alert.setContentText("Seleccione a una persona de la tabla");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleDeleteReserva() {
        int selectedIndex = reservaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reserva reservaToDelete = reservaTable.getItems().get(selectedIndex);
            try {
                ReservaVO reservaVO = reservaUtil.convertirReservaAReservaVO(reservaToDelete);
                modelo.borrarReserva(reservaVO);
                loadReservasData(reservaToDelete.getDni_Cliente()); // Recargar la lista después de borrar
            } catch (ExcepcionHotel e) {
                mostrarAlertaError("Error al borrar el cliente", e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccion");
            alert.setHeaderText("No Hay Persona Seleccionada");
            alert.setContentText("Seleccione a una persona de la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewReserva() {
        Cliente cliente = clienteTable.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            Reserva tempReserva = new Reserva();
            boolean okClicked = mainApp.showNewReservaEditDialog(tempReserva, cliente);
            if (okClicked) {
                try {
                    ReservaVO reservaVO = reservaUtil.convertirReservaAReservaVO(tempReserva);
                    modelo.nuevaReserva(reservaVO);
                    loadReservasData(reservaVO.getDni_Cliente());
                } catch (ExcepcionHotel e) {
                    mostrarAlertaError("Error al guardar la persona", e.getMessage());
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selección");
            alert.setHeaderText("No hay cliente seleccionado");
            alert.setContentText("Debe seleccionar un cliente antes de crear una reserva.");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleEditReserva() {
        Reserva selectedReserva = reservaTable.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = mainApp.showReservaEditDialog(selectedReserva);
            if (okClicked) {
                try {
                    ReservaVO reservaVO = reservaUtil.convertirReservaAReservaVO(selectedReserva);
                    modelo.editarReserva(reservaVO);
                    loadReservasData(reservaVO.getDni_Cliente());
                    showReservaDetails(selectedReserva);
                } catch (ExcepcionHotel e) {
                    mostrarAlertaError("Error al editar la persona", e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccion");
            alert.setHeaderText("No se ha seleccionado ninguna persona");
            alert.setContentText("Seleccione a una persona de la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearchByDni() {
        String dni = DniField.getText().trim();  // Obtener el DNI ingresado

        if (!dni.isEmpty()) {
            // Filtra los clientes por el DNI
            ObservableList<Cliente> filteredList = FXCollections.observableArrayList();
            for (Cliente cliente : mainApp.getPersonData()) {
                if (cliente.getDni().equals(dni)) {
                    filteredList.add(cliente);
                    break; // Se detiene después de encontrar el primer cliente que coincida
                }
            }
            clienteTable.setItems(filteredList);  // Actualiza la tabla con el cliente filtrado
        } else {
            // Si el campo DNI está vacío, muestra todos los clientes
            clienteTable.setItems(mainApp.getPersonData());
        }
    }


}
