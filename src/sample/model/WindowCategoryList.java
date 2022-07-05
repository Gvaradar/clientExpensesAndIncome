package sample.model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class WindowCategoryList extends  Application {

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL url = new URL("http://localhost:8080/category");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(10000);

        try (BufferedReader br = new BufferedReader(                                              //35-41 получение данных с сервера.
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
             String response = "";
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response += responseLine.trim();
            }
          response = response.substring(1,response.length()-1);

            List<String> list = Arrays.asList(response.split(","));

            for (int i = 0; i < list.size(); i++) {
                String temporary = list.get(i).substring(18,list.get(i).length()-2);
                list.set(i,temporary);
                System.out.println(list.get(i));
            }

            double widht = 400.0;
            double height = 600.0;

            ObservableList<String> langs = FXCollections.observableArrayList(list);
            ListView<String> langsListView = new ListView<String>(langs);

            langsListView.setPrefSize(widht, height);

            FlowPane root = new FlowPane(langsListView);
            Scene scene = new Scene(root, widht, height);

            primaryStage.setScene(scene);
            primaryStage.setTitle("CategoryList");
            primaryStage.show();

        }

    }

}

