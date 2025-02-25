package model;



public class DemandeFourniture {

    private int id_demande;
    private int ref_professeur;
    private int ref_fourniture;
    private int quantite;
    private String raison_demande;
    private String status_demande;
    private int ref_gestionnaire;



    // Constructors

    public DemandeFourniture(int id_demande, int ref_professeur, int ref_fourniture, int quantite, String raison_demande, String status_demande, int ref_gestionnaire) {
        this.id_demande = id_demande;
        this.ref_professeur = ref_professeur;
        this.ref_fourniture = ref_fourniture;
        this.quantite = quantite;
        this.raison_demande = raison_demande;
        this.status_demande = status_demande;
        this.ref_gestionnaire = ref_gestionnaire;
    }
    
    public DemandeFourniture(int ref_professeur, int ref_fourniture, int quantite, String raison_demande, String status_demande, int ref_gestionnaire) {
        this.ref_professeur = ref_professeur;
        this.ref_fourniture = ref_fourniture;
        this.quantite = quantite;
        this.raison_demande = raison_demande;
        this.status_demande = status_demande;
        this.ref_gestionnaire = ref_gestionnaire;
    }

    // Getters and Setters

    public int getId_demande() {
        return id_demande;
    }    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }
    public int getRef_professeur() {
        return ref_professeur;
    }
    public void setRef_professeur(int ref_professeur) {
        this.ref_professeur = ref_professeur;
    }
    public int getRef_fourniture() {
        return ref_fourniture;
    }
    public void setRef_fourniture(int ref_fourniture) {
        this.ref_fourniture = ref_fourniture;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public String getRaison_demande() {
        return raison_demande;
    }
    public void setRaison_demande(String raison_demande) {
        this.raison_demande = raison_demande;
    }
    public String getStatus_demande() {
        return status_demande;
    }
    public void setStatus_demande(String status_demande) {
        this.status_demande = status_demande;
    }
    public int getRef_gestionnaire() {
        return ref_gestionnaire;
    }
    public void setRef_gestionnaire(int ref_gestionnaire) {
        this.ref_gestionnaire = ref_gestionnaire;
    }
}