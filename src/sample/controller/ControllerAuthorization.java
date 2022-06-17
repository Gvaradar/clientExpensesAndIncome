package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.model.RAM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ControllerAuthorization {


    @FXML
    TextField textField;

    @FXML
    PasswordField passField;

    @FXML
    Button buttonEnter;

    @FXML
    Label warning;

    @FXML
    public void buttonPress() throws IOException {
        String text = textField.getText();
        String password = passField.getText();

        URL url = new URL("http://localhost:8080/client");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(10000);

        Map<String, String> map = new HashMap<>();
        map.put("\"login\"", "\"" + text + "\"");
        map.put("\"password\"", "\"" + password + "\"");

        String temp = map.toString();
        temp = temp.replace("=",":");

        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = temp.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            long idClient = Long.parseLong(response.toString());
            if(idClient == 0){
                textField.clear();
                passField.clear();
                warning.setText("Неверно введен логин или пароль");

            }else {
                RAM.idClient = idClient;
                System.out.println(RAM.idClient);
            }

        }
    }
}
