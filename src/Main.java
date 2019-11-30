import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = null;
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller controller = new Controller();
        ventana.setController(controller);
        root = ventana.load();
        Scene scene =new Scene(root, 500, 400);
        scene.getStylesheets().add("estilo.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
