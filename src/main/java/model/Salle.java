package model;

public class Salle {
    private int id_salle;
    private String nom;
    private int capacité;



    // Constructors

    public Salle(int id_salle, String nom, int capacité) {
        this.id_salle = id_salle;
        this.nom = nom;
        this.capacité = capacité;
    }

    public Salle(String nom, int capacité) {
        this.nom = nom;
        this.capacité = capacité;
    }

    // Getters and Setters
    
    public int getId_salle() {
        return id_salle;
    }
    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getCapacité() {
        return capacité;
    }
    public void setCapacité(int capacité) {
        this.capacité = capacité;
    }
}

