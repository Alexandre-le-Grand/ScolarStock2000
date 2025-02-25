package model;



public class Fournisseur {

    private int idFournisseur;
    private String nom;
    private String adresse;
    private String contact;



    // Constructors

    public Fournisseur(int idFournisseur, String nom, String adresse, String contact) {
        this.idFournisseur = idFournisseur;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }
    
    public Fournisseur(String nom, String adresse, String contact) {
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    // Getters and Setters

    public int getIdFournisseur() {
        return idFournisseur;
    }
    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
}

