package com.example.hotel;

import com.example.hotel.Controller.*;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.HotelModelo;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import com.example.hotel.Modelo.Repository.Impl.ReservaRepositoryImpl;
import com.example.hotel.Modelo.ReservaVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> personData = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();
    private HotelModelo hotelModelo;
    private Map<String, List<Image>> imagenesHabitaciones;
    private Map<String, Integer> ocupacionHabitaciones;


    public MainApp(){
        ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
        ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
        hotelModelo = new HotelModelo();
        hotelModelo.setClienteRepository(clienteRepository);
        try{
            personData.addAll(hotelModelo.mostrarClientes());
        }catch (Exception e){
            e.printStackTrace();
        }
        cargarDatosHabitaciones();
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Cliente> getPersonData() {
        return personData;
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("HOTEL");

        initRootLayout();

        showClienteOverview();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showClienteOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteOverview.fxml"));
            AnchorPane clienteOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(clienteOverview);

            // Give the controller access to the main app.
            ClienteOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setHotelModelo(hotelModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified cliente. If the user
     * clicks OK, the changes are saved into the provided cliente object and true
     * is returned.
     *
     * @param cliente the cliente object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showClienteEditDialog(Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cliente into the controller.
            ClienteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNewCliente(cliente);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showNewReservaEditDialog(Reserva reserva, Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ReservaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva Reserva");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the reserva into the controller.
            ReservaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNewReserva(reserva, cliente);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReservaEditDialog(Reserva reserva) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ReservaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the reserva into the controller.
            ReservaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReserva(reserva);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showNewClienteEditDialog(Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Cliente");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cliente into the controller.
            ClienteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showBirthdayStatistics() {
        try {
            // Obtener todas las reservas de todos los clientes
            ArrayList<Reserva> reservas = hotelModelo.mostrarReservasAll();

            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ocupación Habitaciones");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the reservas into the controller
            BirthdayStatisticsController controller = loader.getController();
            controller.setReservaData(reservas);

            dialogStage.show();

        } catch (IOException | ExcepcionHotel e) {
            e.printStackTrace();
        }
    }

    private void cargarDatosHabitaciones() {
        imagenesHabitaciones = new HashMap<>();
        imagenesHabitaciones.put("DOBLE_USO_INDIVIDUAL", List.of(
                new Image(getClass().getResource("/images/doble_i.jpeg").toExternalForm()), new Image(getClass().getResource("/images/doble_i2.jpeg").toExternalForm()), new Image(getClass().getResource("/images/doble_i3.jpeg").toExternalForm())
        ));
        imagenesHabitaciones.put("DOBLE", List.of(
                new Image(getClass().getResource("/images/doble.jpeg").toExternalForm()), new Image(getClass().getResource("/images/doble2.jpeg").toExternalForm()), new Image(getClass().getResource("/images/doble3.jpeg").toExternalForm())
        ));
        imagenesHabitaciones.put("JUNIOR_SUITE", List.of(
                new Image(getClass().getResource("/images/junior.jpeg").toExternalForm()), new Image(getClass().getResource("/images/junior2.jpeg").toExternalForm()), new Image(getClass().getResource("/images/junior3.jpeg").toExternalForm())
        ));
        imagenesHabitaciones.put("SUITE", List.of(
                new Image(getClass().getResource("/images/suite.jpeg").toExternalForm()), new Image(getClass().getResource("/images/suite2.jpeg").toExternalForm()), new Image(getClass().getResource("/images/suite3.jpeg").toExternalForm())
        ));
        actualizarOcupacionHabitaciones();
    }

    private void actualizarOcupacionHabitaciones() {
        ocupacionHabitaciones = new HashMap<>();
        try {
            ArrayList<Reserva> reservasActivas = hotelModelo.mostrarReservasActivas();

            for (Reserva reserva : reservasActivas) {
                String tipoHabitacion = reserva.getTipo_Habitacion().replace(" ", "_").toUpperCase();
                ocupacionHabitaciones.put(tipoHabitacion, ocupacionHabitaciones.getOrDefault(tipoHabitacion, 0) + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ocupacionHabitaciones.put("DOBLE_USO_INDIVIDUAL", 0);
            ocupacionHabitaciones.put("DOBLE", 0);
            ocupacionHabitaciones.put("JUNIOR_SUITE", 0);
            ocupacionHabitaciones.put("SUITE", 0);
        }
    }

    public List<Image> getImagenesPorTipo(String tipoHabitacion) {
        return imagenesHabitaciones.getOrDefault(tipoHabitacion, List.of());
    }

    public int getOcupacionPorTipo(String tipoHabitacion) {
        return ocupacionHabitaciones.getOrDefault(tipoHabitacion, 0);
    }

    public void mostrarCarrusel(String tipoHabitacion) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("CarruselDialog.fxml"));
            VBox page = loader.load();
            actualizarOcupacionHabitaciones();

            // Crear el diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Carrusel de Habitaciones");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CarruselDialogController controller = loader.getController();

            List<Image> imagenes = getImagenesPorTipo(tipoHabitacion);
            int ocupacion = getOcupacionPorTipo(tipoHabitacion);
            controller.configurarCarrusel(tipoHabitacion, imagenes, ocupacion);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showWebView(){
        try {
            WebView webView = new WebView();

            webView.getEngine().load("https://www.wikipedia.org/");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Wikipedia");

            Scene scene = new Scene(webView, 800, 600);
            dialogStage.setScene(scene);

            dialogStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}