package sample.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowRegistration extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/registerOrLogin.fxml"));
        RAM.window.setTitle("Registration");
        RAM.window.setScene(new Scene(root, 600, 400));
        RAM.window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showWindow() throws Exception {
        start(RAM.window);
    }
}
