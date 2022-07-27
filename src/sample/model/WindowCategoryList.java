package sample.model;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
             String response = "";
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                Object obj = new JSONParser().parse(responseLine.trim());
                JSONObject jo = (JSONObject) obj;
                response = jo.get("nameCategory");
              //  System.out.println(response);
//                response += responseLine.trim();
            }

//          response = response.substring(1,response.length()-1);
//
//            List<String> list = Arrays.asList(response.split(","));
//            System.out.println(list);
//            for (int i = 1; i < list.size(); i += 2 ) {
//                System.out.println(list.get(i));
//
//                String temporary = list.get(i).substring(16,list.get(i).length()-2);
//                list.set(i,temporary);
//
//            }

            double widht = 400.0;
            double height = 600.0;

            ObservableList<String> langs = FXCollections.observableArrayList(list);
            ListView<String> langsListView = new ListView<String>(langs);

            langsListView.setPrefSize(widht, height);

            MultipleSelectionModel<String> langsSelectionModel = langsListView.getSelectionModel();

            langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){

                public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){


                    URL url = null;
                    try {
                        url = new URL("http://localhost:8080/category/name/" + newValue);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Content-Type", "application/json; utf-8");
                        connection.setRequestProperty("Accept", "application/json");
                        connection.setConnectTimeout(10000);

                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(connection.getInputStream(), "utf-8"));
                            String response = "";
                            String responseLine;
                            while ((responseLine = br.readLine()) != null) {
                                response += responseLine.trim();
                            }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }
            });

            FlowPane root = new FlowPane(langsListView);
            Scene scene = new Scene(root, widht, height);

            primaryStage.setScene(scene);
            primaryStage.setTitle("CategoryList");
            primaryStage.show();



        }

    }

}

