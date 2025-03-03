package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

public class ConnexionController {

    private UtilisateurRepository utilisateurRepository;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email;

    @FXML
    private PasswordField mdp;

    public ConnexionController() {
        this.utilisateurRepository = new UtilisateurRepository();
    }

    @FXML
    void connexion(ActionEvent event) {
        Utilisateur utilisateur = this.utilisateurRepository.connect(email.getText(), mdp.getText());
        if (utilisateur != null) {
            System.out.println("Bonjour " + utilisateur.getNom() + " " + utilisateur.getPrenom());
            Main.changeScene("DefaultPannel", new DefaultPannelController(utilisateur), "Bienvenue "+utilisateur.getPrenom()+"!!");
        } else {
            System.out.println("Erreur de connexion");
        }
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
