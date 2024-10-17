package ejercicios;

import javafx.application.Application;
import javafx.stage.Stage;

public class DobleContador extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Crear dos instancias de Contador con la misma propiedad
        Contador contador1 = new Contador();
        Contador contador2 = new Contador();

        contador1.numProperty().bindBidirectional(contador2.numProperty());
        // Crear dos stages para mostrar ambos contadores
        Stage stage1 = new Stage();
        contador1.start(stage1);

        Stage stage2 = new Stage();
        contador2.start(stage2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
