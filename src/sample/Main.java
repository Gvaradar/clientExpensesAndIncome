package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.RAM;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/registerOrLogin.fxml"));
        RAM.window.setTitle("Hello World");
        RAM.window.setHeight(700);
        RAM.window.setWidth(800);
        RAM.window.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        RAM.window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
