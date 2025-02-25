package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Connexion {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email;

    @FXML
    private PasswordField mdp;

    @FXML
    void connexion(ActionEvent event) {

    }

    @FXML
    void mdpOublie(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'connexion.fxml'.";
        assert mdp != null : "fx:id=\"mdp\" was not injected: check your FXML file 'connexion.fxml'.";

    }

}
