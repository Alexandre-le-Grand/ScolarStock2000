package controller;

import application.Main;
import jakarta.mail.MessagingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import services.email.Email;
import services.security.Security;

import java.io.UnsupportedEncodingException;

public class ReinitialisationMdpController {

    Integer id;
    String email;
    UtilisateurRepository utilisateurRepository;
    String coderef;
    int countdown = 3;

    public ReinitialisationMdpController(String email) {
        this.email = email;
        this.utilisateurRepository = new UtilisateurRepository();
    }

    @FXML
    private TextField code;
    @FXML
    private PasswordField nouveau_mdp;

    @FXML
    void mdpOublie(ActionEvent event) {
        if (code.getText().equals(coderef)) {
            if (nouveau_mdp.getText().length() >= 6) {
                Utilisateur utilisateur = utilisateurRepository.findById(id);
                utilisateur.setMot_de_passe(Security.hash(nouveau_mdp.getText()));
                utilisateurRepository.update(utilisateur);
                System.out.println("Mot de passe modifié avec succs");
                Main.changeScene("DefaultPannel", new DefaultPannelController(utilisateur), "Bienvenue "+utilisateur.getPrenom()+"!!");
            
            }
            else {
                System.out.println("Le mot de passe doit contenir au moins 6 caractères"); //todo mettre un label error et modifier le texte pour afficher ce qui ne va pas
            }
        }
        else {
            this.countdown--;
            System.out.println("Le code est incorrect, veuillez réessayer\nVous avez encore "+countdown+" essais"); //todo mettre un label error et modifier le texte pour afficher ce qui ne va pas
            if (countdown == 0) {
                System.out.println("Vous avez atteint le nombre d'essais autorisés"); //todo mettre un label error et modifier le texte pour afficher ce qui ne va pas
                Main.changeScene("Connexion", new ConnexionController(), "Connexion");
            }
        }

    }

    @FXML
    void initialize() throws MessagingException, UnsupportedEncodingException {
        this.id = this.utilisateurRepository.emailExiste(email);
        if (id != null) {
            this.coderef = Security.generateRandomCode(5);
            Email.send(this.email, "Reinitialisation de mot de passe", "Votre code de reinitialisation est : " + this.coderef);
            System.out.println("Code envoyé");
        }
    }

}

