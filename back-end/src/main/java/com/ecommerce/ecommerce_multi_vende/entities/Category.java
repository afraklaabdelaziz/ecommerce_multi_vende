package com.ecommerce.ecommerce_multi_vende.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "category")
    List<Produit> produits;

    public Category() {
    }

    public Category(String nom, List<Produit> produits) {
        this.nom = nom;
        this.produits = produits;
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
    @JsonIgnore
    public List<Produit> getProduits() {
        return produits;
    }
    @JsonSetter
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
