package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.model.RAM;
import sample.model.WindowRegistration;
import sample.model.WindowAuthorization;


public class ControllerRegisterOrLogin {


    @FXML
    Button buttonAuthorization;

    @FXML
    Button buttonRegistration;

    @FXML
    public void buttonRegistrationPress() throws Exception {
        WindowRegistration windowRegistration = new WindowRegistration();
        windowRegistration.showWindow();
        RAM.window.close();
    }

    @FXML
    public void buttonAuthorizationPress() throws Exception {
        WindowAuthorization windowAuthorization = new WindowAuthorization();
        windowAuthorization.showWindow();
    }

}
