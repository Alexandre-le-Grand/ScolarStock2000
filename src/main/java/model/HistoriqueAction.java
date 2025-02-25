package model;

import java.time.LocalDateTime;

public class HistoriqueAction {

    private int id_action;
    private LocalDateTime date_heure;
    private String action;
    private int ref_utilisateur;


    
    // Constructors

    public HistoriqueAction(int id_action, LocalDateTime date_heure, String action, int ref_utilisateur) {
        this.id_action = id_action;
        this.date_heure = date_heure;
        this.action = action;
        this.ref_utilisateur = ref_utilisateur;
    }
    
    public HistoriqueAction(LocalDateTime date_heure, String action, int ref_utilisateur) {
        this.date_heure = date_heure;
        this.action = action;
        this.ref_utilisateur = ref_utilisateur;
    }

    // Getters and Setters

    public int getId_action() {
        return id_action;
    }
    public void setId_action(int id_action) {
        this.id_action = id_action;
    }
    public LocalDateTime getDate_heure() {
        return date_heure;
    }
    public void setDate_heure(LocalDateTime date_heure) {
        this.date_heure = date_heure;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public int getRef_utilisateur() {
        return ref_utilisateur;
    }
    public void setRef_utilisateur(int ref_utilisateur) {
        this.ref_utilisateur = ref_utilisateur;
    }
}