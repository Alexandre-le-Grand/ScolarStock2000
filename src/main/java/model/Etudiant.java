package model;



public class Etudiant {

    private int id_etudiant;
    private String nom;
    private String prénom;
    private String dernier_diplome_obtenu;
    private String email;
    private String téléphone;
    private String adresse;
    private int ref_utilisateur;



    // Constructors
    
    public Etudiant(int id_etudiant, String nom, String prénom, String dernier_diplome_obtenu, String email, String téléphone, String adresse, int ref_utilisateur) {
        this.id_etudiant = id_etudiant;
        this.nom = nom;
        this.prénom = prénom;
        this.dernier_diplome_obtenu = dernier_diplome_obtenu;
        this.email = email;
        this.téléphone = téléphone;
        this.adresse = adresse;
        this.ref_utilisateur = ref_utilisateur;
    }
    
    public Etudiant(String nom, String prénom, String dernier_diplome_obtenu, String email, String téléphone, String adresse, int ref_utilisateur) {
        this.nom = nom;
        this.prénom = prénom;
        this.dernier_diplome_obtenu = dernier_diplome_obtenu;
        this.email = email;
        this.téléphone = téléphone;
        this.adresse = adresse;
        this.ref_utilisateur = ref_utilisateur;
    }

    // Getters and Setters

    public int getId_etudiant() {
        return id_etudiant;
    }
    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrénom() {
        return prénom;
    }
    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }
    public String getDernier_diplome_obtenu() {
        return dernier_diplome_obtenu;
    }
    public void setDernier_diplome_obtenu(String dernier_diplome_obtenu) {
        this.dernier_diplome_obtenu = dernier_diplome_obtenu;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTéléphone() {
        return téléphone;
    }
    public void setTéléphone(String téléphone) {
        this.téléphone = téléphone;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public int getRef_utilisateur() {
        return ref_utilisateur;
    }
    public void setRef_utilisateur(int ref_utilisateur) {
        this.ref_utilisateur = ref_utilisateur;
    }
}
