package com.ecommerce.ecommerce_multi_vende.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private StatusCommande statusCommande;

    private Double prixTotal;
    @OneToMany(mappedBy = "commande")
    private List<CommandeItems> commandeItemsList;

    public Commande() {
    }

    public Commande(String reference, StatusCommande statusCommande, Double prixTotal, List<CommandeItems> commandeItemsList) {
        this.reference = reference;
        this.statusCommande = statusCommande;
        this.prixTotal = prixTotal;
        this.commandeItemsList = commandeItemsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StatusCommande getStatusCommande() {
        return statusCommande;

    }

    public void setStatusCommande(StatusCommande statusCommande) {
        this.statusCommande = statusCommande;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<CommandeItems> getCommandeItemsList() {
        return commandeItemsList;
    }

    public void setCommandeItemsList(List<CommandeItems> commandeItemsList) {
        this.commandeItemsList = commandeItemsList;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", statusCommande=" + statusCommande +
                ", prixTotal=" + prixTotal +
                ", commandeItemsList=" + commandeItemsList +
                '}';
    }
}
