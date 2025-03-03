package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;

public class InscriptionController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField mot_de_passe;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    void inscription(ActionEvent event) {
        if (!nom.getText().isBlank() && !prenom.getText().isBlank() && !email.getText().isBlank() && !mot_de_passe.getText().isBlank()) {
            if (email.getText().contains("@") && email.getText().contains(".")) {
                if (mot_de_passe.getText().length() >= 6) {
                    model.Utilisateur utilisateur = new model.Utilisateur(nom.getText(), prenom.getText(), email.getText(), mot_de_passe.getText(), Utilisateur.Role.Default);
                    repository.UtilisateurRepository utilisateurRepository = new repository.UtilisateurRepository();
                    utilisateurRepository.create(utilisateur);
                    Main.changeScene("DefaultPannel", new DefaultPannelController(utilisateur), "Bienvenue "+utilisateur.getPrenom()+"!!");
                }
                else {
                    System.out.println("Le mot de passe doit contenir au moins 6 caractères"); //todo mettre un label error et modifier le texte pour afficher ce qui ne va pas
                }
            }
            else {
                System.out.println("Veuillez saisir une adresse mail valide"); //todo mettre un label error et modifier le texte pour afficher ce qui ne va pas
            }
        }
        else {
            System.out.println("Veuillez remplir tous les champs"); //todo mettre un label error et modifier le texte pour afficher ce qui ne va pas
        }
    }

}
