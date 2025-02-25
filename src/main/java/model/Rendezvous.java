package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rendezvous {
    private int id_rendezvous;
    private LocalDate date;
    private LocalTime heure;
    private int ref_professeur;
    private int ref_etudiant;
    private int ref_salle;
    private Statut statut;



    // Constructors
    
    public Rendezvous(int id_rendezvous, LocalDate date, LocalTime heure, int ref_professeur, int ref_etudiant, int ref_salle, Statut statut) {
        this.id_rendezvous = id_rendezvous;
        this.date = date;
        this.heure = heure;
        this.ref_professeur = ref_professeur;
        this.ref_etudiant = ref_etudiant;
        this.ref_salle = ref_salle;
        this.statut = statut;
    }
    
    public Rendezvous(LocalDate date, LocalTime heure, int ref_professeur, int ref_etudiant, int ref_salle, Statut statut) {
        this.date = date;
        this.heure = heure;
        this.ref_professeur = ref_professeur;
        this.ref_etudiant = ref_etudiant;
        this.ref_salle = ref_salle;
        this.statut = statut;
    }

    // Getters and Setters

    public int getId_rendezvous() {
        return id_rendezvous;
    }
    public void setId_rendezvous(int id_rendezvous) {
        this.id_rendezvous = id_rendezvous;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getHeure() {
        return heure;
    }
    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }
    public int getRef_professeur() {
        return ref_professeur;
    }
    public void setRef_professeur(int ref_professeur) {
        this.ref_professeur = ref_professeur;
    }
    public int getRef_etudiant() {
        return ref_etudiant;
    }
    public void setRef_etudiant(int ref_etudiant) {
        this.ref_etudiant = ref_etudiant;
    }
    public int getRef_salle() {
        return ref_salle;
    }
    public void setRef_salle(int ref_salle) {
        this.ref_salle = ref_salle;
    }
    public Statut getStatut() {
        return statut;
    }
    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    public enum Statut {
        CONFIRME, EN_ATTENTE, ANNULÉ
    }
}
