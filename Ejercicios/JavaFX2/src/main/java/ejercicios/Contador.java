package ejercicios;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Contador extends Application {

    private Button btmas, btmenos, btcero;
    private Label contador;
    private IntegerProperty num = new SimpleIntegerProperty(1);  // Cada contador tiene su propio IntegerProperty

    //     Metodo para manejar los eventos de los botones
    private void botonPulsado(Button button) {
        num.set(button == btmas ? num.get() + 1 :
                button == btmenos ? num.get() - 1 : 0);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(0, 0, 0, 0));
            raiz.setSpacing(0);
            raiz.getStyleClass().add("raiz");

            // Etiqueta que mostrará el valor del contador
            contador = new Label(String.valueOf(num.get()));
            contador.setFont(Font.font(60));
            contador.setPadding(new Insets(30, 0, 0, 130));
            contador.getStyleClass().add("contador");

            // Listener para actualizar la etiqueta cuando cambie el valor de 'num'
            num.addListener((obs, oldVal, newVal) -> contador.setText(newVal.toString()));

            // Contenedor para los botones
            HBox botones = new HBox();
            botones.setPadding(new Insets(30, 0, 0, 50));
            botones.setSpacing(60);

            // Crear los botones
            btmas = new Button();
            btmenos = new Button();
            btcero = new Button();

            // Configurar los textos de los botones
            btmas.setText("+");
            btmas.setOnAction(e -> botonPulsado(btmas));  // Maneja el evento del botón "+"
            btmenos.setText("-");
            btmenos.setOnAction(e -> botonPulsado(btmenos));  // Maneja el evento del botón "-"
            btcero.setText("0");
            btcero.getStyleClass().add("boton");
            btcero.setOnAction(e -> botonPulsado(btcero));  // Maneja el evento del botón "0"

            // Añadir los botones al contenedor
            botones.getChildren().addAll(btmenos, btcero, btmas);
            raiz.getChildren().addAll(botones, contador);

            // Crear la escena y aplicarle el archivo de estilo CSS
            Scene escena = new Scene(raiz, 300, 200);
            escena.getStylesheets().add(getClass().getResource("/styles/Contador.css").toExternalForm());
            stage.setTitle("Contador");
            stage.setScene(escena);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter para acceder a la propiedad 'num'
    public IntegerProperty numProperty() {
        return num;
    }
}