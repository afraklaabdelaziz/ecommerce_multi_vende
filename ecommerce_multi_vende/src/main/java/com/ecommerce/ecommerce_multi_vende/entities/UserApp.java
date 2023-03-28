package com.ecommerce.ecommerce_multi_vende.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserApp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motDePasse;
    @ManyToOne
    private Adresse adresse;
    @OneToMany(mappedBy = "livreur")
    private List<Facture> facturesLivreur;
    @OneToMany(mappedBy = "client")
    private List<Facture> facturesClient;
    @ManyToMany
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "vendeur")
    private List<Produit> produits;
    private Boolean isVendor;

    public UserApp() {
    }

    public UserApp(String nom, String prenom, String email, String telephone, String motDePasse, Adresse adresse, List<Facture> facturesLivreur, List<Facture> facturesClient, List<Role> roles, List<Produit> produits, Boolean isVendor) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.facturesLivreur = facturesLivreur;
        this.facturesClient = facturesClient;
        this.roles = roles;
        this.produits = produits;
        this.isVendor = isVendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Facture> getFacturesLivreur() {
        return facturesLivreur;
    }

    public void setFacturesLivreur(List<Facture> facturesLivreur) {
        this.facturesLivreur = facturesLivreur;
    }

    public List<Facture> getFacturesClient() {
        return facturesClient;
    }

    public void setFacturesClient(List<Facture> facturesClient) {
        this.facturesClient = facturesClient;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Boolean getVendor() {
        return isVendor;
    }

    public void setVendor(Boolean vendor) {
        isVendor = vendor;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", adresse=" + adresse +
                ", isVendor=" + isVendor +
                '}';
    }
}
