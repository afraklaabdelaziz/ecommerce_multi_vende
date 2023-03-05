package com.ecommerce.ecommerce_multi_vende.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pays;
    private String ville;
    private String adresse;
    private String codePostal;
    @OneToMany(mappedBy = "adresse")
    private List<UserApp> userAppList;

    public Adresse() {
    }

    public Adresse(String pays, String ville, String adresse, String codePostal) {
        this.pays = pays;
        this.ville = ville;
        this.adresse = adresse;
        this.codePostal = codePostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public List<UserApp> getUserAppList() {
        return userAppList;
    }

    public void setUserAppList(List<UserApp> userAppList) {
        this.userAppList = userAppList;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", pays='" + pays + '\'' +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }
}
