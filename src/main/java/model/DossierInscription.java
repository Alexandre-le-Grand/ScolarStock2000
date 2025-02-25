package model;

import java.sql.Date;

public class DossierInscription {
    
    private int id_dossier;
    private Date date;
    private Date heure;
    private String filière;
    private String motivations;
    private int ref_etudiant;
    private int ref_secrétaire;



    // Constructors

    public DossierInscription(int id_dossier, Date date, Date heure, String filière, String motivations, int ref_etudiant, int ref_secrétaire) {
        this.id_dossier = id_dossier;
        this.date = date;
        this.heure = heure;
        this.filière = filière;
        this.motivations = motivations;
        this.ref_etudiant = ref_etudiant;
        this.ref_secrétaire = ref_secrétaire;
    }
    
    public DossierInscription(Date date, Date heure, String filière, String motivations, int ref_etudiant, int ref_secrétaire) {
        this.date = date;
        this.heure = heure;
        this.filière = filière;
        this.motivations = motivations;
        this.ref_etudiant = ref_etudiant;
        this.ref_secrétaire = ref_secrétaire;
    }
    
    // Getters and Setters

    public int getId_dossier() {
        return id_dossier;
    }
    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }
    public java.sql.Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public java.sql.Date getHeure() {
        return heure;
    }
    public void setHeure(Date heure) {
        this.heure = heure;
    }
    public String getFilière() {
        return filière;
    }
    public void setFilière(String filière) {
        this.filière = filière;
    }
    public String getMotivations() {
        return motivations;
    }
    public void setMotivations(String motivations) {
        this.motivations = motivations;
    }
    public int getRef_etudiant() {
        return ref_etudiant;
    }
    public void setRef_etudiant(int ref_etudiant) {
        this.ref_etudiant = ref_etudiant;
    }
    public int getRef_secrétaire() {
        return ref_secrétaire;
    }
    public void setRef_secrétaire(int ref_secrétaire) {
        this.ref_secrétaire = ref_secrétaire;
    }
}

