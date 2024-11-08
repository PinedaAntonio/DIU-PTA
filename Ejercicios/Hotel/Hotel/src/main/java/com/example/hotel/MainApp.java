package com.example.hotel;

import com.example.hotel.Controller.BirthdayStatisticsController;
import com.example.hotel.Controller.ClienteOverviewController;
import com.example.hotel.Controller.ClienteEditDialogController;
import com.example.hotel.Modelo.HotelModelo;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> personData = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();
    private HotelModelo hotelModelo;


    public MainApp(){
        ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
        hotelModelo = new HotelModelo();
        hotelModelo.setClienteRepository(clienteRepository);
        try{
            personData.addAll(hotelModelo.mostrarClientes());
        }catch (Exception e){
            e.printStackTrace();
        }
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
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
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
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(reservaData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}