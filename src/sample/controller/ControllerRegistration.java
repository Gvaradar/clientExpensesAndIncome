package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import sample.Main;
import sample.model.RAM;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ControllerRegistration {
    @FXML
    Button btnRegistration;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField nameClientField;

    @FXML
    Button backButton;

    @FXML
    public void pressBackButton() throws Exception {
        new Main().start(RAM.window);
    }

    @FXML
    public void onRegistration() {
        String login = loginField.getText();
        String password = passwordField.getText();
        String nameClient = nameClientField.getText();
        try {
            URL url = new URL("http://localhost:8080/client");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(10000);
//            connection.setConnectTimeout(100);
//            connection.setReadTimeout(100);

            Map<String, String> map = new HashMap<>();
            map.put("\"loginClient\"", "\"" + login + "\"");
            map.put("\"password_client\"", "\"" + password + "\"");
            map.put("\"name_client\"", "\"" + nameClient + "\"");
            String temp = map.toString();
            temp = temp.replace("=", ":");

            connection.setDoOutput(true);


            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = temp.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
            System.out.println(temp);
        } catch (MalformedURLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Соединение невозможно");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Соединение невозможно");
            alert.showAndWait();
        }

    }


}

