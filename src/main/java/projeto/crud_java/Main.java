package projeto.crud_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage.setTitle("Sistema");
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("file:src/main/java/projeto/IconeCRUD.png"));
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}