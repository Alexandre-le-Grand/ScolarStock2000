package model;



public class FournitureFournisseur {
    
    private int ref_fourniture;
    private int ref_fournisseur;
    private double prix;
    


    // Constructor

    public FournitureFournisseur(int ref_fourniture, int ref_fournisseur, double prix) {
        this.ref_fourniture = ref_fourniture;
        this.ref_fournisseur = ref_fournisseur;
        this.prix = prix;
    }

    // Getters and Setters

    public FournitureFournisseur() {
    }
    public int getRefFourniture() {
        return ref_fourniture;
    }
    public void setRefFourniture(int ref_fourniture) {
        this.ref_fourniture = ref_fourniture;
    }
    public int getRefFournisseur() {
        return ref_fournisseur;
    }
    public void setRefFournisseur(int ref_fournisseur) {
        this.ref_fournisseur = ref_fournisseur;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
}
