package ma.emsi.gestionstock.model;

public class Produit {

    private int id,quantite;
    private String reference, designation;
    private int fournisseur;


    public Produit(int id, int quantite, String reference, String designation, int fournisseur) {
        this.id = id;
        this.quantite = quantite;
        this.reference = reference;
        this.designation = designation;
        this.fournisseur = fournisseur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setFournisseur(int fournisseur) {
        this.fournisseur = fournisseur;
    }
}
