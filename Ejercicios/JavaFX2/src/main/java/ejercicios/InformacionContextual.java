package ejercicios;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


public class InformacionContextual extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(20, 20, 20, 20));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER);

            Button  btBeber;
            btBeber = new Button();

            Image imagen1 = new Image("file:resources/imagenes/cerveza.png", 100, 100, true, true);
            btBeber.setGraphic(new ImageView(imagen1));
            btBeber.setText("Beber");
            btBeber.setGraphicTextGap(20);
            btBeber.setFont(Font.font("Ani", 30));

            Tooltip infoCerveza = new Tooltip("Haz un descanso y bébete una cerveza, pero no se te ocurra abusar");
            Image imagen2 = new Image("file:resources/imagenes/apagar.png", 100, 100, true, true);
            infoCerveza.setGraphic(new ImageView(imagen2));
            infoCerveza.setFont(Font.font(15));
            infoCerveza.setPrefWidth(200);
            infoCerveza.setWrapText(true);
            btBeber.setTooltip(infoCerveza);

            raiz.getChildren().addAll(btBeber);

            Scene escena = new Scene(raiz, 400, 120);
            escenarioPrincipal.setTitle("Información contextual");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}