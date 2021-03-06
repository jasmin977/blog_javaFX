package Client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/view/sample.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("view/article.fxml"));
        primaryStage.setTitle("Blog!");
        primaryStage.setScene(new Scene(root, 786, 587));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
