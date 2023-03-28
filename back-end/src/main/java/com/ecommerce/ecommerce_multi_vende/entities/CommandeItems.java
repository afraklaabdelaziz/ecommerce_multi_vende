package com.ecommerce.ecommerce_multi_vende.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CommandeItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Commande commande;
    private String reference;
    private int quantity;
    @ManyToOne
    private Produit produit;



    public CommandeItems(Commande commande, String reference, int quantity, Produit produit) {
        this.commande = commande;
        this.reference = reference;
        this.quantity = quantity;
        this.produit = produit;
    }

    public CommandeItems() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "CommandeItems{" +
                "id=" + id +
                ", commande=" + commande +
                ", reference='" + reference + '\'' +
                ", quantity=" + quantity +
                ", produit=" + produit +
                '}';
    }
}
