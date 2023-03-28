package com.ecommerce.ecommerce_multi_vende.entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class DemandeVendeur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserApp userApp;
    private String demmande;

    public DemandeVendeur(UserApp userApp, String demmande) {
        this.userApp = userApp;
        this.demmande = demmande;
    }

    public DemandeVendeur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getDemmande() {
        return demmande;
    }

    public void setDemmande(String demmande) {
        this.demmande = demmande;
    }

    @Override
    public String toString() {
        return "demandeVendeur{" +
                "id=" + id +
                ", userApp=" + userApp +
                ", demmande='" + demmande + '\'' +
                '}';
    }
}
