package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AccueilController {

    Utilisateur utilisateur;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    AccueilController() {}

    AccueilController(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    initialize() {
        
    }

    @FXML
    void connexion(ActionEvent event) {
        Main.changeScene("Connexion", new ConnexionController(), "Connectez vous ;)");
    }

    @FXML
    void inscription(ActionEvent event) {
        Main.changeScene("Inscription", new InscriptionController(), "Inscription");
    }

    @FXML
    void initialize() {

    }
//    @FXML void Connexion(ActionEvent event) {}



}
