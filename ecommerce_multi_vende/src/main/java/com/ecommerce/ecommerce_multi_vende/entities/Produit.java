package com.ecommerce.ecommerce_multi_vende.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String reference;
    private Double prix;
    private int quantity;
    @ManyToOne
    private UserApp vendeur;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "produit")
    private List<CommandeItems> commandeItemsList;
    @OneToMany(mappedBy = "produit")
    private List<Achat> achatList;

    public Produit() {
    }


    public Produit(String nom, String reference, Double prix, int quantity, UserApp vendeur, Category category, List<CommandeItems> commandeItemsList, List<Achat> achatList) {
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.quantity = quantity;
        this.vendeur = vendeur;
        this.category = category;
        this.commandeItemsList = commandeItemsList;
        this.achatList = achatList;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UserApp getVendeur() {
        return vendeur;
    }

    public void setVendeur(UserApp vendeur) {
        this.vendeur = vendeur;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CommandeItems> getCommandeItemsList() {
        return commandeItemsList;
    }

    public void setCommandeItemsList(List<CommandeItems> commandeItemsList) {
        this.commandeItemsList = commandeItemsList;
    }

    public List<Achat> getAchatList() {
        return achatList;
    }

    public void setAchatList(List<Achat> achatList) {
        this.achatList = achatList;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", quantity=" + quantity +
                ", userApp=" + vendeur +
                ", category=" + category +
                '}';
    }
}
