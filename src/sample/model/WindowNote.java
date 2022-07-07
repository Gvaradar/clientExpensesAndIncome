package sample.model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class WindowNote extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        ObservableList<String> langs = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
        ListView<String> langsListView = new ListView<String>(langs);

        double widht = 400.0;
        double height = 600.0;

        langsListView.setPrefSize(widht, height);

        FlowPane root = new FlowPane(langsListView);
        Scene scene = new Scene(root,widht,height);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Note");
        primaryStage.show();
    }
}
