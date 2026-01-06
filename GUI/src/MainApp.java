import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        TableView<String> table = new TableView<>();

        Button ladenBtn = new Button("Möbel laden");

        VBox root = new VBox(10, table, ladenBtn);
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Möbelhaus Verwaltung");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
