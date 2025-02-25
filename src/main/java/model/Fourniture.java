package model;



public class Fourniture {
    private int idFourniture;
    private String libelle;
    private String description;
    private int quantiteDisponible;

    

    // Constructors

    public Fourniture(int idFourniture, String libelle, String description, int quantiteDisponible) {
        this.idFourniture = idFourniture;
        this.libelle = libelle;
        this.description = description;
        this.quantiteDisponible = quantiteDisponible;
    }
    
    public Fourniture(String libelle, String description, int quantiteDisponible) {
        this.libelle = libelle;
        this.description = description;
        this.quantiteDisponible = quantiteDisponible;
    }

    // Getters and setters
    
    public int getIdFourniture() {
        return idFourniture;
    }
    public void setIdFourniture(int idFourniture) {
        this.idFourniture = idFourniture;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }
    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }
}

