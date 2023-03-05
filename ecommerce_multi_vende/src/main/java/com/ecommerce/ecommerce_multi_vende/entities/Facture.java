package com.ecommerce.ecommerce_multi_vende.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    private UserApp livreur;
    @ManyToOne
    private UserApp client;
    @OneToOne
    private Commande commande;

    public Facture() {
    }

    public Facture(LocalDate date, UserApp livreur, UserApp client, Commande commande) {
        this.date = date;
        this.livreur = livreur;
        this.client = client;
        this.commande = commande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserApp getLivreur() {
        return livreur;
    }

    public void setLivreur(UserApp livreur) {
        this.livreur = livreur;
    }

    public UserApp getClient() {
        return client;
    }

    public void setClient(UserApp client) {
        this.client = client;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", date=" + date +
                ", livreur=" + livreur +
                ", client=" + client +
                ", commande=" + commande +
                '}';
    }
}
