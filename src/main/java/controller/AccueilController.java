package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AccueilController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void connexion(ActionEvent event) {
        Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");
    }

    @FXML
    void inscription(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
//    @FXML void Connexion(ActionEvent event) {}



}
