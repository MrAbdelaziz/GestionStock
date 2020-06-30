package ma.emsi.gestionstock.model;



public class ProduitResponse {

    private int id,quantite;
    private String reference, designation;
    private int fournisseur;


    public ProduitResponse(int id, int quantite, String reference, String designation, int fournisseur) {
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


    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getReference() {
        return reference;
    }

    public String getDesignation() {
        return designation;
    }

    public int getFournisseur() {
        return fournisseur;
    }

}
